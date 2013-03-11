package com.shangde.edu.cou.dto;

import java.io.Serializable;

import com.shangde.edu.cou.domain.Course;

public class SellCouDTO implements Serializable{
    private int id;
    private int courseId;
    private int sellId;
    private float sellCouPrice;
    private Course course;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCourseId(){
        return courseId;
    }
    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getSellId(){
        return sellId;
    }

    public void setSellId(int sellId){
        this.sellId = sellId; 
    }

	public float getSellCouPrice() {
		return sellCouPrice;
	}

	public void setSellCouPrice(float sellCouPrice) {
		this.sellCouPrice = sellCouPrice;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


}
