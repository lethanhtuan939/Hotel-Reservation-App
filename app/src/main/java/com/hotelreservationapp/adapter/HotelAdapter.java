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

import java.util.ArrayList;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    Activity activity;
    int layout;
    ArrayList<Hotel> hotels;

    public HotelAdapter(Activity activity, int layout, ArrayList<Hotel> hotels) {
        super(activity, layout, hotels);
        this.activity = activity;
        this.layout = layout;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout,null);
        Hotel hotel = hotels.get(position);
        ImageView imageView= convertView.findViewById(R.id.image);
        imageView.setImageResource(hotel.getImage());
        TextView textView_name = convertView.findViewById(R.id.name);
        textView_name.setText(hotel.getName());
        TextView textView_acreage = convertView.findViewById(R.id.acreage);
        textView_acreage.setText(String.valueOf(hotel.getAcreage()));
        TextView textView_rating = convertView.findViewById(R.id.rating);
        textView_rating.setText(String.valueOf(hotel.getRating()));
        return convertView;
    }
}
