package com.epam.ism.entity;

import java.util.Objects;

public class Bill {
    private long id;
    private Order order;
    private Passenger client;
    private Company provider;

    public Bill(long id, Order order, Passenger client, Company provider) {
        this.id = id;
        this.order = order;
        this.client = client;
        this.provider = provider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Passenger getClient() {
        return client;
    }

    public void setClient(Passenger client) {
        this.client = client;
    }

    public Company getProvider() {
        return provider;
    }

    public void setProvider(Company provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                Objects.equals(order, bill.order) &&
                Objects.equals(client, bill.client) &&
                Objects.equals(provider, bill.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, client, provider);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", order=" + order +
                ", client=" + client +
                ", provider=" + provider +
                '}';
    }
}
