package com.example.demo.service;

import com.example.demo.service.RabbitMQConfig;
import com.example.demo.entity.Book;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class BookPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public BookPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendBookToSlave(Book book) {
    	try {
            book.setPublished(LocalDateTime.now());
            String jsonMessage = objectMapper.writeValueAsString(book);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, jsonMessage);
            System.out.println("🚀 Sent Book to Slave: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
