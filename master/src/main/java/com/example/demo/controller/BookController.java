package com.example.demo.controller;

import com.example.demo.service.BookPublisher;
import com.example.demo.service.BookService;
import com.example.demo.entity.Book;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookPublisher bookPublisher;
    private final BookService bookService;

    public BookController(BookPublisher bookPublisher, BookService bookService) {
        this.bookPublisher = bookPublisher;
        this.bookService = bookService;
    }

    // 📌 책 목록 조회 (books.html로 이동)
    @GetMapping
    public String showBooks(Model model) {
    	List<Book> books = bookService.getBooks();
        System.out.println("📄 Books sent to UI: " + books);
        model.addAttribute("books", books);
        return "books";
    }

    // 📌 책 추가 폼 (book-form.html로 이동)
    @GetMapping("/form")  // 📌 "/books/form"으로 변경하여 충돌 방지
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";  // 📌 새로운 book-form.html 사용
    }

    // 📌 책 추가 후 RabbitMQ로 전송
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
    	book.setPublished(LocalDateTime.now()); // 📌 현재 시점으로 published 설정
        bookPublisher.sendBookToSlave(book);
        return "redirect:/books";
    }
}
