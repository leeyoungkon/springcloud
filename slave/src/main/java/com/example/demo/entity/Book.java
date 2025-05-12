package com.example.demo.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(name = "published", columnDefinition = "DATETIME")
    private LocalDateTime published;


    public Book(String name, String author, LocalDateTime published) {
        this.name = name;
        this.author = author;
        this.published = published;
    }
}

