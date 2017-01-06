package com.mc.info.lumc;

import java.io.Serializable;
import java.util.Date;



public class Consults implements Serializable {
    private int id,
                pid,
                did;
    private Date dateOfConsultation;

    public Consults() {
    }

    public Consults(int did, int pid, int id, Date dateOfConsultation) {
        this.did = did;
        this.pid = pid;
        this.id = id;
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
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
