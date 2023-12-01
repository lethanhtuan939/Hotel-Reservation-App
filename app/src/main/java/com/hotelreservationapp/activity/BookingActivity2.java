package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.utils.DateUtil;
import com.squareup.picasso.Picasso;

public class BookingActivity2 extends AppCompatActivity {

    Button btnNext;

    ImageView imgb2Room;
    TextView txtHotel, txtRoomType, txtRoom, txtCheckin, txtCheckout;
    TextView txtFrom, txtTo, txtRawPrice, txtTotal, txtCustomer, txtPhoneCustomer;
    RadioGroup rdMethod;
    ImageView imgBack;
    Reservation reservation;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        mapping();
        Intent intent = getIntent();
        reservation = (Reservation) intent.getSerializableExtra("reservation");
        txtHotel.setText(reservation.getRoom().getHotel().getName());
        txtRoom.setText("Phòng: " + reservation.getRoom().getName());
        Picasso.get().load(reservation.getRoom().getImgRoom()).into(imgb2Room);
        txtCheckin.setText(reservation.getDayStart());
        txtCheckout.setText(reservation.getDayEnd());
        txtFrom.setText(reservation.getDayStart());
        txtTo.setText(reservation.getDayEnd());
        txtRawPrice.setText(String.valueOf(reservation.getPrice()));
        txtTotal.setText(String.valueOf(reservation.getPrice()));

        sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");

        txtCustomer.setText(name);
        txtPhoneCustomer.setText(phoneNumber);

        rdMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdCash) {
                    reservation.setPaymentMethod("TRẢ BẰNG TIỀN MẶT");
                } else if (checkedId == R.id.rdCredit) {
                    reservation.setPaymentMethod("TRẢ BẰNG THẺ");
                } else {
                    reservation.setPaymentMethod("TRẢ BẰNG TIỀN MẶT");
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity2.this, BookingActivity3.class);
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

    private void mapping() {
        btnNext = (Button) findViewById(R.id.btnNextStep2);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgb2Room = findViewById(R.id.imageView11);
        txtHotel = findViewById(R.id.txtHotel_s2);
        txtRoom = findViewById(R.id.txtName_s2);
        txtRoomType = findViewById(R.id.txtRoomType);
        txtCheckout = findViewById(R.id.txtCheckout);
        txtCheckin = findViewById(R.id.txtCheckin);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        txtRawPrice = findViewById(R.id.txtRawPrice);
        txtTotal = findViewById(R.id.txtTotal);
        txtCustomer = findViewById(R.id.txtCustomer);
        txtPhoneCustomer = findViewById(R.id.txtPhoneCustomer);
        rdMethod = findViewById(R.id.grMethod);
    }
}