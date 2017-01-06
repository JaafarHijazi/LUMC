package com.mc.info.lumc;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BurgerMan on 1/5/2017.
 */

public class Consult implements Serializable {
    private int id;
    private int pid;
    private int did;
    private Date dateOfConsultation;

    public Consult() {
    }

    public Consult(int id, int pid, int did, Date dateOfConsultation) {
        this.id = id;
        this.pid = pid;
        this.did = did;
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
        return (Date)dateOfConsultation.clone();
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = (Date)dateOfConsultation.clone();
    }
}
