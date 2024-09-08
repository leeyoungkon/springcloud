package com.example.demo.service;

import org.springframework.cache.annotation.CachePut;

import org.springframework.stereotype.Service;



@Service
public class BookService {
	
	
	public String getBook(int id) {
		return "my";
	}
	
	public String getBookInfoFallback(int id) {
		return "my fallback";
	}

}
