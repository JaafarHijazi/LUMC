package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient extends Person implements Serializable {

    private List<Consults> consults;
    private List<Medication> medication;
    private List<MedicalReport> medicalReport;

    public Patient() {
        super();
        consults = new ArrayList<>();
        medication = new ArrayList<>();
        medicalReport = new ArrayList<>();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address) {
        super(id, firstName, lastName, phone, email, address);
        consults = new ArrayList<>();
        medication = new ArrayList<>();
        medicalReport = new ArrayList<>();
    }

    public Patient(String id, String firstName, String lastName, String phone, String email, Address address, List<Consults> consults, List<Medication> medication, List<MedicalReport> medicalReport) {
        super(id, firstName, lastName, phone, email, address);
        this.consults = consults;
        this.medication = medication;
        this.medicalReport = medicalReport;
    }

    public List<Consults> getConsults() {
        return new ArrayList<>(consults);
    }

    public void setConsults(List<Consults> consults) {
        this.consults = new ArrayList<>(consults);
    }

    public List<Medication> getMedication() {
        return new ArrayList<>(medication);
    }

    public void setMedication(List<Medication> medication) {
        this.medication = new ArrayList<>(medication);
    }

    public List<MedicalReport> getMedicalReport() {
        return new ArrayList<>(medicalReport);
    }

    public void setMedicalReport(List<MedicalReport> medicalReport) {
        this.medicalReport = new ArrayList<>(medicalReport);
    }

    public void addConsultation(Consults c){
        consults.add(c);
    }

    public void addMedication(Medication m){
        medication.add(m);
    }

    public void addMedicalReport(MedicalReport mr){
        medicalReport.add(mr);
    }

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_FIRST_NAME,getFirstName());
        returnValue.put(DBHandler.COLUMN_LAST_NAME,getLastName());
        returnValue.put(DBHandler.COLUMN_ID,String.valueOf(getId()));
        return returnValue;
    }
}