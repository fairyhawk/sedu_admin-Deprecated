package com.shangde.edu.cus.condition;

import com.shangde.common.vo.PageQuery;

public class QueryTotolsScoreCondition extends PageQuery {
    private int tsId;
    private int cusId;
    private int tsCurrent;
    private String cusName;
    
    public int getTsId(){
        return tsId;
    }

    public void setTsId(int tsId){
        this.tsId = tsId;
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getTsCurrent() {
		return tsCurrent;
	}

	public void setTsCurrent(int tsCurrent) {
		this.tsCurrent = tsCurrent;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
}