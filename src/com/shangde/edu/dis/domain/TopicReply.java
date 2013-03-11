package com.shangde.edu.dis.domain;

import java.io.Serializable;

import com.shangde.edu.cus.domain.Customer;

public class TopicReply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6035997108029431730L;
	private int id;
	private int topicId;
	private int disId;
	private String content;
	private java.util.Date replyTime;
	private int cusId;
	private Customer customer;
	
	//private int target_cus_id;
	//private int target_reply_id
	private Customer targetCustomer;
	private TopicReply targetTopicReply;

	public Customer getTargetCustomer() {
		return targetCustomer;
	}

	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}

	public TopicReply getTargetTopicReply() {
		return targetTopicReply;
	}

	public void setTargetTopicReply(TopicReply targetTopicReply) {
		this.targetTopicReply = targetTopicReply;
	}

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

	public int getDisId() {
		return disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(java.util.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
