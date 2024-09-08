package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.BookRepository;
import com.example.demo.model.Book;

@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepository; 
	
	
	@GetMapping("/book/{id}")
	public Book getBookInfo(@PathVariable("id") int id) {
		Book book = bookRepository.findOne(id);
		return book; 
	}
	@GetMapping("/book")
	public Iterable<Book> getBookAll() {
		Iterable<Book> books = bookRepository.findAll();
		return books; 
	}
	@PostMapping("/book")
	public String postBook(@RequestBody Book book) {
		bookRepository.save(book); 
		return book.getId()+"가 잘 저장됨"; 
	}
	@PutMapping("/book/{id}")
	public String putBook(@PathVariable("id") int id, @RequestBody Book book) {
		if (bookRepository.exists(id))
			bookRepository.save(book); 
		return book.getId()+"가 수정됨"; 
	}
	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		if (bookRepository.exists(id))
			bookRepository.delete(id);
		return "id"+"가 삭제됨"; 
	}
}
