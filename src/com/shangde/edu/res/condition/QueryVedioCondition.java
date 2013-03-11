package com.shangde.edu.res.condition;

import com.shangde.common.vo.PageQuery;

public class QueryVedioCondition extends PageQuery{
    private int voId;
    private String searchKey;
    
    

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getVoId(){
        return voId;
    }

    public void setVoId(int voId){
        this.voId = voId;
    }
}