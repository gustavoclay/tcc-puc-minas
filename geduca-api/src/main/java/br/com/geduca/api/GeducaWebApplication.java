package br.com.geduca.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Classe bootstrap do Spring
 * 
 * @author gustavoclay
 *
 */
@SpringBootApplication
@EntityScan(basePackages = { "br.com.geduca.api.model" })
@EnableJpaRepositories(basePackages = { "br.com.geduca.api.repository" })
public class GeducaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeducaWebApplication.class, args);
	}

}
