package com.mc.info.lumc;

import java.util.HashMap;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Patient extends Person {

    public Patient() {
        super();
    }

    public Patient(int id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(MyDatabaseHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(MyDatabaseHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(MyDatabaseHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}
