package com.shangde.edu.purse.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryRecordCondition extends PageQuery  {
	private String recordId;//交易编号
	private String userAccount;//用户账号
	private Integer recordType;//交易类型
	private String remark;//交易备注
	private Integer payType;//交易方式
	private String creator;//操作人
	private Date createBeginDate;//创建开始时间
	private Date createEndDate;//创建结束时间
	private Integer szStatus;//收支方向
	public String getRecordId() {
		return recordId;
	}
	public Date getCreateBeginDate() {
		return createBeginDate;
	}
	public void setCreateBeginDate(Date createBeginDate) {
		this.createBeginDate = createBeginDate;
	}
	public Date getCreateEndDate() {
		return createEndDate;
	}
	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getSzStatus() {
		return szStatus;
	}
	public void setSzStatus(Integer szStatus) {
		this.szStatus = szStatus;
	}
        
}