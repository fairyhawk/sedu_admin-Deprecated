package com.shangde.edu.ass.domain;

import java.io.Serializable;

public class Reassess implements Serializable{
    private int id;
    private int assId;
    private String reassContext;
    private java.util.Date reassTime;
    private int reCusId;
    private String mark;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getAssId(){
        return assId;
    }

    public void setAssId(int assId){
        this.assId = assId; 
    }
        
    public String getReassContext(){
        return reassContext;
    }

    public void setReassContext(String reassContext){
        this.reassContext = reassContext; 
    }
        
    public java.util.Date getReassTime(){
        return reassTime;
    }

    public void setReassTime(java.util.Date reassTime){
        this.reassTime = reassTime; 
    }
        
    public int getReCusId(){
        return reCusId;
    }

    public void setReCusId(int reCusId){
        this.reCusId = reCusId; 
    }
        
    public String getMark(){
        return mark;
    }

    public void setMark(String mark){
        this.mark = mark; 
    }
}
