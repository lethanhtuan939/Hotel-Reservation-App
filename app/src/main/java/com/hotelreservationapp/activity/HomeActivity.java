package com.hotelreservationapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.Constant;
import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Location;

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

public class HomeActivity extends AppCompatActivity {
    ArrayList<Hotel> list_hotels;
    ListView listView;
    HotelAdapter hotelAdapter;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listHotel);
        list_hotels = new ArrayList<>();
        new FetchData("").start();
        hotelAdapter = new HotelAdapter(HomeActivity.this, R.layout.hotel_item, list_hotels);
        listView.setAdapter(hotelAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                intent.putExtra("hotel", list_hotels.get(position));
                startActivity(intent);
            }
        });
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_home);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_home)
                    return true;
                else if (item.getItemId() == R.id.btn_setting) {
                    Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.btn_favourites) {
                    Intent intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        String[] items = {
                "Hà Nội",
                "Hồ Chí Minh",
                "Đà Nẵng",
                "Hải Phòng"
        };

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapter = new ArrayAdapter<>(this, R.layout.dropdow_item, items);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                new FetchData(item).start();
            }
        });
    }



    class FetchData extends Thread {
        private String keyword;
        String data = "";

        public FetchData(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public void run() {

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(HomeActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            try {
                URL url = new URL(Constant.PRE_FIX + "/hotels/?keyword=" + this.keyword);
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
                    list_hotels.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);

                        int id = object.getInt("id");
                        String name = object.getString("name");
                        String image = Constant.PRE_FIX + "/file/" + object.getString("image");
                        boolean active = object.getBoolean("active");
                        double acreage = object.getDouble("acreage");
                        double rating = object.getDouble("rating");
                        String locationName = object.getJSONObject("location").getString("name");
                        int idLocation = object.getJSONObject("location").getInt("id");

                        Location location = new Location(idLocation, locationName);
                        Hotel hotel1 = new Hotel(id, name, image, active, acreage, rating, location);

                        list_hotels.add(hotel1);
                        adapter.notifyDataSetChanged();
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
                }
            });
        }
    }

}