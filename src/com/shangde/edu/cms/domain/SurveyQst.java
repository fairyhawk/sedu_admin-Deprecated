package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class SurveyQst implements Serializable{
    private int sqId;
    private String sqContent;
    private java.util.Date createTime;
    private String surveyName;
    private int sqType;
    private String sqOptions;
        
    public int getSqId(){
        return sqId;
    }

    public void setSqId(int sqId){
        this.sqId = sqId; 
    }
        
    public String getSqContent(){
        return sqContent;
    }

    public void setSqContent(String sqContent){
        this.sqContent = sqContent; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public String getSurveyName(){
        return surveyName;
    }

    public void setSurveyName(String surveyName){
        this.surveyName = surveyName; 
    }

	public int getSqType() {
		return sqType;
	}

	public void setSqType(int sqType) {
		this.sqType = sqType;
	}

	public String getSqOptions() {
		return sqOptions;
	}

	public void setSqOptions(String sqOptions) {
		this.sqOptions = sqOptions;
	}
}
