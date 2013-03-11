package com.shangde.edu.vst.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 主体功能:用户视频记录
 *
 * @author		HQL
 * @date		2012-6-26
 * @version 	修改人:
 * 				修改日期:
 */
public class VideoCusInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;//主键
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private String subjectName;//项目名称
	
	private String videoName;//视频名称
	
	private String teacherName;//老师名称
	
	private int count;//观看次数
	
	private int courseId;//课程ID
	
	private int subjectId;//项目ID
	
	private int sellId;//售卖方式ID
	
	private int kpointId;//知识点ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getKpointId() {
		return kpointId;
	}

	public void setKpointId(int kpointId) {
		this.kpointId = kpointId;
	}
}
