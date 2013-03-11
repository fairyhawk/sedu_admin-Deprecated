package com.shangde.edu.cus.condition;

public class QueryStudyPlanCondition {
    private int id;
    private int cusId;
    private String timeStr;
    private String startTime;
    private String endTime;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
}