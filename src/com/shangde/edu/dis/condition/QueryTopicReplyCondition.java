package com.shangde.edu.dis.condition;

import com.shangde.common.vo.PageQuery;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.dis.domain.TopicReply;

public class QueryTopicReplyCondition extends PageQuery implements java.io.Serializable{
    private String title;
    private String cusName;
    private String email;
    private int cusId;
    private String replyContent;
    private String replyTime;
    private int replyId;
    private int topicId;
    private String photo;
    
    private Customer customer;
    private Customer targetCustomer;
    
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customer getTargetCustomer() {
		return targetCustomer;
	}
	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}