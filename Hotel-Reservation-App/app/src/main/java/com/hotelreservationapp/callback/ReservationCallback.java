package com.hotelreservationapp.callback;

import com.hotelreservationapp.model.Reservation;

import java.util.List;

public interface ReservationCallback {
    void onReservationsFetched(List<Reservation> reservations);
}
