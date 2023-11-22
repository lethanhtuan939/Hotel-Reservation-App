package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.adapter.RoomAdapter;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {
    int[] image = {
            R.drawable.room1, R.drawable.room2, R.drawable.room3,
            R.drawable.room1, R.drawable.room2, R.drawable.room3,
            R.drawable.room1, R.drawable.room2, R.drawable.room3
    };
    ArrayList<Room> list_room;
    ListView listView;
    RoomAdapter roomAdapter;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        listView = findViewById(R.id.listRoom);

        list_room = new ArrayList<Room>();


    }
}