package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Book;

@FeignClient(name = "book-api", url = "http://fourth-service:8092/api/books")
public interface BookClient {
    @GetMapping
    List<Book> getAllBooks();

    @GetMapping("/{id}")
    Book getBookById(@PathVariable Long id);

    @PostMapping
    Book createBook(@RequestBody Book book);

    @PutMapping("/{id}")
    Book updateBook(@PathVariable Long id, @RequestBody Book book);

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id);
}

