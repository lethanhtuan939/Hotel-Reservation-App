package com.hotelreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView =  findViewById(R.id.hotelpng);
        imageView.animate().translationY(-1000).setDuration(2700).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}