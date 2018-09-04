package com.example.springbootfeignconsumer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Component
public class HelloServiceFallBack implements HelloService {

	@Override
	public String index() {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hello1(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User hello2(@RequestParam("name") String name, @RequestParam("age") int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hello3(@RequestBody User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hello4(@RequestParam("name")  String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User hello5(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User hello6(@RequestBody User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
