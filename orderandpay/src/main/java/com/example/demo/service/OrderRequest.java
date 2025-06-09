package com.example.demo.service;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private int tableNo;
    private List<OrderItemDTO> items;

    public static class OrderItemDTO {
        public Long menuId;
        public int quantity;
    }
}
