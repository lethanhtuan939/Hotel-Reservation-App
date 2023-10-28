package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.hotelreservationapp.R;

import java.util.Calendar;

public class BookingActivity1 extends AppCompatActivity {

    Button btnDateStart, btnDateEnd, btnNext;
    ImageView imgBack;
    EditText txtDateStart, txtDateEnd;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking1);

        mapping();

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
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity1.this, BookingActivity2.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity1.this, DetailActivity.class);
                startActivity(intent);
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
    }
}