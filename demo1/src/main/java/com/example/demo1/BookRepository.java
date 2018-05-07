package com.example.demo1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findByReader(String reader);
}
