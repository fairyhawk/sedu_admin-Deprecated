package com.shangde.edu.cus.condition;

import com.shangde.common.vo.PageQuery;

public class QueryReProblemCondition extends PageQuery{
    private int reId;
    private int pblId;
    private int reManType;
    private int reManId;
    
    
    public int getReId(){
        return reId;
    }

    public void setReId(int reId){
        this.reId = reId;
    }

	public int getPblId() {
		return pblId;
	}

	public void setPblId(int pblId) {
		this.pblId = pblId;
	}

	public int getReManType() {
		return reManType;
	}

	public void setReManType(int reManType) {
		this.reManType = reManType;
	}

	public int getReManId() {
		return reManId;
	}

	public void setReManId(int reManId) {
		this.reManId = reManId;
	}
}