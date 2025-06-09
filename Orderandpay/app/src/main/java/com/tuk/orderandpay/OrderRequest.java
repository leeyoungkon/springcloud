package com.tuk.orderandpay;

import java.util.List;

public class OrderRequest {
    public int tableNo;
    public List<OrderItemDTO> items;

    public OrderRequest(int tableNo, List<OrderItemDTO> items) {
        this.tableNo = tableNo;
        this.items = items;
    }
}
