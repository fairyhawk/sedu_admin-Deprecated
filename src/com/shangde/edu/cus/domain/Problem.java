package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Problem implements Serializable{
	/** 问题id  */
	private int pblId;
	 /** 课程id   */
    private int courseId;
	 /** 注册用户id   */
    private int cusId;
	 /** 问题标题   */
    private String pblTitle;
	 /** 创建时间   */
    private Date createTime;
    /** 回答问题的list   */
    private List<ReProblem> reProblemList;
    /** 前台的问题编号   */
    private int nbId;
    /** 问题类型*///1 考试问题 2 课程问题 3 视频问题 4 讲义问题
    private int pblType;
    /** 问题积分*/
    private int pblTotols;
    /** 是否解决问题*/ //2为待解决问题，1为已解决的问题
    private int pblSolv =1;
    /** 是否隐藏问题*/ //0:否 1：是
    private int pblStatus =-1;
    /**问题详细内容*/
    private String pblContent;
    /**是否为热门问题*/ //0为普通问题 1为热门问题
    private int pblHot; 
    /**专业id*/
    private int subjectId;
    /**提问名称*/
    private Customer customer;
    
    private String cusEmail;//用户email
    private String cusname;//用户昵称
    private int reflayCount;//问题回复数量
    private String subjectName;//专业名称
    private Date bestReTime;//最佳答案的回复时间
    private String bestReEmail;//最佳答案的回复人
    
    public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public int getPblId(){
        return pblId;
    }

    public void setPblId(int pblId){
        this.pblId = pblId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getPblTitle(){
        return pblTitle;
    }

    public void setPblTitle(String pblTitle){
        this.pblTitle = pblTitle; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }

	public List<ReProblem> getReProblemList() {
		return reProblemList;
	}

	public void setReProblemList(List<ReProblem> reProblemList) {
		this.reProblemList = reProblemList;
	}

	public int getNbId() {
		return nbId;
	}

	public void setNbId(int nbId) {
		this.nbId = nbId;
	}

	public int getPblType() {
		return pblType;
	}

	public void setPblType(int pblType) {
		this.pblType = pblType;
	}

	public int getPblTotols() {
		return pblTotols;
	}

	public void setPblTotols(int pblTotols) {
		this.pblTotols = pblTotols;
	}

	public int getPblSolv() {
		return pblSolv;
	}

	public void setPblSolv(int pblSolv) {
		this.pblSolv = pblSolv;
	}

	public String getPblContent() {
		return pblContent;
	}

	public void setPblContent(String pblContent) {
		this.pblContent = pblContent;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getReflayCount() {
		return reflayCount;
	}

	public void setReflayCount(int reflayCount) {
		this.reflayCount = reflayCount;
	}

	public int getPblStatus() {
		return pblStatus;
	}

	public void setPblStatus(int pblStatus) {
		this.pblStatus = pblStatus;
	}

	public Date getBestReTime() {
		return bestReTime;
	}

	public void setBestReTime(Date bestReTime) {
		this.bestReTime = bestReTime;
	}

	public String getBestReEmail() {
		return bestReEmail;
	}

	public void setBestReEmail(String bestReEmail) {
		this.bestReEmail = bestReEmail;
	}

	
}
