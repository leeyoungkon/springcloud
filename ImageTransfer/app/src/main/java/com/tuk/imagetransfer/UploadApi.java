package com.tuk.imagetransfer;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApi {
    @Multipart
    @POST("upload")  // 서버의 업로드 경로
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);
}

