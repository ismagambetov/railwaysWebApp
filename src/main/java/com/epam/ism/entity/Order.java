package com.epam.ism.entity;

import java.util.Date;

/**
 *
 */
public class Order extends IdEntity {
    private Date departureDate;
    private User passenger;
    private Train train;
    private String carriage;
    private String placeNumber;
    private Double cost;

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String place) {
        this.placeNumber = place;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "passenger=" + passenger +
                ", travelCost=" + cost +
                ", departureDate=" + departureDate +
                ", place='" + placeNumber + '\'' +
                '}';
    }
}
