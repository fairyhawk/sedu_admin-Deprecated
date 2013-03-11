/**
 * 
 */
package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频观看记录
 * 
 * @author Libg
 * 
 */
public class VideoWatchStat implements Serializable {

	private Integer id;// id
	private Integer videoId;// 视频id
	private Integer userId;// 用户id
	private Date statitcsTime;// 统计日期，发布日期
	private Integer courseId;// 课程id
	private Integer isWatchEnd;// 是否观看完

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
	 * @return the statitcsTime
	 */
	public Date getStatitcsTime() {
		return statitcsTime;
	}

	/**
	 * @param statitcsTime
	 *            the statitcsTime to set
	 */
	public void setStatitcsTime(Date statitcsTime) {
		this.statitcsTime = statitcsTime;
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
	 * @return the isWatchEnd
	 */
	public Integer getIsWatchEnd() {
		return isWatchEnd;
	}

	/**
	 * @param isWatchEnd
	 *            the isWatchEnd to set
	 */
	public void setIsWatchEnd(Integer isWatchEnd) {
		this.isWatchEnd = isWatchEnd;
	}

}
