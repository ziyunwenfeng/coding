package com.example.service001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class Service001Application {
	public static void main(String[] args) {
		SpringApplication.run(Service001Application.class, args);
	}
}
