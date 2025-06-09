package com.tuk.orderandpay;

public class OrderItemDTO {
    public Long menuId;
    public int quantity;

    public OrderItemDTO(Long menuId, int quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }
}
