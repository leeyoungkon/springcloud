package com.tuk.app0505;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String published; // String으로 처리 (ISO date)

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public String getPublished() { return published; }
}
