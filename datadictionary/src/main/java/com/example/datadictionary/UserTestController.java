package com.example.datadictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.User;

//@RestController
//@EnableAutoConfiguration
//
//@ComponentScan
@SpringBootApplication
@RequestMapping("/test")
public class UserTestController {
	 
	@Autowired
	UserService userService;
	@RequestMapping(value="/user",method = RequestMethod.GET)
	@ResponseBody
	public User getUser(){
		return new User(1,"r");
	}
	@RequestMapping(value="/allUsers",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	@ResponseBody
	public String hello(){
		return "hello";
	}
}
