package com.mc.info.lumc;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by TOSHIBA on 07-Jan-17.
 */

public class Medication implements Serializable {

    private String  id;
    private String name;

    public Medication() {
        id = "";
        name = "";
    }

    public Medication(String id, String name) {
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

    public HashMap<String,String> toHashMap()
    {
        HashMap<String, String> returnValue = new HashMap<>();
        returnValue.put(DBHandler.COLUMN_MEDICINE_NAME, getName());
        return returnValue;
    }


}
