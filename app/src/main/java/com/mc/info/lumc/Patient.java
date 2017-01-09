package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Patient extends Person implements Serializable {

    private List<String> consults;
    public Patient() {
        super();
        consults = new ArrayList<>();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
        consults = new ArrayList<>();
    }

    public List<String> getConsults() {
        return new ArrayList<>(consults);
    }

    public void setConsults(List<String> consults) {
        this.consults = new ArrayList<>(consults);
    }

    public void addConsult (Consults c){
        consults.add(c.getCid());
    }


    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(DBHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(DBHandler.COLUMN_ID,getId());
        returnValue.put(DBHandler.COLUMN_BUILDING,getAddress().getBuilding());
        returnValue.put(DBHandler.COLUMN_CITY,getAddress().getCity());
        returnValue.put(DBHandler.COLUMN_STREET,getAddress().getStreet());
        returnValue.put(DBHandler.COLUMN_PHONE,getPhone());
        returnValue.put(DBHandler.COLUMN_EMAIL,getEmail());
        return returnValue;
    }
}
