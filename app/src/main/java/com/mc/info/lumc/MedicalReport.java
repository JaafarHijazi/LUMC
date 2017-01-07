package com.mc.info.lumc;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedicalReport implements Serializable{
    private int id;
    private List<Integer> examinations,
                          prescriptions;

    public MedicalReport() {
    }

    public MedicalReport(int id) {
        this.id = id;
        this.examinations = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public List<Integer> getExaminations() {
        return new ArrayList<>(examinations);
    }

    public void setExaminations(List<Integer> examinations) {
        this.examinations = new ArrayList<>(examinations);
    }

    public List<Integer> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public void setPrescriptions(List<Integer> prescriptions) {
        this.prescriptions = new ArrayList<>(prescriptions);
    }
}
