package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.client.BookClient;
import com.example.demo.client.UserClient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;

@Controller
@RequestMapping("/books")
class BookController {
	
    @Autowired
    private BookClient bookClient;
    
    @Autowired
    private UserClient userClient;

    @GetMapping
    public String listBooksUsers(Model model) {
        model.addAttribute("books", bookClient.getAllBooks());
        model.addAttribute("users", userClient.getAllUsers());
        return "book/book-list";
    }
    
    @GetMapping("/edit")
    public String editBook(@RequestParam Long id, Model model) {
    	Book book = bookClient.getBookById(id);
    	model.addAttribute("book", book);
    	return "book/edit-book";
    }
    
    @GetMapping("/edit/user")
    public String editUser(@RequestParam String id, Model model) {
    	User user = userClient.getUserById(id);
    	model.addAttribute("user", user);
    	return "user-edit";
    }
    
    @PostMapping("/update/user")
    public String updateUser(@RequestParam String id, @RequestParam String name, @RequestParam String password, 
    		@RequestParam int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        userClient.updateUser(id, user);
        return "redirect:/books";
    }
    
    @PostMapping("/update")
    public String updateBook(@RequestParam Long id, @RequestParam String name, @RequestParam String author, @RequestParam String published) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        book.setPublished(LocalDateTime.parse(published, formatter));

        bookClient.updateBook(id, book);
        return "redirect:/books";
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
    
    @PostMapping("/user")
    public String addUser(@RequestParam String id, @RequestParam String name, @RequestParam String password, @RequestParam String age) {
        User user = new User();
        user.setName(name);
        user.setId(id);
        user.setPassword(password);
        user.setAge(Integer.parseInt(age));
        userClient.createUser(user);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookClient.deleteBook(id);
        return "redirect:/books";
    }
    
    @PostMapping("/delete/user")
    public String deleteUser(@RequestParam String id) {
        userClient.deleteUser(id);
        return "redirect:/books";
    }
}