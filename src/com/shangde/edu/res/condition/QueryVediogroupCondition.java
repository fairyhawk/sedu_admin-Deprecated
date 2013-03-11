package com.shangde.edu.res.condition;

import com.shangde.common.vo.PageQuery;

public class QueryVediogroupCondition extends PageQuery{
    private int vgId;
    private String searchKey;
    
    
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getVgId(){
        return vgId;
    }

    public void setVgId(int vgId){
        this.vgId = vgId;
    }
}