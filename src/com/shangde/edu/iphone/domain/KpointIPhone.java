package com.shangde.edu.iphone.domain;

import java.io.Serializable;

import com.shangde.edu.res.domain.Vedio;

/**
 * 知识点
 * @author chenshuai
 *
 */
public class KpointIPhone implements Serializable, Comparable{
	
	public static final int KPOINT_DELETE_STATUS = 2;
	
	public static final int KPOINT_DEFAULT_STATUS = 0;
	
	public static final int KPOINT_FREEZE_STATUS = 1;
	
	/**
	 *知识点ID
	 */
    private int pointId;
    
    /**
     * 知识点父ID
     */
    private int pId;
    
    /**
     * 课程ID
     */
    private int courseId;
    
    /**
     * 知识点名称
     */
    private String name;
    /**
     *介绍
     */
    private String introduce;
    
    /**
     * 叶子节点
     */
    private int leaf;
    
    /**
     * 添加时间
     */
    private java.util.Date addTime;
    
    /**
     *排序
     */
    private int sort;
    
    /**
     * 知识点级别
     */
    private int level;
    
    /**
     * 知识点状态
     */
    private int status;
    
    /**
     * 最终排序（按此数进行排序）
     */
    private int sortNum;
    
    /**
     * 讲义
     */
    private String lecture;
    
    /**
     * 视频对象
     */
    private Vedio vedio;
    /**
     * 售卖方式ID
     */
    private int sellWayId;
    /**
     * 专业ID
     */
    private int subId;
    /**
     * 
     * 知识点的评论数量
     */
    private int assCount;
        
    public int getAssCount() {
		return assCount;
	}

	public void setAssCount(int assCount) {
		this.assCount = assCount;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPointId(){
        return pointId;
    }

    public void setPointId(int pointId){
        this.pointId = pointId; 
    }
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
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
        
    public String getIntroduce(){
        return introduce;
    }

    public void setIntroduce(String introduce){
        this.introduce = introduce; 
    }
        
    public int getLeaf(){
        return leaf;
    }

    public void setLeaf(int leaf){
        this.leaf = leaf; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }
        
    public int getSort(){
        return sort;
    }

    public void setSort(int sort){
        this.sort = sort; 
    }
    
    /**
     * 重写equals方法
     */
	public boolean equals(Object obj) {
    	KpointIPhone kointtemp = (KpointIPhone)obj;
		if(kointtemp != null && kointtemp.getPointId() == this.pointId){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 重写hashCode方法
	 */
	public int hashCode() {
		int result = 15;
		result = result*37 + this.getCourseId();
		
		return result;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int compareTo(Object arg0) {
		KpointIPhone kpointtemp = (KpointIPhone)arg0;
		return new Integer(this.sortNum).compareTo(new Integer(kpointtemp.sortNum));
	}

	public Vedio getVedio() {
		return vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public int getSellWayId() {
		return sellWayId;
	}

	public void setSellWayId(int sellWayId) {
		this.sellWayId = sellWayId;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}
}
