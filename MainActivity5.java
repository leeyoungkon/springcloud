package com.tuk.app0505;

import android.Manifest;
import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity5 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private final Map<Marker, Restaurant> markerRestaurantMap = new HashMap<>();


    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, MODE_PRIVATE);

        googleMap.setOnMarkerClickListener(marker -> {
            Restaurant restaurant = markerRestaurantMap.get(marker);
            if (restaurant != null) {
                showRestaurantDialog(restaurant);
            }
            return true; // 기본 InfoWindow 막기
        });


        fetchRestaurants();
    }

    private void fetchRestaurants() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://223.130.153.51:30102/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestaurantApi api = retrofit.create(RestaurantApi.class);
        api.getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Restaurant restaurant : response.body()) {
                        addRestaurantMarker(restaurant);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void addRestaurantMarker(Restaurant restaurant) {
        LatLng location = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title(restaurant.getName())
                .snippet(restaurant.getFeatures());

        Marker marker = googleMap.addMarker(markerOptions);

        // 마커와 Restaurant 객체 매핑
        markerRestaurantMap.put(marker, restaurant);
    }

    private void showRestaurantDialog(Restaurant restaurant) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_restaurant); // 아래 XML 참고

        ImageView imageView = dialog.findViewById(R.id.restaurant_image);
        TextView nameView = dialog.findViewById(R.id.restaurant_name);
        TextView featureView = dialog.findViewById(R.id.restaurant_features);

        String BASE_IMAGE_URL = "http://223.130.153.51:30102/";
        String fullUrl = BASE_IMAGE_URL + restaurant.getImage();
        Glide.with(this)
                .load(fullUrl) // 서버 이미지 URL 사용
                .placeholder(R.drawable.food_icon)
                .error(R.drawable.food_icon)
                .into(imageView);

        nameView.setText(restaurant.getName());
        featureView.setText(restaurant.getFeatures());

        dialog.show();
    }



}
