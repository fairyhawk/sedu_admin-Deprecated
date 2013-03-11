package com.shangde.edu.kb.condition;

import com.shangde.common.vo.PageQuery;

public class QuerySectionCondition extends PageQuery{
    private int sId;
    private int chId;
        
    public int getSId(){
        return sId;
    }

    public void setSId(int sId){
        this.sId = sId;
    }

	public int getChId() {
		return chId;
	}

	public void setChId(int chId) {
		this.chId = chId;
	}
}