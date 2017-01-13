package com.epam.ism.entity;

/**
 *
 */
public class Route extends IdEntity {

    //Properties
    private Long order_id;
    private Course course;
    private Double cost1;// цена за купе.
    private Double cost2; // цена за место в плацкарт.
    private String departureTime; // departure time from Main station
    private String arrivalTime; //arrival time to final Main station
    private Train train;
    private int parkingTime;
    private boolean mainRoute;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

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

    @Override
    public String toString() {
        return "Route{" +
                "parkingTime=" + parkingTime+"}";
    }
}
