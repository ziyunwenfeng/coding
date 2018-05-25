package com.example.datadictionary;

import java.util.List;
import java.util.Map;

import entity.User;

public interface UserMapper {
	public List<User> getAllUser();
	public List<User> getAllUser2(Map<String,Object> map);
	public void deleteUser(int userId);
	public void saveUser(User u);
	public List<User> getUserByPage(Map<String,Object> map);
	public int insertUser(User user);
	public List<User> getByList(List<Integer> list);
}
