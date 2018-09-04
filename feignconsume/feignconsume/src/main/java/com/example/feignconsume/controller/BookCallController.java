package com.example.feignconsume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignconsume.bookinterface.BookCallInterface;
import com.example.feignconsume.entity.Book;
@RestController
public class BookCallController {
	@Autowired
	BookCallInterface bookCallInterface;
	
	@GetMapping(value="/allbooks")
	List<Book> allBooks(){
		return bookCallInterface.allBooks();
	}
	@GetMapping(value="/getBookById")
	Book getBookById(@RequestParam("id") int id ){
		return (Book)bookCallInterface.getBookById(id);
	}
}
