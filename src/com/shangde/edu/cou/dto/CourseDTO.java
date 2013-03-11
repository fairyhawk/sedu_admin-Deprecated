package com.shangde.edu.cou.dto;

import java.io.Serializable;

import com.shangde.edu.cou.domain.Teacher;

/**
 * 课程DTO
 * 
 * @author chenshuai
 */
public class CourseDTO implements Serializable, Comparable {
	
	/**
	 * 教师ID
	 */
	private int teacherId;
	/**
	 * 课程ID
	 */
	private int courseId;

	/**
	 * 教师ID
	 */
	private Teacher teacher;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 视频图片
	 */
	private String vedioPicUrl;

	/**
	 * 价格
	 */
	private float price;
	
	/**
	 * 介绍
	 */
	private String introduce;
	
	/**
	 * 视频地址
	 */
	private String voUrl;
    /**
     * 课时
     */
    private float lessionTime;
    /**
     * 积分
     */
    private int tsScore;
    /**
     * 原始价格
     * @return
     */
    private float oldPrice;
    
	
    
	private float courseTime;
	
	private int kpointSum;
	
	private String voideUrl;
	
	//课程上传进度属性
	private String pointName;
	
	private int pointId;
	
	private int pId;
	
	private String pdfUrl;
	
	private String ypUrl;
	
	private String bkUrl;
	
	private String tcName;
	
	private String zName;
	
	private String jName;
	
	private String zsdName;
	
	private int level;
	
	private int leaf;
	
	private String  iswatch="0";
	
	//结束
	
	public String getZName() {
		return zName;
	}

	public void setZName(String name) {
		zName = name;
	}

	public String getJName() {
		return jName;
	}

	public void setJName(String name) {
		jName = name;
	}

	public String getZsdName() {
		return zsdName;
	}

	public void setZsdName(String zsdName) {
		this.zsdName = zsdName;
	}

	public String getVoideUrl() {
		return voideUrl;
	}

	public void setVoideUrl(String voideUrl) {
		this.voideUrl = voideUrl;
	}

	public int getKpointSum() {
		return kpointSum;
	}

	public void setKpointSum(int kpointSum) {
		this.kpointSum = kpointSum;
	}

	public float getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(float courseTime) {
		this.courseTime = courseTime;
	}

	public String getVoUrl() {
		return voUrl;
	}

	public void setVoUrl(String voUrl) {
		this.voUrl = voUrl;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVedioPicUrl() {
		return vedioPicUrl;
	}

	public void setVedioPicUrl(String vedioPicUrl) {
		this.vedioPicUrl = vedioPicUrl;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public float getLessionTime() {
		return lessionTime;
	}

	public void setLessionTime(float lessionTime) {
		this.lessionTime = lessionTime;
	}

	public int getTsScore() {
		return tsScore;
	}

	public void setTsScore(int tsScore) {
		this.tsScore = tsScore;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

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

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getYpUrl() {
		return ypUrl;
	}

	public void setYpUrl(String ypUrl) {
		this.ypUrl = ypUrl;
	}

	public String getBkUrl() {
		return bkUrl;
	}

	public void setBkUrl(String bkUrl) {
		this.bkUrl = bkUrl;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

    public String getIswatch() {
        return iswatch;
    }

    public void setIswatch(String iswatch) {
        this.iswatch = iswatch;
    }

    

}
