package com.shangde.edu.crm.domain;

import java.io.Serializable;
import java.util.Date;

public class Chance implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    /**
     * 用户库用户id
     */
    private int crmUserId;
    /**
     * 机会来源
     */
    private int origin;
    
    /**
     * 域名来源
     */
    private String webName;
   
    /**
     * 项目id
     */
    private int subjectId;
    /**
     * 用户跟踪状态
     * 1放弃2跟踪3热点4成交
     */
    private int followStatus;
    /**
     * 销售坐席id
     */
    private int userId;
    /**
     * 创建乐语销售坐席id
     */
    private int createUserId;
    /**
     * 创建时间
     */
    private java.util.Date chanceStime;
    /**
     * 更新时间
     */
    private java.util.Date chanceUtime;
    /**
     * 沟通记录数量
     */
    private int chanceNumber;
    
    /**
     * 最后沟通时间
     */
    private Date endCommTime;
    
    /**
     * 最后沟通状态
     */
    private int endCommStatus;
    
    /**
     * 咨询状态
     */
    private int consultStatus;
    /**
     * 
     */
    private int backUserId;
    
    /**
     * 领取状态
     */
    private int drawStatus;
    /**
     * 是否主动放弃 0否 1是
     */
    private int autoGiveup;
    
    
    
    public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCrmUserId(){
        return crmUserId;
    }

    public void setCrmUserId(int crmUserId){
        this.crmUserId = crmUserId; 
    }
        
    public int getOrigin(){
        return origin;
    }

    public void setOrigin(int origin){
        this.origin = origin; 
    }
        
    public String getWebName(){
        return webName;
    }

    public void setWebName(String webName){
        this.webName = webName; 
    }
        
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getFollowStatus(){
        return followStatus;
    }

    public void setFollowStatus(int followStatus){
        this.followStatus = followStatus; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public java.util.Date getChanceStime(){
        return chanceStime;
    }

    public void setChanceStime(java.util.Date chanceStime){
        this.chanceStime = chanceStime; 
    }
        
    public java.util.Date getChanceUtime(){
        return chanceUtime;
    }

    public void setChanceUtime(java.util.Date chanceUtime){
        this.chanceUtime = chanceUtime; 
    }
        
    public int getChanceNumber(){
        return chanceNumber;
    }

    public void setChanceNumber(int chanceNumber){
        this.chanceNumber = chanceNumber; 
    }

	public Date getEndCommTime() {
		return endCommTime;
	}

	public void setEndCommTime(Date endCommTime) {
		this.endCommTime = endCommTime;
	}

	public int getEndCommStatus() {
		return endCommStatus;
	}

	public void setEndCommStatus(int endCommStatus) {
		this.endCommStatus = endCommStatus;
	}

	public int getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(int consultStatus) {
		this.consultStatus = consultStatus;
	}

	public int getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(int drawStatus) {
		this.drawStatus = drawStatus;
	}

	public int getBackUserId() {
		return backUserId;
	}

	public void setBackUserId(int backUserId) {
		this.backUserId = backUserId;
	}

	public int getAutoGiveup() {
		return autoGiveup;
	}

	public void setAutoGiveup(int autoGiveup) {
		this.autoGiveup = autoGiveup;
	}



}
