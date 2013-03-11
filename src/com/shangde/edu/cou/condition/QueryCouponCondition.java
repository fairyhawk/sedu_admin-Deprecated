package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

public class QueryCouponCondition extends PageQuery{
    private int id;
    private String cInfo;
    private int cusId;
    private String idsStr;
    private String courses;
    private int[] ids;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public String getCInfo() {
		return cInfo;
	}

	public void setCInfo(String info) {
		cInfo = info;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getIdsStr() {
		return idsStr;
	}

	public void setIdsStr(String idsStr) {
		this.idsStr = idsStr;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}
}