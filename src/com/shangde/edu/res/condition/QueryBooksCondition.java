package com.shangde.edu.res.condition;

import com.shangde.common.vo.PageQuery;

public class QueryBooksCondition extends PageQuery{
    private int bkId;
    private String searchKey;
    
    
	
    public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getBkId(){
        return bkId;
    }

    public void setBkId(int bkId){
        this.bkId = bkId;
    }

	
    
}