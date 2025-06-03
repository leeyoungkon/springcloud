package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/book-ui")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
    	List<Book> bookList = bookService.getAllBooks().collectList().block(); // Flux → List
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookList);
        return "book/book-list";
    }

    @PostMapping
    public Mono<String> addBook(@ModelAttribute Book book) {
        return bookService.addBook(book)
                .then(Mono.just("redirect:/book-ui"));
    }
}
