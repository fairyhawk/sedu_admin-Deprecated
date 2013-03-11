package com.shangde.edu.cms.domain;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

import com.shangde.edu.cus.domain.Customer;

public class Comment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int CMT_CHECK_STATE_NEW = 0;
	public static int CMT_CHECK_STATE_PASS = 1;
	public static int CMT_CHECK_MAN_TYPE_VISITOR = 0; 
	public static int CMT_CHECK_MAN_TYPE_USER = 1; 
	private Integer cmtId;
    private Integer cmtParentId;
    private String cmtInfo;
    private Integer cfId;
    private Integer checkState;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer checkmanId;
    private Integer checkmanType;
    private Integer sourceId;
    private Integer grade;
    private String visitorName;
    private Object sourceObject;
    private Customer customer;  
    /**
     * 后台最后一次对评论操作的人
     */
    private String author;
    /**
     * 是否置顶
     */
    private int isTop;
    /**
     * 置顶时间  排序使用
     */
    private java.util.Date topTime;
    /**
     * 是否冻结 0否 1是
     */
    private int isFreeze;
    /**
     * 专业名称
     */
    private String subjectName;
    
    
	public int getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(int isFreeze) {
		this.isFreeze = isFreeze;
	}
	public java.util.Date getTopTime() {
		return topTime;
	}
	public void setTopTime(java.util.Date topTime) {
		this.topTime = topTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public Integer getCmtId() {
		return cmtId;
	}
	public void setCmtId(Integer cmtId) {
		this.cmtId = cmtId;
	}
	public Integer getCmtParentId() {
		return cmtParentId;
	}
	public void setCmtParentId(Integer cmtParentId) {
		this.cmtParentId = cmtParentId;
	}
	public String getCmtInfo() {
		return cmtInfo;
	}
	public void setCmtInfo(String cmtInfo) {
		this.cmtInfo = cmtInfo;
	}
	public Integer getCfId() {
		return cfId;
	}
	public void setCfId(Integer cfId) {
		this.cfId = cfId;
	}
	public Integer getCheckState() {
		return checkState;
	}
	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
	@JSON(format="yyyy-MM-dd hh-mm-ss")
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	@JSON(format="yyyy-MM-dd hh-mm-ss")
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCheckmanId() {
		return checkmanId;
	}
	public void setCheckmanId(Integer checkmanId) {
		this.checkmanId = checkmanId;
	}
	public Integer getCheckmanType() {
		return checkmanType;
	}
	public void setCheckmanType(Integer checkmanType) {
		this.checkmanType = checkmanType;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public Object getSourceObject() {
		return sourceObject;
	}
	public void setSourceObject(Object sourceObject) {
		this.sourceObject = sourceObject;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
