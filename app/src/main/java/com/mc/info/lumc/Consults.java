package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Consults implements Serializable {
    private String id,
                   pid,
                   did;
    private Date dateOfConsultation;
    private List<MedicalReport> medicalReports;

    public Consults() {
        this.id = "";
        this.did = "";
        this.pid = "";
        this.dateOfConsultation = new Date();
        this.medicalReports = new ArrayList<>();
    }

    public Consults(String id, String did, String pid, Date dateOfConsultation) {
        this.id = id;
        this.did = did;
        this.pid = pid;
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
        this.medicalReports = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public List<MedicalReport> getMedicalReports() {
        return new ArrayList<>(medicalReports);
    }

    public void setMedicalReports(List<MedicalReport> medicalReports) {
        this.medicalReports = new ArrayList<>(medicalReports);
    }

    public Date getDateOfConsultation() {
        return (Date) dateOfConsultation.clone();
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
    }
}
