package com.shangde.edu.stu.domain;

import java.io.Serializable;

/**
 * 项目表
 * @author Administrator
 *
 */
public class SubjectEntity implements Serializable{
	private int subjectId;
	private String subjectName;
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
