package com.mc.info.lumc;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class Medication implements Serializable {

    private String id;
    private String Medname;

    public Medication() {
        this.id = "";
        this.Medname = "";
    }

    public Medication(String id, String Medname) {
        this.id = id;
        this.Medname = Medname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedname() {
        return Medname;
    }

    public void setMedname(String medname) {
        this.Medname = medname;
    }

    public HashMap<String,String> toHashMap() {
        //TODO
        return null;
    }
}
