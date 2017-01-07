package com.mc.info.lumc;

import java.io.DataInput;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class Medication implements Serializable {

    private int id;
    private String name;

    public Medication() {
    }

    public Medication(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
