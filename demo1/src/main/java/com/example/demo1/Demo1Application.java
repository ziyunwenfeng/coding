package com.example.demo1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Demo1Application {
	protected static Logger logger=LoggerFactory.getLogger(HelloController.class); 

	public static void main(String[] args) {
		logger.info("");
		SpringApplication.run(Demo1Application.class, args);
	}
}
