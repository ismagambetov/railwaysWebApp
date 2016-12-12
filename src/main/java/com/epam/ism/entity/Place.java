package com.epam.ism.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Place {
    private Train train;
    List<Carriage> carriageList = new ArrayList<>();

    private Carriage carriage;
    private int placeNumber;

    public Place(Carriage carriage, int placeNumber) {
        this.carriage = carriage;
        this.placeNumber = placeNumber;
    }



}
