package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class Teacher implements Serializable{
    private int tcId;
    private String name;
    private String education;
    private String career;
    private int isStar;
    private String picPath;
        
    public int getTcId(){
        return tcId;
    }

    public void setTcId(int tcId){
        this.tcId = tcId; 
    }
        
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name; 
    }
        
    public String getEducation(){
        return education;
    }

    public void setEducation(String education){
        this.education = education; 
    }
        
    public String getCareer(){
        return career;
    }

    public void setCareer(String career){
        this.career = career; 
    }
        
    public int getIsStar(){
        return isStar;
    }

    public void setIsStar(int isStar){
        this.isStar = isStar; 
    }
        
    public String getPicPath(){
        return picPath;
    }

    public void setPicPath(String picPath){
        this.picPath = picPath; 
    }
}
