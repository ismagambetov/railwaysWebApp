package com.epam.ism.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class MainRoute extends IdEntity{

    //Properties
    private Station From; // From Main station
    private Station To; // To final Main station
    private Double interval; // the interval between two Main stations
    private Double priceForOpenSection;// цена за купе.
    private Double priceForCloseSection; // цена за место в плацкарт.
//    private Train train; // The Train that moves between two Main stations.
    private List<Route> routes = new ArrayList<>(); // Routes that are located between Main stations

    //Getters and setters
    public Station getFrom() {
        return From;
    }

    public void setFrom(Station from) {
        From = from;
    }

    public Station getTo() {
        return To;
    }

    public void setTo(Station to) {
        To = to;
    }

    public Double getInterval() {
        return interval;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
    }

    public Double getPriceForOpenSection() {
        return priceForOpenSection;
    }

    public void setPriceForOpenSection(Double priceForOpenSection) {
        this.priceForOpenSection = priceForOpenSection;
    }

    public Double getPriceForCloseSection() {
        return priceForCloseSection;
    }

    public void setPriceForCloseSection(Double priceForCloseSection) {
        this.priceForCloseSection = priceForCloseSection;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MainRoute that = (MainRoute) o;
        return Objects.equals(From, that.From) &&
                Objects.equals(To, that.To) &&
                Objects.equals(priceForOpenSection, that.priceForOpenSection) &&
                Objects.equals(priceForCloseSection, that.priceForCloseSection);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), From, To,
                priceForOpenSection, priceForCloseSection);
    }

    @Override
    public String toString() {
        return "\nMain route{" +
                "From=" + From +
                ", To=" + To +
                ", priceForOpenCompartment=" + priceForOpenSection +
                ", priceForCloseCompartment=" + priceForCloseSection +
                '}'+"\n";
    }
}
