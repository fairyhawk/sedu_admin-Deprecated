package com.shangde.edu.sys.domain;

import java.io.Serializable;
public class SubjectUrl implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**自增列id**/
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**项目id*/
    private int subjectId;
    /**项目url*/
    private String subjectUrl;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectUrl() {
		return subjectUrl;
	}
	public void setSubjectUrl(String subjectUrl) {
		this.subjectUrl = subjectUrl;
	}
}
