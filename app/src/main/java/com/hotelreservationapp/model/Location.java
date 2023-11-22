package com.hotelreservationapp.model;

import java.util.List;

public class Location {
    private int id;
    private String name;
    private List<User> users;
    private List<Hotel> hotels;

    public Location() {
    }

    public Location(int id, String name, List<User> users, List<Hotel> hotels) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.hotels = hotels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
