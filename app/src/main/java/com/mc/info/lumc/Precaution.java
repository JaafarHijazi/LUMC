package com.mc.info.lumc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class Precaution implements Serializable {

    private String id;
    private String name;

    public Precaution() {
        this.id = "";
        this.name = "";
    }

    public Precaution(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
