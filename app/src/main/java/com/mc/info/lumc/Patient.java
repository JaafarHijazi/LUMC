package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Patient extends Person implements Serializable {

    private List<Integer> consults;

    public Patient() {
        super();
        consults = new ArrayList<>();
    }

    public Patient(int id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
        consults = new ArrayList<>();
    }

    public List<Integer> getConsults() {
        return new ArrayList<>(consults);
    }

    public void setConsults(List<Integer> consults) {
        this.consults = new ArrayList<>(consults);
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(DBHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(DBHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}
///