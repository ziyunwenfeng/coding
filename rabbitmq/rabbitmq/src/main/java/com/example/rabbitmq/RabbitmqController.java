package com.example.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {
	@Autowired
	Sender sender ;
	@PostMapping("/hello")
	public void hello(){
		System.out.println("sender");
		sender.send();
	}
}
