package com.shangde.edu.freshnews.domain;

import java.io.Serializable;

public class ActionRecord implements Serializable{
    private Integer id;
    private Integer relateId;
    private Integer currentType;
    private String content;
    private String cusName;
    private Integer cusId;
    private String cusEmail;
    private Integer isAnswer;
    private Integer subjectId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String otherInfo;
    private String headImg;//头像图片
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRelateId() {
		return relateId;
	}
	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}
	public Integer getCurrentType() {
		return currentType;
	}
	public void setCurrentType(Integer currentType) {
		this.currentType = currentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public Integer getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(Integer isAnswer) {
		this.isAnswer = isAnswer;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
        
   
}
