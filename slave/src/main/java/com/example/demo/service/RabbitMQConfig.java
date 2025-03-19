package com.example.demo.service;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "bookQueue";
    public static final String EXCHANGE_NAME = "bookExchange";
    public static final String ROUTING_KEY = "bookRoutingKey";

    // Queue 생성
    @Bean
    public Queue bookQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    // Exchange 생성 (Direct Exchange 사용)
    @Bean
    public DirectExchange bookExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Queue와 Exchange를 Binding
    @Bean
    public Binding binding(Queue bookQueue, DirectExchange bookExchange) {
        return BindingBuilder.bind(bookQueue).to(bookExchange).with(ROUTING_KEY);
    }
}
