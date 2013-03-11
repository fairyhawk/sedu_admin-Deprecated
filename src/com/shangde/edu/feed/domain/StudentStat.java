package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

public class StudentStat implements Serializable {

	/**
	 * 微学习学员统计id
	 */
	private Integer id;
	/**
	 * 来源id
	 */
	private Integer fromId;
	/**
	 * 页流量(独立ip)
	 */
	private Integer pageFlow;
	/**
	 * 注册人数
	 */
	private Integer registerCount;
	/**
	 * 存放注册用户的id
	 */
	private String registerIds;
	/**
	 * 登录人数
	 */
	private Integer loginCount;
	/**
	 * 存放登录用户的id
	 */
	private String loginIds;
	/**
	 * 跳出人数
	 */
	private Integer outCount;
	/**
	 * 到达微学习页人数
	 */
	private Integer arriveWxxCount;
	/**
	 * 存放到达微学习页的用户id
	 */
	private String arriveWxxIds;
	/**
	 * 首次用户访问数量
	 */
	private Integer firstUserCount;
	/**
	 * 存放首次用户访问id
	 */
	private String firstUserIds;
	/**
	 * 非重复用户人数
	 */
	private Integer nonRepeatUserCount;
	/**
	 * 存放非重复用户id
	 */
	private String nonRepeatUserIds;
	/**
	 * 重复用户人数
	 */
	private Integer repeatUserCount;
	/**
	 * 存放重复用户id
	 */
	private String repeatUserIds;
	/**
	 * 带来订单数量
	 */
	private Integer orderCount;
	/**
	 * 存放带来订单数量id
	 */
	private String orderIds;

	/**
	 * 流水数量
	 */
	private double runningWaterCount;
	/**
	 * 查询那一天的日期 yyyy-MM-dd
	 */
	private String searchDate;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public Integer getPageFlow() {
		return pageFlow;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public void setPageFlow(Integer pageFlow) {
		this.pageFlow = pageFlow;
	}

	public Integer getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}

	public String getRegisterIds() {
		return registerIds;
	}

	public void setRegisterIds(String registerIds) {
		this.registerIds = registerIds;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginIds() {
		return loginIds;
	}

	public void setLoginIds(String loginIds) {
		this.loginIds = loginIds;
	}

	public Integer getOutCount() {
		return outCount;
	}

	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}

	public Integer getArriveWxxCount() {
		return arriveWxxCount;
	}

	public void setArriveWxxCount(Integer arriveWxxCount) {
		this.arriveWxxCount = arriveWxxCount;
	}

	public String getArriveWxxIds() {
		return arriveWxxIds;
	}

	public void setArriveWxxIds(String arriveWxxIds) {
		this.arriveWxxIds = arriveWxxIds;
	}

	public Integer getFirstUserCount() {
		return firstUserCount;
	}

	public void setFirstUserCount(Integer firstUserCount) {
		this.firstUserCount = firstUserCount;
	}

	public String getFirstUserIds() {
		return firstUserIds;
	}

	public void setFirstUserIds(String firstUserIds) {
		this.firstUserIds = firstUserIds;
	}

	public Integer getNonRepeatUserCount() {
		return nonRepeatUserCount;
	}

	public void setNonRepeatUserCount(Integer nonRepeatUserCount) {
		this.nonRepeatUserCount = nonRepeatUserCount;
	}

	public String getNonRepeatUserIds() {
		return nonRepeatUserIds;
	}

	public void setNonRepeatUserIds(String nonRepeatUserIds) {
		this.nonRepeatUserIds = nonRepeatUserIds;
	}

	public Integer getRepeatUserCount() {
		return repeatUserCount;
	}

	public void setRepeatUserCount(Integer repeatUserCount) {
		this.repeatUserCount = repeatUserCount;
	}

	public String getRepeatUserIds() {
		return repeatUserIds;
	}

	public void setRepeatUserIds(String repeatUserIds) {
		this.repeatUserIds = repeatUserIds;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public double getRunningWaterCount() {
		return runningWaterCount;
	}

	public void setRunningWaterCount(double runningWaterCount) {
		this.runningWaterCount = runningWaterCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the searchDate
	 */
	public String getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate
	 *            the searchDate to set
	 */
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

}
