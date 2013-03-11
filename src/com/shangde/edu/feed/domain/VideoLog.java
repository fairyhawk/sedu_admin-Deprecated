package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 视频log记录模型
 * 
 * @author Libg
 * 
 */
public class VideoLog implements Serializable {

	private int id;
	private int subjectId;// 专业id
	private int videoId;// 视频id
	private String videoName;// 视频名称
	private int total;// 总记录
	private int activeNum;// 激活次数
	private int clickNum;// 点击次数
	private int clickBuyNum;// 点击购买次数
	private int buyNum;// 购买成功次数
	private java.util.Date pubdate;// 发布时间
	private java.util.Date modified;// 修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getActiveNum() {
		return activeNum;
	}

	public void setActiveNum(int activeNum) {
		this.activeNum = activeNum;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getClickBuyNum() {
		return clickBuyNum;
	}

	public void setClickBuyNum(int clickBuyNum) {
		this.clickBuyNum = clickBuyNum;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public java.util.Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(java.util.Date pubdate) {
		this.pubdate = pubdate;
	}

	public java.util.Date getModified() {
		return modified;
	}

	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}
}
