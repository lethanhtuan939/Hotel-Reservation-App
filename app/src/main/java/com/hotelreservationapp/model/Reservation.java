package com.hotelreservationapp.model;

import java.util.Date;

public class Reservation {
    private int id;

    private Date bookingDate;

    private Date dayStart;

    private Date dayEnd;

    private double price;

    private String status;

    private String paymentMethod;

    private String notes;

    private User user;

    private Room room;

    public Reservation() {
    }

    public Reservation(int id, Date bookingDate, Date dayStart, Date dayEnd, double price, String status, String paymentMethod, String notes, User user, Room room) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.price = price;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.notes = notes;
        this.user = user;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public Date getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
