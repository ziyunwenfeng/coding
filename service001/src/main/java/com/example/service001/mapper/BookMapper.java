package com.example.service001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.service001.entity.Book;
@Mapper
public interface BookMapper {
	public List<Book> allBooks();
}
