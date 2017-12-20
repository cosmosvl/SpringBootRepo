package com.boot.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
 * The project use JPA and Spring Data JPA (see architecture.png) so the Spring MVC
 * controllers have a PERSISTENCE LAYER they can utilize.
 * The JPA ORM layer will then talk to a database.
 * Conclusion: The JPA is the bridge for the controllers to store the data into
 * the data source (h2 and flyway)
 * 
 * */

// To tell Spring that this class is a configuration class.
@Configuration
public class PersistenceConfiguration {
	
	/*	COMMENTS:
	 * 	The @Bean annotation tells Spring in Spring Boot to set up and store
	 * 	the DataSource as a Spring Bean in the Spring Context.
	 * 
	 * 
	 * 	The ConfigurationProperties annotation tells the DataSourceBuilder to use the
	 * 	conn and pulling properties from "application.properties", where the
	 * 	properties begin with "spring.datasource" prefix.
	 * 
	 * 	The @Primary annotation used to tell Spring that "spring.datasource" is the
	 * 	default data source.
	 * 
	 * */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix="datasource.flyway")
	@FlywayDataSource
	public DataSource flywayDataSource() {
		return DataSourceBuilder.create().build();
	}

}
