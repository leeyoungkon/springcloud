package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.client.BookClient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;

@Controller
@RequestMapping("/books")
class BookController {
    @Autowired
    private BookClient bookClient;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookClient.getAllBooks());
        return "book-list";
    }

    @PostMapping
    public String addBook(@RequestParam String name, @RequestParam String author, @RequestParam String published) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        book.setPublished(LocalDateTime.parse(published, formatter));

        bookClient.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookClient.deleteBook(id);
        return "redirect:/books";
    }
}