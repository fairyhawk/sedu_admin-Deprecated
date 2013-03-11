package com.shangde.edu.cou.service;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.CourseIsAuditionDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.ListeningCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;

/**
 * Course服务实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class CourseImpl extends BaseService implements ICourse{
	
	
	/**
	 * Course 添加课程
	 * @param course 要添加的课程Course
	 * @return id
	 */
    public java.lang.Integer addCourse(Course course) {
    	return simpleDao.createEntity("Course_NS.createCourse",course);
    }
    
    /**
     * 删除课程
     * @param courseId 要删除的课程ID
     */
    public void delCourseById(int courseId){
        simpleDao.deleteEntity("Course_NS.deleteCourseById",courseId);
    }
    /**
     * Course更新课程
     * @param course 要更新的课程
     * @return void
     */
    public void updateCourse(Course course) {
        simpleDao.updateEntity("Course_NS.updateCourse",course);
    }
    
    /**
     * 通过课程ID获取课程
     * @param courseId 课程ID
     * @return 课程
     */
    public Course getCourseById(int courseId) {
        return simpleDao.getEntity("Course_NS.getCourseById",courseId);
    }
    
    /**
     * 通过课程ID获取课程
     * @param courseId 课程ID
     * @return 课程
     */
    public Course getAllCourseInfoById(int courseId) {
        return simpleDao.getEntity("Course_NS.getAllCourseInfoById",courseId);
    }
    
    /**
     *获取课程列表
     *@param queryCourseCondition 查询条件
     *@return 课程集合 
     */
    public List<Course> getCourseList(QueryCourseCondition queryCourseCondition) {
        return simpleDao.getForList("Course_NS.getCourseList",queryCourseCondition);
    }
    /**
     *获取课程列表
     *@param queryCourseCondition 查询条件
     *@return 课程集合 
     */
    public List<Course> getAllCourseInfoList(QueryCourseCondition queryCourseCondition) {
        return simpleDao.getForList("Course_NS.getAllCourseInfoList",queryCourseCondition);
    }
    
    /**
     * 通过分类查询课程
     * @param queryCourseCondition
     * @return 查询结果集合
     */
	public List<Course> getCourseListBySortId(QueryCourseCondition queryCourseCondition) {
		// TODO Auto-generated method stub
		List result = new ArrayList();
		
		Course course = new Course();
		course.setCourseId(-1);
		course.setTitle("请选择");
		result.add(course);
		result.addAll(simpleDao.getForList("Course_NS.getCourseListBySortId", queryCourseCondition));
		return result;
	}
	
	/**
     * 通过课程分类ID分页查询课程
     * @param queryCourseCondition
     * @return
     */
	public PageResult getNomalCourseListBySortId(QueryCourseCondition queryCourseCondition) {
		return simpleDao.getPageResult("Course_NS.getNomalCourseListBySortId", "Course_NS.getNomalCourseListCountBySortId", queryCourseCondition);
	}
	
	/**
     * 获取课程列表
     * @param queryCourseCondition
     * @return
     */
	public List<KeyValueDTO> getCourseDTOListtBySortId(QueryCourseCondition queryCourseCondition){
		List result = new ArrayList();
		
		KeyValueDTO dto = new KeyValueDTO();
		dto.setId(-1);
		dto.setName("请选择");
		result.add(dto);
		
		result.addAll(simpleDao.getForList("Course_NS.getCourseDTOListtBySortId", queryCourseCondition));
		
		return result;
	}
	
	/**
	 * 获取指定数量课程
	 */
	public List<Course> getCourseListtBySortIdLimit(QueryCourseCondition queryCourseCondition) {
		
		return simpleDao.getForList("Course_NS.getCourseListtBySortIdLimit", queryCourseCondition);
	}
	
	/**
	 * 获取课程集合
	 */
	public List<Course> getWebCourseList(QueryCourseCondition queryCourseCondition){
		return simpleDao.getForList("Course_NS.getWebCourseList", queryCourseCondition);
	}
	
	/**
     * 通过视频ID获取课程DTO
     * @param vedioId
     * @return
     */
	public ListeningCourseDTO getWebListenintCourseDTOByVedioId(int vedioId){
		return simpleDao.getEntity("Course_NS.getWebListenintCourseDTOByVedioId", vedioId);
	}
	
	/**
     * 获得制定数量的最新的课程
     * @param size
     * @return
     */
	public List<CourseDTO> getWebCourseListLimit(int size){
		return simpleDao.getForList("Course_NS.getWebCourseListLimit", size);
	}
	
	/**
     * 获取课程DTO
     * @param CourseId
     * @return
     */
	public CourseDTO getCourseDTOById(int CourseId){
		return simpleDao.getEntity("Course_NS.getCourseDTOById", CourseId);
	}

	public List<Course> getCourseListForTag(QueryCourseCondition queryCourseCondition) {
		return simpleDao.getForList("Course_NS.getCourseListForTag",queryCourseCondition);
	}
    
    public List<Course> getCourseListByClazzIdForTag(QueryCourseCondition queryCourseCondition){
    	return simpleDao.getForList("Course_NS.getCourseListByClazzIdForTag", queryCourseCondition);
    }
    
    public List<CourseIsAuditionDTO> getAuditionCourseListByCourseId(int courseId){
    	return simpleDao.getForList("Course_NS.getAuditionCourseListByCourseId", courseId);
    }
    
    /**
	 * 按状态查找课程
	 * @param queryCoursesortCondition
	 * @return
	 */
	public List getCourseListByStatus(QueryCourseCondition queryCourseCondition){
		return simpleDao.getForList("Course_NS.getCourseListByStatus", queryCourseCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：根据包下的课程集合
	 * @param args
	 * @param cusId 用户ID
	 * @return
	 */
	public List<UserCenterCourseDTO> getUserCenterCourseListByCusId(QueryCourseCondition queryCourseCondition){
		return simpleDao.getForList("Course_NS.getUserCenterCourseListByCusId", queryCourseCondition);
	}
	/**
	 * @author liuqinggang
	 * 功能：根据用户ID获取用户前台已购包集合
	 * @param args
	 * @param cusId 用户ID
	 * @return
	 */
	public List<SellWay> getUserCenterSellWayListByCusId(QueryCourseCondition queryCourseCondition){
		return simpleDao.getForList("Course_NS.getUserCenterSellWayListByCusId", queryCourseCondition);
	}

	public List<KeyValueDTO> getCourseDTOListAddOrUpdate(
			QueryCourseCondition queryCourseCondition) {
		List result = new ArrayList();
		
		KeyValueDTO dto = new KeyValueDTO();
		dto.setId(-1);
		dto.setName("请选择");
		result.add(dto);
		
		result.addAll(simpleDao.getForList("Course_NS.getCourseDTOListAddOrUpdate", queryCourseCondition));
		
		return result;
	}

	public List<CourseDTO> getCoursePlan(int courseId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Course_NS.getCoursePlan", courseId);
	}

	/**
	 * 获取所有课程id 
	 * @param subjectId
	 * @return
	 */
     public List<Integer> getCourseIdAll(Integer subjectId){
    	 return simpleDao.getForList("Course_NS.getCourseIdAll", subjectId);
     }
}
