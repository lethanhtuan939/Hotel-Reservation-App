package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.hotelreservationapp.utils.DateUtil;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity3 extends AppCompatActivity {

    Button btnComplete;
    ImageView imgBack, imgb3_room;
    TextView txtHotel_s3, txtb3_room, txt_location_s3, txtRoomType_s3, txtFromTo, txtMoney, txtNoticeBooking3;
    Reservation reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking3);

        mapping();

        Intent intent = getIntent();
        reservation = (Reservation) intent.getSerializableExtra("reservation");
        Log.e("reservation", reservation.toString());

        txtHotel_s3.setText(reservation.getRoom().getHotel().getName());
        txtRoomType_s3.setText(reservation.getRoom().getName());
        txt_location_s3.setText(reservation.getRoom().getHotel().getLocation().getName());
        txtRoomType_s3.setText(reservation.getRoom().getRoomType().getName());
        txtFromTo.setText("Từ ngày " + reservation.getDayStart() + " đến ngày " + reservation.getDayEnd());
        txtMoney.setText(reservation.getPrice() + " $");
        Picasso.get().load(reservation.getRoom().getImgRoom()).into(imgb3_room);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking(reservation);

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity3.this, BookingActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void booking(Reservation reservation) {
//        Gson gson = new Gson();
//        String json =gson.toJson(reservation);
        WebService.api.reservation(reservation).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                if(responseObject.getCode() > 201) {
                    txtNoticeBooking3.setText(responseObject.getMessage());
                } else {
                    txtNoticeBooking3.setText("");
                    Intent intent = new Intent(BookingActivity3.this, CompletedActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

    private void mapping() {
        btnComplete = (Button) findViewById(R.id.btnCompleteBooking);
        imgBack = findViewById(R.id.imgBack);
        txt_location_s3 = findViewById(R.id.txt_location_s3);
        txtb3_room = findViewById(R.id.txtb3_room);
        txtHotel_s3 = findViewById(R.id.txtHotel_s3);
        txtFromTo = findViewById(R.id.txtFromTo);
        txtRoomType_s3 = findViewById(R.id.txtRoomType_s3);
        txtMoney = findViewById(R.id.txtmoney);
        imgb3_room = findViewById(R.id.imgb3_room);
        txtNoticeBooking3 = findViewById(R.id.txtNoticeBooking3);
    }
}