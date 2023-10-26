package com.hotelreservationapp.model;

public class Hotel {
    private int id;
    private String name;
    private  int image;
    private boolean active;
    private double acreage;
    private double rating;

    public Hotel() {
    }

    public Hotel(int id, String name, int image, boolean active, double acreage, double rating) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.active = active;
        this.acreage = acreage;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
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

    public void setImage(int image) {
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
}
