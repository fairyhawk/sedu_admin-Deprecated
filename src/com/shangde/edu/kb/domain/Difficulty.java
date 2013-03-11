package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class Difficulty implements Serializable{
	//难度id
    private int dId;
    //难度名称
    private String dName;
    //备注
    private String note;
    //难度序号
    private String dLevel;
        
    public int getDId(){
        return dId;
    }

    public void setDId(int dId){
        this.dId = dId; 
    }
        
    public String getDName(){
        return dName;
    }

    public void setDName(String dName){
        this.dName = dName; 
    }
        
    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note; 
    }
        
    public String getDLevel(){
        return dLevel;
    }

    public void setDLevel(String dLevel){
        this.dLevel = dLevel; 
    }
}
