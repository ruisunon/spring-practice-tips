package com.rycloud.techie.r2cms.accounting;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "accountingEntityManagerFactory", //
    transactionManagerRef = "accountingTransactionManager") //
public class AccountingDatasourceConfiguration {
  @Bean
  @ConfigurationProperties(prefix = "accounting.datasource")
  DataSource accountingDatasource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  PlatformTransactionManager accountingTransactionManager(
      @Qualifier("accountingEntityManagerFactory") LocalContainerEntityManagerFactoryBean accountingEntityManagerFactory) {
    return new JpaTransactionManager(accountingEntityManagerFactory.getObject());
  }

  @Bean
  LocalContainerEntityManagerFactoryBean accountingEntityManagerFactory(
      @Qualifier("accountingDatasource") DataSource accountingDatasource, EntityManagerFactoryBuilder builder) {
    return builder.dataSource(accountingDatasource).packages(Accounting.class).build();
  }
}