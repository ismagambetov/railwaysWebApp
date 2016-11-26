package com.epam.ism.entity;

import java.util.Date;
import java.util.Objects;

public class Passenger extends User {
    private Order order;
    private int identificationCode;
    private Date birthday;

    public Passenger(long id, String firstName, String lastName, Date birthday, int identificationCode, String email) {
        super(id, firstName, lastName, birthday, identificationCode, email);
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Passenger passenger = (Passenger) o;
        return identificationCode == passenger.identificationCode &&
                Objects.equals(order, passenger.order) &&
                Objects.equals(birthday, passenger.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), order, identificationCode, birthday);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "order=" + order +
                ", identificationCode=" + identificationCode +
                ", birthday=" + birthday +
                '}';
    }
}
