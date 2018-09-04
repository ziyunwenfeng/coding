package com.example.feignconsume.bookinterface;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.feignconsume.entity.Book;

@FeignClient(value="service001")
public interface BookCallInterface {
	
	@GetMapping(value="/bookId")
	Book getBookById(@RequestParam("id") int id) ;
	@GetMapping(value="/allbooks")
	List<Book> allBooks() ;
}
