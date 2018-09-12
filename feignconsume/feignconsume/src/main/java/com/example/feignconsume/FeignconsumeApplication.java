package com.example.feignconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignconsumeApplication {
//	@Bean
//	public FeignRequestInceptor feignRequestInceptor() {
//		return new FeignRequestInceptor();
//	}
	public static void main(String[] args) {
		SpringApplication.run(FeignconsumeApplication.class, args);
	}
}
