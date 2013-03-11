package com.shangde.edu.cou.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryTjcourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Tjcourse;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ITjcourse;

/**  
 * 推荐课程Action
 * @author chenshuai
 * @version 1.0
 */
public class TjCourseAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(TjCourseAction.class);
	
	private Tjcourse tjcourse;
	
	private Course course;
	
	private ITjcourse tjcourseService;
	
	private ICourse courseService;
	
	private ICoursesort coursesortService;
	
	private List<Coursesort> courseSortList;
	
	private List<Course> courseList;
	
	private List<Tjcourse> tjcourseList;
	
	private QueryTjcourseCondition queryTjcourseCondition;
	
	/**
	 * 预添加推荐课程
	 * @return
	 */
	public String toAddTjCourse(){
		try{
			QueryCoursesortCondition queryCoursesortCondition = new QueryCoursesortCondition();
			
			courseSortList = coursesortService.getChildCoursesortList(queryCoursesortCondition);
			return "toAddTjCourse";
		}catch(Exception ex){
			logger.error("TjCourseAction.toAddTjCourse", ex);
			return ERROR;
		}
	}
	
	/**
	 * 添加推荐课程
	 * @return
	 */
	public String addTjCourse(){
		try{
			tjcourseService.addTjcourse(tjcourse);
			return "changeSuccess";
		}catch(Exception ex){
			logger.error("TjCourseAction.addTjCourse", ex);
			return ERROR;
		}
	}
	
	/**
	 * 删除推荐课程
	 * @return
	 */
	public String deleteTjCourse(){
		try{
			tjcourseService.delTjcourseByCourseId(tjcourse);
			return "changeSuccess";
		}catch(Exception ex){
			logger.error("TjCourseAction.deleteTjCourse", ex);
			return ERROR;
		}
	}
	
	/**
	 * 查看推荐课程列表
	 * @return
	 */
	public String listTjcourses(){
		try{
			QueryCoursesortCondition queryCoursesortCondition = new QueryCoursesortCondition();
			List<Tjcourse> tjcourseListtemp = null;
			
			courseSortList = coursesortService.getChildCoursesortList(queryCoursesortCondition);
			if(tjcourse != null){
				this.getQueryTjcourseCondition().setYcourseId(tjcourse.getCourseId());
				tjcourseListtemp = tjcourseService.getTjcourseList(this.getQueryTjcourseCondition());
				
				course = courseService.getCourseById(tjcourse.getCourseId());
			}
			
			courseList = new ArrayList<Course>();
			
			Tjcourse tjtemp = null;
			Course coursetemp = null;
			
			if(tjcourseListtemp != null){
				for(int i = 0; i < tjcourseListtemp.size(); i ++){
					tjtemp = tjcourseListtemp.get(i);
					coursetemp = courseService.getCourseById(tjtemp.getTjcourseId());
					courseList.add(coursetemp);
				}
			}
			
			return "listTjcourses";
			
		}catch(Exception e){
			logger.error("TjCourseAction.listTjcourses", e);
			return ERROR;
		}
			
	}

	public Tjcourse getTjcourse() {
		return tjcourse;
	}

	public void setTjcourse(Tjcourse tjcourse) {
		this.tjcourse = tjcourse;
	}

	public ITjcourse getTjcourseService() {
		return tjcourseService;
	}

	public void setTjcourseService(ITjcourse tjcourseService) {
		this.tjcourseService = tjcourseService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public QueryTjcourseCondition getQueryTjcourseCondition() {
		if(queryTjcourseCondition == null){
			queryTjcourseCondition = new QueryTjcourseCondition();
		}
		
		return queryTjcourseCondition;
	}

	public void setQueryTjcourseCondition(
			QueryTjcourseCondition queryTjcourseCondition) {
		this.queryTjcourseCondition = queryTjcourseCondition;
	}

	public List<Tjcourse> getTjcourseList() {
		return tjcourseList;
	}

	public void setTjcourseList(List<Tjcourse> tjcourseList) {
		this.tjcourseList = tjcourseList;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
