package com.shangde.edu.feed.dto;

import java.io.Serializable;
import java.util.Date;

public class UserStatisticsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String subjectName;
	private String videoName;
	private String courseName;
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private String regFrom;
	private Date regTime;
	private Integer comeFeedNum;
	private Integer watchCourseNum;
	private Integer watchVideoNum;
	private String watchAllTime;
	private Integer downloadVideoNum;
	private Integer collectionVideoNum;
	private Integer questionNum;
	private Date statisticsTime;
	public Date getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getRegFrom() {
		return regFrom;
	}
	public void setRegFrom(String regFrom) {
		this.regFrom = regFrom;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Integer getComeFeedNum() {
		return comeFeedNum;
	}
	public void setComeFeedNum(Integer comeFeedNum) {
		this.comeFeedNum = comeFeedNum;
	}
	public Integer getWatchCourseNum() {
		return watchCourseNum;
	}
	public void setWatchCourseNum(Integer watchCourseNum) {
		this.watchCourseNum = watchCourseNum;
	}
	public Integer getWatchVideoNum() {
		return watchVideoNum;
	}
	public void setWatchVideoNum(Integer watchVideoNum) {
		this.watchVideoNum = watchVideoNum;
	}

	public String getWatchAllTime() {
		return watchAllTime;
	}
	public void setWatchAllTime(String watchAllTime) {
		this.watchAllTime = watchAllTime;
	}
	public Integer getDownloadVideoNum() {
		return downloadVideoNum;
	}
	public void setDownloadVideoNum(Integer downloadVideoNum) {
		this.downloadVideoNum = downloadVideoNum;
	}
	public Integer getCollectionVideoNum() {
		return collectionVideoNum;
	}
	public void setCollectionVideoNum(Integer collectionVideoNum) {
		this.collectionVideoNum = collectionVideoNum;
	}
	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
}
