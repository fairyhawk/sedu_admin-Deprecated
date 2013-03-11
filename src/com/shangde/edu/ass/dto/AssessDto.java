package com.shangde.edu.ass.dto;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.ass.domain.Reassess;

public class AssessDto implements Serializable {
	
	private String expName;  //级别称号
	private String expLevel;
	private String expValue;
	
	private int level1;
	private int level2;
	private int level3;
	private int level4;
	private int level5;
	
	private int tsAction;
	private int tsCurrent;
	private int tsId;
	
	private String photo;  //用户头像
	private String email;	//用户email
	private String cusName;	//用户昵称
	private int cusId;		//用户ID
	
	private String subjectName;
	private String kpointName;
   
	private int id;
    private int subjectId;
    private int sellwayId;
    private int courseId;
    private int kpointId;
    private String assTitle;
    private String assContext;
    private java.util.Date assTime;
    private java.util.Date verifyTime;
    private int assLeavel;
    private int status;
    private String verifyContext;
    private int perNumber;
    
    private List<Reassess> reList; //嗨学网回复对象
    
    
	public List<Reassess> getReList() {
		return reList;
	}
	public void setReList(List<Reassess> reList) {
		this.reList = reList;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	public String getExpLevel() {
		return expLevel;
	}
	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}
	public String getExpValue() {
		return expValue;
	}
	public void setExpValue(String expValue) {
		this.expValue = expValue;
	}
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
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getSellwayId() {
		return sellwayId;
	}
	public void setSellwayId(int sellwayId) {
		this.sellwayId = sellwayId;
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
	public String getAssTitle() {
		return assTitle;
	}
	public void setAssTitle(String assTitle) {
		this.assTitle = assTitle;
	}
	public String getAssContext() {
		return assContext;
	}
	public void setAssContext(String assContext) {
		this.assContext = assContext;
	}
	public java.util.Date getAssTime() {
		return assTime;
	}
	public void setAssTime(java.util.Date assTime) {
		this.assTime = assTime;
	}
	public java.util.Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(java.util.Date verifyTime) {
		this.verifyTime = verifyTime;
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
	public String getVerifyContext() {
		return verifyContext;
	}
	public void setVerifyContext(String verifyContext) {
		this.verifyContext = verifyContext;
	}
	public int getTsAction() {
		return tsAction;
	}
	public void setTsAction(int tsAction) {
		this.tsAction = tsAction;
	}
	public int getTsCurrent() {
		return tsCurrent;
	}
	public void setTsCurrent(int tsCurrent) {
		this.tsCurrent = tsCurrent;
	}
	public int getTsId() {
		return tsId;
	}
	public void setTsId(int tsId) {
		this.tsId = tsId;
	}
	public int getLevel1() {
		return level1;
	}
	public void setLevel1(int level1) {
		this.level1 = level1;
	}
	public int getLevel2() {
		return level2;
	}
	public void setLevel2(int level2) {
		this.level2 = level2;
	}
	public int getLevel3() {
		return level3;
	}
	public void setLevel3(int level3) {
		this.level3 = level3;
	}
	public int getLevel4() {
		return level4;
	}
	public void setLevel4(int level4) {
		this.level4 = level4;
	}
	public int getLevel5() {
		return level5;
	}
	public void setLevel5(int level5) {
		this.level5 = level5;
	}
	public int getPerNumber() {
		return perNumber;
	}
	public void setPerNumber(int perNumber) {
		this.perNumber = perNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getKpointName() {
		return kpointName;
	}
	public void setKpointName(String kpointName) {
		this.kpointName = kpointName;
	}

}
