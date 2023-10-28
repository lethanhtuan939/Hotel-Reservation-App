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

        for (int i = 0; i < image.length; i++) {
            list_room.add(new Room(i, i, image[i]));
        }
        roomAdapter = new RoomAdapter(RoomActivity.this,R.layout.room_item,list_room);
        listView.setAdapter(roomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        back = (ImageButton) findViewById(R.id.imgBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}