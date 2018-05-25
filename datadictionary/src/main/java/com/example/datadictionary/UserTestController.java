package com.example.datadictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value="/allUsers2",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUser2(@RequestParam("userId") int userId){
		int uid = userId;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid",userId);
		return userService.getAllUser2(map);
	}
	@RequestMapping(value="/allUsers3",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUser3(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		return userService.getAllUser2(map);
	}
	@RequestMapping(value="/hello",method = RequestMethod.GET)
//	@GetMapping(value="/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	@RequestMapping(value="/deleteuser",method = RequestMethod.POST)
	@ResponseBody
	public void deleteUser(@RequestParam("userId") int userId){
		userService.deleteUser(userId);
	}
	@RequestMapping(value="/saveuser",method = RequestMethod.POST)
	
	@ResponseBody
	public void saveUser(@RequestBody User user){
		userService.saveUser(user);
	}
	
	@RequestMapping(value="/insertuser",method = RequestMethod.POST)
	@ResponseBody
	public void insertUser(@RequestBody User user){
		int n = userService.insertUser(user);
		System.out.println("n----"+n);
	}
	@RequestMapping(value="/getuser/{page}/{pageSize}",method = RequestMethod.POST)
	@ResponseBody
	public List<User> getUserByPage(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize){
//		int page2 = page;
//		int pageSize2 = pageSize;
		
		return userService.getUserByPage(page,pageSize);
	}
	
	@RequestMapping(value="/getbylist",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserByList(){
		return userService.getByList();
	}
	
	
	
}
