package com.shangde.edu.iphone.domain;

import java.io.Serializable;

public class SellWayIPhone implements Serializable{
	/**
	 *  ID
	 **/
    private int sellId;
    /**
	 *   专业ID
	 **/
    private int subjectId;
    /**
	 *  售卖方式名
	 **/
    private String sellName;
    /**
	 *  售卖方式状态
	 **/
    private int status;
    /**
	 *  添加时间
	 **/
    private java.util.Date addTime;
    /**
	 *  售卖方式价格
	 **/
    private float sellPrice;
    /**
	 * 
	 **/
    private String sellMark;
    /**
     * 讲师名称
     */
    private String teacher;
    /**
     * 专业关键字
     */
    private String subjectKey;
    /**
     * 课时数
     */
    private int lessonNumber;
    /**
     * 课程开始上传时间
     * 
     */
    private String startTime;
    
    /**
     *说明
     */
    private String remark;
    
    /**
     * 课程有效期
     */
    private String validity;
    
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
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

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}


	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSellMark() {
		return sellMark;
	}

	public void setSellMark(String sellMark) {
		this.sellMark = sellMark;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSubjectKey() {
		return subjectKey;
	}

	public void setSubjectKey(String subjectKey) {
		this.subjectKey = subjectKey;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
        
   
}
