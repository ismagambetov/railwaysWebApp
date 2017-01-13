package com.epam.ism.entity;

public class Course extends IdEntity {
    private Station departureStation; // From Main station
    private Station arrivalStation; // To  final Main station
    private Double interval; // the interval between two Main stations

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

    public Double getInterval() {
        return interval;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
    }
}
