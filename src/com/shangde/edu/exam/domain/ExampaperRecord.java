package com.shangde.edu.exam.domain;

import java.io.Serializable;

import com.shangde.edu.cus.domain.Customer;

/**
 * 试卷记录
 * @author chenshuai
 *
 */
public class ExampaperRecord implements Serializable{
	
	/**
	 * 用户试卷ID
	 */
	private int userEpId;
	
	/**
	 * 用户ID
	 */
    private int cusId;
    
    /**
     * 试卷ID
     */
    private int epId;
    
    /**
     * 资源类型
     */
    private int resType;
    
    /**
     * 资源类型ID
     */
    private int resId;
    /**
     * 正确率
     */
    private float accuracy;
    
    /**
     * 用户得分
     */
    private float userScore;
    
    /**
     * 测试用时
     */
    private float testTime;
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    
    private String reviewsIds;
    
    /**
     * 试卷
     */
    private Exampaper exampaper;
    
    /**
     *用户实体
     */
    private Customer customer;
  
        
    public Exampaper getExampaper() {
		return exampaper;
	}

	public void setExampaper(Exampaper exampaper) {
		this.exampaper = exampaper;
	}

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
    public int getResType(){
        return resType;
    }

    public void setResType(int resType){
        this.resType = resType; 
    }
        
    public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	public float getAccuracy() {
		accuracy = (float) (Math.round(accuracy * 1000)) / 1000;
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	

	public float getTestTime() {
		return testTime;
	}

	public void setTestTime(float testTime) {
		this.testTime = testTime;
	}

	public int getUserEpId() {
		return userEpId;
	}

	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}

	public String getReviewsIds() {
		return reviewsIds;
	}

	public void setReviewsIds(String reviewsIds) {
		this.reviewsIds = reviewsIds;
	}

	public float getUserScore() {
		return userScore;
	}

	public void setUserScore(float userScore) {
		this.userScore = userScore;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
