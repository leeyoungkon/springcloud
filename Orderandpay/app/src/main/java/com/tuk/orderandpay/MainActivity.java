package com.tuk.orderandpay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Menu> menuList = new ArrayList<>();
    private Map<Long, Integer> selectedItems = new HashMap<>();
    private LinearLayout menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuLayout = findViewById(R.id.menuLayout);

        fetchMenus();

        findViewById(R.id.orderButton).setOnClickListener(v -> {
            placeOrder();
        });
    }

    private void fetchMenus() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.103:30118/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        api.getMenus().enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.isSuccessful()) {
                    menuList = response.body();
                    Log.d("API", "받은 메뉴 수: " + menuList.size());
                    for (Menu m : menuList) {
                        Log.d("API", "메뉴: " + m.name + ", 가격: " + m.price);
                    }
                        runOnUiThread(() -> showMenuItems());
                } else {
                    Log.e("API", "응답 실패: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showMenuItems() {
        for (Menu menu : menuList) {
            CheckBox cb = new CheckBox(this);
            cb.setText(menu.name + " - " + menu.price + "원");
            cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) selectedItems.put(menu.id, 1);
                else selectedItems.remove(menu.id);
            });
            menuLayout.addView(cb);
        }
    }

    private void placeOrder() {
        List<OrderItemDTO> items = new ArrayList<>();
        for (Long menuId : selectedItems.keySet()) {
            items.add(new OrderItemDTO(menuId, selectedItems.get(menuId)));
        }

        OrderRequest req = new OrderRequest(1, items); // 1번 테이블 고정 예시

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.103:30118/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.placeOrder(req).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Long orderId = response.body().id;
                    Toast.makeText(MainActivity.this, "주문완료, 결제 진행", Toast.LENGTH_SHORT).show();
                    doPayment(orderId);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void doPayment(Long orderId) {
        PaymentRequest paymentRequest = new PaymentRequest(orderId, "CARD");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.103:30118/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.pay(paymentRequest).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "결제 완료", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
