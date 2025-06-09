package com.example.demo.entity;


import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Order order;

    private String method;
    private String status;
    private LocalDateTime paymentTime = LocalDateTime.now();
}
