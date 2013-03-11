package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.edu.exam.domain.Exampaper;

public class Subject implements Serializable{
	public static final int SUBJECT_DEFAULT_STATUS = 0;
	public static final int SUBJECT_FREEZE_STATUS = 1;
	public static final int SUBJECT_DELETE_STATUS = 2;
	
	/**专业id*/
    private int subjectId;
    /**专业名称*/
    private String subjectName;
    /**状态*/
    private int status;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    
    private Date testTime;
    
    /**#add试听课程编号*/
 
    private String freeClasses;
    
    private List<Exampaper> exampapers;
    
    private List<Grade> gradeList = new ArrayList<Grade>();
    
    
    //400热线
    private String hotline;
    //课程咨询联系号码
    private String courseConsultNumber;
    //课程咨询人员姓名
    private String courseConsultName;
    //售后咨询联系号码
    private String customerServiceNumber;
    //售后咨询人员姓名
    private String customerServiceName;
    //投诉建议联系号码
    private String complaintServiceNumber;
    //投诉建议人员名称
    private String complaintServiceName;
        
    public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Exampaper> getExampapers() {
		return exampapers;
	}

	public void setExampapers(List<Exampaper> exampapers) {
		this.exampapers = exampapers;
	}

	public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	/**
	 * @return the freeClasses
	 */
	public String getFreeClasses() {
		return freeClasses;
	}

	/**
	 * @param freeClasses the freeClasses to set
	 */
	public void setFreeClasses(String freeClasses) {
		this.freeClasses = freeClasses;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getCourseConsultNumber() {
		return courseConsultNumber;
	}

	public void setCourseConsultNumber(String courseConsultNumber) {
		this.courseConsultNumber = courseConsultNumber;
	}

	public String getCourseConsultName() {
		return courseConsultName;
	}

	public void setCourseConsultName(String courseConsultName) {
		this.courseConsultName = courseConsultName;
	}

	public String getCustomerServiceNumber() {
		return customerServiceNumber;
	}

	public void setCustomerServiceNumber(String customerServiceNumber) {
		this.customerServiceNumber = customerServiceNumber;
	}

	public String getCustomerServiceName() {
		return customerServiceName;
	}

	public void setCustomerServiceName(String customerServiceName) {
		this.customerServiceName = customerServiceName;
	}

	public String getComplaintServiceNumber() {
		return complaintServiceNumber;
	}

	public void setComplaintServiceNumber(String complaintServiceNumber) {
		this.complaintServiceNumber = complaintServiceNumber;
	}

	public String getComplaintServiceName() {
		return complaintServiceName;
	}

	public void setComplaintServiceName(String complaintServiceName) {
		this.complaintServiceName = complaintServiceName;
	}
	
	
}
