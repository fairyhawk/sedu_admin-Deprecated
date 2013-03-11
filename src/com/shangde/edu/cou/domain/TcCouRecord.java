package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class TcCouRecord implements Serializable{
    private int courseId;
    private int tcId;
    private int id;
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getTcId(){
        return tcId;
    }

    public void setTcId(int tcId){
        this.tcId = tcId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
}
