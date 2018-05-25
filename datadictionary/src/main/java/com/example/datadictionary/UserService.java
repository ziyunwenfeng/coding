package com.example.datadictionary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public List<User> getAllUser2(Map<String,Object> map){
		userMapper.getAllUser2(map);
		if(map==null) {
			System.out.println("--------------------------------------null");
		}else {
			System.out.println("------------------------------------------not null");
		}
		return (List<User>)map.get("result");
	}
	public void deleteUser(int userId) {
		userMapper.deleteUser(userId);
	}
	public void saveUser(User u) {
		userMapper.saveUser(u);
	}
	public List<User> getUserByPage(Integer page,Integer pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("high", (page-1)*pageSize);
		map.put("low", page*pageSize);
		System.out.println("--------------------------------------null");
		System.out.println((page-1)*pageSize+1);
		System.out.println(page*pageSize);
		return (List<User>)userMapper.getUserByPage(map);
	}
	
	int insertUser(User user) {
		return userMapper.insertUser(user);
	}
	
	public List<User> getByList() {
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		return userMapper.getByList(list);
	}
}
