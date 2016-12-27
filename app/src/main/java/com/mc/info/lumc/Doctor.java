package com.mc.info.lumc;

import java.util.HashMap;

/**
 * Created by BurgerMan on 12/9/2016.
 */

//Burger

public class Doctor extends Person {
    private String specialty;
    private int experienceYears;

    public Doctor() {
        super();
        specialty="";
        experienceYears=0;
    }

    public Doctor(int id, String firstName, String lastName, String phone, String email, Address address, String specialty, int experienceYears) {
        super(id, firstName, lastName, phone, email, address);
        this.specialty = specialty;
        this.experienceYears = experienceYears;
    }

    public String getSpecialty() {
        return specialty;
    }
//burger
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(MyDatabaseHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(MyDatabaseHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(MyDatabaseHandler.COLUMN_SPECIALTY,getSpecialty());
        returnValue.put(MyDatabaseHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}
