package com.example.feignconsume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FeignController {
	@Autowired
	FeignInterface feign;
	@Autowired
	RefactorService refactorService;
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	String getService() {
		return feign.getService();
	}
	@RequestMapping(value="/hello1",method=RequestMethod.GET)
	String getValue(@RequestParam("name") String name) {
		System.out.println("hello1******************");
		return feign.getInsert(name);
	}
	@RequestMapping(value="/hello3",method=RequestMethod.POST)
	public String insert(@RequestBody User u) {
		return feign.insert(u);
	}
	//继承serviceapi
	@RequestMapping(value="/refactor1",method=RequestMethod.GET)
	public String refactor(@RequestParam("name") String name) {
		return refactorService.hello(name);
	}
	@RequestMapping(value="/refactor2",method=RequestMethod.POST)
	public String refactor2(@RequestBody com.example.helloserviceapi.User u) {
		return refactorService.hello2(u);
	}
	@RequestMapping(value="/refactor3",method=RequestMethod.GET)
	public com.example.helloserviceapi.User refactor3(@RequestParam("name") String name,@RequestParam("age") int age) {
		return refactorService.setUser(name,age);
	}
	
}
