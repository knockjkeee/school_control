package com.verification.detectioncam.config;


import com.verification.detectioncam.domain.result.Result;
import com.verification.detectioncam.repo.result.ResultRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = ResultRepo.class,
        entityManagerFactoryRef = "tempDSmFactory",
        transactionManagerRef = "tempDSTransitionManager")
public class ResultDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties tempDSProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource tempDS(@Qualifier("tempDSProperties") DataSourceProperties tempDSProperties) {
        return tempDSProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean tempDSmFactory(
            @Qualifier("tempDS") DataSource tempDS,
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(tempDS).packages(Result.class).build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager tempDSTransitionManager(EntityManagerFactory tempDSmFactory) {
        return new JpaTransactionManager(tempDSmFactory);
    }


}
