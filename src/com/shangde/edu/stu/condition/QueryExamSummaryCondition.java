package com.shangde.edu.stu.condition;

public class QueryExamSummaryCondition {
	
	/**
	 * 用户id
	 * */
	 private int cusId;
		
	/**
	 * 考试总结时间
	 * */
	private java.util.Date summarydate;
	
	
	private java.util.Date bfdate;
	
	private int examId;
	

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

	public java.util.Date getBfdate() {
		return bfdate;
	}

	public void setBfdate(java.util.Date bfdate) {
		this.bfdate = bfdate;
	}

	/**
	 * @return the examId
	 */
	public int getExamId() {
		return examId;
	}

	/**
	 * @param examId the examId to set
	 */
	public void setExamId(int examId) {
		this.examId = examId;
	}
       
	
}