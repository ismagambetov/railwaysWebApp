package com.epam.ism.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the Train model. This model can be used throughout
 * all layers, the data layer, the controller layer and the view layer.
 *
 * @author IDS.
 */
public class Train extends IdEntity {

    //Properties
    private String name;
    private MainRoute mainRoute;
    private List<Carriage> carriages = new ArrayList<>();


    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainRoute getMainRoute() {
        return mainRoute;
    }

    public void setMainRoute(MainRoute mainRoute) {
        this.mainRoute = mainRoute;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }



    /**
     * Returns true if the user ID and name are equal.
     * @param otherObject The object to be compared with current Train object.
     * @return True if the given object equals to current Train object.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Train other = (Train) otherObject;
        return Objects.equals(name, other.name);
    }

    /**
     * The Train with same ID and name should return same hashcode.
     * @return int type hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash( name);
    }

    /**
     * Returns the String representation of this Train.
     */
    @Override
    public String toString() {
        return String.format("Train[id=%d, name=%s]",getId(),name);
    }

}
