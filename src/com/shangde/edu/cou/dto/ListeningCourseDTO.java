package com.shangde.edu.cou.dto;

import java.io.Serializable;

import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.res.domain.Vedio;

/**
 * 正在听课程
 * 用于学员中心，初始化正在听的课程
 * @author chenshuai
 * 
 */
public class ListeningCourseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int courseId;
	private String title;
	private int teacherId;
	private Teacher teacher;
	private int voId;
	private String voName;
	private Vedio vedio;

	private int pointId;
	private String kpointName;

	public Vedio getVedio() {
		return vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getVoId() {
		return voId;
	}

	public void setVoId(int voId) {
		this.voId = voId;
	}

	public String getVoName() {
		return voName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getKpointName() {
		return kpointName;
	}

	public void setKpointName(String kpointName) {
		this.kpointName = kpointName;
	}

}
