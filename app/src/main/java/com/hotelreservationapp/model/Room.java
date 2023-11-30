package com.hotelreservationapp.model;

import java.io.Serializable;

public class Room implements Serializable {

    private int id;
    private  String name;
    private int floor;
    private String imgRoom;
    private Hotel Hotel;
    private String state;
    private  double sale;
    private RoomType roomType;
    private  double price;


    public Room(int id, String name,int floor, String imgRoom, Hotel Hotel, String state, double sale, RoomType roomType, double price) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.imgRoom = imgRoom;
        this.Hotel = Hotel;
        this.state = state;
        this.sale = sale;
        this.roomType = roomType;
        this.price = price;
    }
    public Room() {
    }
    public double getSale() {
        return sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }


    public int getId() {
        return id;
    }

    public void setId(int idRoom) {
        this.id = idRoom;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getImgRoom() {
        return imgRoom;
    }

    public void setImgRoom(String imgRoom) {
        this.imgRoom = imgRoom;
    }

    public com.hotelreservationapp.model.Hotel getHotel() {
        return Hotel;
    }

    public void setHotel(com.hotelreservationapp.model.Hotel hotel) {
        Hotel = hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                ", imgRoom='" + imgRoom + '\'' +
                ", Hotel=" + Hotel +
                ", state='" + state + '\'' +
                ", sale=" + sale +
                ", roomType=" + roomType +
                ", price=" + price +
                '}';
    }
}
