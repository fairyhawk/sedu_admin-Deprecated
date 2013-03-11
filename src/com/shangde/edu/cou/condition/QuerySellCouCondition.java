package com.shangde.edu.cou.condition;

public class QuerySellCouCondition {
    private int id;
    private int courseId;
    private int sellId;
        
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}