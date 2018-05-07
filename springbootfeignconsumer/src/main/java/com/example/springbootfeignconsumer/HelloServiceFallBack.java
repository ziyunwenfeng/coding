package com.example.springbootfeignconsumer;



import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.helloserviceapi.User;
@Component
public class HelloServiceFallBack implements HelloService {

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hello1(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public com.example.springbootfeignconsumer.User hello2(@RequestHeader("name") String name,@RequestHeader("age") int age) {
		// TODO Auto-generated method stub
		return new com.example.springbootfeignconsumer.User("testhystrix",1);
	}

	@Override
	public String hello3(@RequestBody com.example.springbootfeignconsumer.User user) {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public String hello4(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.example.springbootfeignconsumer.User hello5(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.example.springbootfeignconsumer.User hello6(com.example.springbootfeignconsumer.User user) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public User hello6(com.example.springbootfeignconsumer.User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
