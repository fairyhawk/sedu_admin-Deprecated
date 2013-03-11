package com.shangde.edu.vst.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.vst.domain.VideoTeacherStaticsCountDTO;
import com.shangde.edu.vst.service.IVideoTeacherStaticstics;

/**
 * 教师（购买，试听）人数及时长统计
 * @author HQL
 */
public class VideoTeacherStatisticsAction extends CommonAction {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(VideoTeacherStatisticsAction.class);
	
	private VideoTeacherStaticsCountDTO condition;
	
	private List<VideoTeacherStaticsCountDTO> data;
	private List<Subject> subList;
	
	
	private IVideoTeacherStaticstics iTeacherStaticsticsService;	
	private ISubject subjectService;
	
	
	public String getTeacherCount(){
		this.data = iTeacherStaticsticsService.getVStByAllTeacher(condition);
		this.subList = subjectService.getAllSubject();
		return "vsCount";
	}
	
	public List<VideoTeacherStaticsCountDTO> getData() {
		return data;
	}

	public void setData(List<VideoTeacherStaticsCountDTO> data) {
		this.data = data;
	}

	public VideoTeacherStaticsCountDTO getCondition() {
		return condition;
	}

	public void setCondition(VideoTeacherStaticsCountDTO condition) {
		this.condition = condition;
	}

	public IVideoTeacherStaticstics getiTeacherStaticsticsService() {
		return iTeacherStaticsticsService;
	}

	public void setiTeacherStaticsticsService(
			IVideoTeacherStaticstics iTeacherStaticsticsService) {
		this.iTeacherStaticsticsService = iTeacherStaticsticsService;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
}
