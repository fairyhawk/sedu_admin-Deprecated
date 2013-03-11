package com.shangde.edu.cms.dto;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

import com.shangde.edu.cus.domain.Customer;

public class ExportCommentDTO implements Serializable{
	/**
	 * 编号
	 */
	private Integer cmtId;
	/**
	 * 内容
	 */
    private String cmtInfo;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 建议人姓名
     */
    private String visitorName;
    /**
     *  置顶状态
     */
    private String isTop;
    /**
     * 客服回复时间
     */
    private java.util.Date reviewTime;
    /**
     * 回复状态
     */
    private String isReview;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 客服回复人名称
     */
    private String reviewName;
    /**
     * 客服回复内容
     */
    private String reviewInfo;
	public Integer getCmtId() {
		return cmtId;
	}
	public void setCmtId(Integer cmtId) {
		this.cmtId = cmtId;
	}
	public String getCmtInfo() {
		return cmtInfo;
	}
	public void setCmtInfo(String cmtInfo) {
		this.cmtInfo = cmtInfo;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public java.util.Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(java.util.Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getIsReview() {
		return isReview;
	}
	public void setIsReview(String isReview) {
		this.isReview = isReview;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	public String getReviewInfo() {
		return reviewInfo;
	}
	public void setReviewInfo(String reviewInfo) {
		this.reviewInfo = reviewInfo;
	}
     
}
