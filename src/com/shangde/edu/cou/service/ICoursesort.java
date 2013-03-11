package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.CourseSortListDTO;

/**
 * Coursepic课程分类集合
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ICoursesort {
	/**
     * 添加课程分类
     * @param coursesort 
     * @return id
     */
    public java.lang.Integer addCoursesort(Coursesort coursesort);

    /**
     * 删除课程通过课程ID
     * @param coursesortId 课程ID
     */
    public void delCoursesortById(int coursesortId);

    /**
     * 更新课程分类
     * @param coursesort 要更新的课程分类
     */
    public void updateCoursesort(Coursesort coursesort);

    /**
     * 通过课程分类ID获取课程分类
     * @param coursesortId 课程分类ID
     * @return 课程分类
     */
    public Coursesort getCoursesortById(int coursesortId);

    /**
     * 根据条件查询课程集合
     * @param queryCoursesortCondition 查询条件
     * @return 结果结合
     */
    public List<Coursesort> getCoursesortList(QueryCoursesortCondition queryCoursesortCondition);
    
    /**
     * 
     *获取子课程集合
     *@return 结果集合 
     */
    public List<Coursesort> getChildCoursesortList(QueryCoursesortCondition queryCoursesortCondition);
    
    /**
     * 
     *批量删除子课程
     *@param sortIds
     *@return void
     */
    public void deleteCourseSorts(String sortIds);
    
    /**
     * 获取课程分类树
     * @param queryCourseCondition 查询条件
     * @return 课程集合
     */
    public List<Coursesort> getCoursesortTreeList(QueryCoursesortCondition queryCoursesortCondition);
    
    /**
     * 获取所有课程分类集合
     * @return
     */
    public PageResult getNomalCoursesortList(QueryCoursesortCondition queryCoursesortCondition);
    
    /**
     * 获取前台课程列表
     * @return
     */
    public List<CourseSortListDTO> getCourseSortListDTOList();
    
    /**
     * 获得三级分类（即主页显示分类）
     * @param queryCoursesortCondition
     * @return
     */
    public List<Coursesort> getLastCourseSortList(QueryCoursesortCondition queryCoursesortCondition);
    
	/**
	 * 为Tag查询课程分类集合
	 * @param queryCoursesortCondition
	 * @return
	 */
	public List getCoursesortListForTag(QueryCoursesortCondition queryCoursesortCondition);
}