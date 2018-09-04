package com.example.springbootfeignconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
	@Autowired
	RestTemplate restTemplate;
	@GetMapping(value="/ribbon")
	public String getValue() {
		return restTemplate.getForEntity("http://service001/hello", String.class).getBody();
	}
}
