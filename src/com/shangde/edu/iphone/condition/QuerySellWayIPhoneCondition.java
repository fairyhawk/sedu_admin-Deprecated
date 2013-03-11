package com.shangde.edu.iphone.condition;

import com.shangde.common.vo.PageQuery;

public class QuerySellWayIPhoneCondition extends PageQuery {
    private int saleId;
    
    private int customerId;
	private String searchKey;
    private int id;
    private int userId;
    private String startTime;
    private String endTime;
    private String contractId;
    private String contractFrom;
    private String startCountTime;
    private String endCountTime;
    private int status;
    private String newStatus;
    private int conStr;
    private int subjectId=-1;
    private String payStartTime;
    private String payEndTime;
    private String email;
    private String contractCDkey;
    private int payType = -1;
    private int cusIdAddress;
    private int courseId;
    private String contractFromUrl;
    private String webFrom;
    private String webAgent;
    private String src;
    private String wi ;
    private Integer cid ;
    private String createTime ;
        
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSaleId(){
        return saleId;
    }

    public void setSaleId(int saleId){
        this.saleId = saleId;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractFrom() {
		return contractFrom;
	}

	public void setContractFrom(String contractFrom) {
		this.contractFrom = contractFrom;
	}

	public String getStartCountTime() {
		return startCountTime;
	}

	public void setStartCountTime(String startCountTime) {
		this.startCountTime = startCountTime;
	}

	public String getEndCountTime() {
		return endCountTime;
	}

	public void setEndCountTime(String endCountTime) {
		this.endCountTime = endCountTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public int getConStr() {
		return conStr;
	}

	public void setConStr(int conStr) {
		this.conStr = conStr;
	}

	public String getPayStartTime() {
		return payStartTime;
	}

	public void setPayStartTime(String payStartTime) {
		this.payStartTime = payStartTime;
	}

	public String getPayEndTime() {
		return payEndTime;
	}

	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContractCDkey() {
		return contractCDkey;
	}

	public void setContractCDkey(String contractCDkey) {
		this.contractCDkey = contractCDkey;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getCusIdAddress() {
		return cusIdAddress;
	}

	public void setCusIdAddress(int cusIdAddress) {
		this.cusIdAddress = cusIdAddress;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getContractFromUrl() {
		return contractFromUrl;
	}

	public void setContractFromUrl(String contractFromUrl) {
		this.contractFromUrl = contractFromUrl;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getWi() {
		return wi;
	}

	public void setWi(String wi) {
		this.wi = wi;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
   
}