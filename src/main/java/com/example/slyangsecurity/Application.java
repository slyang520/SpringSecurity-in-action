package com.example.slyangsecurity;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 为测试环境添加相关的 Request Dumper information，便于调试
	 */
	@Bean
	RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}


}
