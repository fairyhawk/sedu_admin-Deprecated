package com.shangde.edu.exam.domain;

import java.io.Serializable;

import com.shangde.edu.cus.domain.Customer;

/**
 * 用户答题记录
 * @author chenshuai
 *
 */
public class OptRecord implements Serializable{
	
	/**
	 * 用户试卷ID
	 */
	private int userEpId;
	
	/**
	 * 问题ID
	 */
    private int qstId;
    
    /**
     * 答案Id
     */
    private int asrId;
    
    /**
     * 用户Id
     */
    private int cusId;
    
    /**
     * 答案内容，
     */
    private String answerContent;
    
    /**
     * 用户答案
     */
    private String userAnswer;
    
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    
    private int reviewsId;
    
    /**
     * 试题类型，以Qstmiddle为准
     */
    private int qstType;
    
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public int getAsrId(){
        return asrId;
    }

    public void setAsrId(int asrId){
        this.asrId = asrId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public int getUserEpId() {
		return userEpId;
	}

	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}

	public int getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(int reviewsId) {
		this.reviewsId = reviewsId;
	}

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}


}
