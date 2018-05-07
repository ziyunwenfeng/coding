package com.example.service001;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloserviceapi.HelloService;
import com.example.helloserviceapi.User;;

@RestController
public class RefctorHelloController implements HelloService {

	@Override
	public String hello(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return "refactorSrvice001: "+name;
	}

	@Override
	public User hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age) {
		// TODO Auto-generated method stub
		return new User(name,age);
	}

	@Override
	public String hello(@RequestBody User user) {
		// TODO Auto-generated method stub
		return "hello "+user.getName();
	}
}
