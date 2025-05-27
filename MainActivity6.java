package com.tuk.app0505;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.*;

public class MainActivity6 extends AppCompatActivity {

    private EditText nameInput, latitudeInput, longitudeInput, featuresInput, imageInput;
    private Button saveButton;
    private RestaurantApi restaurantApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        nameInput = findViewById(R.id.nameInput);
        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        featuresInput = findViewById(R.id.featuresInput);
        imageInput = findViewById(R.id.imageInput);
        saveButton = findViewById(R.id.saveButton);

        restaurantApi = RestaurantClient.getApiService();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                double latitude = Double.parseDouble(latitudeInput.getText().toString());
                double longitude = Double.parseDouble(longitudeInput.getText().toString());
                String features = featuresInput.getText().toString();
                String image = imageInput.getText().toString();

                Restaurant restaurant = new Restaurant(name, latitude, longitude, features, image);

                restaurantApi.addRestaurant(restaurant).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(MainActivity6.this, "저장 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity6.this, "저장 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
