package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consults implements Serializable {
    private int id,
                pid,
                did;
    private Date dateOfConsultation;
    private List<Integer> medicalReports;

    public Consults() {
    }

    public Consults(int did, int pid, int id, Date dateOfConsultation) {
        this.did = did;
        this.pid = pid;
        this.id = id;
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
        this.medicalReports = new ArrayList<>();
    }

    public List<Integer> getMedicalReports() {
        return new ArrayList<>(medicalReports);
    }

    public void setMedicalReports(List<Integer> medicalReports) {
        this.medicalReports = new ArrayList<>(medicalReports);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public Date getDateOfConsultation() {
        return (Date) dateOfConsultation.clone();
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
    }
}
