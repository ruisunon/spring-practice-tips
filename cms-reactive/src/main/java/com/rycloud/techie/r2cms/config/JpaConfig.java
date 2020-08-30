package com.rycloud.techie.r2cms.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JpaConfig {

  @Bean(name = "h2DataSource")
  public DataSource h2DataSource()
  {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.h2.Driver");
    dataSourceBuilder.url("jdbc:h2:file:C:/temp/test");
    dataSourceBuilder.username("sa");
    dataSourceBuilder.password("");
    return dataSourceBuilder.build();
  }

  @Bean(name = "mySqlDataSource")
  @Primary
  public DataSource mySqlDataSource()
  {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:mysql://localhost/testdb");
    dataSourceBuilder.username("dbuser");
    dataSourceBuilder.password("dbpass");
    return dataSourceBuilder.build();
  }

  @Bean(name = "postgresDataSource")
  @Primary
  public DataSource postgresDataSource()
  {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:mysql://localhost/testdb");
    dataSourceBuilder.username("dbuser");
    dataSourceBuilder.password("dbpass");
    return dataSourceBuilder.build();
  }
}