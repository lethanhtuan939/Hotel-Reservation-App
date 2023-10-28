package com.hotelreservationapp.adapter;

import android.app.Activity;
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
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomAdapter extends ArrayAdapter<Room> {
    Activity activity;
    int layout;
    ArrayList<Room> rooms;

    public RoomAdapter(Activity activity, int layout, ArrayList<Room> hotels) {
        super(activity, layout, hotels);
        this.activity = activity;
        this.layout = layout;
        this.rooms = hotels;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout,null);
        Room room = rooms.get(position);
        ImageView imageView= convertView.findViewById(R.id.imgRoom);
        imageView.setImageResource(room.getImgRoom());
        TextView txt_floor = convertView.findViewById(R.id.txt_floor);
        txt_floor.setText(String.valueOf(room.getFloor()));
        return convertView;
    }
}
