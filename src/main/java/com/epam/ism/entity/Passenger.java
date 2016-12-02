package com.epam.ism.entity;

import java.util.Date;
import java.util.Objects;

/**
 * This class represents the Passenger model which extends by the User model.
 * This model can be used throughout all layers, the data layer, the controller layer and the view layer.
 *
 * @author IDS.
 */
public class Passenger extends User {
    //Constants
    private static final long serialVersionUID = 1L;

    //Properties
    private Order order;
    private int identificationCode;
    private Date birthday;

    //Getters and setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(int identificationCode) {
        this.identificationCode = identificationCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Returns true if the passenger identificationCode is equal.
     * @param otherObject The object to be compared with current passenger object.
     * @return True if two object are same.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        if (!super.equals(otherObject)) return false;
        Passenger other = (Passenger) otherObject;
        return identificationCode == other.identificationCode;
    }

    /**
     * The passenger with same id and identificationCode should return same hashcode.
     * @return int type hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identificationCode);
    }

    /**
     * Returns string representation of this Passenger.
     */
    @Override
    public String toString() {
        return String.format("Passenger[identification = %d, birthday = %s, orderNumber = %d]",
                identificationCode, birthday, order.getId());
    }
}
