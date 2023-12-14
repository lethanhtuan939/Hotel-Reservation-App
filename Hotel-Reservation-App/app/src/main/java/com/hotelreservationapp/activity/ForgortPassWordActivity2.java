package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.R;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgortPassWordActivity2 extends AppCompatActivity {
    EditText edt_Email, edt_Code;
    TextView txt_mail, txt_verify;
    Button btn_Send, btn_Verify;
    ImageView back;
    int code = 0, otp;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgort_pass_word2);
        Mapping();
        btn_Verify.setActivated(false);

        //String email = edt_Email.getText().toString().trim();

        btn_Send.setEnabled(false);
        btn_Verify.setEnabled(false);

        edt_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn_Send.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_Code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn_Verify.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_Email.getText().toString().trim();
                if (Constant.isValidEmailId(email)) {
                    SendCode(email);

                } else {
                    txt_mail.setText("Nhập sai email ! Vui lòng nhập lại.");
                }
            }
        });
        btn_Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = Integer.parseInt(edt_Code.getText().toString().trim());
                VerifyOtp(otp);
            }
        });
    }


    private void SendCode(String email) {
        WebService.api.getCode(email).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                code = responseObject.getCode();
                if (code != 200) {
                    txt_mail.setText(responseObject.getMessage());
                }else {
                    txt_mail.setText("Vui lòng kiểm tra email!");
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });


    }



    private void VerifyOtp(int otpU) {
        WebService.api.Verify(otpU).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject object = response.body();
                if (object.getCode() != 200) {
                    txt_verify.setText(object.getMessage());
                } else {
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        edt_Email = (EditText) findViewById(R.id.edt_fg_Email);
        edt_Code = (EditText) findViewById(R.id.edt_fg_Code);
        txt_mail = findViewById(R.id.txt_noticeEmail);
        txt_verify = findViewById(R.id.txt_noticeCode);
        btn_Send = findViewById(R.id.btn_fg_SendCode);
        btn_Verify = findViewById(R.id.btn_fg_Verify);
        back = findViewById(R.id.imgBack);
    }
}