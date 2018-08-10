package com.example.feignconsume;

import org.springframework.stereotype.Component;

@Component
public class FeignFallBack implements FeignInterface {

	@Override
	public String getService() {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public String getInsert(String name) {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public String insert(User u) {
		// TODO Auto-generated method stub
		return "error";
	}

}
