package com.mc.info.lumc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class Examination implements Serializable{
    private String id;
    private Date date;
    private examType type;
    private List<MedicalData> data;

    public Examination() {
        this.id = "";
        this.date = new Date();
        this.data = new ArrayList();
    }

    public Examination(String id, Date date, List<MedicalData> data) {
        this.id = id;
        this.date = (Date) date.clone();
        this.data = new ArrayList<>(data);
    }

    public enum examType{
        HEMATOLOGY,VITAMIN_A,VITAMIN_B,VITAMIN_C,VITAMIN_D,CHOLESTEROL;

    }

    public String getExamName(){
        switch (type){
            case HEMATOLOGY: return "hematology";
            case VITAMIN_A: return "vitamin A";
            case VITAMIN_B: return "vitamin B";
            case VITAMIN_C: return "vitamin  C";
            case VITAMIN_D: return "vitamin D";
            case CHOLESTEROL: return "cholesterol";
        }
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }

    public List<MedicalData> getData() {
        return new ArrayList<>(data);
    }

    public void setData(List<MedicalData> data) {
        this.data = new ArrayList<>(data);
    }

    public void addData(String name[], double value[]){
        for(int i = 0 ; i<name.length ; i++)
            data.add(new MedicalData(name[i],value[i]));
    }

    public abstract HashMap<String, String> toHashMap();

}