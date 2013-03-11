package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

public class QueryCourKpJSONCondition extends PageQuery{
    
	private  int cousid;
	private int eptye;
	private String epkeyword;
	
	public int getCousid() {
		return cousid;
	}
	public void setCousid(int cousid) {
		this.cousid = cousid;
	}
	public int getEptye() {
		return eptye;
	}
	public void setEptye(int eptye) {
		this.eptye = eptye;
	}
	public String getEpkeyword() {
		return epkeyword;
	}
	public void setEpkeyword(String epkeyword) {
		this.epkeyword = epkeyword;
	}
	
	
}