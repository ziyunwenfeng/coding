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
}
