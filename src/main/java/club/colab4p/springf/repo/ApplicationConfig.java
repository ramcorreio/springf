/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package club.colab4p.springf.repo;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JavaConfig class to enable Spring Data JPA repositories. Re-using common
 * infrastrcuture configuration from {@link InfrastructureConfigTest}.
 * 
 * @author Oliver Gierke
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "club.colab4p.springf.repo")
public class ApplicationConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println(env);

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		//vendorAdapter.setDatabase(Database.valueOf(env.getProperty("springf.database.vendor")));
		//vendorAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("springf.database.ddl")));

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("club.colab4p.springf.domain");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		em.setLoadTimeWeaver(new org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		System.out.println(env);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("springf.hibernate.connection.driver_class"));
		dataSource.setUrl(env.getProperty("springf.hibernate.connection.url"));
		dataSource.setUsername(env.getProperty("springf.hibernate.connection.username"));
		dataSource.setPassword(env.getProperty("springf.hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("springf.hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("springf.hibernate.dialect"));
		properties.setProperty("hibernate.current_session_context_class", env.getProperty("springf.hibernate.current_session_context_class"));
		return properties;
	}
}
