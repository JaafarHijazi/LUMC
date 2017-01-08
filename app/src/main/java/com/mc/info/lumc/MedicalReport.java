package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedicalReport implements Serializable {
    private List<Integer> prescriptions;

    public MedicalReport() {
        prescriptions = new ArrayList<>();
    }

    public MedicalReport(List<Integer> prescriptions) {
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    public List<Integer> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public void setPrescriptions(List<Integer> prescriptions) {
        this.prescriptions = new ArrayList<>(prescriptions);
    }
}