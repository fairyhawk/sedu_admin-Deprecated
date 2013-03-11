package com.shangde.edu.feed.dto;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.sys.domain.Subject;

/**
 * DTO 微学习视频模型
 * 
 * @author Libg
 * 
 */
public class MicroVideoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer subjectId;// 专业id

	/** 新增属性 */
	private Integer courseId;// 课程id
	private Integer stageNum;// 阶段,隶属于课程下的阶段数，而定
	private String ksnId;// 关联知识树KSN_ID值 [value,value,value]
	private Integer importance;// 表示视频的重要性;重要性1-5个程度，[页面效果星级]

	private String url;

	/** 新增属性 */
	private String lectureUrl;// 讲义说明文件，下载地址
	private Integer orderList;// 排序[填写数字]，排序规则：升序
	private String teachers;// 老师[王某,李某]最大3项
	private String content;// 课程讲义[支持图文插入]

	private Integer poinId;
	private String name;// 课程名称
	private Date pubDate;
	private Date modified;

	private Subject subject;// 专业对象，subjectId与sys_subject_tbl.id关联
	private Course course;// 课程对象，courseId与feed_sourse_tbl.id关联

	private Integer avCount;// 用户点击数,如果大于0说明点击过

	private Integer supportNumber;//支持数
	private Integer antilogNumber;//反对数
	private Integer totalWatchTime;// 观看总时长
	private String totalWatchTimeStr;// 观看总时长
	private Integer clickNum;// 总次数
	private Integer clickUserNum;// 其中人数
	private Integer clickDoneUserNum;// 其中观看完人数
	private Integer collectNum;// 收藏人数
	private Integer downNum;// 下载人数
	/**
	 * @return the downNum
	 */
	public Integer getDownNum() {
		return downNum;
	}

	/**
	 * @param downNum
	 *            the downNum to set
	 */
	public void setDownNum(Integer downNum) {
		this.downNum = downNum;
	}

	/**
	 * @return the clickNum
	 */
	public Integer getClickNum() {
		return clickNum;
	}

	/**
	 * @param clickNum
	 *            the clickNum to set
	 */
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	/**
	 * @return the clickUserNum
	 */
	public Integer getClickUserNum() {
		return clickUserNum;
	}

	/**
	 * @param clickUserNum
	 *            the clickUserNum to set
	 */
	public void setClickUserNum(Integer clickUserNum) {
		this.clickUserNum = clickUserNum;
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

	public Integer getStageNum() {
		return stageNum;
	}

	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}

	public String getKsnId() {
		return ksnId;
	}

	public void setKsnId(String ksnId) {
		this.ksnId = ksnId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPoinId() {
		return poinId;
	}

	public void setPoinId(Integer poinId) {
		this.poinId = poinId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public String getLectureUrl() {
		return lectureUrl;
	}

	public void setLectureUrl(String lectureUrl) {
		this.lectureUrl = lectureUrl;
	}

	public Integer getAvCount() {
		return avCount;
	}

	public void setAvCount(Integer avCount) {
		this.avCount = avCount;
	}

	/**
	 * @return the clickDoneUserNum
	 */
	public Integer getClickDoneUserNum() {
		return clickDoneUserNum;
	}

	/**
	 * @param clickDoneUserNum
	 *            the clickDoneUserNum to set
	 */
	public void setClickDoneUserNum(Integer clickDoneUserNum) {
		this.clickDoneUserNum = clickDoneUserNum;
	}

	/**
	 * @return the totalWatchTime
	 */
	public Integer getTotalWatchTime() {
		return totalWatchTime;
	}

	/**
	 * @param totalWatchTime
	 *            the totalWatchTime to set
	 */
	public void setTotalWatchTime(Integer totalWatchTime) {
		this.totalWatchTime = totalWatchTime;
	}

	/**
	 * @return the collectNum
	 */
	public Integer getCollectNum() {
		return collectNum;
	}

	/**
	 * @param collectNum
	 *            the collectNum to set
	 */
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
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
	 * @return the totalWatchTimeStr
	 */
	public String getTotalWatchTimeStr() {
		return totalWatchTimeStr;
	}

	/**
	 * @param totalWatchTimeStr the totalWatchTimeStr to set
	 */
	public void setTotalWatchTimeStr(String totalWatchTimeStr) {
		this.totalWatchTimeStr = totalWatchTimeStr;
	}
}
