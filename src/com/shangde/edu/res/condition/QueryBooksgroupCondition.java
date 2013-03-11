package com.shangde.edu.res.condition;

import com.shangde.common.vo.PageQuery;

public class QueryBooksgroupCondition extends PageQuery{
    private int bgId;
    private String searchKey;
    
    
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getBgId(){
        return bgId;
    }

    public void setBgId(int bgId){
        this.bgId = bgId;
    }
}