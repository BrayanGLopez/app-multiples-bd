package com.computec.entregas.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(
		entityManagerFactoryRef = "postgreSQLEMF",
		transactionManagerRef = "postgreSQLTrxManager",
		basePackages = "com.computec.entregas.persistence.postgres.repository"
)
public class PostgreSQLJpaConfig {
	
	@Bean("postgreSQLEMF")
	LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("postgreSQLDataSource") DataSource postgreDS) {
		
		Map<String, String> additionalProps = new HashMap<>();
		additionalProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		
		return builder
				.dataSource(postgreDS)
				.packages("com.computec.entregas.persistence.postgres.model")
				.persistenceUnit("postgreSQL")
				.properties(additionalProps)
				.build();		
	}
	
	@Bean("postgreSQLTrxManager")
	PlatformTransactionManager getPostgreSQLTransaccionManager(
			@Qualifier("postgreSQLEMF") LocalContainerEntityManagerFactoryBean postgreSQLEMF) {
		return new JpaTransactionManager(Objects.requireNonNull(postgreSQLEMF.getObject()));
		
	}
	
}
