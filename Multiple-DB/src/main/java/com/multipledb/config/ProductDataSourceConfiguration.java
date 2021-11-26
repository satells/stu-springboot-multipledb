package com.multipledb.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

import com.multipledb.product.entities.Product;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory", transactionManagerRef = "productTransactionManager", basePackages = {
		"com.multipledb.product" })
public class ProductDataSourceConfiguration {

	@Bean(name = "productDataSourceProperties")
	@ConfigurationProperties("spring.datasource-product")
	public DataSourceProperties productDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "productDataSource")
	public DataSource productDataSource() {
		return productDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "productEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		HashMap<String, String> productJpaProperties = new HashMap<String, String>();
		productJpaProperties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
		productJpaProperties.put("spring.jpa.properties.hibernate.show_sql", "true");

		return builder.dataSource(productDataSource()).packages(Product.class).persistenceUnit("productDataSource").properties(productJpaProperties)
				.build();
	}

	@Bean(name = "productTransactionManager")
	public PlatformTransactionManager productTransactionManager(
			@Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
		return new JpaTransactionManager(productEntityManagerFactory);
	}
}