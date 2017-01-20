package com.epam.ism.entity;

public class Place extends IdEntity {
    private int num;
    private boolean booked;

    public int getPlace() {
        return num;
    }

    public void setPlace(int place) {
        this.num = place;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
