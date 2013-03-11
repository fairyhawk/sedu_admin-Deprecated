package com.shangde.edu.cus.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryProblemCondition extends PageQuery{
    private int pblId;//主键id
    private int courseId;//售卖方式
    private int cusId;//学院id
    private String pblTitle;//问题标题
    private int highProblem;
    private int pblSolv;//是否解决：1：已解决，2：未解决
    private int pblStatus;//是否隐藏：0：未隐藏，1：已隐藏
    private int pblHot;//是否热门：0：否，1：是
    private int pblType;//问题类型
    private int subjectId;//所属项目id
    private Date createTime;//问题提出时间
    private Date queryStartTime;//查询问题开始时间
    private Date queryEndTime;//查询问题结束时间
    private String useremail;//用户名
	private String userothername;//昵称
	private int hasReply=-1;//是否已经回复 

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPblId(){
        return pblId;
    }

    public void setPblId(int pblId){
        this.pblId = pblId;
    }

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getPblTitle() {
		return pblTitle;
	}

	public void setPblTitle(String pblTitle) {
		this.pblTitle = pblTitle;
	}

	public int getHighProblem() {
		return highProblem;
	}

	public void setHighProblem(int highProblem) {
		this.highProblem = highProblem;
	}

	public int getPblSolv() {
		return pblSolv;
	}

	public void setPblSolv(int pblSolv) {
		this.pblSolv = pblSolv;
	}

	public int getPblType() {
		return pblType;
	}

	public void setPblType(int pblType) {
		this.pblType = pblType;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

	public Date getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(Date queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public Date getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserothername() {
		return userothername;
	}

	public void setUserothername(String userothername) {
		this.userothername = userothername;
	}

	public int getHasReply() {
		return hasReply;
	}

	public void setHasReply(int hasReply) {
		this.hasReply = hasReply;
	}

	public int getPblStatus() {
		return pblStatus;
	}

	public void setPblStatus(int pblStatus) {
		this.pblStatus = pblStatus;
	}

	
}