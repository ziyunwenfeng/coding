package com.example.demo1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/world")
public class HelloController {
	@RequestMapping("/w1")
	public String world(){
		return "w1";
	}
}
