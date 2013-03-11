/**
 * 
 */
package com.shangde.edu.sys.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author	caowei
 * @date	2011-8-9
 * @desc	
 */
public class SubjectAgentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
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
    
    private String freeClasses;
    
    

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
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
    
    
}
