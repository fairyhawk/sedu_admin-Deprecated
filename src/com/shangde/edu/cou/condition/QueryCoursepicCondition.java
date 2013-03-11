package com.shangde.edu.cou.condition;

/**
 * 查询课程图片条件
 * @author chenshuai
 *
 */
public class QueryCoursepicCondition {
	/**
	 * 图片ID
	 */
    private int picId;
    
    /**
     * 课程ID
     */
    private int courseId;
    
    /**
     * 是否是首图片
     */
    private int homeFlag;
        
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getHomeFlag() {
		return homeFlag;
	}

	public void setHomeFlag(int homeFlag) {
		this.homeFlag = homeFlag;
	}

	public int getPicId(){
        return picId;
    }

    public void setPicId(int picId){
        this.picId = picId;
    }
}