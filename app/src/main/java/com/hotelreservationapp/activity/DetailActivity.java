package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.model.Room;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.utils.DateUtil;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    Button btnReservation;
    ImageView imgBack, imgDetail;

    TextView nameHotel, nameRoom, price, location, floor, roomType, status, sale;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Mapping();

        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra("room");
        nameHotel.setText("Phòng " + room.getHotel().getName());
        Picasso.get().load(room.getImgRoom()).into(imgDetail);
        nameRoom.setText(room.getName());
        price.setText(String.valueOf(room.getPrice()));
        location.setText(room.getHotel().getLocation().getName());
        roomType.setText(room.getRoomType().getName());
        floor.setText("Tầng " + room.getFloor());
        status.setText(room.getState());
        sale.setText(room.getSale() + "%");

        if (room.getState().equals("ĐÃ ĐƯỢC ĐẶT")) {
            btnReservation.setEnabled(false);
        }
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity1.class);
                sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
                User user = new User(sharedPreferences.getInt("id", 0),
                        sharedPreferences.getString("name", ""),
                        sharedPreferences.getString("email", ""),
                        sharedPreferences.getString("phoneNumber", ""));

                Reservation reservation = new Reservation();
                Date currentDate = Calendar.getInstance().getTime();

                reservation.setBookingDate(DateUtil.formatToString(currentDate, "yyyy-MM-dd"));
                reservation.setUser(user);
                reservation.setRoom(room);
                intent.putExtra("reservation", reservation);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        sale = findViewById(R.id.sale);
    }

}
