package com.hotelreservationapp.model;

import android.os.Build;

import com.hotelreservationapp.utils.DateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class Reservation implements Serializable {
    private int id;

    private String bookingDate;

    private String dayStart;

    private String dayEnd;

    private double price;

    private String status;

    private String paymentMethod;

    private String notes;

    private User user;

    private Room room;

    public Reservation() {
    }

    public Reservation(int id, String bookingDate, String dayStart, String dayEnd, double price, String status, String paymentMethod, String notes, User user, Room room) {
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

    public Reservation(Room room, User user, String bookingDate, double price) {
        this.bookingDate = bookingDate;
        this.price = price;
        this.user = user;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDayStart() {
        return dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public void setDayEnd(String dayEnd) {
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", dayStart=" + dayStart +
                ", dayEnd=" + dayEnd +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", notes='" + notes + '\'' +
                ", user=" + user +
                ", room=" + room +
                '}';
    }

    public double totalPrice() throws ParseException {
        long diff = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate localStartDate = DateUtil.formatToDate(this.dayStart, "yyyy-MM-dd").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localEndDate = DateUtil.formatToDate(this.dayEnd, "yyyy-MM-dd").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Duration duration = Duration.between(localStartDate.atStartOfDay(), localEndDate.atStartOfDay());

            diff = duration.toDays();
        }

        this.price = Math.abs(diff) * ((1 - this.room.getSale()/100) * this.room.getPrice());

        return this.price;
    }
}
