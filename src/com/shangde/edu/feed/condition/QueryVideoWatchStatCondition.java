package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 微学习，ad广告查询接口
 * 
 * @author Libg
 * 
 */
public class QueryVideoWatchStatCondition extends PageQuery implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer videoId;
	private String startTime;
	private String endTime;

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
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

}