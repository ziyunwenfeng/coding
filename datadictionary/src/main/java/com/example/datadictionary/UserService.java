package com.example.datadictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	public List<User> getAllUser(){
		return userMapper.getAllUser();
	}
}
