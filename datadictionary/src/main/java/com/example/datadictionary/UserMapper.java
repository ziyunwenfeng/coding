package com.example.datadictionary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import entity.User;
@Mapper
public interface UserMapper {
	public List<User> getAllUser();
}
