package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.List;

/**
 *课程分类
 *@author chenshuai 
 */
public class Coursesort implements Serializable , Comparable<Object>{
	public static final int COURSESORT_DELETE_STATUS = 2;
	
	public static final int COURSESORT_DEFAULT_STATUS = 0;
	
	public static final int COURSESORT_FREEZE_STATUS = 1;
	
	/**
	 * 课程分类ID
	 */
    private int coursesortId;
    
    /**
	 * 父ID
	 */
    private int pId;
   
    /**
     *课程分类名称
     */
    private String coursesortName;
    
    /**
	 * 添加时间
	 */
    private java.util.Date addTime;
    
    /**
	 * 排序
	 */
    private int sortNum;
    
    /**
	 * 状态
	 */
    private int status;
    
    /**
     * 分类级别
     */
    private int level;
    
    /**
     * 专业ID
     */
    private int subjectId;
    
    /**
     * 分类图片
     */
    private String sortPicUrl;
    
    /**
     * 课程ID集合
     */
    private List courseIdList;
        
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCoursesortId(){
        return coursesortId;
    }

    public void setCoursesortId(int coursesortId){
        this.coursesortId = coursesortId; 
    }
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
    }
        
    public String getCoursesortName(){
        return coursesortName;
    }

    public void setCoursesortName(String coursesortName){
        this.coursesortName = coursesortName; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }
        
    public int getSortNum(){
        return sortNum;
    }

    public void setSortNum(int sortNum){
        this.sortNum = sortNum; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }

	public int compareTo(Object o) {
		Coursesort sorttemp = (Coursesort)o;
		return new Integer(this.getCoursesortId()).compareTo(new Integer(sorttemp.getCoursesortId()));
	}

	public boolean equals(Object obj) {
		Coursesort sorttemp = (Coursesort)obj;
		if(sorttemp != null && sorttemp.getCoursesortId() == this.getCoursesortId()){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public List getCourseIdList() {
		return courseIdList;
	}

	public void setCourseIdList(List courseIdList) {
		this.courseIdList = courseIdList;
	}

	public String getSortPicUrl() {
		return sortPicUrl;
	}

	public void setSortPicUrl(String sortPicUrl) {
		this.sortPicUrl = sortPicUrl;
	}
	
}
