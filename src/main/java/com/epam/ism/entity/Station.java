package com.epam.ism.entity;

public class Station extends IdEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Station[id=%d, name=%s]",getId(),name);
    }
}
