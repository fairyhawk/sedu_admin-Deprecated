package com.shangde.edu.feed.domain;

import java.util.Date;

/**
 * 下载模型
 * 
 * @author Libg
 * 
 */
public class Download implements java.io.Serializable {

	private Integer id;// id
	private Integer videoId;// 视频id
	private Integer userId;// 用户id
	private Integer courseId;// 课程id
	private Date statisticsTime;// 统计时间

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
	 * @return the videoId
	 */
	public Integer getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId
	 *            the videoId to set
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the statisticsTime
	 */
	public Date getStatisticsTime() {
		return statisticsTime;
	}

	/**
	 * @param statisticsTime
	 *            the statisticsTime to set
	 */
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

}
