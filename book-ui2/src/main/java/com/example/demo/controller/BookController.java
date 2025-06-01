package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.client.BookClient;
import com.example.demo.client.UserClient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;

@Controller
//@RequestMapping("/books")
class BookController {
	
    @Autowired
    private BookClient bookClient;
    
   
    @GetMapping("/book")
    public String listBooksUsers(Model model) {
        model.addAttribute("books", bookClient.getAllBooks());
        return "book/book-list";
    }
    
    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
    	Book book = bookClient.getBookById(id);
    	model.addAttribute("book", book);
    	return "book/edit-book";
    }
    
    @PostMapping("/book/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);  // 혹시 모를 null 방지
        bookClient.updateBook(id, book);
        return "redirect:/book";
    }

    
    @PostMapping("/book/add")
    public String addBook(@ModelAttribute Book book) {
        bookClient.createBook(book);
        return "redirect:/book"; // 목록 페이지로 리다이렉트
    }
   
    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookClient.deleteBook(id);
        return "redirect:/book";
    }
    
    
}