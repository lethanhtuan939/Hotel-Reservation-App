package com.hotelreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hotelreservationapp.adapter.SettingListAdapter;
import com.hotelreservationapp.model.SettingList;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    SettingListAdapter settingListAdapter;
    ArrayList<SettingList> lists;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        lists = new ArrayList<>();
        int[] icons = {R.drawable.user_icon, R.drawable.history, R.drawable.lock};
        String[] titles = {"Profile Details", "History Reservations", "Privacy Policy"};
        String[] subDes = {"View & Edit details", "View your reservation history", ""};

        for(int i=0;i<icons.length;i++) {
            SettingList item = new SettingList(titles[i], subDes[i], icons[i]);
            lists.add(item);
        }

        settingListAdapter = new SettingListAdapter(SettingActivity.this, lists);

        listView = findViewById(R.id.list_item_setting);
        listView.setAdapter(settingListAdapter);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.btn_setting);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.btn_setting)
                    return true;
                else if (item.getItemId() == R.id.btn_favourites) {
                    Intent intent= new  Intent(getApplicationContext(),FavouriteActivity.class);
                    startActivity(intent);

                } else if (item.getItemId() == R.id.btn_home) {
                    Intent intent= new  Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}