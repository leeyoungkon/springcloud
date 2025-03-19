package com.example.demo.service;


import com.example.demo.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookConsumer {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public BookConsumer(BookService bookService, ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.objectMapper = objectMapper;
    }

    // 📌 Slave가 보낸 책 목록을 수신하여 Master의 bookList에 저장
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveBookListMessage(String message) {
        try {
            if (message.startsWith("[")) {
                // 📌 JSON이 배열([]) 형태라면 List<Book>로 변환
                List<Book> books = Arrays.asList(objectMapper.readValue(message, Book[].class));
                bookService.updateBookList(books);
                System.out.println("📥 Received Book List from Slave: " + books);
            } else {
                // 📌 JSON이 단일 객체({}) 형태라면 Book 객체로 변환 후 리스트에 추가
                Book book = objectMapper.readValue(message, Book.class);
                bookService.updateBookList(List.of(book));
                System.out.println("📥 Received Single Book from Slave: " + book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
