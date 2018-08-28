package com.example.service001.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service001.entity.Book;
import com.example.service001.service.BookService;
@RestController
public class BookController {
	@Autowired
	BookService service;
	@RequestMapping(value="/allbooks",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> allBooks(){
		return service.allBooks();
	}
	@RequestMapping(value="/bookId",method=RequestMethod.GET)
	@ResponseBody
	public Book getBookById(@RequestParam("id") int id) {
		return service.getBookById(id);
	}
}
