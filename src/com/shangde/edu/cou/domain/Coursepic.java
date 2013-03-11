package com.shangde.edu.cou.domain;

import java.io.Serializable;

/**
 * 课程图片
 * @author chenshuai
 *
 */
public class Coursepic implements Serializable{
	public static final int COURSEPIC_MAIN_PIC = 1;
	public static final int COURSEPIC_OTHER_PIC = 0;
	
	/**
	 * 图片ID
	 */
    private int picId;
    
    /**
	 * 课程ID
	 */
    private int courseId;
    
    /**
	 * 图片名称
	 */
    private String name;
    
    /**
	 * 图片大小
	 */
    private double size;
    
    /**
	 * 图片类型（如jpg,gif）
	 */
    private String type;
    
    /**
	 * 是否为首图片（1：是，0：不是）
	 */
    private int homeFlag;
    
    /**
	 * 图片地址
	 */
    private String picUrl;
    
    /**
	 * 添加时间
	 */
    private java.util.Date addtime;
    
    /**
	 * 图片介绍
	 */
    private String introduce;
        
    public int getPicId(){
        return picId;
    }

    public void setPicId(int picId){
        this.picId = picId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name; 
    }
        
    public double getSize(){
        return size;
    }

    public void setSize(double size){
        this.size = size; 
    }
        
    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type; 
    }
        
    public int getHomeFlag(){
        return homeFlag;
    }

    public void setHomeFlag(int homeFlag){
        this.homeFlag = homeFlag; 
    }
        
    public String getPicUrl(){
        return picUrl;
    }

    public void setPicUrl(String picUrl){
        this.picUrl = picUrl; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }
        
    public String getIntroduce(){
        return introduce;
    }

    public void setIntroduce(String introduce){
        this.introduce = introduce; 
    }
}
