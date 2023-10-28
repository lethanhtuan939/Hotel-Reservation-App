package com.hotelreservationapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.HotelAdapter;
import com.hotelreservationapp.model.Hotel;

import java.util.ArrayList;

public class CurrentFragment extends Fragment {

    int[] id = {
            1, 2, 3
    };
    int[] image = {
            R.drawable.ex_hotel, R.drawable.ex_hotel, R.drawable.ex_hotel
    };
    String[] name = {
            "Hotel Da Nang", "Hotel Da Nang", "Hotel Da Nang"
    };
    double[] acreage = {
            3, 3, 3
    };
    double[] rating = {
            5, 5 , 5
    };

    ArrayList<Hotel> list_hotels;

    public CurrentFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);

//        list_hotels = new ArrayList<>();
//        for (int i = 0; i < name.length; i++) {
//            list_hotels.add(new Hotel(id[i], name[i], image[i], true, acreage[i], rating[i]));
//        }
//        ListView listView = view.findViewById(R.id.current_list);
//        listView.setAdapter(new CurrentAdapter(getContext(), R.layout.item_favourites_custom, list_hotels));

        return view;
    }

    class CurrentAdapter extends ArrayAdapter {

        public CurrentAdapter(@NonNull Context context, int resource, ArrayList<Hotel> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = ((FragmentActivity) getContext()).getLayoutInflater().inflate(R.layout.fragment_current, null);

            Hotel hotel = list_hotels.get(position);
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
}