package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

public class QueryClazzCondition extends PageQuery{
    private int clazzId;
    
    private String searchKey;
    
    private  int subjectId;
        
    public int getClazzId(){
        return clazzId;
    }

    public void setClazzId(int clazzId){
        this.clazzId = clazzId;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}