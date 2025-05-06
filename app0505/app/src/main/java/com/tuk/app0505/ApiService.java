package com.tuk.app0505;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/book/{id}")
    Call<Book> getBook(@Path("id") Long id);
}
