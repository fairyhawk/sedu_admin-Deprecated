package com.shangde.edu.crm.domain;

import java.io.Serializable;

public class Sellrecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int crmId;
	/**
	 * 用户Id
	 */
    private int cusId;
    /**
     * 指派人id
     */
    private int sysUserId;
    /**
     * 指派人登录名
     */
    private String sellName;
    /**
     * 场景
     */
    private int scene;
    /**
     * 沟通内容
     */
    private String reason;
    /**
     * 创建时间
     */
    private java.util.Date sellTime;
    /**
     * 沟通时间
     */
    private java.util.Date reasonTime;
    /**
     * 指派人姓名
     */
    private String sysUserName;
    /**
     * 沟通状态
     */
    private int commuStatus;
    /**
     * 学员姓名
     */
    private String realName;
    /**
     * 学员性别
     */
    private String sex;
    /**
     * 学员手机
     */
    private String mobile;
    /**
     * 学员年龄
     */
    private int age=0;
    /**
     * 学员职业
     */
    private String profession;
    /**
     * 学员学习课程原因
     */
    private String studyReason;
    /**
     * 学员问题1
     */
    private String fProblem;
    /**
     * 学员问题2
     */
    private String twProblem;
    /**
     * 学员问题3
     */
    private String thProblem;
    /**
     * 学员顾虑1
     */
    private String fWorry;
    /**
     * 学员顾虑2
     */
    private String twWorry;
    /**
     * 学员顾虑3
     */
    private String thWorry;
    /**
     * 咨询类型
     */
    private int consultType;
        
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getStudyReason() {
		return studyReason;
	}

	public void setStudyReason(String studyReason) {
		this.studyReason = studyReason;
	}

	public String getFProblem() {
		return fProblem;
	}

	public void setFProblem(String problem) {
		fProblem = problem;
	}

	public String getTwProblem() {
		return twProblem;
	}

	public void setTwProblem(String twProblem) {
		this.twProblem = twProblem;
	}

	public String getThProblem() {
		return thProblem;
	}

	public void setThProblem(String thProblem) {
		this.thProblem = thProblem;
	}

	public String getFWorry() {
		return fWorry;
	}

	public void setFWorry(String worry) {
		fWorry = worry;
	}

	public String getTwWorry() {
		return twWorry;
	}

	public void setTwWorry(String twWorry) {
		this.twWorry = twWorry;
	}

	public String getThWorry() {
		return thWorry;
	}

	public void setThWorry(String thWorry) {
		this.thWorry = thWorry;
	}

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
        


	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason; 
    }
        
    public java.util.Date getSellTime(){
        return sellTime;
    }

    public void setSellTime(java.util.Date sellTime){
        this.sellTime = sellTime; 
    }
        
    public java.util.Date getReasonTime(){
        return reasonTime;
    }

    public void setReasonTime(java.util.Date reasonTime){
        this.reasonTime = reasonTime; 
    }

	public int getCrmId() {
		return crmId;
	}

	public void setCrmId(int crmId) {
		this.crmId = crmId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public int getCommuStatus() {
		return commuStatus;
	}

	public void setCommuStatus(int commuStatus) {
		this.commuStatus = commuStatus;
	}

	public int getScene() {
		return scene;
	}

	public void setScene(int scene) {
		this.scene = scene;
	}

	public int getConsultType() {
		return consultType;
	}

	public void setConsultType(int consultType) {
		this.consultType = consultType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
