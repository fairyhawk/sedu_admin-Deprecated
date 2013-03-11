package com.shangde.edu.res.domain;

import java.io.Serializable;

public class Tryvedio implements Serializable{
    private int id;
    private int courseId;
    private java.util.Date addTime;
    private String picPath;
    private String title;
    private String info;
    private String buyMethod;
    private int clickTimes;
    private String url;
    private int tryTimes;
        
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
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }
        
    public String getPicPath(){
        return picPath;
    }

    public void setPicPath(String picPath){
        this.picPath = picPath; 
    }
        
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title; 
    }
        
    public String getInfo(){
        return info;
    }

    public void setInfo(String info){
        this.info = info; 
    }
        
    public String getBuyMethod(){
        return buyMethod;
    }

    public void setBuyMethod(String buyMethod){
        this.buyMethod = buyMethod; 
    }
        
    public int getClickTimes(){
        return clickTimes;
    }

    public void setClickTimes(int clickTimes){
        this.clickTimes = clickTimes; 
    }
        
    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url; 
    }

	public int getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
}
