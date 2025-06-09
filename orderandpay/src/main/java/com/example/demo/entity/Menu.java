package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Menu {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private String description;
}
