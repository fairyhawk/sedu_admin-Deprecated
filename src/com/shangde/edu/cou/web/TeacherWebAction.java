package com.shangde.edu.cou.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.service.IColumns;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.TeacherCookieDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ITeacher;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TeacherWebAction extends CommonAction{
	
	/**
	 * 教师服务对象
	 */
	private ITeacher teacherService;
	
	/**
	 * 栏目服务对象
	 */
	private IColumns columnsService;
	
	/**
	 * 课程服务对象
	 */
	private ICourse courseService;
	
	/**
	 * 教师实体
	 */
	private Teacher teacher = new Teacher();
	
	/**
	 * 课程实体
	 */
	private Course course;
	
	/**
	 * 教师查询条件
	 */
	private QueryTeacherCondition queryTeacherCondition;
	
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	
	/**
	 * 教师list
	 */
	private List<Teacher> teacherList=new ArrayList<Teacher>();
	
	/**
	 * 课程列表
	 */
	private List<Course> courseList=new ArrayList<Course>();
	
	/**
	 * 根据subject id获取明星教师
	 * @return
	 */
	public String getStarTeacherListBySubject() {
		try {
			List<Teacher> list = teacherService.getStarTeacherListBySubject(getQueryTeacherCondition().getSubjectId());
			if(list != null) {
				for(int i=0; i<list.size(); i++) {
					//这里要求每个老师必须要有首推课程和首推视频,用career属性临时存储视频的url
					list.get(i).setCareer(teacherService.getTcShowVedioURL(list.get(i).getTcId()));
				}
			}
			
			//迭代教师列表，将用户所点击的教师排在首位
			if(queryTeacherCondition.getTcId()!=null  &&  queryTeacherCondition.getTcId()!=0) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getTcId() == queryTeacherCondition.getTcId()) {
						list.add(0, list.remove(i));
						break;
					}
				}
			}
			setResult(new Result<List<Teacher>>(true, "返回成功!", null, list));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 获取教师信息
	 * @return
	 */
	public String getTeacherInfo(){
		try{
			if(teacher!=null&&teacher.getTcId()!=0){
				teacher=teacherService.getTeacherById(teacher.getTcId());
			}
			if(course!=null&&course.getCourseId()!=0){
				course=courseService.getCourseById(course.getCourseId());
			}
			//查出所有的老师
			teacherList=teacherService.getTeacherList(getQueryTeacherCondition());
			//查出老师所对应的课程
			getQueryCourseCondition().setTeacherId(teacher.getTcId());
			//courseList=this.courseService.getCourseList(getQueryCourseCondition());
			courseList=courseService.getWebCourseList(getQueryCourseCondition());
			
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "teacherinfo";
	}
	
	/**
	 * 获取教师信息
	 * @return
	 */
	public String getTeachingCourse(){
		try{
			if(teacher!=null&&teacher.getTcId()!=0){
				teacher=teacherService.getTeacherById(teacher.getTcId());
			}
			getQueryCourseCondition().setTeacherId(teacher.getTcId());
			courseList=courseService.getWebCourseList(getQueryCourseCondition());
			
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
			if(courseList != null  &&  courseList.size() > 0) {
				for(int i=0; i<courseList.size(); i++) {
					Course course = courseList.get(i);
					queryColumnsCondition.setColumnId(course.getSubjectId());
					queryColumnsCondition.setMeta("tryListener");
					List<Columns> columnList = columnsService.getColumnsListForTag(queryColumnsCondition);
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("columnId", "" + columnList.get(0).getColumnId());
					map.put("title", course.getTitle());
					map.put("courseId", course.getCourseId() + "");
					
					list.add(map);
				}
			}
			setResult(new Result<List<Map<String, String>>>(false, "success", null, list));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 获取教师DTO
	 * @return
	 */
	public String getTeacherDTOJson(){
		try{
			if(teacher != null){
				TeacherCookieDTO teachertemp = teacherService.getTeacherCookieDTOById(teacher.getTcId());
				JSONArray jslist = JSONArray.fromObject(teachertemp);
				setResult(new Result<Object>(true, jslist.toString(), null, null));
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
	}

	public QueryTeacherCondition getQueryTeacherCondition() {
		if(queryTeacherCondition == null) {
			queryTeacherCondition = new QueryTeacherCondition();
		}
		return queryTeacherCondition;
	}

	public void setQueryTeacherCondition(QueryTeacherCondition queryTeacherCondition) {
		this.queryTeacherCondition = queryTeacherCondition;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public ITeacher getTeacherService() {
		return teacherService;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	public QueryCourseCondition getQueryCourseCondition() {
		if(this.queryCourseCondition == null){
			this.queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}
	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
	}
}
