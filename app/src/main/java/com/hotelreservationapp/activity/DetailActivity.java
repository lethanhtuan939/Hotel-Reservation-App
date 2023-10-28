package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hotelreservationapp.R;

public class DetailActivity extends AppCompatActivity {

    Button btnReservation;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnReservation = findViewById(R.id.btnReservation);
        imgBack = findViewById(R.id.imgBack);
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity1.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}