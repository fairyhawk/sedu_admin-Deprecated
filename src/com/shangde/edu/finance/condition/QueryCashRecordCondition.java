package com.shangde.edu.finance.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryCashRecordCondition extends PageQuery {
    private int id;
    private String searchKey;
    private int userId;
    private int packId ;//商品id
    private String createTimeBegin;
    private String createTimeEnd;
    private String payTimeBegin;
    private String payTimeEnd;
    private String contractId;//订单编号
    private int type;
    private int ctId;
    private String email;
    private int courseId;
    private int status;//支付状态
    private String shopName;//商品名称
    private int subjectId;//项目id
    private int shopStatus;//商品状态
    private int cusId; //用户ID
    private int sellType; //商品属性 是否DS。1否 2是
    
    //新加
    private int carecorstatus=-1;//流水支付状态
    private int carecordshopStatus=-1;//流水商品状态
    /*
	 * delayTime
	 */
	private Date delayTime;
	/*
	 * packId
	 */
	private String cousmerId;
	/*
	 * pcId
	 */
	private String pcId;
	
	/*
	 * Liming 延期时间String修改
	 */
	private String delayTimeString;

    public String getDelayTimeString() {
		return delayTimeString;
	}

	public void setDelayTimeString(String delayTimeString) {
		this.delayTimeString = delayTimeString;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}

	public String getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getPayTimeBegin() {
		return payTimeBegin;
	}

	public void setPayTimeBegin(String payTimeBegin) {
		this.payTimeBegin = payTimeBegin;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}


	public Date getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}

	public String getCousmerId() {
		return cousmerId;
	}

	public void setCousmerId(String cousmerId) {
		this.cousmerId = cousmerId;
	}

	public int getCarecorstatus() {
		return carecorstatus;
	}

	public void setCarecorstatus(int carecorstatus) {
		this.carecorstatus = carecorstatus;
	}

	public int getCarecordshopStatus() {
		return carecordshopStatus;
	}

	public void setCarecordshopStatus(int carecordshopStatus) {
		this.carecordshopStatus = carecordshopStatus;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getSellType() {
		return sellType;
	}

	public void setSellType(int sellType) {
		this.sellType = sellType;
	}
	
	
}