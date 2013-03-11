package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class ExamStat implements Serializable{
    private int eStatId;
    private int cusId;
    private int courseId;
    private String examCode;
    private java.util.Date lastexamdate;
    private int lastexamid;
    private String lastexamname;
        
    public int getEStatId(){
        return eStatId;
    }

    public void setEStatId(int eStatId){
        this.eStatId = eStatId; 
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
        
    public String getExamCode(){
        return examCode;
    }

    public void setExamCode(String examCode){
        this.examCode = examCode; 
    }
        
    public java.util.Date getLastexamdate(){
        return lastexamdate;
    }

    public void setLastexamdate(java.util.Date lastexamdate){
        this.lastexamdate = lastexamdate; 
    }
        
    public int getLastexamid(){
        return lastexamid;
    }

    public void setLastexamid(int lastexamid){
        this.lastexamid = lastexamid; 
    }
        
    public String getLastexamname(){
        return lastexamname;
    }

    public void setLastexamname(String lastexamname){
        this.lastexamname = lastexamname; 
    }
}
