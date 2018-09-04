package com.example.feignconsume;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.example.helloserviceapi.HelloService;
@FeignClient("service001")
public interface RefactorService extends HelloService {
	
}
