package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 07-Jan-17.
 */

public class Prescription implements Serializable{
    private String id;
    private List<String> medication,examination,precaution;

    public Prescription() {
        id ="";
        medication = new ArrayList<>();
        examination = new ArrayList<>();
        precaution = new ArrayList<>();
    }

    public Prescription(String id, List<String> medication, List<String> examination, List<String> precaution) {
        this.id = id;
        this.medication = new ArrayList<>(medication);
        this.examination = new ArrayList<>(examination);
        this.precaution = new ArrayList<>(precaution);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMedication() {
        return new ArrayList<>(medication);
    }

    public void setMedication(List<String> medication) {
        this.medication = new ArrayList<>(medication);
    }

    public List<String> getExamination() {
        return new ArrayList<>(examination);
    }

    public void setExamination(List<String> examination) {
        this.examination = new ArrayList<>(examination);
    }

    public List<String> getPrecaution() {
        return new ArrayList<>(precaution);
    }

    public void setPrecaution(List<String> precaution) {
        this.precaution = new ArrayList<>(precaution);
    }

    public void addMedication (Medication m){
        medication.add(m.getId());
    }
}
