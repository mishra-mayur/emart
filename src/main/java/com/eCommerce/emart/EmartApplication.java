package com.eCommerce.emart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.eCommerce.emart.repository"})
public class EmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmartApplication.class, args);
	}

}
