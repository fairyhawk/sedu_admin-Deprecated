package com.shangde.edu.cou.domain;

import java.io.Serializable;



	/**
	 *@author chenshuai
	 *推荐课程 
	 */
public class Tjcourse implements Serializable{
	
	/**
	 * ID
	 */
    private int id;
    
    /**
	 * 课程ID
	 */
    private int courseId;
    
    /**
	 * 推荐课程ID
	 */
    private int tjcourseId;
  
    /**
     *添加时间 
     */
    private java.util.Date addTime;
    
    /**
     * 图片路径
     */
    private String picPath;
    
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String info;

    /**
     * 购买方法
     */
    private String buyMethod;

    /**
     * 推荐图片路径
     */
    private String tjPicPath;

    /**
     * 推荐标题
     */
    private String tjTitle;

    /**
     * 推荐播放次数
     */
    private int tjClickTimes;
    
    /**
     * 推荐URL
     */
    private String tjURL;

    /**
     * 推荐购买方法
     */
    private String tjBuyMethod; 
        
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
        
    public int getTjcourseId(){
        return tjcourseId;
    }

    public void setTjcourseId(int tjcourseId){
        this.tjcourseId = tjcourseId; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getBuyMethod() {
		return buyMethod;
	}

	public void setBuyMethod(String buyMethod) {
		this.buyMethod = buyMethod;
	}

	public String getTjPicPath() {
		return tjPicPath;
	}

	public void setTjPicPath(String tjPicPath) {
		this.tjPicPath = tjPicPath;
	}

	public String getTjTitle() {
		return tjTitle;
	}

	public void setTjTitle(String tjTitle) {
		this.tjTitle = tjTitle;
	}

	public int getTjClickTimes() {
		return tjClickTimes;
	}

	public void setTjClickTimes(int tjClickTimes) {
		this.tjClickTimes = tjClickTimes;
	}

	public String getTjURL() {
		return tjURL;
	}

	public void setTjURL(String tjURL) {
		this.tjURL = tjURL;
	}

	public String getTjBuyMethod() {
		return tjBuyMethod;
	}

	public void setTjBuyMethod(String tjBuyMethod) {
		this.tjBuyMethod = tjBuyMethod;
	}
}
