package com.shangde.edu.sp.condition;

import com.shangde.common.vo.PageQuery;


public class QuerySentPersonInfoCondition extends PageQuery {
	
	private int Id;
    private int spId;
    private int subjectId;
    
    private String subjectIds;

    
    
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public int getSpId() {
		return spId;
	}
	
	public void setSpId(int spId) {
		this.spId = spId;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}
	

}
