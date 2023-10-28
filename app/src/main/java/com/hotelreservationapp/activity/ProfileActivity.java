package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelreservationapp.R;

public class ProfileActivity extends AppCompatActivity {

    Button btnSaveChange;
    TextView txtChange;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtChange = findViewById(R.id.txtChange);
        btnSaveChange = findViewById(R.id.btnComplete);
        imgBack = findViewById(R.id.imgBack);
        mapping();

        txtChange.setPaintFlags(txtChange.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }


    private void mapping() {
        txtChange = findViewById(R.id.txtChange);
        btnSaveChange = findViewById(R.id.btnComplete);
        imgBack = findViewById(R.id.imgBack);
    }
}