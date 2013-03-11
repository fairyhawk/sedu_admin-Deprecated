package com.shangde.edu.crm.domain;

import java.io.Serializable;

public class Users implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int cusId;
    /**0
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String mobile;
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
     * 注册时间
     */
    private java.util.Date regTime;
    /**
     * qq号
     */
    private String qq;
    /**
     * 用户类型
     * 1注册2乐语在线3乐语转注册
     */
    private int userType;
    /**
     * 专业id
     */
    private int subjectId;
    /**
     * 年龄
     */
    private int age;
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
     * 项目名字
     */
    private String subjectName;
        
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

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
        
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
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
        
    public java.util.Date getRegTime(){
        return regTime;
    }

    public void setRegTime(java.util.Date regTime){
        this.regTime = regTime; 
    }
        
    public String getQq(){
        return qq;
    }

    public void setQq(String qq){
        this.qq = qq; 
    }
        
    public int getUserType(){
        return userType;
    }

    public void setUserType(int userType){
        this.userType = userType; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age; 
    }

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
    
    
}
