package com.shangde.edu.exam.condition;

/**
 * 查询评论条件
 * @author chenshuai
 *
 */
public class QueryReviewsCondition {
	
	/**
	 * 评论ID
	 */
    private int rvId;
    
    /**
     * 试卷ID
     */
    private int epId;
        
    public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getRvId(){
        return rvId;
    }

    public void setRvId(int rvId){
        this.rvId = rvId;
    }
}