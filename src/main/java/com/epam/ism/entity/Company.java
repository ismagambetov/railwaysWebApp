package com.epam.ism.entity;

import java.util.Objects;

public class Company {
    private long id;
    private String name;
    private String identificationNumber;

    public Company(long id, String name, String identificationNumber) {
        this.id = id;
        this.name = name;
        this.identificationNumber = identificationNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(name, company.name) &&
                Objects.equals(identificationNumber, company.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identificationNumber);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                '}';
    }
}
