package com.shangde.edu.sys.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.sys.domain.Grade;


public class SubjectCountsDTO  implements Serializable{
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
    
    private List<Exampaper> exampapers;
    /**
     * 注册量，内部用户，面授用户
     */
    private int qbRegist;
	private int qbRegistNb;
	private int qbRegistWb;
    
	private int contracts;
	private int payedContracts;
	private int zfbContracts;
	private int zfbPayedContracts;
	private int hdfkContracts;
	private int hdfkPayedContracts;
	private int wyzxContracts;
	private int wyzxPayedContracts;
    private List<Grade> gradeList = new ArrayList<Grade>();
        
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

	public int getQbRegist() {
		return qbRegist;
	}

	public void setQbRegist(int qbRegist) {
		this.qbRegist = qbRegist;
	}

	public int getQbRegistNb() {
		return qbRegistNb;
	}

	public void setQbRegistNb(int qbRegistNb) {
		this.qbRegistNb = qbRegistNb;
	}

	public int getQbRegistWb() {
		return qbRegistWb;
	}

	public void setQbRegistWb(int qbRegistWb) {
		this.qbRegistWb = qbRegistWb;
	}

	public int getContracts() {
		return contracts;
	}

	public void setContracts(int contracts) {
		this.contracts = contracts;
	}

	public int getPayedContracts() {
		return payedContracts;
	}

	public void setPayedContracts(int payedContracts) {
		this.payedContracts = payedContracts;
	}

	public int getZfbContracts() {
		return zfbContracts;
	}

	public void setZfbContracts(int zfbContracts) {
		this.zfbContracts = zfbContracts;
	}

	public int getZfbPayedContracts() {
		return zfbPayedContracts;
	}

	public void setZfbPayedContracts(int zfbPayedContracts) {
		this.zfbPayedContracts = zfbPayedContracts;
	}

	public int getHdfkContracts() {
		return hdfkContracts;
	}

	public void setHdfkContracts(int hdfkContracts) {
		this.hdfkContracts = hdfkContracts;
	}

	public int getHdfkPayedContracts() {
		return hdfkPayedContracts;
	}

	public void setHdfkPayedContracts(int hdfkPayedContracts) {
		this.hdfkPayedContracts = hdfkPayedContracts;
	}

	public int getWyzxContracts() {
		return wyzxContracts;
	}

	public void setWyzxContracts(int wyzxContracts) {
		this.wyzxContracts = wyzxContracts;
	}

	public int getWyzxPayedContracts() {
		return wyzxPayedContracts;
	}

	public void setWyzcPayedContracts(int wyzxPayedContracts) {
		this.wyzxPayedContracts = wyzxPayedContracts;
	}
}
