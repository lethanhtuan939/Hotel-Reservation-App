package com.hotelreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.model.Hotel;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    int[] id = {
            1, 2, 3,
            4, 5, 6
    };
    int[] image = {
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel,
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel
    };
    String[] name = {
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang",
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang"
    };
    double[] acreage = {
            3, 3, 3,
            3, 3, 3
    };
    double[] rating = {
            3, 3, 3,
            3, 3, 3
    };
    ArrayList<Hotel> list_hotel;
    ListView listView_faVou;
    HotelAdapter favouriteAdapter;
    NavigationBarView navigationBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView_faVou = findViewById(R.id.listFavourHotel);
        setContentView(R.layout.activity_favourite);
        list_hotel = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            list_hotel.add(new Hotel(id[i], name[i], image[i], true, acreage[i], rating[i]));
        }
        favouriteAdapter = new HotelAdapter(FavouriteActivity.this, R.layout.item_favourites_custom, list_hotel);
        listView_faVou.setAdapter(favouriteAdapter);
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_favourites);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_favourites)
                    return true;
                else if (item.getItemId() == R.id.btn_home) {
                    Intent intent= new  Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else if (item.getItemId() == R.id.btn_setting) {
                    Intent intent= new  Intent(getApplicationContext(),SettingActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}