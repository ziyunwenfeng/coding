package com.example.demoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDemo {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping(value="/helloservice")
	String helloService() {
		System.out.println("*******************call service**************");
		return "hello";
	}
}
