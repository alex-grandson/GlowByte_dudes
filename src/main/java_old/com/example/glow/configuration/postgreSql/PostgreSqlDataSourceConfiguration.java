package com.example.glow.configuration.postgreSql;

import com.zaxxer.hikari.HikariDataSource;
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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgreSqlEntityManagerFactory",
        transactionManagerRef = "postgreSqlTransactionManager",
        basePackages = {"com.example.glow.repository.postgreSqlRep"}
)
public class PostgreSqlDataSourceConfiguration {

    @Bean( name = "postgreSqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource-postgres")
    public DataSourceProperties postgreSqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "postgreDataSource")
    @ConfigurationProperties("spring.datasource-postgres.configuration")
    public DataSource postgreSqlDataSource(@Qualifier("postgreSqlDataSourceProperties") DataSourceProperties postgreSqlDataSourceProperties) {
        return postgreSqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "postgreSqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder postgreSqlEntityManagerFactory, @Qualifier("postgreDataSource") DataSource postgreDataSource) {
        Map<String, String> primaryJpaProperties = new HashMap<>();
        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "none");

        return postgreSqlEntityManagerFactory
                .dataSource(postgreDataSource)
                .packages("com.example.glow.entity.postgreSql")
                .persistenceUnit("postgreDataSource")
                .properties(primaryJpaProperties)
                .build();
    }

    @Bean(name = "postgreSqlTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("postgreSqlEntityManagerFactory") EntityManagerFactory postgreSqlTransactionManager) {

        return new JpaTransactionManager(postgreSqlTransactionManager);
    }

}
