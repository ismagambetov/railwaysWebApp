package com.epam.ism.entity;

/**
 *
 */
public class Route extends MainRoute {

    private int parkingTime;

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "parkingTime='" + parkingTime + '\'' +
                '}';
    }


}
