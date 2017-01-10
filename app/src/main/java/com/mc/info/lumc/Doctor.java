package com.mc.info.lumc;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Doctor extends Person implements Serializable{
    private String specialty;
    private int experienceYears;
    private List<Consults> consults;

    public Doctor() {
        super();
        specialty="";
        experienceYears=0;
        consults = new ArrayList<>();
    }

    public Doctor(String id, String firstName, String lastName, String phone, String email, Address address, String specialty, int experienceYears) {
        super(id, firstName, lastName, phone, email, address);
        this.specialty = specialty;
        this.experienceYears = experienceYears;
        consults = new ArrayList<>();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = Integer.parseInt(experienceYears);
    }

    public List<Consults> getConsults() {
        return new ArrayList<>(consults);
    }

    public void setConsults(List<Consults> consults) {
        this.consults = new ArrayList<>(consults);
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(DBHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(DBHandler.COLUMN_SPECIALTY,getSpecialty());
        returnValue.put(DBHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}
