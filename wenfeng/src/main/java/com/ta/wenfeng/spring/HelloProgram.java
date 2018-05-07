package com.ta.wenfeng.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ta.wenfeng.spring.helloworld.HelloWorld;
import com.ta.wenfeng.spring.helloworld.HelloWorldService;

public class HelloProgram {
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorldService service = 
				(HelloWorldService)context.getBean("helloWorldService");
		HelloWorld hw = service.getHelloworld();
		hw.sayHello();
	}
}
