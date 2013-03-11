package com.shangde.edu.cou.dto;
import java.io.Serializable;

import com.shangde.edu.res.domain.Vedio;

public class KPointDTO implements Serializable{
	
	public static final int KPOINT_DELETE_STATUS = 2;
	
	public static final int KPOINT_DEFAULT_STATUS = 0;
	
	public static final int KPOINT_FREEZE_STATUS = 1;
	
	/**
	 * ֪知识点ID
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
     *知识点名称 
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
     *添加时间
     */
    private java.util.Date addTime;
    
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
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public int getPId() {
		return pId;
	}
	public void setPId(int id) {
		pId = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getLeaf() {
		return leaf;
	}
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}
	public java.util.Date getAddTime() {
		return addTime;
	}
	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getLecture() {
		return lecture;
	}
	public void setLecture(String lecture) {
		this.lecture = lecture;
	}
	public Vedio getVedio() {
		return vedio;
	}
	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
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
	public int getAssCount() {
		return assCount;
	}
	public void setAssCount(int assCount) {
		this.assCount = assCount;
	}
}
