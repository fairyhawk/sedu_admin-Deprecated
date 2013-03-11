package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

public class QueryTcCouRecordCondition  extends PageQuery{
    private Integer id;
    private Integer tcId;
    private Integer courseId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTcId() {
		return tcId;
	}
	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}