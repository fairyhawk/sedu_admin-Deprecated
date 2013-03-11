package com.shangde.edu.exam.condition;

import java.io.Serializable;
import java.util.Date;

import com.shangde.common.vo.PageResult;

/**
 * 查询试题条件
 * @author chenshuai
 *
 */
public class QueryQstCondition extends PageResult implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int qstId;
    private int epId;
    private int qstType;
    private int qstindexId;//父题的id
    private String searchKey;//关键字
    private String author;	//录试卷作者
    private int tiaojian;//条件
    private Date startTime;
    private Date endTime;
    private int subject_id;
    private int sortId;  //课程ID；
    private int subjectId; //项目ID
    private int ksnId;
  

	public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId;
    }

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public int getQstindexId() {
		return qstindexId;
	}

	public void setQstindexId(int qstindexId) {
		this.qstindexId = qstindexId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(int tiaojian) {
		this.tiaojian = tiaojian;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
}