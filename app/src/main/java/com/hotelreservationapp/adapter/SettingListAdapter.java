package com.hotelreservationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.SettingList;

import java.util.ArrayList;

public class SettingListAdapter extends ArrayAdapter<SettingList> {
    public SettingListAdapter(@NonNull Context context, ArrayList<SettingList> dataArrayList) {
        super(context, R.layout.setting_list_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SettingList listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.setting_list_item, parent, false);
        }
        ImageView listImage = view.findViewById(R.id.setting_list_icon);
        TextView listName = view.findViewById(R.id.setting_list_title);
        TextView listTime = view.findViewById(R.id.setting_list_des);
        listImage.setImageResource(listData.getIcon());
        listName.setText(listData.getTitle());
        listTime.setText(listData.getSubDes());

        return view;
    }
}
