package com.hotelreservationapp.model;

public class Room {
    private int id;
    private int floor;
    private int imgRoom;
    private int idHotel;
    private String state;
    private String roomType;

    public Room(int id, int floor, int imgRoom, int idHotel, String state, String roomType) {
        this.id = id;
        this.floor = floor;
        this.imgRoom = imgRoom;
        this.idHotel = idHotel;
        this.state = state;
        this.roomType = roomType;
    }

    public Room() {
    }
    public int getIdRoom() {
        return id;
    }

    public void setIdRoom(int idRoom) {
        this.id = idRoom;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getImgRoom() {
        return imgRoom;
    }

    public void setImgRoom(int imgRoom) {
        this.imgRoom = imgRoom;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
