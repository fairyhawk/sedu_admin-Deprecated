package com.shangde.edu.test.domain;

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
    
    private List<Exampaper> exampapers;
    
   

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
}
