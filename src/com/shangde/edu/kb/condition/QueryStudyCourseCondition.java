package com.shangde.edu.kb.condition;

import com.shangde.common.vo.PageQuery;

public class QueryStudyCourseCondition extends PageQuery{
    private int cId;
    private int pId;
    
    public int getCId(){
        return cId;
    }

    public void setCId(int cId){
        this.cId = cId;
    }

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}
}