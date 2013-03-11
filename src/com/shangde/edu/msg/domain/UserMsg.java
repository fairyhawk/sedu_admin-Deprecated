package com.shangde.edu.msg.domain;

import java.io.Serializable;

public class UserMsg implements Serializable{
	public static final int SENDER_TYPE_ADMIN = 2;//系统管理者
	public static final int SENDER_TYPE_CUSTOMER = 1;//网站用户
	
    private int msgId;
    private int id;
    private int senderId;
    private int receiverId;
    private int msgStatus;
    private java.util.Date sendTime;
    private java.util.Date checkTime;
    private int isExpired;
    private int senderType;
    private int receiverType; 
    private String senderName;
    private String receiverName;
    
    private Message msg;
    
    public int getMsgId(){
        return msgId;
    }

    public void setMsgId(int msgId){
        this.msgId = msgId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getSenderId(){
        return senderId;
    }

    public void setSenderId(int senderId){
        this.senderId = senderId; 
    }
        
    public int getReceiverId(){
        return receiverId;
    }

    public void setReceiverId(int receiverId){
        this.receiverId = receiverId; 
    }
        
    public int getMsgStatus(){
        return msgStatus;
    }

    public void setMsgStatus(int msgStatus){
        this.msgStatus = msgStatus; 
    }
        
    public java.util.Date getSendTime(){
        return sendTime;
    }

    public void setSendTime(java.util.Date sendTime){
        this.sendTime = sendTime; 
    }
        
    public java.util.Date getCheckTime(){
        return checkTime;
    }

    public void setCheckTime(java.util.Date checkTime){
        this.checkTime = checkTime; 
    }
        
    public int getIsExpired(){
        return isExpired;
    }

    public void setIsExpired(int isExpired){
        this.isExpired = isExpired; 
    }

	public int getSenderType() {
		return senderType;
	}

	public void setSenderType(int senderType) {
		this.senderType = senderType;
	}

	public int getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(int receiverType) {
		this.receiverType = receiverType;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}
	
}
