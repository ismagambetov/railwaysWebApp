package com.epam.ism.entity;

import java.util.Objects;

/**
 * This abstract class represents entity with single property ID,
 * to prevent copy-paste and relate all entities.
 *
 * @author IDS.
 */
public abstract class IdEntity {
    //Property
    private Long id;

    //Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * The entity ID is unique for each User.
     * So this should compare entity by ID only.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        IdEntity other = (IdEntity) otherObject;
        return Objects.equals(id, other.id);
    }

    /*
    * The entity ID is unique for each entity.
    * So entity with same ID should return same hashcode.
    */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //// Returns the String representation of this entity.
    @Override
    public String toString() {
        return String.format("id = %d", id);
    }
}
