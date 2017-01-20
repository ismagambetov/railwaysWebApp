package com.epam.ism.entity;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Wagon extends IdEntity {
    private int wagonNum;
    private WagonCategory wagonCategory;
    private int capacity;
    private Train train;
    private List<Place> places;

    public int getWagonNum() {
        return wagonNum;
    }

    public void setWagonNum(int wagonNum) {
        this.wagonNum = wagonNum;
    }

    public WagonCategory getWagonCategory() {
        return wagonCategory;
    }

    public void setWagonCategory(WagonCategory wagonCategory) {
        this.wagonCategory = wagonCategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces() {

        List<Place> places = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            Place place = new Place();
            place.setPlace(i+1);
            place.setBooked(false);

            places.add(place);
        }

        this.places = places;
    }
}
