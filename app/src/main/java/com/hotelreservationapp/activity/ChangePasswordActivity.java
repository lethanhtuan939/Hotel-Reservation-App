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
import android.widget.Toast;

import com.hotelreservationapp.R;

public class ChangePasswordActivity extends AppCompatActivity {
    Button btnSaveChange;
    ImageView imgBack;
    EditText edtNewPassword, edtRepeatPassword;
    TextView notice1, notice2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent = getIntent();
        String password = intent.getStringExtra("password");

        mapping();
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1, password2;
                password1 = edtNewPassword.getText().toString().trim();
                password2 = edtRepeatPassword.getText().toString().trim();
                boolean isValid = false;
                if (password1.isEmpty()) {
                    notice1.setText("Nhập mật khẩu mới");
                    isValid = false;
                } else {
                    notice1.setText("");
                    isValid = true;
                }
                if (password2.isEmpty()) {
                    notice2.setText("Nhập lại mật khẩu mới");
                    isValid = false;
                } else {
                    notice2.setText("");
                    isValid = true;
                }
                if (!password2.equals(password1)) {
                    notice2.setText("Mật khẩu nhập lại không đúng");
                    isValid = false;
                } else {
                    notice2.setText("");
                    isValid = true;
                }
                if(isValid){
                    Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                    intent.putExtra("changePassword",password1);
                    sharedPreferences = getSharedPreferences("data_user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("password",password1);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }

//                Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        btnSaveChange = findViewById(R.id.cp_btnSaveChange);
        imgBack = findViewById(R.id.imgBack);
        edtNewPassword = findViewById(R.id.cp_edtNewPassword);
        edtRepeatPassword = findViewById(R.id.cp_edtRepeatPassword);
        notice1 = findViewById(R.id.cp_notice1);
        notice2 = findViewById(R.id.cp_notice2);
    }
}