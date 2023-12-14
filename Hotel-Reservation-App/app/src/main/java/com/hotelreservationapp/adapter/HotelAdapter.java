package com.hotelreservationapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hotelreservationapp.MainActivity;
import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    Activity activity;
    int layout;
    ArrayList<Hotel> hotels;
    int userId;

    public HotelAdapter(Activity activity, int layout, ArrayList<Hotel> hotels, int userId) {
        super(activity, layout, hotels);
        this.activity = activity;
        this.layout = layout;
        this.hotels = hotels;
        this.userId = userId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout,null);

        Hotel hotel = hotels.get(position);
        ImageView imageView = convertView.findViewById(R.id.image);
        Picasso.get().load(hotel.getImage()).into(imageView);
        TextView textView_name = convertView.findViewById(R.id.name);
        textView_name.setText(hotel.getName());
        TextView textView_acreage = convertView.findViewById(R.id.acreage);
        textView_acreage.setText(String.valueOf(hotel.getAcreage()));
        TextView textView_rating = convertView.findViewById(R.id.rating);
        textView_rating.setText(String.valueOf(hotel.getRating()));
        TextView textView_location = convertView.findViewById(R.id.txt_location);
        textView_location.setText(String.valueOf(hotel.getLocation().getName()));
        CheckBox btnFav = convertView.findViewById(R.id.favou);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnFav.isChecked()) {
                    addToFavorite(userId, hotel.getId());
                } else {
                    removeFromFavorite(userId, hotel.getId());
                }
            }
        });

        return convertView;
    }

    private void addToFavorite(int userId, int hotelId) {
        WebService.api.addFavourite(userId, hotelId).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject object = response.body();
                if(object.getCode() != 200) {
                    Toast.makeText(getContext(), object.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

    private void removeFromFavorite(int userId, int hotelId) {
        WebService.api.removeFavourite(userId, hotelId).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject object = response.body();
                if(object.getCode() != 200) {
                    Toast.makeText(getContext(), object.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}
