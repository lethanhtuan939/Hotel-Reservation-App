package com.hotelreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView_faVou = (ListView) findViewById(R.id.listFavourHotel);
        setContentView(R.layout.activity_favourite);
        list_hotel = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            list_hotel.add(new Hotel(id[i], name[i], image[i], true, acreage[i], rating[i]));
        }
        favouriteAdapter = new HotelAdapter(FavouriteActivity.this, R.layout.item_favourites_custom, list_hotel);
        listView_faVou.setAdapter(favouriteAdapter);
    }
}