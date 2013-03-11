package com.shangde.edu.ass.condition;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

public class QueryAssessCondition extends PageQuery{
    private int assLeavel;
    private int subId;
    private int isGood;//查询已评价课程时，条件是否为好评
    private int cusId;
    private int kpointId;
    private int sellwayId;
    private int courseId;
    private int isPage;
    private int status=-1;
    private String startTime;
    private String endTime;
    private String assTitle;
    private String verifyStartTime;
    private String verifyEndTime;
    
    private List<String> watchKids=new ArrayList<String>();
    private List<Integer> assKids=new ArrayList<Integer>();
    

	public List<String> getWatchKids() {
		return watchKids;
	}

	public void setWatchKids(List<String> watchKids) {
		this.watchKids = watchKids;
	}

	public List<Integer> getAssKids() {
		return assKids;
	}

	public void setAssKids(List<Integer> assKids) {
		this.assKids = assKids;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getKpointId() {
		return kpointId;
	}

	public void setKpointId(int kpointId) {
		this.kpointId = kpointId;
	}

	public int getSellwayId() {
		return sellwayId;
	}

	public void setSellwayId(int sellwayId) {
		this.sellwayId = sellwayId;
	}

	public int getIsGood() {
		return isGood;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getAssLeavel() {
		return assLeavel;
	}

	public void setAssLeavel(int assLeavel) {
		this.assLeavel = assLeavel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAssTitle() {
		return assTitle;
	}

	public void setAssTitle(String assTitle) {
		this.assTitle = assTitle;
	}

	public String getVerifyStartTime() {
		return verifyStartTime;
	}

	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}

	public String getVerifyEndTime() {
		return verifyEndTime;
	}

	public void setVerifyEndTime(String verifyEndTime) {
		this.verifyEndTime = verifyEndTime;
	}
}