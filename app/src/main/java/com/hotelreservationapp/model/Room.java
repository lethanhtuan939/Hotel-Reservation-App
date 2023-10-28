package com.hotelreservationapp.model;

public class Room {
    private int idRoom;
    private int floor;
    private int imgRoom;
    private int idHotel;
    private int idSale;
    private String roomType;

    public Room(int idRoom, int floor, int imgRoom) {
        this.idRoom = idRoom;
        this.floor = floor;
        this.imgRoom = imgRoom;
        this.idHotel = 1;
        this.idSale = 1;
        this.roomType = "";
    }
    public Room() {
    }
    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


}
