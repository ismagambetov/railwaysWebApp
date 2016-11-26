package com.epam.ism.entity;

import java.util.Date;
import java.util.Objects;

public class Order {
    private long id;
    private Passenger passenger;
    private Date leaveDate;
    private Date arriveDate;
    private Train train;
    private String coach;
    private String place;
    private CoachClass coachClass;
    private double price;

    public Order(long id, Passenger passenger, Date leaveDate, Date arriveDate,
                 Train train, String coach, String place, CoachClass coachClass, double price) {
        this.id = id;
        this.passenger = passenger;
        this.leaveDate = leaveDate;
        this.arriveDate = arriveDate;
        this.train = train;
        this.coach = coach;
        this.place = place;
        this.coachClass = coachClass;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public CoachClass getCoachClass() {
        return coachClass;
    }

    public void setCoachClass(CoachClass coachClass) {
        this.coachClass = coachClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                Objects.equals(passenger, order.passenger) &&
                Objects.equals(leaveDate, order.leaveDate) &&
                Objects.equals(arriveDate, order.arriveDate) &&
                Objects.equals(train, order.train) &&
                Objects.equals(coach, order.coach) &&
                Objects.equals(place, order.place) &&
                coachClass == order.coachClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, leaveDate, arriveDate, train, coach, place, coachClass, price);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", leaveDate=" + leaveDate +
                ", arriveDate=" + arriveDate +
                ", train=" + train +
                ", coach='" + coach + '\'' +
                ", place='" + place + '\'' +
                ", coachClass=" + coachClass +
                ", price=" + price +
                '}';
    }
}
