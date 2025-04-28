package com.example.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.model.BookComm;

@FeignClient(name="book-slave", url="http://133.186.135.68:30135/slave")
public interface BookSlave {
	@GetMapping("/book/{id}")
	public Book getBookInfo(@PathVariable("id") int id) ;
	@GetMapping("/book")
	public Book[] getBookAll() ;
	@PostMapping("/book")
	public String postBook(@RequestBody BookComm book) ; 
	@PutMapping("/book/{id}")
	public String putBook(@PathVariable("id") int id, @RequestBody BookComm book) ;
	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable("id") int id);
}
