package com.kshrd.derphsar_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kshrd.*")
public class DerphsarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DerphsarApiApplication.class, args);
	}

}
