package com.authenticator.validateJWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValidateJwtApplication {
	private static final Logger logger = LoggerFactory.getLogger(ValidateJwtApplication.class);


	public static void main(String[] args) {
		logger.info("Starting Application");

		SpringApplication.run(ValidateJwtApplication.class, args);
	}

}
