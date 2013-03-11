package com.shangde.edu.crm.dto;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.crm.domain.Record;

public class ChanceDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机会ID
	 */
	private Integer id;
	
	/**
	 * 销售机会用户库ID
	 */
	private Integer userId;
	
	private int mesStatus;
	
	/**
	 * 学员ID
	 */
	private Integer cusId;
	/**
	 * 登录次数
	 */
	private int loginTimes;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	/**
	 * 项目名
	 */
	private String subjectName;
	
	/**
	 * 域名来源
	 */
	private String webName;
	
	/**
	 * 机会来源
	 */
	private Integer origin;
	
	/**
	 * 销售坐席
	 */
	private String userName;
	
	/**
	 * 用户跟踪状态
	 */
	private Integer followStatus;
	
	/**
	 * 机会创建时间
	 */
	private Date chanceSTime;
	
	/**
	 * 最后指派时间
	 */
	private Date chanceUTime;
	
	/**
	 * 支付数
	 */
	private Integer fcount1;
	
	/**
	 * 订单数
	 */
	private Integer fcount2;
	
	/**
	 * 货到付款数
	 */
	private Integer fcount3;
	
	/**
	 * 活到付款订单取消数
	 */
	private Integer fcount4;
	
    /**
     * 最后沟通时间
     */
    private Date endCommTime;
    
    /**
     * 最后沟通状态
     */
    private Integer endCommStatus;
    
    /**
     * 咨询状态
     */
    private Integer consultStatus;
    private int sendCount;
    private float sendPrice;
    private int intBankCount;
    private float intBankPrice;
    private String firstUserName;
    private int bankCount;
    private float bankPrice;
    
    private String endRecordRemarks;//机会的最后沟通记录
    /**
     * 最新沟通记录
     */
    private Record record;
    /**
     * 流量来源
     */
    private String cusWebFrom;
    /**
     * 是否主动放弃 0否 1是
     */
    private int autoGiveup;
    
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	public Date getChanceSTime() {
		return chanceSTime;
	}

	public void setChanceSTime(Date chanceSTime) {
		this.chanceSTime = chanceSTime;
	}

	public Date getChanceUTime() {
		return chanceUTime;
	}

	public void setChanceUTime(Date chanceUTime) {
		this.chanceUTime = chanceUTime;
	}

	public Integer getFcount1() {
		return fcount1;
	}

	public void setFcount1(Integer fcount1) {
		this.fcount1 = fcount1;
	}

	public Integer getFcount2() {
		return fcount2;
	}

	public void setFcount2(Integer fcount2) {
		this.fcount2 = fcount2;
	}

	public Date getEndCommTime() {
		return endCommTime;
	}

	public void setEndCommTime(Date endCommTime) {
		this.endCommTime = endCommTime;
	}

	public Integer getEndCommStatus() {
		return endCommStatus;
	}

	public void setEndCommStatus(Integer endCommStatus) {
		this.endCommStatus = endCommStatus;
	}

	public Integer getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(Integer consultStatus) {
		this.consultStatus = consultStatus;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getFcount3() {
		return fcount3;
	}

	public void setFcount3(Integer fcount3) {
		this.fcount3 = fcount3;
	}

	public Integer getFcount4() {
		return fcount4;
	}

	public void setFcount4(Integer fcount4) {
		this.fcount4 = fcount4;
	}

	public int getMesStatus() {
		return mesStatus;
	}

	public void setMesStatus(int mesStatus) {
		this.mesStatus = mesStatus;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public float getSendPrice() {
		return sendPrice;
	}

	public void setSendPrice(float sendPrice) {
		this.sendPrice = sendPrice;
	}

	public int getIntBankCount() {
		return intBankCount;
	}

	public void setIntBankCount(int intBankCount) {
		this.intBankCount = intBankCount;
	}

	public float getIntBankPrice() {
		return intBankPrice;
	}

	public void setIntBankPrice(float intBankPrice) {
		this.intBankPrice = intBankPrice;
	}

	public String getFirstUserName() {
		return firstUserName;
	}

	public void setFirstUserName(String firstUserName) {
		this.firstUserName = firstUserName;
	}

	public int getBankCount() {
		return bankCount;
	}

	public void setBankCount(int bankCount) {
		this.bankCount = bankCount;
	}

	public float getBankPrice() {
		return bankPrice;
	}

	public void setBankPrice(float bankPrice) {
		this.bankPrice = bankPrice;
	}

	public String getEndRecordRemarks() {
		return endRecordRemarks;
	}

	public void setEndRecordRemarks(String endRecordRemarks) {
		this.endRecordRemarks = endRecordRemarks;
	}


	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public String getCusWebFrom() {
		return cusWebFrom;
	}

	public void setCusWebFrom(String cusWebFrom) {
		this.cusWebFrom = cusWebFrom;
	}

	public int getAutoGiveup() {
		return autoGiveup;
	}

	public void setAutoGiveup(int autoGiveup) {
		this.autoGiveup = autoGiveup;
	}
	
	
}
