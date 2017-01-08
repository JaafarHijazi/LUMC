package com.mc.info.lumc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by BurgerMan on 1/5/2017.
 */

public class Consult implements Serializable {
    private int id;
    private String pid;
    private String did;
    private Date dateOfConsultation;

    public Consult() {
    }

    public Consult(int id, String pid, String did, Date dateOfConsultation) {
        this.id = id;
        this.pid = pid;
        this.did = did;
        this.dateOfConsultation = (Date) dateOfConsultation.clone();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Date getDateOfConsultation() {
        return (Date)dateOfConsultation.clone();
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = (Date)dateOfConsultation.clone();
    }
}
