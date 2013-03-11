package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class VideoStat implements Serializable{
    private int vStatId;
    private int cusId;
    private int courseId;
    private String viewCode;
    private java.util.Date lastviewdate;
    private int lastviewid;
    private String lastviewname;
        
    public int getVStatId(){
        return vStatId;
    }

    public void setVStatId(int vStatId){
        this.vStatId = vStatId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public String getViewCode(){
        return viewCode;
    }

    public void setViewCode(String viewCode){
        this.viewCode = viewCode; 
    }
        
    public java.util.Date getLastviewdate(){
        return lastviewdate;
    }

    public void setLastviewdate(java.util.Date lastviewdate){
        this.lastviewdate = lastviewdate; 
    }
        
    public int getLastviewid(){
        return lastviewid;
    }

    public void setLastviewid(int lastviewid){
        this.lastviewid = lastviewid; 
    }
        
    public String getLastviewname(){
        return lastviewname;
    }

    public void setLastviewname(String lastviewname){
        this.lastviewname = lastviewname; 
    }
}
