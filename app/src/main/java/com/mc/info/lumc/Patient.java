package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient extends Person implements Serializable {

    private List<Integer> consults;
    private List<Medication> medication;

    public Patient() {
        super();
        consults = new ArrayList<>();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
        consults = new ArrayList<>();
        medication = new ArrayList<>();
    }

    public List<Integer> getConsults() {
        return new ArrayList<>(consults);
    }

    public void setConsults(List<Integer> consults) {
        this.consults = new ArrayList<>(consults);
    }

    public List<Medication> getMedication() {
        return new ArrayList<>(medication);
    }

    public void setMedication(List<Medication> medication) {
        this.medication = new ArrayList<>(medication);
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(DBHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(DBHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}