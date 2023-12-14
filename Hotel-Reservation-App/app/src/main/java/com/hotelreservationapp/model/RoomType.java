package com.hotelreservationapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomType implements Serializable {
    private int id;

    private String name;

    private List<Room> rooms = new ArrayList<>();

    public RoomType(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
