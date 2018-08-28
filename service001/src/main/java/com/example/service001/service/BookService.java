package com.example.service001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service001.entity.Book;
import com.example.service001.mapper.BookMapper;

@Service
public class BookService {
	@Autowired
	BookMapper dao ;
	public void setdao(BookMapper dao) {
		this.dao = dao; 
	}
	public List<Book> allBooks(){
		return dao.allBooks();
	}
	public Book getBookById(int id) {
		return dao.getBookById(id);
	}
}
