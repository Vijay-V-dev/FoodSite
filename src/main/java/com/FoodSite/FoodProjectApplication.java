package com.FoodSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@ComponentScan({"com.FoodSite"})
@EnableJpaAuditing
public class FoodProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodProjectApplication.class, args);
	}

}
