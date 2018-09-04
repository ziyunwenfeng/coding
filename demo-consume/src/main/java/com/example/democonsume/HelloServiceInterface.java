package com.example.democonsume;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("servicedemo")
public interface HelloServiceInterface {
	@RequestMapping("/helloservice")
	String helloService() ;
}
