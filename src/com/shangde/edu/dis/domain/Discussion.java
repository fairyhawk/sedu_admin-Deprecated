package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 讨论组
 * 
 * 
 */
public class Discussion implements Serializable {
	private static String DEFAULT_PIC = "http://import.highso.org.cn/test/upload/dis/discussion.gif";
	/**
     * 
     */
	private static final long serialVersionUID = 5732233606466236426L;
	private int id;
	private String name;
	private int subjectId;
	private String subject;
	private java.util.Date createtime;
	private String creatuser;
	private String features;
	private String introduction;
	private String picurl;
	private int inflag;
	private int type;//0固定小组，1自创建小组
	private int status;//0启用，1禁用
	// 小组成员数
	private int member;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatuser() {
		return creatuser;
	}

	public void setCreatuser(String creatuser) {
		this.creatuser = creatuser;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		if (picurl == null || picurl.equals("")) {
			this.picurl = DEFAULT_PIC;
		} else {
			this.picurl = picurl;
		}
	}

	public int getInflag() {
		return inflag;
	}

	public void setInflag(int inflag) {
		this.inflag = inflag;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the member
	 */
	public int getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(int member) {
		this.member = member;
	}
}
