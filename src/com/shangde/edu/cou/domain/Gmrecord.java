package com.shangde.edu.cou.domain;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

/**
 * 购买记录
 * @author chenshuai
 *
 */
public class Gmrecord implements Serializable{
	
	/**
	 * ID
	 */
    private int id;
    
    /**
	 * 用户ID
	 */
    private int userId;
    
    /**
	 * 课程ID
	 */
    private int courseId;
    
    /**
	 * 添加时间
	 */
    private java.util.Date addTime;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }

	@JSON(format="yyyy-MM-dd")
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }
}
