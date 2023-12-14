package com.hotelreservationapp.model;

import java.io.Serializable;

public class Room implements Serializable {

    private int id;
    private  String name;
    private int floor;
    private String imgRoom;
    private Hotel hotel;
    private String state;
    private  double sale;
    private RoomType roomType;
    private  double price;


    public Room(int id, String name,int floor, String imgRoom, Hotel hotel, String state, double sale, RoomType roomType, double price) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.imgRoom = imgRoom;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
                ", Hotel=" + hotel +
                ", state='" + state + '\'' +
                ", sale=" + sale +
                ", roomType=" + roomType +
                ", price=" + price +
                '}';
    }
}
