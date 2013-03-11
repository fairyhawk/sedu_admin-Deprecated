package com.shangde.edu.msg.domain;

import java.io.Serializable;

public class Message implements Serializable{
	
	public static final int MSG_TYPE_SEND_TO_SINGLE = 1;
	public static final int MSG_TYPE_SEND_TO_ALL = 2;
	public static final int MSG_TYPE_SEND_TO_INNER = 3;
	public static final int MSG_TYPE_SEND_TO_WEBUSER = 4;
	public static final int MSG_TYPE_SEND_TO_TIMING = 5;
    private int msgId;
  //表示某个版块的提示信息。1：建议;2:问答；3：讨论；4：学习计划；5：课程相关；6：平台欢迎词
    private int msgType;
    private int msgSort;
    private String msgContent;
    private java.util.Date createTime;
    private String msgTitle;
    private int senderType;
    private int senderId;
    private String senderName;
    private String keyWord;//关键字，一般消息无关键字，除指定消息
    private int cmtId;//用户建议ID
    private int repId;//管理员回复ID
        
    public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public int getCmtId() {
		return cmtId;
	}

	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}

	public int getMsgId(){
        return msgId;
    }

    public void setMsgId(int msgId){
        this.msgId = msgId; 
    }
        
    public int getMsgType(){
        return msgType;
    }

    public void setMsgType(int msgType){
        this.msgType = msgType; 
    }
        
    public int getMsgSort(){
        return msgSort;
    }

    public void setMsgSort(int msgSort){
        this.msgSort = msgSort; 
    }
        
    public String getMsgContent(){
        return msgContent;
    }

    public void setMsgContent(String msgContent){
        this.msgContent = msgContent; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public String getMsgTitle(){
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle){
        this.msgTitle = msgTitle; 
    }

	public int getSenderType() {
		return senderType;
	}

	public void setSenderType(int senderType) {
		this.senderType = senderType;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
