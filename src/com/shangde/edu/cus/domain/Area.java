package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class Area implements Serializable{
    private int id;
    private int parentId;
    private String areaName;
    private int areaType;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId = parentId; 
    }
        
    public String getAreaName(){
        return areaName;
    }

    public void setAreaName(String areaName){
        this.areaName = areaName; 
    }
        
    public int getAreaType(){
        return areaType;
    }

    public void setAreaType(int areaType){
        this.areaType = areaType; 
    }
}
