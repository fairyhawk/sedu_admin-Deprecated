package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class CourseSummary implements Serializable{
    private int cSumId;
    private int planId;
    private java.util.Date summarydate;
    private int iscourse;
    private int pointId;
    private String pointName;
    private int cusId;
        
    public int getCSumId(){
        return cSumId;
    }

    public void setCSumId(int cSumId){
        this.cSumId = cSumId; 
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
        
    public int getIscourse(){
        return iscourse;
    }

    public void setIscourse(int iscourse){
        this.iscourse = iscourse; 
    }
        
    public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
}
