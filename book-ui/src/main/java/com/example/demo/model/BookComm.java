package com.example.demo.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


public class BookComm {

   
    private int id;

    private String name;

    private String author;

    private String published;
    
    public BookComm() {}
    
    

	public BookComm(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}
    
}
