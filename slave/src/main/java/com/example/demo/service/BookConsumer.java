package com.example.demo.service;

import com.example.demo.entity.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookConsumer {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public BookConsumer(BookService bookService, ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "bookQueue")
    public void receiveBookMessage(String message) {
        try {
            if (message.trim().startsWith("[")) {
                List<Book> books = objectMapper.readValue(message, new TypeReference<List<Book>>() {});
                for (Book book : books) {
                    bookService.saveBook(book);
                    System.out.println("📥 Received & Saved Book from Master: " + book);
                }
            } else {
                Book book = objectMapper.readValue(message, Book.class);
                bookService.saveBook(book);
                System.out.println("📥 Received & Saved Book from Master: " + book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
