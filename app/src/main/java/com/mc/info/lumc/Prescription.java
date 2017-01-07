package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class Prescription implements Serializable {

    private int id;
    private List<Integer> medication,
                          examination,
                          precaution;

    public Prescription() {
    }

    public Prescription(int id) {
        this.id = id;
        this.medication = new ArrayList<>();
        this.examination = new ArrayList<>();
        this.precaution = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getMedication() {
        return new ArrayList<>(medication);
    }

    public void setMedication(List<Integer> medication) {
        this.medication = new ArrayList<>(medication);
    }

    public List<Integer> getExamination() {
        return new ArrayList<>(examination);
    }

    public void setExamination(List<Integer> examination) {
        this.examination = new ArrayList<>(examination);
    }

    public List<Integer> getPrecaution() {
        return new ArrayList<>(precaution);
    }

    public void setPrecaution(List<Integer> precaution) {
        this.precaution = new ArrayList<>(precaution);
    }
}
