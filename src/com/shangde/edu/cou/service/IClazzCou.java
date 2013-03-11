package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.ClazzCou;
import com.shangde.edu.cou.condition.QueryClazzCouCondition;

public interface IClazzCou {
    public java.lang.Integer addClazzCou(ClazzCou clazzCou);
    public void delClazzCouById(int id);
    public void updateClazzCou(ClazzCou clazzCou);

    public ClazzCou getClazzCouById(int id);

    public List<ClazzCou> getClazzCouList(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 按班级名查询课程列表
     * @param queryClazzCouCondition
     * @return
     */
    public PageResult getCourseListByClazzId(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 判断在班级中课程是否存在
     *
     * @param queryClazzCouCondition
     * @return
     */
    public Integer isExistInClazz(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 从班级中删除课程
     * @param queryClazzCouCondition
     */
    public void deleteCourseInClazz(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 
     * @param courseId
     * @return
     * @authorcxs
     * 功能：按课程ID获取班级集合
     * @param args
     */
    public List getClazzCouByCourseId(int courseId);
}