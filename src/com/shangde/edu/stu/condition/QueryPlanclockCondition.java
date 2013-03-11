package com.shangde.edu.stu.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * 查询提醒的条件
 * 
 * @author baiang.zhao 2011-5-18 16:55:29
 */
public class QueryPlanclockCondition extends PageQuery{

	/**
	 * 闹钟ID
	 */
	private int clockId;
	/**
	 * 提醒标题
	 */
	private String ctitle;
	/**
	 * 提醒内容
	 */
	private String ccontent;
	/**
	 * 项目ID
	 * 检索中项目ID
	 */
	private int subjectId;
	/**
	 * 呼叫时间（定时提醒）
	 */
	private Date alertdate;
	/**
	 * 提醒状态（1发布、2新稿、0删除）
	 * 检索状态
	 */
	private int cstatus;
	/**
	 * 项目名称
	 */
	private String subjectnamne;
	/**
	 * 提醒类型（默认为‘系统提醒’ , 默认为0）
	 */
	private int ctype;
	/**
	 * 课程名称
	 */
	private String coursename;
	/**
	 * 开始时间
	 * 检索提醒开始时间
	 */
	private Date startdate;
	/**
	 * 结束时间
	 * 检索提醒结束时间
	 */
	private Date enddate;
	/**
	 * 是否发送提醒(0、默认，1、每天，2、每月，3、每年)
	 */
	private int issended;
	/**
	 * 提醒创建时间
	 */
	private Date createdate;
	/**
	 * 检索关键字
	 */
	private String kyewords;
	/**
	 * 检索提醒开始时间
	 */
	private Date startCreatedate;
	/**
	 * 检索提醒结束时间
	 */
	private Date endCreatedate;
	
	
	/**
	 * 匹配查询日期
	 * 
	 * 范昕===添加 2011.6.1
	 */
	private Date calendar;
	
    /** 
     * 提醒 是否发送过（0==未发送过，1==已发送）
     * 
     */ 
    private int isent;
    /**
     * 设置一个字段进行模糊查询
     * @return
     */
    private String dateFind;
    
	


	public String getDateFind() {
		return dateFind;
	}

	public void setDateFind(String dateFind) {
		this.dateFind = dateFind;
	}

	public int getClockId() {
		return clockId;
	}

	public void setClockId(int clockId) {
		this.clockId = clockId;
	}

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public java.util.Date getAlertdate() {
		return alertdate;
	}

	public void setAlertdate(Date alertdate) {
		this.alertdate = alertdate;
	}

	public int getCstatus() {
		return cstatus;
	}

	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}

	public String getSubjectnamne() {
		return subjectnamne;
	}

	public void setSubjectnamne(String subjectnamne) {
		this.subjectnamne = subjectnamne;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public java.util.Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public java.util.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getIssended() {
		return issended;
	}

	public void setIssended(int issended) {
		this.issended = issended;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getKyewords() {
		return kyewords;
	}

	public void setKyewords(String kyewords) {
		this.kyewords = kyewords;
	}

	public Date getStartCreatedate() {
		return startCreatedate;
	}

	public void setStartCreatedate(Date startCreatedate) {
		this.startCreatedate = startCreatedate;
	}

	public Date getEndCreatedate() {
		return endCreatedate;
	}

	public void setEndCreatedate(Date endCreatedate) {
		this.endCreatedate = endCreatedate;
	}

	public Date getCalendar() {
		return calendar;
	}

	public void setCalendar(Date calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the isent
	 */
	public int getIsent() {
		return isent;
	}

	/**
	 * @param isent the isent to set
	 */
	public void setIsent(int isent) {
		this.isent = isent;
	}



}