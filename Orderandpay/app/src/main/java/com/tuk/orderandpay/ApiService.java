package com.tuk.orderandpay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("menus")
    Call<List<Menu>> getMenus();

    @POST("orders")
    Call<Order> placeOrder(@Body OrderRequest request);

    @POST("payments")
    Call<Payment> pay(@Body PaymentRequest request);
}

