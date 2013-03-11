package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 查询推荐课程条件
 * @author chenshuai
 *
 */
public class QueryTjcourseCondition  extends PageQuery{
	/**
	 * 推荐课程ID
	 */
    private int id;
    
    /**
     * 被推荐的课程ID
     */
    private int ycourseId;
        
    public int getYcourseId() {
		return ycourseId;
	}

	public void setYcourseId(int ycourseId) {
		this.ycourseId = ycourseId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}