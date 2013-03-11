package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class StudyPlan implements Serializable{
    private int id;
    private int cusId;
    private String spInfo;
    private java.util.Date spDate;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getSpInfo(){
        return spInfo;
    }

    public void setSpInfo(String spInfo){
        this.spInfo = spInfo; 
    }
        
    public java.util.Date getSpDate(){
        return spDate;
    }

    public void setSpDate(java.util.Date spDate){
        this.spDate = spDate; 
    }
}
