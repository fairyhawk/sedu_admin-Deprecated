package com.shangde.edu.ass.domain;

import java.io.Serializable;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Kpoint;

public class Assess implements Serializable{
    private int id;
    private int subjectId;
    private int cusId;
    private int sellwayId;
    private int courseId;
    private int kpointId;
    private String assTitle;
    private String assContext;
    private java.util.Date assTime;
    private java.util.Date verifyTime;
    private int assLeavel;
    private int status;//0:未审核；1：已审核；2：审核未通过；3：
    private String verifyContext;
    private double levelAvg;
    private Course course;
    private Kpoint kpoint;
    public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getSellwayId(){
        return sellwayId;
    }

    public void setSellwayId(int sellwayId){
        this.sellwayId = sellwayId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getKpointId(){
        return kpointId;
    }

    public void setKpointId(int kpointId){
        this.kpointId = kpointId; 
    }
        
    public String getAssTitle(){
        return assTitle;
    }

    public void setAssTitle(String assTitle){
        this.assTitle = assTitle; 
    }
        
    public String getAssContext(){
        return assContext;
    }

    public void setAssContext(String assContext){
        this.assContext = assContext; 
    }
        
    public java.util.Date getAssTime(){
        return assTime;
    }

    public void setAssTime(java.util.Date assTime){
        this.assTime = assTime; 
    }
        
    public java.util.Date getVerifyTime(){
        return verifyTime;
    }

    public void setVerifyTime(java.util.Date verifyTime){
        this.verifyTime = verifyTime; 
    }
        
    public int getAssLeavel(){
        return assLeavel;
    }

    public void setAssLeavel(int assLeavel){
        this.assLeavel = assLeavel; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public String getVerifyContext(){
        return verifyContext;
    }

    public void setVerifyContext(String verifyContext){
        this.verifyContext = verifyContext; 
    }

	public double getLevelAvg() {
		return levelAvg;
	}

	public void setLevelAvg(double levelAvg) {	
		
		this.levelAvg = levelAvg;
	}
}
