package com.shangde.edu.stu.condition;

public class QueryCourseSummaryCondition {
	
	/**
	 * 用户id
	 * */
	 private int cusId;
	 
	/**
	 * 课程总结时间
	 * */
	private java.util.Date summarydate;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	
	public java.util.Date getSummarydate() {
		return summarydate;
	}

	public void setSummarydate(java.util.Date summarydate) {
		this.summarydate = summarydate;
	}
    
	 
	 
}