package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class SellCou implements Serializable{
    private int id;
    private int courseId;
    private int sellId;
    private float sellCouPrice;
        
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
}
