package com.shangde.edu.tk.dto;

import java.io.Serializable;
import java.util.Date;

public class NomalTaskDTO implements Serializable{	
    private int taskId;
    private String taskName;
    private int isRecommend;
    private int isTop;
    private int sortId;
    private String executeUrl;
    private String taskTarget;
    private String taskContent;
    private String taskTips;
    private String taskType;
    private int limitNum;
    private int taskLevel;
    private int resultType;
    private int judgeLevel;
    private String judgeUrl;
    private int experience;
    private String iconUrl;
    private Date createTime;
    private int jifen;
    private int preTaskId;
    private String keyWord;
        
    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId = taskId; 
    }
        
    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName; 
    }
        
    public int getIsRecommend(){
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend){
        this.isRecommend = isRecommend; 
    }
        
    public int getIsTop(){
        return isTop;
    }

    public void setIsTop(int isTop){
        this.isTop = isTop; 
    }
        
    public int getSortId(){
        return sortId;
    }

    public void setSortId(int sortId){
        this.sortId = sortId; 
    }
        
    public String getExecuteUrl(){
        return executeUrl;
    }

    public void setExecuteUrl(String executeUrl){
        this.executeUrl = executeUrl; 
    }
        
    public String getTaskTarget(){
        return taskTarget;
    }

    public void setTaskTarget(String taskTarget){
        this.taskTarget = taskTarget; 
    }
        
    public String getTaskContent(){
        return taskContent;
    }

    public void setTaskContent(String taskContent){
        this.taskContent = taskContent; 
    }
        
    public String getTaskTips(){
        return taskTips;
    }

    public void setTaskTips(String taskTips){
        this.taskTips = taskTips; 
    }
        
    public String getTaskType(){
        return taskType;
    }

    public void setTaskType(String taskType){
        this.taskType = taskType; 
    }
        
    public int getLimitNum(){
        return limitNum;
    }

    public void setLimitNum(int limitNum){
        this.limitNum = limitNum; 
    }
        
    public int getTaskLevel(){
        return taskLevel;
    }

    public void setTaskLevel(int taskLevel){
        this.taskLevel = taskLevel; 
    }
        
        
    public int getResultType(){
        return resultType;
    }

    public void setResultType(int resultType){
        this.resultType = resultType; 
    }
        
    public int getJudgeLevel(){
        return judgeLevel;
    }

    public void setJudgeLevel(int judgeLevel){
        this.judgeLevel = judgeLevel; 
    }
        
    public String getJudgeUrl(){
        return judgeUrl;
    }

    public void setJudgeUrl(String judgeUrl){
        this.judgeUrl = judgeUrl; 
    }
        
        
    public int getExperience(){
        return experience;
    }

    public void setExperience(int experience){
        this.experience = experience; 
    }
        
    public String getIconUrl(){
        return iconUrl;
    }

    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public int getPreTaskId() {
		return preTaskId;
	}

	public void setPreTaskId(int preTaskId) {
		this.preTaskId = preTaskId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
