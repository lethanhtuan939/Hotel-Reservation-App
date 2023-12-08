package com.hotelreservationapp.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Location;
import com.hotelreservationapp.model.Role;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.utils.RealPathUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    Button btnSaveChange;
    TextView txtChange;
    ImageView imgBack, avatar;
    EditText edtName, edtEmail, edtPassword, edtPhone;
    SharedPreferences sharedPreferences;
    User user;
    String password, accessToken;
    Uri uri;
    ProgressDialog progressDialog;

    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mapping();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        password = sharedPreferences.getString("password", "");
        accessToken = sharedPreferences.getString("accessToken", "");
        getUser(id);

        txtChange.setPaintFlags(txtChange.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                intent.putExtra("password", user.getPassword());
                startActivity(intent);
            }
        });
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChange();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            uri = data.getData();
            avatar.setImageURI(uri);
        }
    }

    private void mapping() {
        txtChange = findViewById(R.id.txtChange);
        btnSaveChange = findViewById(R.id.pf_btnSaveChange);
        imgBack = findViewById(R.id.imgBack);
        avatar = findViewById(R.id.pf_imageView);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.profile_edtPhone);
    }

    private void getUser(int id) {
        WebService.api.getUserById(id).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(response.body());
                user = getUserFromJson(jsonString);
                setInfo(user);
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });

    }

    private User getUserFromJson(String jsonString) {
        User user;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            int id = jsonObject.getJSONObject("data").getInt("id");
            String name = jsonObject.getJSONObject("data").getString("name");
            String email = jsonObject.getJSONObject("data").getString("email");
            String password = sharedPreferences.getString("password", "");
            Boolean enabled = jsonObject.getJSONObject("data").getBoolean("enabled");
            String image = Constant.PRE_FIX + "/file/" + jsonObject.getJSONObject("data").getString("image");

            String gender = jsonObject.getJSONObject("data").getString("gender");

            String phoneNumber = jsonObject.getJSONObject("data").getString("phoneNumber");
            String nameLocation = jsonObject.getJSONObject("data").getJSONObject("location").getString("name");
            int idLocation = jsonObject.getJSONObject("data").getJSONObject("location").getInt("id");
            Location location = new Location(idLocation, nameLocation);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("roles");
            Set<Role> roles = new HashSet<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                roles.add(new Role(object.getInt("id"), object.getString("name")));
            }
            user = new User(id, name, email, password, enabled, image, gender, phoneNumber, location, roles);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void setInfo(User user) {
        Picasso.get().load(user.getImage()).into(avatar);
        edtPhone.setText(user.getPhoneNumber());
        edtPassword.setText(password);
        edtEmail.setText(user.getEmail());
        edtName.setText(user.getName());
    }

    private void saveChange() {
        String phone = edtPhone.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setPassword(password);

        Gson gson = new Gson();
        String userJson = gson.toJson(user);

        if(uri == null) {
            updateUser(user.getId(), userJson);
        } else {
            updateUserWithFile(user.getId(), userJson, uri);
        }

    }

    private void updateUser(int id, String user) {
        progressDialog.show();
        WebService.api.updateUser(id, user).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                if (responseObject.getCode() != 200) {
                    Toast.makeText(ProfileActivity.this, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Đã cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    String userJson = gson.toJson(responseObject);
                    setInfo(getUserFromJson(userJson));
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

    private void updateUserWithFile(int id, String user, Uri uri) {
        progressDialog.show();

        RequestBody requestBodyUser = RequestBody.create(MediaType.parse("multipart/form-data"), user);
        String realPathFile = RealPathUtil.getRealPath(this, uri);
        File file = new File(realPathFile);
        RequestBody requestBodyFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part avt = MultipartBody.Part.createFormData("file", file.getName(), requestBodyFile);
        WebService.api.updateUserWithFile(id, requestBodyUser, avt).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                if (responseObject.getCode() != 200) {
                    Toast.makeText(ProfileActivity.this, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Đã cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    String userJson = gson.toJson(responseObject);
                    setInfo(getUserFromJson(userJson));
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}