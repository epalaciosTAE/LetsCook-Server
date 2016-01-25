package com.tae.letscook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@ComponentScan("com.tae.letscook")
//@EnableJpaRepositories("com.tae.letscook.model")
@EnableJpaRepositories
@EnableWebMvc
@Configuration
@SpringBootApplication
public class LetsCookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsCookServerApplication.class, args);
	}
	
	@Bean
    public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
}
