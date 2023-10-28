package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hotelreservationapp.R;

public class BookingActivity3 extends AppCompatActivity {

    Button btnComplete;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking3);

        mapping();

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity3.this, CompletedActivity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity3.this, BookingActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnComplete = findViewById(R.id.btnNext2);
        imgBack = findViewById(R.id.imgBack);
    }
}