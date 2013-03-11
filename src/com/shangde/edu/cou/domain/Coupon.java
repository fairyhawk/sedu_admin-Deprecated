package com.shangde.edu.cou.domain;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

public class Coupon implements Serializable{
    private int id;
    private float price;
    private String cInfo;
    private String picPath;
    private String title;
    private java.util.Date createTime;
    private java.util.Date stopTime;
    private int courseId;
    private Course course;
    private int num;
    private int toScore;
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public float getPrice(){
        return price;
    }

        
    public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCInfo(){
        return cInfo;
    }

    public void setCInfo(String cInfo){
        this.cInfo = cInfo; 
    }
        
    public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@JSON(format="yyyy-MM-dd")
	public java.util.Date getStopTime(){
        return stopTime;
    }

    public void setStopTime(java.util.Date stopTime){
        this.stopTime = stopTime; 
    }

	@JSON(format="yyyy-MM-dd")
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getToScore() {
		return toScore;
	}

	public void setToScore(int toScore) {
		this.toScore = toScore;
	}
}
