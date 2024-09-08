package com.example.demo.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.BookSlave;
import com.example.demo.model.Book;


@RestController
public class BookController {
	
	@Autowired
	BookSlave bookSlave; 
	
	@GetMapping("/master/{id}")
	public String getBookInfo(@PathVariable("id") int id) {
		Book book = bookSlave.getBookInfo(id); 
		return book.getId()+":"+book.getAuthor()+":"+book.getName(); 
	}
	@GetMapping("/master")
	public String getBookAll() {
		String temp = null;
		Book book = null;
		Iterable<Book> books = bookSlave.getBookAll();
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext()) {
			book = iterator.next(); 
			temp += book.getId()+":"+book.getAuthor()+":"+book.getName()+"<br>";
		}
		return temp; 
	}
	@PostMapping("/master")
	public String postBook(@RequestBody Book book) {
		return bookSlave.postBook(book); 
	}
	@PutMapping("/master/{id}")
	public String putBook(@PathVariable("id") int id, @RequestBody Book book) {
		return bookSlave.putBook(id, book);  
	}
	@DeleteMapping("/master/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		return bookSlave.deleteBook(id);  
	}
	

}
