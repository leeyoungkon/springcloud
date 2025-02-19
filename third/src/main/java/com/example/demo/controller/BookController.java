package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list";
    }

    @PostMapping
    public String addBook(@RequestParam String name, @RequestParam String author, @RequestParam String published) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublished(LocalDateTime.parse(published));
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book-edit";
        }
        return "redirect:/books";
    }

    @PostMapping("/update")
    public String updateBook(@RequestParam Long id, @RequestParam String name, @RequestParam String author, @RequestParam String published) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(name);
            book.setAuthor(author);
            book.setPublished(LocalDateTime.parse(published));
            bookService.saveBook(book);
        }
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}

