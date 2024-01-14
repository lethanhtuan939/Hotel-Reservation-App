package com.hotelreservationapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ListView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.R;

import com.hotelreservationapp.adapter.FavoriteAdapter;
import com.hotelreservationapp.model.Favourite;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    ArrayList<Favourite> list_favorite;
    ListView listView_faVou;
    FavoriteAdapter favouriteAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        listView_faVou = findViewById(R.id.listFavourHotel);
        list_favorite = new ArrayList<>();
        user = getUser();
        new FetchData(user.getId()).start();
        favouriteAdapter = new FavoriteAdapter(FavouriteActivity.this, R.layout.item_favourites_custom, list_favorite);
        listView_faVou.setAdapter(favouriteAdapter);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_favourites);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_favourites)
                    return true;
                else if (item.getItemId() == R.id.btn_home) {
                    finish();
                } else if (item.getItemId() == R.id.btn_setting) {
                    Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                    finish();
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    class FetchData extends Thread {

        String data = "";
        private int userId;

        public FetchData(int userId) {
            this.userId = userId;
        }

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(FavouriteActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            try {
                URL url = new URL(Constant.PRE_FIX + "/favorite?user=" + this.userId);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    data += line;
                }

                bufferedReader.close();
                if (!data.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray response = jsonObject.getJSONArray("data");
                    list_favorite.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);

                        int id = object.getInt("id");

                        String image = Constant.PRE_FIX + "/file/" + object.getJSONObject("hotel").getString("image");
                        String name = object.getJSONObject("hotel").getString("name");
                        double rating = object.getJSONObject("hotel").getDouble("rating");


                        Favourite favourite = new Favourite(id, user, new Hotel(name, image, rating));

                        list_favorite.add(favourite);
//                        favouriteAdapter.notifyDataSetChanged();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    favouriteAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private User getUser() {
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user_favorite");
        return user;
    }
}