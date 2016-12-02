package com.epam.ism.entity;

import java.io.Serializable;
import java.util.Objects;
/*
* This class represents the User model. This model class can be used throughout
* all layers, the data layer, the controller layer and the view layer.
*
* @author IDS
* */
public class User implements Serializable {

    //Constants
    private static final long serialVersionUID = 1L;

    //Properties
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * The User ID is unique for each User.
     * So this should compare User by ID only.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        User other = (User) otherObject;
        return Objects.equals(id, other.id);
    }

    /*
    * The user ID is unique for each User.
    * So User with same ID should return same hashcode.
    */
    @Override
    public int hashCode() {
        return Objects.hash(id, this.getClass().hashCode());
    }

    // Returns the String representation of this User.
    @Override
    public String toString() {
        return String.format("User[id=%d,firstname=%s,lastname=%s,email=%s]",
                id,firstName,lastName,email);
    }
}
