package com.shangde.edu.purse.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Record implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String recordId;
    private int recordType;
    private int szStatus;
    private BigDecimal money;
    private int payType;
    private int payStatus;
    private Date createTime;
    private int cardId;
    private String remark;
    private String creator;
    private String recordUrl;
    private String updateUser;
    private java.util.Date updateTime;
    private Integer userAccountId;
    private String bank;
    private String orderCode;
    private String userAccount;//用户账号
	private String recordTypeName;//交易类型
	private String payTypeName;//交易方式
	private String szStatusName;//收支情况
	private String payStatusName;//状态
	
	public String getPayStatusName() {
		return payStatusName;
	}
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getSzStatusName() {
		return szStatusName;
	}
	public void setSzStatusName(String szStatusName) {
		this.szStatusName = szStatusName;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
        

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getRecordId(){
        return recordId;
    }

    public void setRecordId(String recordId){
        this.recordId = recordId; 
    }
        
    public int getRecordType(){
        return recordType;
    }

    public void setRecordType(int recordType){
        this.recordType = recordType; 
    }
        
    public int getSzStatus(){
        return szStatus;
    }

    public void setSzStatus(int szStatus){
        this.szStatus = szStatus; 
    }
        
    public java.math.BigDecimal getMoney(){
        return money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money = money; 
    }
        
    public int getPayType(){
        return payType;
    }

    public void setPayType(int payType){
        this.payType = payType; 
    }
        
    public int getPayStatus(){
        return payStatus;
    }

    public void setPayStatus(int payStatus){
        this.payStatus = payStatus; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getCardId(){
        return cardId;
    }

    public void setCardId(int cardId){
        this.cardId = cardId; 
    }
        
    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark; 
    }
        
    public String getCreator(){
        return creator;
    }

    public void setCreator(String creator){
        this.creator = creator; 
    }
        
    public String getRecordUrl(){
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl){
        this.recordUrl = recordUrl; 
    }
        
    public String getUpdateUser(){
        return updateUser;
    }

    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }
}
