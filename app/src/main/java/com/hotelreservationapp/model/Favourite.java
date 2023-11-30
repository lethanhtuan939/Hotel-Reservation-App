package com.hotelreservationapp.model;

public class Favourite {
    private int id;
    private User user;
    private Hotel hotel;

    public Favourite(int id,User usr, Hotel hotel) {
        this.id = id;
        this.user = usr;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
