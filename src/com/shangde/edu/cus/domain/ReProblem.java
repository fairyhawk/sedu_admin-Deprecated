package com.shangde.edu.cus.domain;

import java.io.Serializable;

import com.shangde.edu.sys.domain.User;

public class ReProblem implements Serializable{
	public static final int  REPROBLEM_COSTOMER=0;
	public static final int  REPROBLEM_USER=1;
	 /** 回复问题id   */
    private int reId;
	 /** 问题id   */
    private int pblId;
	 /** 回复内容   */
    private String reInfo;
	 /** 回复时间   */
    private java.util.Date reTime;
	 /** 回复状态   */
    private int reManType;
	 /** 回复人 （前台用户，后台老师） */
    private int reManId;
    /** 是否采纳为答案  *///0为没有采纳 1 为采纳了
    private int isResult;
    /**提问名称*/
    private Customer customer;
    private int reStatus;//是否隐藏
    
    private String isOfficial;//是否是达人回答
    
    private User user;
        
    public int getReId(){
        return reId;
    }

    public void setReId(int reId){
        this.reId = reId; 
    }
        
    public int getPblId(){
        return pblId;
    }

    public void setPblId(int pblId){
        this.pblId = pblId; 
    }
        
    public String getReInfo(){
        return reInfo;
    }

    public void setReInfo(String reInfo){
        this.reInfo = reInfo; 
    }
        
    public java.util.Date getReTime(){
        return reTime;
    }

    public void setReTime(java.util.Date reTime){
        this.reTime = reTime; 
    }
        
    public int getReManType(){
        return reManType;
    }

    public void setReManType(int reManType){
        this.reManType = reManType; 
    }
        
    public int getReManId(){
        return reManId;
    }

    public void setReManId(int reManId){
        this.reManId = reManId; 
    }

	public int getIsResult() {
		return isResult;
	}

	public void setIsResult(int isResult) {
		this.isResult = isResult;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getReStatus() {
		return reStatus;
	}

	public void setReStatus(int reStatus) {
		this.reStatus = reStatus;
	}

	public String getIsOfficial() {
		return isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}
}
