package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class Customer implements Serializable{
	/**
	 * 是否完善信息
	 * 			0 : 未完善
	 * 			1 : 完善
	 */
	public static int CUS_COMPLETE_FALSE = 0;
	public static int CUS_COMPLETE_TRUE = 1;
	
	public static int CUS_CUS_TYPE_REGISTER = 0; 	//注册用户
	public static int CUS_CUS_TYPE_INNER = 1;		//内部用户
	public static int CUS_CUS_TYPE_TEMP = 2;		//临时用户
	public static int CUS_CUS_TYPE_TEMP_EXP=3;		//体验用户10天
	public static int CUS_CUS_TYPE_TEMP_EXP_MONTH=4;	//体验用户30天
	
    private int cusId;
    private String cusName;
    private String cusPwd;
    private String email;
    private String mobile;
    private String realName;
    private String sex;
    private String idNum;
    private String qq;
    private String photo;
    private String lastloginIP;
    private java.util.Date lastloginTime;
    private java.util.Date regTime;
    private java.util.Date birthday;
    private int loginTimes;
    private int isComplete;
    private java.util.Date completeTime;
    private String MSN;
    private String address;
    private int cusType;  //类型　0代表注册学员，1内部学员，2临时学员，3内部体验，4内部体验(三十天)
    private int monthRigist;
    private int monthContract;
    private int monthPayContract;
    private int monthPaySumMoney;
    private float percent;
    private List<LoginRecord> loginRecordList;
    private String studyType;
    private int subjectId;
    private String cusFromUrl;
    //注册标识来源页面 1项目页面注册 2 订单页面注册
    private String fromType;
    private Integer freezeStatus; // 是否冻结（1：正常 0：已冻结）
    private java.util.Date freezeChangedDate; // 冻结状态改变（包括冻结和解冻操作）发生的日期
    
    /**
     * 弹窗的记录格式
     * a 未购买用户的弹窗
     * b 已经购买用户 我的课程
     * c 已经购买用户 问答
     * d 已经购买用户 开始学习
     */
    private String newerflag;
    //注册来源标识
    private String cusWebFrom;
    //用户在注册来源上的账户编号
    private String cusWebAgent;
    
    // 保过协议的详细信息
    private List<CusProtocalDetail> cusProtocalDetailList;
	
	public String getCusWebFrom() {
		return cusWebFrom;
	}

	public void setCusWebFrom(String cusWebFrom) {
		this.cusWebFrom = cusWebFrom;
	}

	public String getCusWebAgent() {
		return cusWebAgent;
	}

	public void setCusWebAgent(String cusWebAgent) {
		this.cusWebAgent = cusWebAgent;
	}

	public String getNewerflag() {
        return newerflag;
    }

    public void setNewerflag(String newerflag) {
        this.newerflag = newerflag;
    }

    public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getCusName(){
        return cusName;
    }

    public void setCusName(String cusName){
        this.cusName = cusName; 
    }
        
    public String getCusPwd(){
        return cusPwd;
    }

    public void setCusPwd(String cusPwd){
        this.cusPwd = cusPwd; 
    }
        
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
        
    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealName(){
        return realName;
    }

    public void setRealName(String realName){
        this.realName = realName; 
    }
        
    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex; 
    }
        
    public String getIdNum(){
        return idNum;
    }

    public void setIdNum(String idNum){
        this.idNum = idNum; 
    }
        
    public String getLastloginIP() {
		return lastloginIP;
	}

	public void setLastloginIP(String lastloginIP) {
		this.lastloginIP = lastloginIP;
	}

	@JSON(format="yyyy-MM-dd")
	public java.util.Date getLastloginTime(){
        return lastloginTime;
    }

    public void setLastloginTime(java.util.Date lastloginTime){
        this.lastloginTime = lastloginTime; 
    }

	@JSON(format="yyyy-MM-dd")
    public java.util.Date getRegTime(){
        return regTime;
    }

    public void setRegTime(java.util.Date regTime){
        this.regTime = regTime; 
    }

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	public boolean equals(Object obj) {//重写比较函数
		Customer sorttemp = (Customer)obj;
		if(sorttemp != null && sorttemp.getCusId() == this.getCusId()){
			return true;
		}else{
			return false;
		}
	}
    
	public int hashCode() {
		// TODO Auto-generated method stub
		return 10000;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@JSON(format="yyyy-MM-dd")
	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public java.util.Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(java.util.Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getMSN() {
		return MSN;
	}

	public void setMSN(String msn) {
		MSN = msn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCusType() {
		return cusType;
	}

	public void setCusType(int cusType) {
		this.cusType = cusType;
	}

	public int getMonthRigist() {
		return monthRigist;
	}

	public void setMonthRigist(int monthRigist) {
		this.monthRigist = monthRigist;
	}

	public int getMonthContract() {
		return monthContract;
	}

	public void setMonthContract(int monthContract) {
		this.monthContract = monthContract;
	}

	public int getMonthPayContract() {
		return monthPayContract;
	}

	public void setMonthPayContract(int monthPayContract) {
		this.monthPayContract = monthPayContract;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public List<LoginRecord> getLoginRecordList() {
		return loginRecordList;
	}

	public void setLoginRecordList(List<LoginRecord> loginRecordList) {
		this.loginRecordList = loginRecordList;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getCusFromUrl() {
		return cusFromUrl;
	}

	public void setCusFromUrl(String cusFromUrl) {
		this.cusFromUrl = cusFromUrl;
	}

	public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

	public int getMonthPaySumMoney() {
		return monthPaySumMoney;
	}

	public void setMonthPaySumMoney(int monthPaySumMoney) {
		this.monthPaySumMoney = monthPaySumMoney;
	}

	public List<CusProtocalDetail> getCusProtocalDetailList() {
		return cusProtocalDetailList;
	}

	public void setCusProtocalDetailList(
			List<CusProtocalDetail> cusProtocalDetailList) {
		this.cusProtocalDetailList = cusProtocalDetailList;
	}

	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public java.util.Date getFreezeChangedDate() {
		return freezeChangedDate;
	}

	public void setFreezeChangedDate(java.util.Date freezeChangedDate) {
		this.freezeChangedDate = freezeChangedDate;
	}


	
}
