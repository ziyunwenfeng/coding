package com.example.springbootfeignconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController

public class HelloController {
	@Autowired
	HelloService helloService;
	@Autowired
	RefactorHelloService refactorHelloService ;
	
	@RequestMapping(value="/feign-consumer",method=RequestMethod.GET)
	public String hellocontroller(String name){
		return helloService.hello4(name);
	}
	@RequestMapping(value="/feign-consumerU",method=RequestMethod.GET)
	public  User hellocontroller2(String name){
		System.out.println("-----------------");
		User u =  helloService.hello5(name);
		System.out.println("------"+u.getName()+"-----"+u.getAge());
		return u;
	}
	@RequestMapping(value="/feign-consumerS",method=RequestMethod.POST)
//	@ResponseBody
	public User hellocontroller3(@RequestBody User u){
		System.out.println("-----------------");
		User uu = helloService.hello6(u);
		System.out.println("-------"+uu.getName()+"-----"+uu.getAge());
		return uu;
		
	}
	
	@RequestMapping(value="/feign-consumer2",method=RequestMethod.GET)
	public String hellocontroller2(){
		String str = "";
		return 
				str+helloService.hello1("hello1 ")+"*****"
				+helloService.hello2("userTang",12).getName()+"*******"
				+helloService.hello3(new User("userWu",16));
	}
	@RequestMapping(value="/feign-consumer3",method=RequestMethod.GET)
	public String hellocontroller3(){
		String str = "";
		return 
				str+refactorHelloService.hello("cloud")+"*****"
				+refactorHelloService.hello("Uber",12).getName()+"*****"
				+refactorHelloService.hello(new com.example.helloserviceapi.User("Didid",16));
	}
	
	@RequestMapping(value = "/jwtTest",method = RequestMethod.POST)
	@ResponseBody
	public void jwtTest(HttpRequest request) {
		System.out.println("header");
		System.out.println(request.getHeaders());
		
	}
	
	
}
