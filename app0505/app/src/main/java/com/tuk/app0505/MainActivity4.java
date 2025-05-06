package com.tuk.app0505;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends AppCompatActivity {

    private EditText editBookId;
    private Button btnSearch;
    private TextView txtBookInfo;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        editBookId = findViewById(R.id.editBookId);
        btnSearch = findViewById(R.id.btnSearch);
        txtBookInfo = findViewById(R.id.txtBookInfo);

        apiService = ApiClient.getClient().create(ApiService.class);

        btnSearch.setOnClickListener(view -> {
            String idStr = editBookId.getText().toString().trim();
            if (!idStr.isEmpty()) {
                try {
                    long bookId = Long.parseLong(idStr);
                    fetchBookInfo(bookId);
                } catch (NumberFormatException e) {
                    txtBookInfo.setText("숫자 형식의 ID를 입력하세요.");
                }
            } else {
                txtBookInfo.setText("책 ID를 입력하세요.");
            }
        });
    }

    private void fetchBookInfo(long bookId) {

        apiService.getBook(bookId).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Book book = response.body();
                    String info = "제목: " + book.getName() + "\n" +
                            "저자: " + book.getAuthor() + "\n" +
                            "출판일: " + book.getPublished();
                    txtBookInfo.setText(info);
                } else {
                    txtBookInfo.setText("책을 찾을 수 없습니다.");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                txtBookInfo.setText("서버 오류: " + t.getMessage());
            }
        });
    }
}
