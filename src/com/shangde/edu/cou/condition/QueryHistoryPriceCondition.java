package com.shangde.edu.cou.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * 查询课程历史价格条件
 * @author chenshuai
 *
 */
public class QueryHistoryPriceCondition extends PageQuery{
	/**
	 * 历史价格ID
	 */
    private int id;
    
    /**
     * 课程ID
     */
    private int courseId;
    
    /**
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 售卖方式ID
     */
    private int sellId;
    
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
}