package com.computec.entregas.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourcesConfig {

	@Primary
	@Bean("mysqlProperties")
	@ConfigurationProperties("spring.datasource.mysql")
	DataSourceProperties getMySQLProperties(){
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean("mysqlDatasource")
	DataSource getMySQLDataSource() {
		return getMySQLProperties()
				.initializeDataSourceBuilder()
				.build();
	}
	
	@Bean("postgreSQLProperties")
	@ConfigurationProperties("spring.datasource.postgresql")
	DataSourceProperties getPostgreSQLProperties() {
		return new DataSourceProperties();
	}
	
	@Bean("postgreSQLDataSource")
	DataSource getPostgreSQLDataSource() {
		return getPostgreSQLProperties()
				.initializeDataSourceBuilder()
				.build();
	}
	
	@Bean("sqlServerProperties")
	@ConfigurationProperties("spring.datasource.sqlserver")
	DataSourceProperties getSQLServerProperties() {
		return new DataSourceProperties();
	}
	
	@Bean("sqlServerDataSource")
	DataSource getSQLServerDataSource() {
		return getSQLServerProperties()
				.initializeDataSourceBuilder()
				.build();
	}
}
