package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AuthorMapper;
import entity.Author;
@Service
public class AuthorService { 
	@Autowired
	AuthorMapper authorMapper;
	public void insertAuthor(Author author){
		authorMapper.insert(author);
	}
	public void deleteAuthor(Long authorId){
		authorMapper.deleteByPrimaryKey(authorId);
	}
}
