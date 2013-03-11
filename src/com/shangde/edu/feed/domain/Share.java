/**
 * 
 */
package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享模型
 * 
 * @author Libg
 * 
 */
public class Share implements Serializable {

	private Integer id;// id
	private Integer videoId;// 视频id
	private Integer userId;// 用户id
	private Integer courseId;// 课程id
	private Date shareTime;// 分享时间

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
	 * @return the shareTime
	 */
	public Date getShareTime() {
		return shareTime;
	}

	/**
	 * @param shareTime
	 *            the shareTime to set
	 */
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

}
