package com.hotelreservationapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.User;
import com.squareup.picasso.Picasso;

public class SettingActivity extends AppCompatActivity {

    ConstraintLayout profile_tab, history_tab, aboutus_tab;
    TextView txt_name,txt_email;
    ImageView avtar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        mapping();

        User user = getUser();
        txt_name.setText(user.getName());
        txt_email.setText(user.getEmail());
        Picasso.get().load(user.getImage()).into(avtar);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_setting);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_setting)
                    return true;
                else if (item.getItemId() == R.id.btn_home) {
                    finish();

                } else if (item.getItemId() == R.id.btn_favourites) {
                    Intent intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                    startActivity(intent);
                    finish();
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
                Intent intent = new Intent(SettingActivity.this, HistoryActivity.class);

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
        txt_email = findViewById(R.id.setting_email);
        txt_name =  findViewById(R.id.setting_username);
        avtar = findViewById(R.id.setting_avatar);
    }

    private User getUser() {
        sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        String image = sharedPreferences.getString("image", "");
        String phone = sharedPreferences.getString("phoneNumber", "");
        User user = new User(id, name, email, password, image, phone);
        return user;
    }
}