package com.mc.info.lumc;

import java.io.Serializable;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Patient extends Person implements Serializable {

    public Patient() {
        super();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
    }

}
