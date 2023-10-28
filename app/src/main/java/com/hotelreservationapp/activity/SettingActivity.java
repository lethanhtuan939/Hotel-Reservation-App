package com.hotelreservationapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.R;


public class SettingActivity extends AppCompatActivity {

    ConstraintLayout profile_tab, history_tab, aboutus_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mapping();

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_setting);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_setting)
                    return true;
                else if (item.getItemId() == R.id.btn_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                } else if (item.getItemId() == R.id.btn_favourites) {
                    Intent intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        profile_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        history_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        aboutus_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);

            }
        });

    }

    private void mapping() {
        profile_tab = (ConstraintLayout) findViewById(R.id.profile_tab);
        history_tab = (ConstraintLayout) findViewById(R.id.history_tab);
        aboutus_tab = (ConstraintLayout) findViewById(R.id.aboutus_tab);

    }
}