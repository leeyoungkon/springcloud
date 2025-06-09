package com.example.demo.service;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired private MenuRepository menuRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderItemRepository orderItemRepo;
    @Autowired private PaymentRepository paymentRepo;

    public List<Menu> getMenus() {
        return menuRepo.findAll();
    }

    public Order createOrder(OrderRequest req) {
        Order order = new Order();
        order.setTableNo(req.getTableNo());
        order = orderRepo.save(order);

        for (OrderRequest.OrderItemDTO item : req.getItems()) {
            Menu menu = menuRepo.findById(item.menuId).orElseThrow();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenu(menu);
            orderItem.setQuantity(item.quantity);
            orderItemRepo.save(orderItem);
        }

        return order;
    }

    public Payment processPayment(PaymentRequest req) {
        Order order = orderRepo.findById(req.orderId).orElseThrow();
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(req.method);
        payment.setStatus("PAID");
        return paymentRepo.save(payment);
    }
}
