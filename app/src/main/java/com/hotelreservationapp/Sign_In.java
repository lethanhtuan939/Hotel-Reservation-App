package com.hotelreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sign_In extends AppCompatActivity {
    private Button btn_signin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signin = (Button) findViewById(R.id.btnsingin) ;
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Sign_In.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}