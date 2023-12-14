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
import com.squareup.picasso.Picasso;

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
        TextView name = convertView.findViewById(R.id.nRoom);
        name.setText("Phòng " + room.getName());
        ImageView imageView= convertView.findViewById(R.id.imgRoom);
        Picasso.get().load(room.getImgRoom()).into(imageView);
        TextView txt_floor = convertView.findViewById(R.id.txt_floor);
        txt_floor.setText(String.valueOf(room.getFloor()));
        TextView sale = convertView.findViewById(R.id.txt_sale);
        sale.setText(String.valueOf(room.getSale()));
        TextView status = convertView.findViewById(R.id.txt_status);
        status.setText(room.getState());
        TextView roomType = convertView.findViewById(R.id.roomType);
        roomType.setText(room.getRoomType().getName());
        TextView price = convertView.findViewById(R.id.txt_price);
        price.setText(String.valueOf(room.getPrice()));
        return convertView;
    }
}
