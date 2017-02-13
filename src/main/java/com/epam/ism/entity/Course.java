package com.epam.ism.entity;

public class Course extends IdEntity {
    private Station departureStation; // From station
    private Station arrivalStation; // To  final station
    private int distance; // the interval between two stations

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Course{" +
                "departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", distance=" + distance +
                '}';
    }
}
