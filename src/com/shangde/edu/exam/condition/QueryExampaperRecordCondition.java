package com.shangde.edu.exam.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

/**
 * 查询试卷记录条件
 * @author chenshuai
 *
 */
public class QueryExampaperRecordCondition extends PageQuery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private int cusId;
	/**
	 * 试卷id
	 */
	private int epId;
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 添加时间
	 * 
	 *  范昕====2011-05-26 添加
	 */
    private java.util.Date addtime;
    

	/**
	 * 添加时间
	 * 
	 *  范昕====2011-05-26 添加
	 */
    private java.util.Date bftime;
    
    /**
     * 条件
     */
    private int tiaojian;
    
    /**
     * 关键字
     * @return
     */
    private String searchKey;
    
    /**
     * 专业id
     * @return
     */
    private int subject_id;
    
    /**
     * 试卷类型
     * @return
     */
    private int eptype;
    
    /**
     * 开始时间
     * @return
     */
    private java.util.Date startTime;
    /**
     * 结束时间
     */
    private java.util.Date endTime;
    
    private int paixu;
    
    /**
     * 课程id
     * @return
     */
    
    private int sortId;
    
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public java.util.Date getAddtime() {
		return addtime;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public java.util.Date getBftime() {
		return bftime;
	}

	public void setBftime(java.util.Date bftime) {
		this.bftime = bftime;
	}

	public int getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(int tiaojian) {
		this.tiaojian = tiaojian;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getEptype() {
		return eptype;
	}

	public void setEptype(int eptype) {
		this.eptype = eptype;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		if(endTime!=null){
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
		}
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public int getPaixu() {
		return paixu;
	}

	public void setPaixu(int paixu) {
		this.paixu = paixu;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}
	
	
}