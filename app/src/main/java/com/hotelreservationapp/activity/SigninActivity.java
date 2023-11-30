package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.request.AuthResponse;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.request.UserRequest;
import com.hotelreservationapp.service.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {
    private ImageView imgBack;
    private TextView forgotPassword, txtSignup, result;
    private EditText edt_email, edt_pass;
    private Button btn_loGin;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    private String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        map();
        sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        String user_text = sharedPreferences.getString("email", "");
        String pass_text = sharedPreferences.getString("password", "");

        edt_email.setText(user_text);
        edt_pass.setText(pass_text);

        btn_loGin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_email.getText().toString().trim();
                pass = edt_pass.getText().toString().trim();
                boolean isValid = false;
                if (email.equals(null) && pass.equals(null)) {
                    result.setText("Vui lòng điền thông tin đăng nhập");
                    isValid = false;
                } else {
                    result.setText("");
                    isValid = true;
                }
                if (!Constant.isValidEmailId(email)) {
                    result.setText("Email nhập chưa đúng định dạng!");
                    edt_pass.setText("");
                    isValid = false;
                } else {
                    result.setText("");
                    isValid = true;
                }
                if (pass == null) {
                    result.setText("Vui lòng nhập mật khẩu");
                    isValid = false;
                } else {
                    result.setText("");
                    isValid = true;
                }
                if (email == null) {
                    result.setText("Vui lòng nhập email");
                    isValid = false;
                } else {
                    result.setText("");
                    isValid = true;
                }
                if (isValid) {
                    UserRequest userRequest = new UserRequest(email, pass);
                    login(userRequest);
                }
            }
        });


        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgortPassWordActivity2.class);
                startActivity(intent);
                finish();
            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void map() {
        imgBack = findViewById(R.id.imgBack);
        forgotPassword = findViewById(R.id.forgot);
        txtSignup = findViewById(R.id.txtSignin);
        btn_loGin = (Button) findViewById(R.id.btnSingin);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        edt_email = (EditText) findViewById(R.id.user);
        edt_pass = (EditText) findViewById(R.id.password);
        result = findViewById(R.id.result);
    }

    private void login(UserRequest user) {

        WebService.api.login(user).enqueue(new Callback<AuthResponse>() {

            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if (authResponse != null) {
                    if (authResponse.getCode() == 200) {
                        result.setText(authResponse.getMessage());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        if (checkBox.isChecked()) {
                            editor.putString("email", user.getEmail());
                            editor.putString("password", user.getPassword());

                            editor.commit();
                        }
                        editor.putString("accessToken", "Bearer " + authResponse.getAccessToken());
                        getUserByEmail(user.getEmail(), user.getPassword());
                    } else {
                        result.setText(authResponse.getMessage());
                    }
                } else
                    result.setText("error");
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                result.setText("error");
            }
        });
    }

    private void getUserByEmail(String email, String password) {
        WebService.api.getUser(email).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    JSONObject jsonObject = new JSONObject(json);
                    String image = Constant.PRE_FIX + "/file/" + jsonObject.getJSONObject("data").getString("image");
                    int id = jsonObject.getJSONObject("data").getInt("id");
                    String phoneNumber = jsonObject.getJSONObject("data").getString("phoneNumber");
                    String name = jsonObject.getJSONObject("data").getString("name");

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    User user = new User(id, name, email, password, image, phoneNumber);
                    intent.putExtra("user", user);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("id", user.getId());
                    editor.putString("name", user.getName());
                    editor.putString("phoneNumber", user.getPhoneNumber());
                    editor.putString("image", user.getImage());
                    editor.putString("password", user.getPassword());
                    editor.commit();
                    startActivity(intent);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}