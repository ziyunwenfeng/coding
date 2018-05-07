package com.example.service001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String index(){
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		System.out.println(instance.getHost()+" "+instance.getServiceId());
		return "service0001";
	}
	@RequestMapping(value="/hello1",method=RequestMethod.GET)
	String hello1(@RequestParam("name") String name){
		return "service0001: "+name;
	}
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	User hello2(@RequestHeader("name") String name,@RequestHeader("age") int age){
		return new User(name,age);
	}
	@RequestMapping(value="/hello3",method=RequestMethod.POST)
	String hello3(@RequestBody User user){
		return user.getName();
	}
	@RequestMapping(value="/hello4",method=RequestMethod.GET)
	void hello4(@RequestParam("name") String name){
		System.out.println("-----------------"+name);
	}
	@RequestMapping(value="/hello5",method=RequestMethod.GET)
	User hello5(@RequestParam("name") String name){
		System.out.println("-----------------"+name);
		return new User("wenfeng",24);
	}
	@RequestMapping(value="/hello6",method=RequestMethod.POST)
	@ResponseBody
	User hello6(@RequestBody User user){
		System.out.println("-------"+user.getName()+"----"+user.getAge());
		return new User("wenfeng",24);
	}
	
}
