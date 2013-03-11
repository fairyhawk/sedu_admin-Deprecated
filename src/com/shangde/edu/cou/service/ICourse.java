package com.shangde.edu.cou.service;

import java.util.List;

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
 * Course课程服务类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ICourse {
  
	 /**
     * 添加课程
     * @param course  要添加的课程Course
     * @return id
     */
    public java.lang.Integer addCourse(Course course);

    /**
     * 删除课程
     * @param courseId 要删除的课程ID
     */
    public void delCourseById(int courseId);

    /**
     *更新课程
     * @param course 要更新的课程
     */
    public void updateCourse(Course course);

    /**
     * 通过课程ID获取课程
     * @param courseId 课程ID
     * @return 课程
     */
    public Course getCourseById(int courseId);

    /**
     * 获取课程列表
     * @param queryCourseCondition 查询条件
     * @return 课程集合
     */
    public List<Course> getCourseList(QueryCourseCondition queryCourseCondition);
    
    /**
     * 通过分类查询课程
     * @param queryCourseCondition
     * @return 查询结果集合
     */
    public List<Course> getCourseListBySortId(QueryCourseCondition queryCourseCondition);
    
    /**
     * 通过课程分类ID分页查询课程
     * @param queryCourseCondition
     * @return
     */
    public PageResult getNomalCourseListBySortId(QueryCourseCondition queryCourseCondition);
    
    /**
     * 获取课程列表
     * @param queryCourseCondition
     * @return
     */
    public List<KeyValueDTO> getCourseDTOListtBySortId(QueryCourseCondition queryCourseCondition);
    
    /**
     *获取有效课程立标，针对课程节点添加及修改，课程重复数据问题
     * 
     */
    public List<KeyValueDTO> getCourseDTOListAddOrUpdate (QueryCourseCondition queryCourseCondition);
    
    /**
	 * 获取指定数量课程
	 */
    public List<Course> getCourseListtBySortIdLimit(QueryCourseCondition queryCourseCondition);
    
    /**
	 * 获取课程集合
	 */
    public List<Course> getWebCourseList(QueryCourseCondition queryCourseCondition);
    
    /**
     * 通过视频ID获取课程
     * @param vedioId
     * @return
     */
    public ListeningCourseDTO getWebListenintCourseDTOByVedioId(int vedioId);
    
    /**
     * 获得制定数量的最新的课程
     * @param size
     * @return
     */
    public List<CourseDTO> getWebCourseListLimit(int size);
    
    /**
     * 获取课程DTO
     * @param CourseId
     * @return
     */
    public CourseDTO getCourseDTOById(int CourseId);

    /**
     * 获取课程列表（为静态化替换自定义标签准备）
     * @param queryCourseCondition
     * @return
     */
	public List<Course> getCourseListForTag(QueryCourseCondition queryCourseCondition);
    
    /**
     * 
     * @param queryCourseCondition
     * @return
     * @authorcxs
     * 功能：按课程查询课程
     * @param args
     */
    public List<Course> getCourseListByClazzIdForTag(QueryCourseCondition queryCourseCondition);
    
    /**
     * 
     * @param courseId
     * @return
     * @authorcxs
     * 功能：生成可试听片段
     * @param args
     */
    public List<CourseIsAuditionDTO> getAuditionCourseListByCourseId(int courseId);
    
    /**
     * 
     * @param courseId
     * @return
     * @authorcxs
     * 功能：根据课程ID查询课程的所有信息
     * 会级联查出其他相关实例
     * @param args
     */
    public Course getAllCourseInfoById(int courseId);
    
    /**
     * 获取所有课程信息
     * @param queryCourseCondition
     * @return
     * @authorcxs
     * 功能：查询出课程所有信息
     * 级联出课程的相关实例 
     * @param args
     */
    public List<Course> getAllCourseInfoList(QueryCourseCondition queryCourseCondition);
    
    /**
	 * 按状态查找课程
	 * @param queryCoursesortCondition
	 * @return
	 */
	public List getCourseListByStatus(QueryCourseCondition queryCourseCondition);
	
	/**
	 * @author chenshuai
	 * 功能：根据用户ID获取用户前台已购买课程集合
	 * @param args
	 * @param cusId 用户ID
	 * @return
	 */
	public List<UserCenterCourseDTO> getUserCenterCourseListByCusId(QueryCourseCondition queryCourseCondition);
	/**
	 * @author liuqinggang
	 * 功能：根据用户ID获取用户前台已购包集合
	 * @param args
	 * @param cusId 用户ID
	 * @return
	 */
	public List<SellWay> getUserCenterSellWayListByCusId(QueryCourseCondition queryCourseCondition);

	/**
	 * 得到当前课程所有节点信息
	 * @param querySellCouCondition
	 * @return
	 */
	public List<CourseDTO> getCoursePlan(int courseId);
	
	/**
	 * 获取所有课程id 
	 * @param subjectId
	 * @return
	 */
	 public List<Integer> getCourseIdAll(Integer subjectId);
}