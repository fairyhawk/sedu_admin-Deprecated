package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

/**
 * 微学习留言模型(提问表)
 * 
 * @author Libg
 * 
 */
public class Review implements Serializable {

	private Integer id;
	private Integer subjectId;// 专业id
	private Integer courseId;// 课程id
	private String content;// 内容
	private Integer cusId;// 留言人

	private Customer customer;// 留言人

	private Integer targetCusId;// 回复目标人
	private Integer targetReviewId;// 回复目标帖id
	private Integer counts;// 表示针对当前记录回复次数
	private String detail;// 描述，当管理员试图屏蔽记录时，需要填写原因		,这个在V2.0中不使用
	private Integer status;// 状态[1=正常、-1=屏蔽(需要填写detail屏蔽内容)、2=选中]
	private Date pubDate;// 发布时间
	private Date modified;// 修改时间
	private Integer videoId;//视频id
	private Integer antilogNumber;//反对数（对当前提问的操作）
	private Integer supportNumber;//支持数（对当前提问的操作）
	
	private Customer targetCustomer;// 目标人(回复的目标人id)
	private Review targetReview;// 目标帖子(回复的目标帖id)

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getTargetCusId() {
		return targetCusId;
	}

	public void setTargetCusId(Integer targetCusId) {
		this.targetCusId = targetCusId;
	}

	public Integer getTargetReviewId() {
		return targetReviewId;
	}

	public void setTargetReviewId(Integer targetReviewId) {
		this.targetReviewId = targetReviewId;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Customer getTargetCustomer() {
		return targetCustomer;
	}

	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}

	public Review getTargetReview() {
		return targetReview;
	}

	public void setTargetReview(Review targetReview) {
		this.targetReview = targetReview;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the videoId
	 */
	public Integer getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	/**
	 * @return the antilogNumber
	 */
	public Integer getAntilogNumber() {
		return antilogNumber;
	}

	/**
	 * @param antilogNumber the antilogNumber to set
	 */
	public void setAntilogNumber(Integer antilogNumber) {
		this.antilogNumber = antilogNumber;
	}

	/**
	 * @return the supportNumber
	 */
	public Integer getSupportNumber() {
		return supportNumber;
	}

	/**
	 * @param supportNumber the supportNumber to set
	 */
	public void setSupportNumber(Integer supportNumber) {
		this.supportNumber = supportNumber;
	}

}
