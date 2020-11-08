package com.verification.controlout.config;


import com.verification.controlout.domain.result.Timetable;
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
@EnableJpaRepositories(basePackages = "com.verification.controlout.repo.timerable",
        entityManagerFactoryRef = "resultDSmFactory",
        transactionManagerRef = "resultDSTransitionManager")
public class ResultDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties resultDSProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource resultDS(@Qualifier("resultDSProperties") DataSourceProperties resultDSProperties) {
        return resultDSProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean resultDSmFactory(
            @Qualifier("resultDS") DataSource resultDS,
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(resultDS).packages(Timetable.class).build();
//        return builder.dataSource(resultDS).packages(Timetable.class).build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager resultDSTransitionManager(EntityManagerFactory resultDSmFactory) {
        return new JpaTransactionManager(resultDSmFactory);
    }


}
