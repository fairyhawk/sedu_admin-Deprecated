package com.shangde.edu.kb.condition;

import com.shangde.common.vo.PageQuery;

public class QueryChapterCondition extends PageQuery {
    private int chId;
    private int cId;
        
    public int getChId(){
        return chId;
    }

    public void setChId(int chId){
        this.chId = chId;
    }

	public int getCId() {
		return cId;
	}

	public void setCId(int id) {
		cId = id;
	}
}