package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.service.BookConsumer;
import com.example.demo.service.BookPublisher;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookPublisher bookPublisher;
    private final BookConsumer bookConsumer;

    public BookController(BookService bookService, BookPublisher bookPublisher, BookConsumer bookConsumer) {
        this.bookService = bookService;
        this.bookPublisher = bookPublisher;
        this.bookConsumer = bookConsumer;
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
