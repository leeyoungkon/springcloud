package com.tuk.imagetransfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageApi {
    @GET("images")
    Call<List<String>> getImageList();
}
