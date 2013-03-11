package com.shangde.edu.stu.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *<p> Description: </p>
 * Author: baiang.zhao <br>
 * Date: May 19, 2011 <br>
 * Time: 9:26:23 AM <br>
 */
public class Planclock implements Serializable{

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
	 */
    private int subjectId;
    /**
	 * 呼叫时间（定时提醒）
	 */
    private Date alertdate;
    /**
	 * 提醒状态（1发布、2新稿、0删除）
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
	 */
    private Date startdate;
    /**
	 * 结束时间
	 */
    private Date enddate;
    /**
	 * 是否重复发送提醒(0、默认，1、每天，2、每月，3、每年)
	 */
    private int issended;
    /**
	 * 提醒创建时间
	 */
    private Date createdate;
    
    /** 
     * 提醒 是否发送过（0==未发送过，1==已发送）
     * 
     */ 
    private int isent;

        
    public int getClockId(){
        return clockId;
    }

    public void setClockId(int clockId){
        this.clockId = clockId; 
    }
        
    public String getCtitle(){
        return ctitle;
    }

    public void setCtitle(String ctitle){
        this.ctitle = ctitle; 
    }
        
    public String getCcontent(){
        return ccontent;
    }

    public void setCcontent(String ccontent){
        this.ccontent = ccontent; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public java.util.Date getAlertdate(){
        return alertdate;
    }

    public void setAlertdate(Date alertdate){
        this.alertdate = alertdate; 
    }
        
    public int getCstatus(){
        return cstatus;
    }

    public void setCstatus(int cstatus){
        this.cstatus = cstatus; 
    }
        
    public String getSubjectnamne(){
        return subjectnamne;
    }

    public void setSubjectnamne(String subjectnamne){
        this.subjectnamne = subjectnamne; 
    }
        
    public int getCtype(){
        return ctype;
    }

    public void setCtype(int ctype){
        this.ctype = ctype; 
    }
        
    public String getCoursename(){
        return coursename;
    }

    public void setCoursename(String coursename){
        this.coursename = coursename; 
    }
        
    public java.util.Date getStartdate(){
        return startdate;
    }

    public void setStartdate(Date startdate){
        this.startdate = startdate; 
    }
        
    public java.util.Date getEnddate(){
        return enddate;
    }

    public void setEnddate(Date enddate){
        this.enddate = enddate; 
    }
        
    public int getIssended(){
        return issended;
    }

    public void setIssended(int issended){
        this.issended = issended; 
    }
        
    public java.util.Date getCreatedate(){
        return createdate;
    }

    public void setCreatedate(Date createdate){
        this.createdate = createdate; 
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
