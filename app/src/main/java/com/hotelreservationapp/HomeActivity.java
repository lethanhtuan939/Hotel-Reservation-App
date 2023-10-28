package com.hotelreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.model.Hotel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    int[] id = {
            1, 2, 3,
            4, 5, 6,
            7, 8, 9
    };
    int[] image = {
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel,
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel,
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel
    };
    String[] name = {
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang",
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang",
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang"
    };
    double[] acreage = {
            3, 3, 3,
            3, 3, 3,
            3, 3, 3
    };
    double[] rating = {
            3, 3, 3,
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
            list_hotels.add(new Hotel(id[i], name[i], image[i], true, acreage[i], rating[i]));
        }
        hotelAdapter = new HotelAdapter(HomeActivity.this, R.layout.hotel_item, list_hotels);
        listView.setAdapter(hotelAdapter);

    }
}