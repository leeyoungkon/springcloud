package com.example.demo.controller;


import com.example.demo.entity.Book;

import com.example.demo.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(bookRepository.findAll());
    }

    @PostMapping
    public Mono<Book> addBook(@RequestBody Book book) {
        return Mono.just(bookRepository.save(book));
    }
}
