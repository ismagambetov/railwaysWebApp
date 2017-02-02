package com.epam.ism.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Restriction {
    @XmlElement
    private User admin;

    @XmlElement
    private User cashier;

    @XmlElement
    private User passenger;

    public User getAdmin() {
        return admin;
    }

    public User getCashier() {
        return cashier;
    }

    public User getPassenger() {
        return passenger;
    }
}
