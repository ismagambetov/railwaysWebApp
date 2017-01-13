package com.epam.ism.entity;

import java.util.List;

/**
 *
 */
public class Wagon extends IdEntity {
    private String name;
    private WagonCategory type;
    private List<Place> places;
    private static final int CARRIAGE_CAPACITY = 36;

    /**
     * Constructs places for This carriage.
     */
    public Wagon() {
        for (int i = 0; i < CARRIAGE_CAPACITY; i++) {
            Place place = new Place();
            place.setPlace(String.valueOf(++i));
            place.setBooked(false);
            places.add(place);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WagonCategory getType() {
        return type;
    }

    public void setType(WagonCategory type) {
        this.type = type;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
