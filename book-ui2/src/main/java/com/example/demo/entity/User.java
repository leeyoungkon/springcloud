package com.example.demo.entity;


import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private int age;
}
