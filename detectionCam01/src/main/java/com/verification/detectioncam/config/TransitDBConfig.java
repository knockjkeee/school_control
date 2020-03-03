package com.verification.detectioncam.config;


import com.verification.detectioncam.domain.transit.Transit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.verification.detectioncam.repo.transit",
        entityManagerFactoryRef = "transitDSEmFactory",
        transactionManagerRef = "transitDSTransactionManager")
public class TransitDBConfig {

    @Bean
    @ConfigurationProperties("transit.datasource")
    public DataSourceProperties transitDSProperties() {
        return new DataSourceProperties();
    }


    @Bean
    public DataSource transitDS(
            @Qualifier("transitDSProperties") DataSourceProperties transitDSProperties
    ) {
        return transitDSProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean transitDSEmFactory(
            @Qualifier("transitDS") DataSource transitDS,
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(transitDS).packages(Transit.class).build();
    }


    @Bean
    public PlatformTransactionManager transitDSTransactionManager(
            @Qualifier("transitDSEmFactory") EntityManagerFactory transitDSEmFactory) {
        return new JpaTransactionManager(transitDSEmFactory);
    }

}

