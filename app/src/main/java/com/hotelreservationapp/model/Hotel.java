package com.hotelreservationapp.model;

import java.io.Serializable;

public class Hotel implements Serializable {
    private int id;
    private String name;
    private String image;
    private boolean active;
    private double acreage;
    private double rating;
    private Location location;

    public Hotel() {
    }

    public Hotel(int id, String name, String image, boolean active, double acreage, double rating, Location location) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.active = active;
        this.acreage = acreage;
        this.rating = rating;
        this.location = location;
    }

    public Hotel(String name, String image,double rating) {
        this.name = name;
        this.image = image;
        this.rating = rating;
    }

    public Hotel(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }

    public double getAcreage() {
        return acreage;
    }

    public double getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", active=" + active +
                ", acreage=" + acreage +
                ", rating=" + rating +
                ", location=" + location +
                '}';
    }
}
