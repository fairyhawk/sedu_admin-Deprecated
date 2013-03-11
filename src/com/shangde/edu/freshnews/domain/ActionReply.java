package com.shangde.edu.freshnews.domain;

import java.io.Serializable;

public class ActionReply implements Serializable{
    private Integer id;
    private Integer actionId;
    private Integer operateType;
    private String operateUserId;
    private String operateUserEmail;
    private String operateUserName;
    private java.util.Date createTime;
    private Integer replyFrom;
    private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public String getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserEmail() {
		return operateUserEmail;
	}
	public void setOperateUserEmail(String operateUserEmail) {
		this.operateUserEmail = operateUserEmail;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public Integer getReplyFrom() {
		return replyFrom;
	}
	public void setReplyFrom(Integer replyFrom) {
		this.replyFrom = replyFrom;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
        
   
}
