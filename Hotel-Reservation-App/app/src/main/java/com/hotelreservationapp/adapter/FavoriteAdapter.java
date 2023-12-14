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

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Favourite;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAdapter extends ArrayAdapter<Favourite> {
    Activity activity;
    int layout;
    ArrayList<Favourite> favourites;

    public FavoriteAdapter(Activity activity, int layout, ArrayList<Favourite> hotels) {
        super(activity, layout, hotels);
        this.activity = activity;
        this.layout = layout;
        this.favourites = hotels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout, null);

        Favourite favourite = favourites.get(position);
        ImageView imageView = convertView.findViewById(R.id.image_favorite);
        Picasso.get().load(favourite.getHotel().getImage()).into(imageView);
        TextView textView_name = convertView.findViewById(R.id.name_favorite);
        textView_name.setText(favourite.getHotel().getName());
        TextView textView_rating = convertView.findViewById(R.id.f_rating);
        textView_rating.setText(favourite.getHotel().getRating() + "");


        CheckBox btnFav = convertView.findViewById(R.id.f_favourite);


        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeFromFavorite(favourite.getId());
                favourites.remove(position);

            }
        });

        return convertView;
    }

    private void removeFromFavorite(int id) {
        WebService.api.deleteByIdFavourite(id).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject object = response.body();
                if (object.getCode() != 200) {
                    Toast.makeText(getContext(), object.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

}
