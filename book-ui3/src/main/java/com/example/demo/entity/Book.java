package com.example.demo.entity;


import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate published;
    public Book() {}
	public Book(Long id, String name, String author, LocalDate published) {
		super();
		this.id = 0L;
		this.name = name;
		this.author = author;
		this.published = published;
	}
    
}
