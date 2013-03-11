package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class KnowledgeSystem implements Serializable{
	//知识体系id
    private int sId;
    //层次
    private String sLevel;
    //名称
    private String sName;
    //备注
    private String note;
        
    public int getSId(){
        return sId;
    }

    public void setSId(int sId){
        this.sId = sId; 
    }
        
    public String getSLevel(){
        return sLevel;
    }

    public void setSLevel(String sLevel){
        this.sLevel = sLevel; 
    }
        
    public String getSName(){
        return sName;
    }

    public void setSName(String sName){
        this.sName = sName; 
    }
        
    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note; 
    }
}
