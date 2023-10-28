package com.hotelreservationapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.model.Hotel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    int[] image = {
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel,
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel
    };
    String[] name = {
            "Muong Thanh Hotel", "Muong Thanh Hotel", "Muong Thanh Hotel",
            "Muong Thanh Hotel", "Muong Thanh Hotel", "Muong Thanh Hotel"
    };
    double[] acreage = {
            3, 3, 3,
            3, 3, 3
    };
    double[] rating = {
            3, 3, 3,
            3, 3, 3
    };
    ArrayList<Hotel> list_hotels;
    ListView listView;
    HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listHotel);


        list_hotels = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            list_hotels.add(new Hotel(i, name[i], image[i], true, acreage[i], rating[i]));
        }
        hotelAdapter = new HotelAdapter(HomeActivity.this, R.layout.hotel_item, list_hotels);
        listView.setAdapter(hotelAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getApplicationContext(),RoomActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_home);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_home)
                    return true;
                else if (item.getItemId() == R.id.btn_favourites) {
                    Intent intent= new  Intent(getApplicationContext(),FavouriteActivity.class);
                    startActivity(intent);

                } else if (item.getItemId() == R.id.btn_setting) {
                    Intent intent= new  Intent(getApplicationContext(),SettingActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}