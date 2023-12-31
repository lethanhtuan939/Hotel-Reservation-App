package com.hotelreservationapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.hotelreservationapp.R;
import com.hotelreservationapp.adapter.ReservationAdapter;
import com.hotelreservationapp.callback.ReservationCallback;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.ReservationService;
import com.hotelreservationapp.service.WebService;
import com.hotelreservationapp.utils.Constant;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedFragment extends Fragment {
    List<Reservation> reservations;
    ListView currentList;
    SharedPreferences sharedPreferences;

    public CompletedFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        currentList = view.findViewById(R.id.completed_list);
        sharedPreferences = getActivity().getSharedPreferences("data_user", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", 2);

        fetchCompletedReservation(Constant.STATUS_COMPLETED, userId, new ReservationCallback() {
            @Override
            public void onReservationsFetched(List<Reservation> reservations) {
                ReservationAdapter reservationAdapter = new ReservationAdapter(getContext(), reservations);
                currentList.setAdapter(reservationAdapter);
            }
        });

        return view;
    }

    public void fetchCompletedReservation(String status, int userId, ReservationCallback callback) {
        WebService.api.getReservationsByStatusAndUser(userId, status).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(responseObject);

                try {
                    reservations = ReservationService.getReservationFromJson(json);
                    callback.onReservationsFetched(reservations);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
            }
        });
    }
}