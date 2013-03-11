package com.shangde.edu.vst.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.vst.util.DateUtil;

public class VideoStatisticsCountDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer subjectId;
	
	private String subjectName;
	//观看完所购买课程学员总数(时长>10)
	private Integer watchedCusNum;
	//学员总观看时间(时长总和)学习总时间（单位：分钟）
    private Integer allWatchTime;
    //观看课程次数（即观看多少次视频）
  	private Integer watchFrAll;
  	//观看完视频总数
  	private Integer watchedTimeFr;
  	//观看完的视频总数(时长>10)
  	private Integer watchedTotal;
  	//项目下所有视频节数
  	private Integer subjectVideoTotal;
	//观看率
  	private float watchedPercent;
  	//开始时间，查询条件
    private Date start;
    //结束时间，查询条件
    private Date end;
    //统计中是否包含试听视频
    private boolean includeAudition;
    
    private String watchAlltimeStr;
    
	public String getWatchAlltimeStr() {
		return DateUtil.getTimeLength(allWatchTime);
	}
	public void setWatchAlltimeStr(String watchAlltimeStr) {
		this.watchAlltimeStr = watchAlltimeStr;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getWatchedCusNum() {
		return watchedCusNum;
	}
	public void setWatchedCusNum(Integer watchedCusNum) {
		this.watchedCusNum = watchedCusNum;
	}
	public Integer getAllWatchTime() {
		return allWatchTime;
	}
	public void setAllWatchTime(Integer allWatchTime) {
		this.allWatchTime = allWatchTime;
	}
	public Integer getWatchFrAll() {
		return watchFrAll;
	}
	public void setWatchFrAll(Integer watchFrAll) {
		this.watchFrAll = watchFrAll;
	}
	public Integer getWatchedTimeFr() {
		return watchedTimeFr;
	}
	public void setWatchedTimeFr(Integer watchedTimeFr) {
		this.watchedTimeFr = watchedTimeFr;
	}
	public Integer getWatchedTotal() {
		return watchedTotal;
	}
	public void setWatchedTotal(Integer watchedTotal) {
		this.watchedTotal = watchedTotal;
	}
	public float getWatchedPercent() {
		return watchedPercent;
	}
	public void setWatchedPercent(float watchedPercent) {
		this.watchedPercent = watchedPercent;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Integer getSubjectVideoTotal() {
		return subjectVideoTotal;
	}
	public void setSubjectVideoTotal(Integer subjectVideoTotal) {
		this.subjectVideoTotal = subjectVideoTotal;
	}
	public boolean isIncludeAudition() {
		return includeAudition;
	}
	public void setIncludeAudition(boolean includeAudition) {
		this.includeAudition = includeAudition;
	}
	
	
	
}
