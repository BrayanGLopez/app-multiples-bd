package com.computec.entregas.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "mysqlEMF",
		transactionManagerRef = "msqlTrxManager",
		basePackages = "com.computec.entregas.persistence.mysql.repository"
)
public class MySQLJpaConfig {
	
	@Primary
	@Bean("mysqlEMF")
	LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("mysqlDatasource") DataSource mysqlDS) {
				
		Map<String, String> additonalsProps = new HashMap<>();		
		additonalsProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		return builder
				.dataSource(mysqlDS)
				.packages("com.computec.entregas.persistence.mysql.model")
				.properties(additonalsProps)
				.persistenceUnit("mysql")
				.build();
	}
	
	@Primary
	@Bean("msqlTrxManager")
	PlatformTransactionManager getMySQLTransactionManager(
			@Qualifier("mysqlEMF") LocalContainerEntityManagerFactoryBean mysqlEMF) {
		return new JpaTransactionManager(Objects.requireNonNull(mysqlEMF.getObject()));
	}

}
