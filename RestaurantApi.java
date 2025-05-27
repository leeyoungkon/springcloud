package com.tuk.app0505;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestaurantApi {
    @GET("/restaurants")
    Call<List<Restaurant>> getRestaurants();

    @POST("restaurants")
    Call<Void> addRestaurant(@Body Restaurant restaurant);
}
