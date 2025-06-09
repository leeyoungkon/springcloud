package com.tuk.orderandpay;

public class PaymentRequest {
    public Long orderId;
    public String method;

    public PaymentRequest(Long orderId, String method) {
        this.orderId = orderId;
        this.method = method;
    }
}
