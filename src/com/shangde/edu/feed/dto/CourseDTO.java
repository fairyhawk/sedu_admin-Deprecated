package com.shangde.edu.feed.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO 课程模型
 * 
 * @author Libg
 * 
 */
public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 专业id
	 */
	private Integer subjectId;
	
	private String courseCategoryName;
	
	public String getCourseCategoryName() {
		return courseCategoryName;
	}

	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}

	public Integer getUsePersonNumber() {
		return usePersonNumber;
	}

	public void setUsePersonNumber(Integer usePersonNumber) {
		this.usePersonNumber = usePersonNumber;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	private Integer usePersonNumber;
	
	private Integer isExclusive;
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
	 * 包含视频总数
	 */
	private Integer videoCount;

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

	/**
	 * @return the videoCount
	 */
	public Integer getVideoCount() {
		return videoCount;
	}

	/**
	 * @param videoCount
	 *            the videoCount to set
	 */
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

}
