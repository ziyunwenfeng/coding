package com.example.demo1;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class HttpAspect {
	@Pointcut("execution(* com.example.demo1.BookController.*(..))")
	public void log(){
		
	}
	@Before("log()")
	public void before(){
		System.out.println("before");
	}
}
