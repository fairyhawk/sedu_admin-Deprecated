package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

public class QueryMicroVideoCondition extends PageQuery implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 视频名称
	 */
	private String name;
	/**
	 * 视频url地址
	 */
	private String url;
	/**
	 * 专业id
	 */
	private Integer subjectId;
	/**
	 * 课程id
	 */
	private Integer courseId;

	private String startTime;
	private String endTime;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取课程id
	 * 
	 * @return
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * 设置课程id
	 * 
	 * @param courseId
	 *            要设置的值
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * 获取专业id
	 * 
	 * @return
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * 设置专业id
	 * 
	 * @param subjectId
	 *            要设置的值
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * 获取编号
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置编号
	 * 
	 * @param id
	 *            要设置的编号
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取视频名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置视频名称
	 * 
	 * @param name
	 *            要设置的视频名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取视频地址
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置视频地址
	 * 
	 * @param url
	 *            要设置的视频地址值
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
