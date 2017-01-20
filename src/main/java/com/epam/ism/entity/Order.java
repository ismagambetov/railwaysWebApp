package com.epam.ism.entity;

import java.util.Date;

/**
 *
 */
public class Order extends IdEntity {
    private Train train;
    private Wagon wagon;
    private Date departureDate;
    private User passenger;
    private Station departureStation;
    private Station arrivalStation;
    private String departureTime;
    private String arrivalTime;
    private int placeNumber;
    private int cost;

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

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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
