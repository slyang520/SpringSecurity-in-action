package com.example.slyangsecurity;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class SlyangsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlyangsecurityApplication.class, args);
	}

	@GetMapping(path = "")
	public String index(Map<String, String> map) {
		return "index";
	}


	/**
	 * 为测试环境添加相关的 Request Dumper information，便于调试
	 */
	@Bean
	RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}

}
