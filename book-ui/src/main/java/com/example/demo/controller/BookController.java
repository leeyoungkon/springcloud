package com.example.demo.controller;

import java.text.ParseException;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.example.demo.client.BookSlave;
//import com.example.demo.model.Book;
//import com.example.demo.model.BookComm;




@Controller
public class BookController {
	
//	@Autowired
//	BookSlave bookSlave;
	
	@GetMapping("/book/list")
	public String bookTable(Model model) {
		//Book[] booksArray = bookSlave.getBookAll();
		//List<Book> books = Arrays.asList(booksArray);
		//model.addAttribute("list", books);
		return "book/list";
	}
/*	
	@GetMapping("/book/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/createbook";
    }
	
	@PostMapping("/book/save")
	    public String saveBook(@ModelAttribute("book") BookComm book) {
	        bookSlave.postBook(book);
	        return "redirect:list";
	    }
	
	 @GetMapping("/book/edit/{id}")
	    public String editBookForm(@PathVariable int id, Model model) {
	        Book book = bookSlave.getBookInfo(id);
	        model.addAttribute("book", book);
	        return "book/edit-book";
	 }

	 // 수정된 Book 데이터를 저장
	 @PostMapping("/book/edit/{id}")
	 public String updateBook(@PathVariable int id, @RequestBody BookComm comm) throws ParseException {
	    bookSlave.putBook(id, comm);
	    return "redirect:/book/list";
	 }
	 
	  @GetMapping("/book/delete/{id}")
	    public String deleteBook(@PathVariable int id) {
	       bookSlave.deleteBook(id);
	       return "redirect:/book/list";
	    }
	
	*/
}
