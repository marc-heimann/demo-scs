package com.swisslog.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SwisslogDemoScsApplication {

	public static void main(String[] args) {			
		SpringApplication.run(SwisslogDemoScsApplication.class, args);
	}

}

