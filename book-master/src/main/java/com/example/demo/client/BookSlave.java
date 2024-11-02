package com.example.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;

@FeignClient(name="book-slave")
public interface BookSlave {
	@GetMapping("/book/{id}")
	public Book getBookInfo(@PathVariable("id") int id) ;
	@GetMapping("/book")
	public Book[] getBookAll() ;
	@PostMapping("/book")
	public String postBook(@RequestBody Book book) ; 
	@PutMapping("/book/{id}")
	public String putBook(@PathVariable("id") int id, @RequestBody Book book) ;
	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable("id") int id);
}
