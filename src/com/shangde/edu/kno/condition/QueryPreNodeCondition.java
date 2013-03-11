package com.shangde.edu.kno.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QueryPreNodeCondition extends PageQuery implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int preNodeId;
    private int preItemId;
    
        
    public int getPreItemId() {
		return preItemId;
	}

	public void setPreItemId(int preItemId) {
		this.preItemId = preItemId;
	}

	public int getPreNodeId(){
        return preNodeId;
    }

    public void setPreNodeId(int preNodeId){
        this.preNodeId = preNodeId;
    }
}