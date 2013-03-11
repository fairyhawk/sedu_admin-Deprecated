package com.shangde.edu.vst.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-9
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class VideoStatisticsCondition extends PageQuery{
	
	private int subjectId;
	
	private Date start;
	
	private Date end;
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
