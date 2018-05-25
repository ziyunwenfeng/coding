package com.example.datadictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.datadictionary")
public class DatadictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatadictionaryApplication.class, args);
	}
}
