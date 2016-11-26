package com.epam.ism.entity;

import java.util.Date;

public class Administrator extends User {


    public Administrator(long id, String firstName, String lastName, Date birthday, int identificationCode, String email) {
        super(id, firstName, lastName, birthday, identificationCode, email);
    }


}
