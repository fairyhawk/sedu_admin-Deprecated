package com.shangde.edu.stu.domain;

import java.util.Date;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-3
 * @version 	修改人:
 * 				修改日期:
 * 				
 *用户行为统计详细表(对应stu_video_statistics_detail_tbl)              
 */
public class VideoStatisticsDetail {
	
	private int id;
	private int cusId;
	private int courseId;
	private int pointId;
	private Date viewDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusId() {
		return cusId;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	
}
