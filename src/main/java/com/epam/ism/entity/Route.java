package com.epam.ism.entity;

import java.util.Date;

/**
 *
 */
public class Route extends IdEntity {
    //Properties
    private Course course;
    private Double cost1;// цена за купе.
    private Double cost2; // цена за место в плацкарт.
    private Date departureDate; // departure date from Main station
    private Date arrivalDate; //arrival date to final Main station
    private String formattedDepartureDate;
    private String formattedArrivalDate;
    private Train train;
    private int parkingTime;
    private boolean mainRoute;
    private String travelTime;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getCost1() {
        return cost1;
    }

    public void setCost1(Double cost1) {
        this.cost1 = cost1;
    }

    public Double getCost2() {
        return cost2;
    }

    public void setCost2(Double cost2) {
        this.cost2 = cost2;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getFormattedDepartureDate() {
        return formattedDepartureDate;
    }

    public void setFormattedDepartureDate(String formattedDepartureDate) {
        this.formattedDepartureDate = formattedDepartureDate;
    }

    public String getFormattedArrivalDate() {
        return formattedArrivalDate;
    }

    public void setFormattedArrivalDate(String formattedArrivalDate) {
        this.formattedArrivalDate = formattedArrivalDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public boolean isMainRoute() {
        return mainRoute;
    }

    public void setMainRoute(boolean mainRoute) {
        this.mainRoute = mainRoute;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "course=" + course +
                ", cost1=" + cost1 +
                ", cost2=" + cost2 +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", train=" + train +
                ", parkingTime=" + parkingTime +
                ", mainRoute=" + mainRoute +
                '}';
    }
}
