package com.shangde.edu.feed.domain;

import java.util.Date;

/**
 * 微学习->课程
 * 
 * @author Libg
 * 
 */
public class Course implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * 专业id
	 */
	private String subjectId;
	/**
	 * 课程阶段[1-10]
	 */
	private Integer stageNum;
	/**
	 * 课程名称
	 */
	private String name;
	/**
	 * 简介
	 */
	private String introduction;
	/**
	 * 课时
	 */
	private Integer hours;
	/**
	 * 状态[1=正常,2=冻结,3=删除]每一项状态值，请根据需求对应程序完成
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Date pubDate;
	/**
	 * 修改时间
	 */
	private Date modified;
	/**
	 * 课程是否独享
	 */
	private Integer isExclusive;
	/**
	 * 课程类别
	 */
	private Integer courseCategoryId;
	/**
	 * 课程有效期
	 */
	private Date effective;
	/**
	 * 课程图片url
	 */
	private String courseImgUrl;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	public Integer getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(Integer courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public Date getEffective() {
		return effective;
	}

	public void setEffective(Date effective) {
		this.effective = effective;
	}

	public String getCourseImgUrl() {
		return courseImgUrl;
	}

	public void setCourseImgUrl(String courseImgUrl) {
		this.courseImgUrl = courseImgUrl;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the stageNum
	 */
	public Integer getStageNum() {
		return stageNum;
	}

	/**
	 * @param stageNum
	 *            the stageNum to set
	 */
	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction
	 *            the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

}
