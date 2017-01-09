package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BurgerMan on 1/5/2017.
 */

public class Consults implements Serializable {
    private String cid;
    private String pid;
    private String did;
    private Date dateOfConsult;
    private ArrayList<String> medicalReports;

    public Consults() {
    }

    public Consults(String cid, String pid, String did, Date dateOfConsultation) {
        this.cid = cid;
        this.pid = pid;
        this.did = did;
        this.dateOfConsult = (Date) dateOfConsultation.clone();
    }

    public String getPid() {
        return pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
        return (Date)dateOfConsult.clone();
    }

    public void setDateOfConsult(Date dateOfConsultation) {
        this.dateOfConsult = (Date)dateOfConsult.clone();
    }
}
