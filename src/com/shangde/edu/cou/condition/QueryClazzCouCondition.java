package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

public class QueryClazzCouCondition extends PageQuery{
    private int id;
    
    private int clazzId;
    
    private int courseId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public int getClazzId() {
		return clazzId;
	}

	public void setClazzId(int clazzId) {
		this.clazzId = clazzId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}