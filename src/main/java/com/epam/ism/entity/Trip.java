package com.epam.ism.entity;

import java.util.Date;

/**
 *
 */
public class Trip extends IdEntity {

    private Station from;
    private Station to;
    private Date departureDate;
    private Date arrivalDate;

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

    @Override
    public String toString() {
        return "Trip{" +
                "from=" + from +
                ", to=" + to +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
