package com.hotelreservationapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotelreservationapp.model.User;
import com.hotelreservationapp.utils.Constant;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.request.AuthResponse;
import com.hotelreservationapp.request.ChangePasswordRequest;
import com.hotelreservationapp.request.RegisterRequest;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.request.UserRequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    WebService api = new Retrofit.Builder()
            .baseUrl(Constant.PRE_FIX + "/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService.class);

    @POST("auth/login")
    Call<AuthResponse> login(@Body UserRequest user);

    @GET("users/")
    Call<ResponseObject> getUser(@Query("e") String email);

    @POST("auth/change-password")
    Call<AuthResponse> changePassword(@Body ChangePasswordRequest request);

    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);

    @GET("auth/verify")
    Call<ResponseObject> getCode(@Query("email") String email);

    @POST("auth/verify")
    Call<ResponseObject> Verify(@Query("otp") int otp);

    @POST("reservation")
    Call<ResponseObject> reservation(@Body Reservation reservation);

    @POST("favorite/add")
    Call<ResponseObject> addFavourite(@Query("user") int userId, @Query("hotel") int hotelId);

    @POST("favorite/remove")
    Call<ResponseObject> removeFavourite(@Query("user") int userId, @Query("hotel") int hotelId);

    @POST("favorite/delete")
    Call<ResponseObject> deleteByIdFavourite(@Query("id") int id);

    @PUT("users/{id}")
    Call<ResponseObject> updateUser(@Path("id") int id, @Query("data") String user);

    @PUT("users/{id}")
    Call<ResponseObject> updateUserWithFile(@Path("id") int id, @Query("data") String user);

    @GET("users/{id}")
    Call<ResponseObject> getUserById(@Path("id") int id);

    @GET("reservation/{user}")
    Call<ResponseObject> getReservationsByStatusAndUser(@Path("user") int id, @Query("status") String status);

    @GET("reservation/{user}/current")
    Call<ResponseObject> getReservationsByStatusAndUser(@Path("user") int id);

    @POST("reservation/state")
    Call<ResponseObject> changeStatusReservation(@Query("id") int id, @Query("state") String state);
}
