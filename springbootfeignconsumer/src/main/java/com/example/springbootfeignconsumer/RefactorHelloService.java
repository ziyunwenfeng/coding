package com.example.springbootfeignconsumer;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.example.helloserviceapi.HelloService;
@FeignClient(value="service001")
public interface RefactorHelloService extends HelloService {

}
