package com.shangde.edu.tk.domain;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	//推荐任务
	public static final int TASK_IS_RECOMMOND = 1;
	//置顶任务
	public static final int TASK_IS_TOP = 1;
	//置顶任务
	public static final int TASK_HAS_PRE_TASK = 1;
	//限时任务
	public static final int TASK_IS_LIMITTIME_TASK = 1;
	//积分
	public static final int TASK_IS_JIFEN = 1;
	//发布状态
	public static final int TASK_IS_PUBLISH = 1;
	//完善电话关键字
	public static final String TASK_KEY_TELEPHONE = "telephone";
	//学习课程关键字
	public static final String TASK_KEY_STUDYCOURSE = "studycourse";
	//第一次评论课程
	public static final String TASK_KEY_EVALUATECOURSE = "evaluatecourse";
	
	//第一次评论课程
	public static final String TASK_KEY_ANSWER_1 = "answer_1";
	
	//第5次评论课程
	public static final String TASK_KEY_ANSWER_5 = "answer_5";
	
	//第10次评论课程
	public static final String TASK_KEY_ANSWER_10 = "answer_10";
	
	//第20次评论课程
	public static final String TASK_KEY_ANSWER_20 = "answer_20";
	
	//第50次评论课程
	public static final String TASK_KEY_ANSWER_50 = "answer_50";
	
	//第100次评论课程
	public static final String TASK_KEY_ANSWER_100 = "answer_100";
	
	//第一次评论课程
	public static final String TASK_KEY_TEST = "test";
	
    private int taskId;
    private int sortNum;
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
    private int userType;
    private int preTaskId;
    private Date limitTimeStart;
    private Date limitTimeEnd;
    private int resultType;
    private int judgeLevel;
    private String judgeUrl;
    private String executeObj;
    private String executePoint;
    private int executeTime;
    private int jifen;
    private int experience;
    private int isJifen;
    private String iconUrl;
    private Date createTime;
    private int isLimitTimeTask;
    private int isHasPreTask;
    
    private int publishStatus;
    
    private String keyWord;
        
    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId = taskId; 
    }
        
    public int getSortNum(){
        return sortNum;
    }

    public void setSortNum(int sortNum){
        this.sortNum = sortNum; 
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
        
    public int getUserType(){
        return userType;
    }

    public void setUserType(int userType){
        this.userType = userType; 
    }
        
    public int getPreTaskId(){
        return preTaskId;
    }

    public void setPreTaskId(int preTaskId){
        this.preTaskId = preTaskId; 
    }
        
    public java.util.Date getLimitTimeStart(){
        return limitTimeStart;
    }

    public void setLimitTimeStart(java.util.Date limitTimeStart){
        this.limitTimeStart = limitTimeStart; 
    }
        
    public java.util.Date getLimitTimeEnd(){
        return limitTimeEnd;
    }

    public void setLimitTimeEnd(java.util.Date limitTimeEnd){
        this.limitTimeEnd = limitTimeEnd; 
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
        
    public String getExecuteObj(){
        return executeObj;
    }

    public void setExecuteObj(String executeObj){
        this.executeObj = executeObj; 
    }
        
    public String getExecutePoint(){
        return executePoint;
    }

    public void setExecutePoint(String executePoint){
        this.executePoint = executePoint; 
    }
        
    public int getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(int executeTime){
        this.executeTime = executeTime; 
    }
        
    public int getJifen(){
        return jifen;
    }

    public void setJifen(int jifen){
        this.jifen = jifen; 
    }
        
    public int getExperience(){
        return experience;
    }

    public void setExperience(int experience){
        this.experience = experience; 
    }
        
    public int getIsJifen(){
        return isJifen;
    }

    public void setIsJifen(int isJifen){
        this.isJifen = isJifen; 
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

	public int getIsLimitTimeTask() {
		return isLimitTimeTask;
	}

	public void setIsLimitTimeTask(int isLimitTimeTask) {
		this.isLimitTimeTask = isLimitTimeTask;
	}

	public int getIsHasPreTask() {
		return isHasPreTask;
	}

	public void setIsHasPreTask(int isHasPreTask) {
		this.isHasPreTask = isHasPreTask;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public boolean equals(Object obj) {
		if(obj != null){
			Task temp = (Task)obj;
			return new Integer(this.getTaskId()).equals(temp.getTaskId());
		}
		return false;
	}

	public int hashCode() {
		return this.taskId * 17;
	}
	
	
	
}
