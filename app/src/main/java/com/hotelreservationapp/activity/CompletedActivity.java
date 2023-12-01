package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.hotelreservationapp.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedActivity extends AppCompatActivity {

    Button btnHome;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);

        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sharedPreferences.getString("email", "");
                String password = sharedPreferences.getString("password", "");
                getUserByEmail(email, password);
            }
        });
    }

    private void getUserByEmail(String email, String password) {
        WebService.api.getUser(email).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    JSONObject jsonObject = new JSONObject(json);
                    String image = Constant.PRE_FIX + "/file/" + jsonObject.getJSONObject("data").getString("image");
                    int id = jsonObject.getJSONObject("data").getInt("id");
                    String phoneNumber = jsonObject.getJSONObject("data").getString("phoneNumber");
                    String name = jsonObject.getJSONObject("data").getString("name");

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    User user = new User(id, name, email, password, image, phoneNumber);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}