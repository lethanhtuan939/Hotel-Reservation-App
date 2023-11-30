package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.utils.DateUtil;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class BookingActivity1 extends AppCompatActivity {

    Button btnDateStart, btnDateEnd, btnNext;
    ImageView imgBack, room;
    TextView txtHotel, txtPrice, txtRoom;
    EditText txtDateStart, txtDateEnd, edtNote;
    private int mYear, mMonth, mDay;
    Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking1);
        mapping();
        Intent intent = getIntent();
        reservation = (Reservation) intent.getSerializableExtra("reservation");
        txtHotel.setText(reservation.getRoom().getHotel().getName());
        txtPrice.setText(String.valueOf(reservation.getRoom().getPrice()));
        txtRoom.setText("Phòng: " + reservation.getRoom().getName());
        Picasso.get().load(reservation.getRoom().getImgRoom()).into(room);

        btnDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity1.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txtDateStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);
                                Date selectedDate = selectedCalendar.getTime();
                                String dateString = DateUtil.formatToString(selectedDate, "yyyy-MM-dd");

                                try {
                                    reservation.setDayStart(DateUtil.formatToDate(dateString, "yyyy-MM-dd"));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity1.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txtDateEnd.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);
                                Date selectedDate = selectedCalendar.getTime();
                                String dateString = DateUtil.formatToString(selectedDate, "yyyy-MM-dd");

                                try {
                                    reservation.setDayEnd(DateUtil.formatToDate(dateString, "yyyy-MM-dd"));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }

                                reservation.totalPrice();
                                txtPrice.setText(String.valueOf(reservation.getPrice()));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        edtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reservation.setNotes(edtNote.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity1.this, BookingActivity2.class);
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
        btnDateStart = (Button) findViewById(R.id.btnDateStart);
        btnDateEnd = (Button) findViewById(R.id.btnDateEnd);
        txtDateStart = findViewById(R.id.txtDateStart);
        txtDateEnd = findViewById(R.id.txtDateEnd);
        btnNext = (Button) findViewById(R.id.btnCompleteBooking);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        room = (ImageView) findViewById(R.id.imgb1_room);
        txtHotel = (TextView) findViewById(R.id.txtHotel_s3);
        txtPrice = (TextView) findViewById(R.id.b1_price);
        txtRoom = (TextView) findViewById(R.id.txtb3_room);
        edtNote = findViewById(R.id.edtNote);
    }
}