package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Room;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    Button btnReservation;
    ImageView imgBack, imgDetail;

    TextView nameHotel, nameRoom, price, location, floor, roomType, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Mapping();

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity1.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra("room");
        nameHotel.setText("Phòng" + room.getHotel().getName());
        Picasso.get().load(room.getImgRoom()).into(imgDetail);
        nameRoom.setText(room.getName());
        price.setText(String.valueOf(room.getPrice()));
        location.setText(room.getHotel().getLocation().getName());
        roomType.setText(room.getRoomType().getName());
        floor.setText("Tầng" + String.valueOf(room.getFloor()));
        status.setText(room.getState());
    }

    private void Mapping() {
        btnReservation = findViewById(R.id.btnReservation);
        imgBack = findViewById(R.id.imgBack);
        nameHotel = findViewById(R.id.txt_name_hotel1);
        imgDetail = findViewById(R.id.imagedetail);
        nameRoom = findViewById(R.id.txt_name_room);
        price = findViewById(R.id.price);
        location = findViewById(R.id.txt_location);
        roomType = findViewById(R.id.roomType);
        floor = findViewById(R.id.txt_floor);
        //   status = findViewById(R.id.txt_status);
        status = findViewById(R.id.state);
    }
}
