package com.example.demo.service;



import com.example.demo.entity.Book;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Service
public class BookService {
    // 📌 Slave에서 받은 책 목록을 저장 (스레드 안전하게 동기화)
    private final List<Book> bookList = Collections.synchronizedList(new ArrayList<>());

    // ✅ Master에서 웹 UI로 표시할 책 목록 조회
    public List<Book> getBooks() {
        return new ArrayList<>(bookList);  // 📌 동기화 문제 방지를 위해 새로운 리스트 반환
    }

    // ✅ Slave에서 받은 책 목록을 저장 (덮어쓰지 않고 추가)
    public synchronized void updateBookList(List<Book> books) {
        bookList.clear();  // 📌 기존 목록을 지우고 새로운 데이터를 저장 (동기화)
        bookList.addAll(books);
        System.out.println("📥 Updated Book List from Slave: " + bookList);
    }
}
