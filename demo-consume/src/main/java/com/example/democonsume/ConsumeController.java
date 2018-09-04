package com.example.democonsume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ConsumeController {
//	@Autowired
//	RestTemplate restTemplate;
//	@GetMapping(value="/ribbon")
//	public String getValue() {
//		return restTemplate.getForEntity("http://servicedemo/helloservice", String.class).getBody();
//	}
//	@GetMapping(value="/ribbon2")
//	public String getValue2() {
//		return "hello";
//	}
	@Autowired
	HelloServiceInterface hello;
	@RequestMapping(value="/helloservice",method=RequestMethod.GET)
	public String getA() {
		System.out.println("*******************consume**************");
		return hello.helloService();
	}
}
