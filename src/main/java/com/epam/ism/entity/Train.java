package com.epam.ism.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the Train model. This model can be used throughout
 * all layers, the data layer, the controller layer and the view layer.
 *
 * @author IDS.
 */
public class Train implements Serializable {

    //Constants
    private static final long serialVersionUID = 1L;

    //Properties
    private Long id;
    private String name;

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return id == other.id &&
                Objects.equals(name, other.name);
    }

    /**
     * The Train with same ID and name should return same hashcode.
     * @return int type hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Returns the String representation of this Train.
     */
    @Override
    public String toString() {
        return String.format("Train[id = %d, name = %s]",id, name);
    }
}
