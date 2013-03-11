package com.shangde.edu.msg.condition;

import com.shangde.common.vo.PageQuery;

public class QueryUserMsgCondition extends PageQuery{
    private int id;
    private int senderId;
    private int receiverId;
    private int senderType;
    private int receiverType; 
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
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
}