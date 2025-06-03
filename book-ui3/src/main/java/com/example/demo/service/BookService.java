package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final WebClient webClient;

    public BookService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://seventh-service:8097").build(); // 서비스 이름이 seventh
    }

    public Flux<Book> getAllBooks() {
        return webClient.get()
                .uri("/books")
                .retrieve()
                .bodyToFlux(Book.class);
    }

    public Mono<Void> addBook(Book book) {
        return webClient.post()
                .uri("/books")
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Void.class);
    }
}

