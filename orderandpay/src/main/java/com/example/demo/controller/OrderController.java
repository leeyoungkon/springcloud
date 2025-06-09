package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.entity.OrderItem;
import com.example.demo.service.OrderRequest;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentRequest;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return orderService.getMenus();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> pay(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(orderService.processPayment(request));
    }
}
