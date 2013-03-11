package com.shangde.edu.crm.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryUsersCondition extends PageQuery {
    private int id;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String mobile;
    
    
    /**
     * 用户类型
     * 1注册2乐语在线3乐语转注册
     */
    private int userType;
    
    /**
     * 注册时间
     */
    private java.util.Date regTime; 
    
	private Date staregTime;
	private Date endregTime;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别
     * 1女2男
     */
    private String sex;
	   /**
     * 职业
     */
    private String profession;
    /**
     * 地址
     */
    private String address;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 项目id
     */
    private int subjectId;
 
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	
	

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	

	public java.util.Date getRegTime() {
		return regTime;
	}

	public void setRegTime(java.util.Date regTime) {
		this.regTime = regTime;
	}

	public Date getStaregTime() {
		return staregTime;
	}

	public void setStaregTime(Date staregTime) {
		this.staregTime = staregTime;
	}

	public Date getEndregTime() {
		return endregTime;
	}

	public void setEndregTime(Date endregTime) {
		this.endregTime = endregTime;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public int getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
}