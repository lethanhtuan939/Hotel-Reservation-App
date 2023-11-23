package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hotelreservationapp.Constant;
import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.adapter.RoomAdapter;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Location;
import com.hotelreservationapp.model.Room;
import com.hotelreservationapp.model.RoomType;

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
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    ArrayList<Room> list_room;
    ListView listView;
    String hName = "";
    RoomAdapter roomAdapter;
    TextView txt_hName, txt_count;
    ImageButton back;


    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mapping();
        list_room = new ArrayList<>();
        Intent intent = getIntent();
        Hotel hotel = (Hotel) intent.getSerializableExtra("hotel");
        new FetchRoom(hotel.getId()).start();
        roomAdapter = new RoomAdapter(RoomActivity.this, R.layout.room_item, list_room);
        listView.setAdapter(roomAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_hName.setText(hotel.getName());
        txt_count.setText(String.valueOf(list_room.size()) + " rooms");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), DetailActivity.class);
                intent1.putExtra("room", list_room.get(position));
                startActivity(intent1);
            }
        });
    }

    private void mapping() {
        txt_count = findViewById(R.id.txt_room);
        txt_hName = findViewById(R.id.txt_name_hotel);
        listView = findViewById(R.id.listRoom);
        back = findViewById(R.id.imgBack);
    }

    class FetchRoom extends Thread {
        String data = "";

        private int hid;

        private FetchRoom(int hid) {
            this.hid = hid;
        }

        @Override
        public void run() {

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(RoomActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            try {
                URL url = new URL(Constant.PRE_FIX + "/hotels/" + this.hid + "/room");
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
                    list_room.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        int floor = object.getInt("floor");
                        String image = Constant.PRE_FIX + "/file/" + object.getString("image");
                        double price = object.getDouble("price");
                        String state = object.getString("state");
                        double sale = object.getDouble("sale");
                        //roomtype
                        int idRoomType = object.getJSONObject("roomType").getInt("id");
                        String nRoomType = object.getJSONObject("roomType").getString("name");
                        RoomType roomType = new RoomType(idRoomType, nRoomType);
                        //hotel
                        hName = object.getJSONObject("hotel").getString("name");
                        String locationName = object.getJSONObject("hotel").getJSONObject("location").getString("name");
                        int idLocation = object.getJSONObject("hotel").getJSONObject("location").getInt("id");

                        Location location = new Location(idLocation, locationName);
                        Hotel hotel = new Hotel(this.hid, hName, location);
                        Room room = new Room(id, name, floor, image, hotel, state, sale, roomType, price);
                        list_room.add(room);
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

                    roomAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}