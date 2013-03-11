package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.edu.res.domain.Vedio;
/**
 * 功能:定时后台批量导入cc视频图片
 * @author Yangning
 *
 */
public interface IVideoTaskService {
	/**
	 * 更新视频
	 * @param vedio
	 * @return
	 */
	public int updatevideoPicByid(Vedio vedio);
	
	/**
	 * 更新课程
	 * @param course
	 * @return
	 */
	public int updateCoursePicByid(Course course);
	
	/**
	 * 
	 * @param vedio
	 * @return
	 */
	public PageResult getNotPicListVedio(QueryVedioCondition condition);
	/**
	 * 
	 * @return
	 */
	public List<Course> getNotPicListCourse();
	/**
	 * 
	 * @return
	 */
	public int getNotPicListVedioCount();
	
	
	/**
	 * 通过课程名找第一个视频图片,地址
	 */
	public String getCoursePicViaVedioPic(int courseId);
}
