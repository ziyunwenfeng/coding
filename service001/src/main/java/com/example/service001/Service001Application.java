package com.example.service001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
@EnableDiscoveryClient
@SpringBootApplication

//@ComponentScan(basePackages= {"com.example.service001.mapper"})
//@MapperScan("com.example.service001.mapper")
public class Service001Application {
	public static void main(String[] args) {
		SpringApplication.run(Service001Application.class, args);
	}
}
