package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageResult;

public class QuerySurveyQstCondition  extends PageResult{
    private int sqId;
    
    private String surveyName;
    
    private String createTime;
        
    public int getSqId(){
        return sqId;
    }

    public void setSqId(int sqId){
        this.sqId = sqId;
    }

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}