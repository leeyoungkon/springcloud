package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 모든 책 목록 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 새로운 책 추가
    public Book saveBook(Book book) {
        book.setPublished(LocalDateTime.now()); // 현재 시간 저장
        return bookRepository.save(book);
    }

    // 책 정보 수정
    public Book updateBook(Long id, Book book) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setName(book.getName());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPublished(book.getPublished()); // 날짜 수정 가능
            return bookRepository.save(updatedBook);
        }
        return null;
    }
}
