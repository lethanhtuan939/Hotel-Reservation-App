package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.request.AuthResponse;
import com.hotelreservationapp.request.ChangePasswordRequest;
import com.hotelreservationapp.service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText edtNewPassword, edtConFirmPassword;
    TextView txtNotice, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        mapping();

        txtEmail.setText("User " + email);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = edtNewPassword.getText().toString().trim();
                String confirmPassword = edtConFirmPassword.getText().toString().trim();

                changePassword(new ChangePasswordRequest(email, newPassword, confirmPassword));
            }
        });

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        btnConfirm = findViewById(R.id.btnConfirm);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConFirmPassword = findViewById(R.id.edtConfirmPassword);
        txtNotice = findViewById(R.id.txtNotice);
        txtEmail = findViewById(R.id.txtEmail);
    }

    private void changePassword(ChangePasswordRequest request) {
        WebService.api.changePassword(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if (authResponse.getCode() != 200) {
                    txtNotice.setText(authResponse.getMessage());
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
                    sharedPreferences.edit().remove("password").commit();
                    Intent intent = new Intent(getApplicationContext(), LoginAgainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }
}