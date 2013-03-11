package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.StudyPlan;
import com.shangde.edu.cus.condition.QueryStudyPlanCondition;

/**
 * StudyPlan管理接口
 * User: guoqiang.liu
 * Date: 2010-08-24
 */
public interface IStudyPlan {
    /**
     * 添加StudyPlan
     * @param studyPlan 要添加的StudyPlan
     * @return id
     */
    public java.lang.Integer addStudyPlan(StudyPlan studyPlan);

    /**
     * 根据id删除一个StudyPlan
     * @param id 要删除的id
     */
    public void delStudyPlanById(int id);

    /**
     * 修改StudyPlan
     * @param studyPlan 要修改的StudyPlan
     */
    public void updateStudyPlan(StudyPlan studyPlan);

    /**
     * 根据id获取单个StudyPlan对象
     * @param id 要查询的id
     * @return 年级
     */
    public StudyPlan getStudyPlanById(int id);

    /**
     * 根据条件获取StudyPlan列表
     * @param queryStudyPlanCondition 查询条件
     * @return 查询结果
     */
    public List<StudyPlan> getStudyPlanList(QueryStudyPlanCondition queryStudyPlanCondition);

    /**
     * 根据时间拿到一个月的有学习计划的日期
     * @param queryStudyPlanCondition 学员id和时间
     */
	public List<Integer> getMonthStudyPlan(
			QueryStudyPlanCondition queryStudyPlanCondition);

	/**
	 * 根据日期和学员id查询学习计划
	 * @param queryStudyPlanCondition 日期和学员id
	 * @return
	 */
	public StudyPlan getStudyPlanByDate(
			QueryStudyPlanCondition queryStudyPlanCondition);
	
	   /**
     * 根据id删除一个StudyPlan
     * @param id 要删除的id
     */
    public void delStudyPlanByCusId(int cusId);
    
    /**
     * Yangning 2011/12/6 后台批量删除用户时级联删除StudyPlan
     * @param ids
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer delBathStudyPlanByCusIds(String ids);
}