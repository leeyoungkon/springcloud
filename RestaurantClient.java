package com.tuk.app0505;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantClient {
    private static final String BASE_URL = "http://223.130.153.51:30102/"; // 끝에 / 꼭!
    private static Retrofit retrofit;

    public static RestaurantApi getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RestaurantApi.class);
    }
}

