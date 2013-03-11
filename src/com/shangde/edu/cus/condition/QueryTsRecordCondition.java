package com.shangde.edu.cus.condition;

import com.shangde.common.vo.PageQuery;

public class QueryTsRecordCondition extends PageQuery{
	private int cusId;
    private int trId;
    private int tsId;
    private int trType;
    
    
    public int getTrId(){
        return trId;
    }

    public void setTrId(int trId){
        this.trId = trId;
    }

	public int getTsId() {
		return tsId;
	}

	public void setTsId(int tsId) {
		this.tsId = tsId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getTrType() {
		return trType;
	}

	public void setTrType(int trType) {
		this.trType = trType;
	}
}