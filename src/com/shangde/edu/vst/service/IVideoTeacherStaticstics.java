package com.shangde.edu.vst.service;

import java.util.List;

import com.shangde.edu.vst.domain.VideoTeacherStaticsCountDTO;

/**
 * 教师（购买，试听）人数及时长统计接口
 * @author HQL
 */
public interface IVideoTeacherStaticstics {

	/**
	 * 获取教师统计数据
	 * @param condition 查询条件
	 * @return List
	 */
	public List<VideoTeacherStaticsCountDTO> getVStByAllTeacher(VideoTeacherStaticsCountDTO condition);
}
