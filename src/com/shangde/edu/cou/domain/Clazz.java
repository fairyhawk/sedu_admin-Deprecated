package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.List;

public class Clazz implements Serializable{
    private int clazzId;
    private String title;
    private int status;
    
    private int subjectId;
    
    private int gradeId;
    
    private java.util.Date addTime;
    private String introduce;
    
    /**
     * 班级图片
     */
    private String clazzPic;
    
    /**
     * 班级首页图片
     */
    private String clazzIndexPic;
    
    private float clazzOfferPrice;
    
    /**
     * 课程ID集合
     */
    private List courseIdList;
        
    public int getClazzId(){
        return clazzId;
    }

    public void setClazzId(int clazzId){
        this.clazzId = clazzId; 
    }
        
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }
        
    public String getIntroduce(){
        return introduce;
    }

    public void setIntroduce(String introduce){
        this.introduce = introduce; 
    }

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public List getCourseIdList() {
		return courseIdList;
	}

	public void setCourseIdList(List courseIdList) {
		this.courseIdList = courseIdList;
	}

	public String getClazzPic() {
		return clazzPic;
	}

	public void setClazzPic(String clazzPic) {
		this.clazzPic = clazzPic;
	}

	public String getClazzIndexPic() {
		return clazzIndexPic;
	}

	public void setClazzIndexPic(String clazzIndexPic) {
		this.clazzIndexPic = clazzIndexPic;
	}

	public float getClazzOfferPrice() {
		return clazzOfferPrice;
	}

	public void setClazzOfferPrice(float clazzOfferPrice) {
		this.clazzOfferPrice = clazzOfferPrice;
	}
}
