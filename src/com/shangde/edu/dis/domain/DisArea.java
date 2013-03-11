package com.shangde.edu.dis.domain;

import java.io.Serializable;
import java.util.Date;

public class DisArea implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -6195443404672609073L;
    private int id;
    private int disId;
    private String name;
    private String introduction;
    private int sort;
    private int subjectId;
    private int userId;
    private Date createTime;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getDisId(){
        return disId;
    }

    public void setDisId(int disId){
        this.disId = disId; 
    }
        
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name; 
    }
        
    public String getIntroduction(){
        return introduction;
    }

    public void setIntroduction(String introduction){
        this.introduction = introduction; 
    }

	/**
	 * @return the order
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param order the order to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
