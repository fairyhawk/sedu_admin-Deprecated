package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.sys.domain.Subject;

/**
 * 用户统计，针对微学习使用统计模型
 * 
 * @author Libg
 * 
 */
public class UserStat implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 专业
	 */
	private Integer subjectId;
	/**
	 * 用户注册时间
	 */
	private Date regTime;
	/**
	 * 用户注册位置
	 */
	private Integer regLocation;
	/**
	 * 用户使用次数
	 */
	private Integer useNum;
	/**
	 * 用户点击左边“微学习”次数
	 */
	private Integer useClickNum;
	/**
	 * 视频观看总时长,毫秒
	 */
	private Integer totalLength;
	/**
	 * 发布时间
	 */
	private Date pubDate;
	/**
	 * 修改时间
	 */
	private Date modified;

	/**
	 * 专业
	 */
	private Subject subject;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the cusId
	 */
	public Integer getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the regTime
	 */
	public Date getRegTime() {
		return regTime;
	}

	/**
	 * @param regTime
	 *            the regTime to set
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	/**
	 * @return the regLocation
	 */
	public Integer getRegLocation() {
		return regLocation;
	}

	/**
	 * @param regLocation
	 *            the regLocation to set
	 */
	public void setRegLocation(Integer regLocation) {
		this.regLocation = regLocation;
	}

	/**
	 * @return the useNum
	 */
	public Integer getUseNum() {
		return useNum;
	}

	/**
	 * @param useNum
	 *            the useNum to set
	 */
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	/**
	 * @return the useClickNum
	 */
	public Integer getUseClickNum() {
		return useClickNum;
	}

	/**
	 * @param useClickNum
	 *            the useClickNum to set
	 */
	public void setUseClickNum(Integer useClickNum) {
		this.useClickNum = useClickNum;
	}

	/**
	 * @return the totalLength
	 */
	public Integer getTotalLength() {
		return totalLength;
	}

	/**
	 * @param totalLength
	 *            the totalLength to set
	 */
	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

	/**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}
}
