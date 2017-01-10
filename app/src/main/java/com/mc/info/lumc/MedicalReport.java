package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class MedicalReport implements Serializable {

    private String id,
                   notes;
    private List<Medication> medication;
    private List<Examination> examination;
    private List<Precaution> precaution;

    public MedicalReport() {
        this.id = "";
        this.notes = "";
        this.medication = new ArrayList<>();
        this.examination = new ArrayList<>();
        this.precaution = new ArrayList<>();
    }

    public MedicalReport(String id,String notes) {
        this.id = id;
        this.notes = notes;
        this.medication = new ArrayList<>();
        this.examination = new ArrayList<>();
        this.precaution = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Medication> getMedication() {
        return new ArrayList<>(medication);
    }

    public void setMedication(List<Medication> medication) {
        this.medication = new ArrayList<>(medication);
    }

    public List<Examination> getExamination() {
        return new ArrayList<>(examination);
    }

    public void setExamination(List<Examination> examination) {
        this.examination = new ArrayList<>(examination);
    }

    public List<Precaution> getPrecaution() {
        return new ArrayList<>(precaution);
    }

    public void setPrecaution(List<Precaution> precaution) {
        this.precaution = new ArrayList<>(precaution);
    }

    public void addMedication(Medication m){
        medication.add(m);
    }

    public void addPrecaution(Precaution p){
        precaution.add(p);
    }

    public void addExamination(Examination e){
        examination.add(e);
    }
}
