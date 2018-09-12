package com.example.feignconsume;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;
//@Configuration
public class FeignRequestInceptor implements RequestInterceptor {
	@Autowired
	HttpServletRequest request;
	@Override
	public void apply(RequestTemplate requestTemplate) {
		// TODO Auto-generated method stub
//		String username = RequestContextHolder.currentRequestAttributes().getSessionId();
//		if(username==null) {
//			username = "null";
//		}else {
//			requestTemplate.header("username", username);
//		}
		System.out.println("incepter");
		requestTemplate.header("username", "wenfeng111s");
	}

}
