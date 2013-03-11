package com.shangde.edu.velocity.domain;

import java.util.Date;


/**
 * 网速测试
 * @author wangzheng
 *
 */
public class Velocity {
	
	private int id;  
	private String mail;  //邮箱
	private String mobile;	 //联系电话
	private int province; //省份
	private int district;  //地区
	private int subject;   //专业项目
	private String browser;  //浏览器
	private String network;  //网络
	private Integer broadBand; //测试结果
	private String problem; //问题描述
	private Date submitTime; //提交时间
	private String solveDepartment;//解决部门
	private String solveScheme;//解决方案
	private Date solveTime;//解决时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) {
		this.district = district;
	}
	public int getSubject() {
		return subject;
	}
	public void setSubject(int subject) {
		this.subject = subject;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getSolveDepartment() {
		return solveDepartment;
	}
	public void setSolveDepartment(String solveDepartment) {
		this.solveDepartment = solveDepartment;
	}
	public String getSolveScheme() {
		return solveScheme;
	}
	public void setSolveScheme(String solveScheme) {
		this.solveScheme = solveScheme;
	}
	public Date getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}
	public Integer getBroadBand() {
		return broadBand;
	}
	public void setBroadBand(Integer broadBand) {
		this.broadBand = broadBand;
	}
	
}
