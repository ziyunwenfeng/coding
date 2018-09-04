package com.example.helloserviceapi;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/refactor")
public interface HelloService {
	@RequestMapping(value="/hello4",method=RequestMethod.GET)
	String hello(@RequestParam("name") String name);
//	@RequestMapping(value="/hello5",method=RequestMethod.GET)
//	User hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age);
	@RequestMapping(value="/hello6",method=RequestMethod.POST)
	String hello2(@RequestBody User user);
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	public User setUser(@RequestParam("name") String name,@RequestParam("age") int age);
}
