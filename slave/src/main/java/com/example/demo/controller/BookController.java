package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.service.BookPublisher;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookPublisher bookPublisher;

    public BookController(BookService bookService, BookPublisher bookPublisher) {
        this.bookService = bookService;
        this.bookPublisher = bookPublisher;
    }
    
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/send")
    public String sendBooksToMaster() {
        List<Book> books = bookService.getAllBooks();
        bookPublisher.sendBooksToMaster(books);
        return "Book list sent to Master!";
    }
}
