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

    public List<String> getRestrictedActions(Role role) {

        List<String> restrictedActions = null;
        if (role.equals(Role.ADMINISTRATOR)) {
            restrictedActions = this.getAdmin().getPages();
        } else if (role.equals(Role.CASHIER)) {
            restrictedActions = this.getCashier().getPages();
        } else if (role.equals(Role.PASSENGER)) {
            restrictedActions = this.getPassenger().getPages();
        }
        return restrictedActions;
    }
}
