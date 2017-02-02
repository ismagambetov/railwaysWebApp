package com.epam.ism.entity;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/*
* This class represents the User model. This model class can be used throughout
* all layers, the data layer, the controller layer and the view layer.
*
* @author IDS
* */
public class User extends IdEntity implements Serializable {

    //Constants
    private static final long serialVersionUID = 1L;

    //Properties
    private String username;
    private String firstName;
    private String lastName;
    private String personalCode;
    private Date birthday;
    private String password;
    private String email;
    private Role role;

    @XmlElement(name="page")
    private List<String> pages;

    //Getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getPages() {
        return pages;
    }

    /*
        * The User personalCode is unique for each User.
        * So this should compare User by personalCode only.
        */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(personalCode, user.personalCode);
    }

    /*
    * The User personalCode is unique for each User.
    * So User with same personalCode should return same hashcode.
    */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personalCode);
    }

    // Returns the String representation of this User.
    @Override
    public String toString() {
        return String.format("User[firstname=%s,lastname=%s,email=%s,personalcode=%s," +
                "birthday=%s,role=%s]",firstName,lastName,email,personalCode,birthday,role);
    }

}
