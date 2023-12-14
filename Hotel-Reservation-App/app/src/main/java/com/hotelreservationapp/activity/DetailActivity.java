package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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

    TextView nameHotel, nameRoom, price, location, floor, roomType, status, new_price, sale, minus, sale2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Mapping();

        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra("room");
        nameHotel.setText(room.getHotel().getName());
        Picasso.get().load(room.getImgRoom()).into(imgDetail);
        nameRoom.setText("Phòng " + room.getName());
        price.setText("$" + (room.getPrice()));
        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        location.setText(room.getHotel().getLocation().getName());
        roomType.setText(room.getRoomType().getName());
        floor.setText("" + room.getFloor());

        double number = room.getPrice() * (1 - room.getSale() / 100);


        String roundedNumberString = String.format("%.1f", number);
        double roundedNumber = Double.parseDouble(roundedNumberString);
        double value = room.getPrice() - roundedNumber;
        String valueNumberString = String.format("%.1f", value);


        minus.setText("$" + valueNumberString);

        status.setText(room.getState());

        new_price.setText("$" + roundedNumberString);
        sale.setText(room.getSale() + "%");
        sale2.setText("$" + roundedNumberString);

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
        floor = findViewById(R.id.detail_txt_floor);
        //   status = findViewById(R.id.txt_status);
        status = findViewById(R.id.state);
        new_price = findViewById(R.id.new_price);
        sale = findViewById(R.id.detail_sale);
        minus = findViewById(R.id.detail_minus);
        sale2 = findViewById(R.id.sale2);
    }

}
