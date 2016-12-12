package com.epam.ism.entity;

import java.util.Date;

/**
 *
 */
public class Order extends IdEntity {

    private User passenger;
    private Trip trip;
    private Double travelCost;
    private Date departureDate;
    private int placeNumber;
    private Train train;
    private Carriage carriage;

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Double getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(Double travelCost) {
        this.travelCost = travelCost;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int place) {
        this.placeNumber = place;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "passenger=" + passenger +
                ", trip=" + trip +
                ", travelCost=" + travelCost +
                ", departureDate=" + departureDate +
                ", place='" + placeNumber + '\'' +
                '}';
    }
}
