package com.shangde.edu.crm.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QueryChanceCondition extends PageQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 销售机会ID
	 */
    private Integer id;
    
    private int drawStatus;
    
    /**
     * 销售人员ID（后台用户登录ID）
     */
    private Integer userId;
    
    /**
     * 机会创建开始时间
     */
    private String startTime;
    
    /**
     * 机会创建结束时间
     */
    private String endTime;
    
    /**
     * 域名来源
     */
    private String webName;
    
    /**
     * 机会来源
     */
    private Integer origin;
    
    /**
     * 用户跟踪状态
     */
    private Integer followStatus;
    
    /**
     * 所属项目
     */
    private Integer subjectId;
    
    /**
     * 手机号码
     */
    private String mobile;
    
    /**
     * 销售坐席
     */
    private String userName;
    
    /**
     * 销售机会状态
     */
    private Integer salesStatus;
    
    /**
     * 邮箱地址
     */
    private String email;
    
    /**
     * 咨询状态
     */
    private Integer consultStatus;
    
    /**
     * 最后沟通状态
     */
    private Integer endCommStatus;

    /**
     * 创建开始时间的时分秒
     */
    private String startHH;
    
    /**
     * 创建结束时间的时分秒
     */
    private String endHH;
    
    /**
     * 沟通记录数开始
     */
    private Integer chanceNum1;
    
    /**
     * 沟通记录数结束
     */
    private Integer chanceNum2;
    /**
     * 筛选类型
     */
    private int orderType;
    
    private int  mesStatus;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(Integer salesStatus) {
		this.salesStatus = salesStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(Integer consultStatus) {
		this.consultStatus = consultStatus;
	}

	public Integer getEndCommStatus() {
		return endCommStatus;
	}

	public void setEndCommStatus(Integer endCommStatus) {
		this.endCommStatus = endCommStatus;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChanceNum1() {
		return chanceNum1;
	}

	public void setChanceNum1(Integer chanceNum1) {
		this.chanceNum1 = chanceNum1;
	}

	public Integer getChanceNum2() {
		return chanceNum2;
	}

	public void setChanceNum2(Integer chanceNum2) {
		this.chanceNum2 = chanceNum2;
	}

	public int getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(int drawStatus) {
		this.drawStatus = drawStatus;
	}

	public int getMesStatus() {
		return mesStatus;
	}

	public void setMesStatus(int mesStatus) {
		this.mesStatus = mesStatus;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
  
}