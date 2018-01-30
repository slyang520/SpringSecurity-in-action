package com.example.slyangsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@SpringBootApplication
public class SlyangsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlyangsecurityApplication.class, args);
	}

}
