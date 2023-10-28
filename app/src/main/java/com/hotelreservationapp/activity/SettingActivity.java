package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hotelreservationapp.R;


public class SettingActivity extends AppCompatActivity {

    ConstraintLayout profile_tab, history_tab, aboutus_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mapping();

        profile_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        history_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        aboutus_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        profile_tab = findViewById(R.id.profile_tab);
        history_tab = findViewById(R.id.history_tab);
        aboutus_tab = findViewById(R.id.aboutus_tab);
    }
}