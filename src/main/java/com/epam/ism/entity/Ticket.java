package com.epam.ism.entity;

/**
 *
 */
public class Ticket extends IdEntity {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "order=" + order +
                '}';
    }
}
