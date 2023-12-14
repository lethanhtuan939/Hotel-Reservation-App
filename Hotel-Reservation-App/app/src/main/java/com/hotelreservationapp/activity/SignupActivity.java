package com.hotelreservationapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.R;
import com.hotelreservationapp.request.AuthResponse;
import com.hotelreservationapp.request.RegisterRequest;
import com.hotelreservationapp.service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword, edtRepeatPassword, edtPhone, edtName;
    Button btnSignup;
    TextView txtEmailMessage, txtPasswordMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mapping();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String repeatPassword = edtRepeatPassword.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                boolean isValid = false;

                if (!Constant.isValidEmailId(email)) {
                    txtEmailMessage.setText("Email nhập vào chưa đúng định dạng!");
                    isValid = false;
                } else {
                    txtEmailMessage.setText("");
                    isValid = true;
                }

                if (!password.equals(repeatPassword)) {
                    txtPasswordMessage.setText("Mật khẩu nhập lại không chính xác!");
                    isValid = false;
                } else {
                    txtPasswordMessage.setText("");
                    isValid = true;
                }

                if (isValid) {
                    RegisterRequest request = new RegisterRequest(name, email, password, phone);
                    singup(request);
                }
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
        btnSignup = findViewById(R.id.btnSignup);
        edtEmail = findViewById(R.id.edtEmailSignup);
        edtPassword = findViewById(R.id.edtSignupPassword);
        edtRepeatPassword = findViewById(R.id.edtRepeatPasswordSignUp);
        edtPhone = findViewById(R.id.edtPhone);
        txtEmailMessage = findViewById(R.id.txtEmailMessage);
        txtPasswordMessage = findViewById(R.id.txtPasswordMessage);
        edtName = findViewById(R.id.edtNameSignup);
    }


    private void singup(RegisterRequest request) {
        WebService.api.register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if (authResponse != null) {
                    if (authResponse.getCode() == 200) {
                        Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }
}