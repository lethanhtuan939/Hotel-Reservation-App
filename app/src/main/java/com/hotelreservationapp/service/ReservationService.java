package com.hotelreservationapp.service;

import com.google.gson.Gson;
import com.hotelreservationapp.model.Hotel;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.model.Room;
import com.hotelreservationapp.model.RoomType;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.request.ResponseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationService {

    public static List<Reservation> getReservationFromJson(String data) throws JSONException {
        List<Reservation> reservations = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(data);
        JSONArray response = jsonObject.getJSONArray("data");

        for (int i = 0; i < response.length(); i++) {
            JSONObject object = response.getJSONObject(i);

            int id = object.getInt("id");
            String bookingDay = object.getString("bookingDate");
            String dayStart = object.getString("dayStart");
            String dayEnd = object.getString("dayEnd");
            double price = object.getDouble("price");
            String paymentMethod = object.getString("paymentMethod");
            String status = object.getString("status");
//            String notes = object.getString("notes");
            Hotel hotel = new Hotel();
            int hotelId = object.getJSONObject("room").getJSONObject("hotel").getInt("id");
            String hotelName = object.getJSONObject("room").getJSONObject("hotel").getString("name");
            hotel.setId(hotelId);
            hotel.setName(hotelName);

            Room room = new Room();
            int roomId = object.getJSONObject("room").getInt("id");
            String roomName = object.getJSONObject("room").getString("name");
            String roomImage = object.getJSONObject("room").getString("image");

            String roomType = object.getJSONObject("room").getJSONObject("roomType").getString("name");
            int roomTypeId = object.getJSONObject("room").getJSONObject("roomType").getInt("id");
            RoomType type = new RoomType(roomTypeId, roomType);

            room.setRoomType(type);
            room.setId(roomId);
            room.setName(roomName);
            room.setImgRoom(roomImage);
            room.setHotel(hotel);

            User user = new User();
            String name = object.getJSONObject("user").getString("name");
            String email = object.getJSONObject("user").getString("email");
            user.setName(name);
            user.setEmail(email);

            Reservation reservation = new Reservation(id, bookingDay, dayStart, dayEnd, price, status, paymentMethod, "", user, room);

            reservations.add(reservation);
        }
        return reservations;
    }
}
