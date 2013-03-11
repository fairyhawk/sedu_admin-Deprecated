package com.shangde.edu.sp.condition;

import com.shangde.common.vo.PageQuery;


public class QuerySentPersonCondition extends PageQuery {
	
	private int Id;
    private String personName;
    private String personPhone;
 		   

	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	
	public String getPersonPhone() {
		return personPhone;
	}


	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	

}
