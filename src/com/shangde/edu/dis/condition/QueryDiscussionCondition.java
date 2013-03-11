package com.shangde.edu.dis.condition;

import com.shangde.common.vo.PageQuery;

public class QueryDiscussionCondition extends PageQuery implements java.io.Serializable{
    private int id;
    private String name;
    private int subjectId = -1;
    private String subject;
    private java.util.Date createtime;
    private String creatuser;
    private String features;
    private String introduction;
    private String picurl;
    private int inflag;
    private int type = -1;
    private int status = -1;
    
    
    private String keyWorld;//搜索关键词
	private String searchCriteria;//检索条件
	private String createTimeStart;//发布时间，起
	private String createTimeEnd;//发布时间，结
    
    
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the creatuser
	 */
	public String getCreatuser() {
		return creatuser;
	}
	/**
	 * @param creatuser the creatuser to set
	 */
	public void setCreatuser(String creatuser) {
		this.creatuser = creatuser;
	}
	/**
	 * @return the features
	 */
	public String getFeatures() {
		return features;
	}
	/**
	 * @param features the features to set
	 */
	public void setFeatures(String features) {
		this.features = features;
	}
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}
	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	/**
	 * @return the inflag
	 */
	public int getInflag() {
		return inflag;
	}
	/**
	 * @param inflag the inflag to set
	 */
	public void setInflag(int inflag) {
		this.inflag = inflag;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
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
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKeyWorld() {
		return keyWorld;
	}
	public void setKeyWorld(String keyWorld) {
		this.keyWorld = keyWorld;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}