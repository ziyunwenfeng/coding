package com.example.demo1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/book")
public class BookController{
	@Autowired
	BookRepository bookRepository;
	public BookController(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}
	@RequestMapping(value="/insert")
	public void insertBook(@Valid Book book,BindingResult result){
		if(result.hasErrors()){
			System.out.println(result.getFieldError().getDefaultMessage());
			return;
		}
	}
	@RequestMapping(value = "/books/{reader}", method = RequestMethod.GET)
	public String getBook(@PathVariable("reader") String reader,Model model){
		List<Book> list = bookRepository.findByReader(reader);
		if(list!=null){
			model.addAttribute("books",list);
			System.out.println("list.size()"+list.size());
		}
			
		else
			System.out.println("null");
		return "book";
	}
}
