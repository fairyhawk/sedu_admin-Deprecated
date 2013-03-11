package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频地址
 * 
 * @author chensong
 * @date 2011-9-9 上午08:49:14
 * 
 * 
 * @author Libg
 * 
 * 
 */
public class MicroVideo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * 专业id
	 */
	private Integer subjectId;

	/** 新增属性 */
	/**
	 * 课程id
	 */
	private Integer courseId;// 课程id
	/**
	 * 阶段,隶属于课程下的阶段数，而定
	 */
	private Integer stageNum;// 阶段,隶属于课程下的阶段数，而定
	/**
	 * 关联知识树KSN_ID值 [value,value,value]
	 */
	private String ksnId;// 关联知识树KSN_ID值 [value,value,value]
	/**
	 * 表示视频的重要性;重要性1-5个程度，[页面效果星级]
	 */
	private Integer importance;// 表示视频的重要性;重要性1-5个程度，[页面效果星级]

	// private String subjectName;
	// private String courseName;
	/**
	 * 视频url地址
	 */
	private String url;
	/**
	 * 讲义说明文件，下载地址
	 */
	private String lectureUrl;

	/** 新增属性 */
	/**
	 * 排序[填写数字]，排序规则：升序
	 */
	private Integer orderList;
	/**
	 * 老师[王某,李某]最大3项...
	 */
	private String teachers;
	/**
	 * 课程讲义[支持图文插入]
	 */
	private String content;

	/**
	 * 知识点id
	 */
	private Integer poinId;
	/**
	 * 视频名称
	 */
	private String name;
	/**
	 * 发布时间
	 */
	private Date pubDate;
	/**
	 * 修改时间
	 */
	private Date modified;

	/**
	 * 视频有效时间
	 */
	private Date effective;
	/**
	 * 知识点1内容
	 */
	private String knowledge1Context;
	/**
	 * 知识点1开始时间
	 */
	private Integer knowledge1Time;
	private String knowledge2Context;
	private Integer knowledge2Time;
	private String knowledge3Context;
	private Integer knowledge3Time;
	/**
	 * 是否免费
	 */
	private Integer isFree;
	/**
	 * 是否显示在推荐位
	 */
	private Integer isRecommend;
	/**
	 * 支持数
	 */
	private Integer supportNumber;
	/**
	 * 反对数
	 */
	private Integer antilogNumber;
	/**
	 * 观看时长
	 */
	private Integer totalWatchTime;
	/**
	 * 视频图片地址
	 */
	private String videoImgUrl;

	public Date getEffective() {
		return effective;
	}

	public void setEffective(Date effective) {
		this.effective = effective;
	}

	public String getKnowledge1Context() {
		return knowledge1Context;
	}

	public void setKnowledge1Context(String knowledge1Context) {
		this.knowledge1Context = knowledge1Context;
	}

	public Integer getKnowledge1Time() {
		return knowledge1Time;
	}

	public void setKnowledge1Time(Integer knowledge1Time) {
		this.knowledge1Time = knowledge1Time;
	}

	public String getKnowledge2Context() {
		return knowledge2Context;
	}

	public void setKnowledge2Context(String knowledge2Context) {
		this.knowledge2Context = knowledge2Context;
	}

	public Integer getKnowledge2Time() {
		return knowledge2Time;
	}

	public void setKnowledge2Time(Integer knowledge2Time) {
		this.knowledge2Time = knowledge2Time;
	}

	public String getKnowledge3Context() {
		return knowledge3Context;
	}

	public void setKnowledge3Context(String knowledge3Context) {
		this.knowledge3Context = knowledge3Context;
	}

	public Integer getKnowledge3Time() {
		return knowledge3Time;
	}

	public void setKnowledge3Time(Integer knowledge3Time) {
		this.knowledge3Time = knowledge3Time;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getAntilogNumber() {
		return antilogNumber;
	}

	public void setAntilogNumber(Integer antilogNumber) {
		this.antilogNumber = antilogNumber;
	}

	public Integer getTotalWatchTime() {
		return totalWatchTime;
	}

	public void setTotalWatchTime(Integer totalWatchTime) {
		this.totalWatchTime = totalWatchTime;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public String toString() {
		return "id=" + getId() + ">subjectId=" + getSubjectId() + ">courseId="
				+ getCourseId() + ">stageNum=" + getStageNum() + ">ksnId="
				+ getKsnId() + ">url=" + getUrl() + "\n" + ">orderList="
				+ getOrderList() + ">teachers=" + getTeachers() + ">poinId="
				+ getPoinId() + ">name=" + getName() + ">content="
				+ getContent() + ">importance=" + getImportance()
				+ ">lectureUrl=" + getLectureUrl();
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
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the stageNum
	 */
	public Integer getStageNum() {
		return stageNum;
	}

	/**
	 * @param stageNum
	 *            the stageNum to set
	 */
	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}

	/**
	 * @return the ksnId
	 */
	public String getKsnId() {
		return ksnId;
	}

	/**
	 * @param ksnId
	 *            the ksnId to set
	 */
	public void setKsnId(String ksnId) {
		this.ksnId = ksnId;
	}

	/**
	 * @return the importance
	 */
	public Integer getImportance() {
		return importance;
	}

	/**
	 * @param importance
	 *            the importance to set
	 */
	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the lectureUrl
	 */
	public String getLectureUrl() {
		return lectureUrl;
	}

	/**
	 * @param lectureUrl
	 *            the lectureUrl to set
	 */
	public void setLectureUrl(String lectureUrl) {
		this.lectureUrl = lectureUrl;
	}

	/**
	 * @return the orderList
	 */
	public Integer getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList
	 *            the orderList to set
	 */
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the teachers
	 */
	public String getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers
	 *            the teachers to set
	 */
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the poinId
	 */
	public Integer getPoinId() {
		return poinId;
	}

	/**
	 * @param poinId
	 *            the poinId to set
	 */
	public void setPoinId(Integer poinId) {
		this.poinId = poinId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the supportNumber
	 */
	public Integer getSupportNumber() {
		return supportNumber;
	}

	/**
	 * @param supportNumber
	 *            the supportNumber to set
	 */
	public void setSupportNumber(Integer supportNumber) {
		this.supportNumber = supportNumber;
	}

}
