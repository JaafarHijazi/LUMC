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
    private List<Medication> patientMedicines;
    public Patient() {
        super();
        consults = new ArrayList<>();
        patientMedicines = new ArrayList<>();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
        consults = new ArrayList<>();
        patientMedicines = new ArrayList<>();
    }

    public List<Medication> getPatientMedicines() {
        return new ArrayList<>(patientMedicines);
    }

    public void setPatientMedicines(List<Medication> patientMedicines) {
        this.patientMedicines = new ArrayList<>(patientMedicines);
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

}
