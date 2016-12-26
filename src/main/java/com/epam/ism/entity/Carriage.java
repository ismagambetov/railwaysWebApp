package com.epam.ism.entity;

import java.util.List;

/**
 *
 */
public class Carriage extends IdEntity {
    private String name;
    private CarriageType type;
    private List<Place> places;
    private static final int CARRIAGE_CAPACITY = 36;

    /**
     * Constructs places for This carriage.
     */
    public Carriage() {
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

    public CarriageType getType() {
        return type;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
