package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class ExamSummary implements Serializable{
    private int eSumId;
    private int planId;
    private java.util.Date summarydate;
    private int isexam;
    private String examname;
    private int examId;
    private int cusId;
        
    public int getESumId(){
        return eSumId;
    }

    public void setESumId(int eSumId){
        this.eSumId = eSumId; 
    }
        
    public int getPlanId(){
        return planId;
    }

    public void setPlanId(int planId){
        this.planId = planId; 
    }
        
    public java.util.Date getSummarydate(){
        return summarydate;
    }

    public void setSummarydate(java.util.Date summarydate){
        this.summarydate = summarydate; 
    }
        
    public int getIsexam(){
        return isexam;
    }

    public void setIsexam(int isexam){
        this.isexam = isexam; 
    }
        
    public String getExamname(){
        return examname;
    }

    public void setExamname(String examname){
        this.examname = examname; 
    }
        
    public int getExamId(){
        return examId;
    }

    public void setExamId(int examId){
        this.examId = examId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
}
