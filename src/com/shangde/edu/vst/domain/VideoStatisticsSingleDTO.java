package com.shangde.edu.vst.domain;

import java.io.Serializable;

public class VideoStatisticsSingleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int subjectId;
	
	private String subjectName;
	
	private int month;
	//登陆次数
	private int loginTimes;
	//观看课程次数（即观看多少次视频）
	private String watchedTimesAll;
	//观看视频总数（数目）
	private String watchedTotal;
	//观看完的视频总数(时长>10)
	private String watchedTimes;
	//学习总时间（单位：分钟）
	private String learningTotalTimes;
	//平均日观看时间
	private String learningAvgTimes;
	//观看率
	private float watchedPercent;
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public float getWatchedPercent() {
		return watchedPercent;
	}

	public void setWatchedPercent(float watchedPercent) {
		this.watchedPercent = watchedPercent;
	}
	

	public String getWatchedTimesAll() {
		return watchedTimesAll;
	}

	public void setWatchedTimesAll(String watchedTimesAll) {
		this.watchedTimesAll = watchedTimesAll;
	}

	public String getWatchedTotal() {
		return watchedTotal;
	}

	public void setWatchedTotal(String watchedTotal) {
		this.watchedTotal = watchedTotal;
	}

	public String getWatchedTimes() {
		return watchedTimes;
	}

	public void setWatchedTimes(String watchedTimes) {
		this.watchedTimes = watchedTimes;
	}

	public String getLearningTotalTimes() {
		return learningTotalTimes;
	}

	public void setLearningTotalTimes(String learningTotalTimes) {
		this.learningTotalTimes = learningTotalTimes;
	}

	public String getLearningAvgTimes() {
		return learningAvgTimes;
	}

	public void setLearningAvgTimes(String learningAvgTimes) {
		this.learningAvgTimes = learningAvgTimes;
	}
	
	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
