package com.shangde.edu.dis.dto;

/**
 * 用于查询用户已购买的专业信息
 * 
 * @author Basil
 * 
 */
public class SubjectDTO implements java.io.Serializable {

	private Integer subjectId;
	private String subjectName;

	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName
	 *            the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
