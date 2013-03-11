package com.shangde.edu.dis.dto;

import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

public class DisReTopicDTO implements java.io.Serializable {

	private int id; // 回复的内容ID
	private int topicId; // 话题ID
	private int cusId; // 创建ID
	private int disId; // 小组ID
	private String topicContent; // 话题内容
	private String reTopicContent; // 回复内容
	private Date topicTime; // 话题创建时间
	private Date reTopicTime; // 话题回复时间
	private Customer customer; // 当前话题回复人
	private Customer targetCustomer; // 针对的某人的回复

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getDisId() {
		return disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public String getReTopicContent() {
		return reTopicContent;
	}

	public void setReTopicContent(String reTopicContent) {
		this.reTopicContent = reTopicContent;
	}

	public Date getTopicTime() {
		return topicTime;
	}

	public void setTopicTime(Date topicTime) {
		this.topicTime = topicTime;
	}

	public Date getReTopicTime() {
		return reTopicTime;
	}

	public void setReTopicTime(Date reTopicTime) {
		this.reTopicTime = reTopicTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getTargetCustomer() {
		return targetCustomer;
	}

	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}

}
