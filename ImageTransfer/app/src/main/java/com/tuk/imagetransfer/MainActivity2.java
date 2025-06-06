package com.tuk.imagetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    private ImageView imageView;
    private ArrayAdapter<String> adapter;
    private List<String> imageList = new ArrayList<>();

    private static final String BASE_URL = "http://172.30.1.26:30115/";  // 실제 IP:Port

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listView);
        imageView = findViewById(R.id.imageView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imageList);
        listView.setAdapter(adapter);

        findViewById(R.id.btnLoad).setOnClickListener(view -> fetchImageList());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String filename = imageList.get(position);
            String imageUrl = BASE_URL + "images/" + filename;
            Glide.with(MainActivity2.this)
                    .load(imageUrl)
                    .into(imageView);
        });
    }

    private void fetchImageList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageApi imageApi = retrofit.create(ImageApi.class);
        imageApi.getImageList().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imageList.clear();
                    imageList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity2.this, "이미지 목록 로딩 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
