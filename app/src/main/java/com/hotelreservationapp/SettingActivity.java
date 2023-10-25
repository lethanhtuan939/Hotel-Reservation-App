package com.hotelreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
    }
}