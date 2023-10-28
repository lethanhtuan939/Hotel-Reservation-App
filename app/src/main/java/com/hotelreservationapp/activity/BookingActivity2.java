package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hotelreservationapp.R;

public class BookingActivity2 extends AppCompatActivity {

    Button btnNext;

    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        mapping();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity2.this, BookingActivity3.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity2.this, BookingActivity1.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnNext = (Button) findViewById(R.id.btnNextStep2);
        imgBack = (ImageView) findViewById(R.id.imgBack);
    }
}