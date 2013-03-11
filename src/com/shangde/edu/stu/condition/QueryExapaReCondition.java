package com.shangde.edu.stu.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 查询试卷记录条件
 * @author chenshuai
 *
 */
public class QueryExapaReCondition extends PageQuery{
	/**
	 * 用户ID
	 */
	private int cusId;
	
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 添加时间
	 * 
	 *  范昕====2011-05-26 添加
	 */
    private java.util.Date addtime;
    

	/**
	 * 添加时间
	 * 
	 *  范昕====2011-05-26 添加
	 */
    private java.util.Date bftime;
    
 
    /**
     * 结束时间
     */
    private java.util.Date endTime;
    
  
    
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public java.util.Date getAddtime() {
		return addtime;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public java.util.Date getBftime() {
		return bftime;
	}

	public void setBftime(java.util.Date bftime) {
		this.bftime = bftime;
	}

	
	public java.util.Date getEndTime() {
		if(endTime!=null){
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
		}
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	
	
}