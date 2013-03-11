package com.shangde.edu.msg.condition;

import com.shangde.common.vo.PageQuery;

public class QueryMessageCondition extends PageQuery{
    private int msgId;
    private String searchKey;
    
    public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getMsgId(){
        return msgId;
    }

    public void setMsgId(int msgId){
        this.msgId = msgId;
    }
}