package com.example.spring15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:custom.properties", "classpath:gemini.properties"})
public class Spring15FinalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring15FinalApiApplication.class, args);
	}

}
