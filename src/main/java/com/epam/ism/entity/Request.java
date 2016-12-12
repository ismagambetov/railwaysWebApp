package com.epam.ism.entity;

import java.util.Date;

public class Request extends IdEntity {
    private User Passenger;
    private Date departureDate;
    private Station from;
    private Station to;

    //private String placeType; //купе или плацкарт

    public User getPassenger() {
        return Passenger;
    }

    public void setPassenger(User passenger) {
        Passenger = passenger;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Passenger's request{" +
                "Passenger=" + Passenger +
                ", departureDate=" + departureDate +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
