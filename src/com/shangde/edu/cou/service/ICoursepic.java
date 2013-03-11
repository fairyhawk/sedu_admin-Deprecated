package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Coursepic;
import com.shangde.edu.cou.condition.QueryCoursepicCondition;

/**
 * Coursepic课程图片
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ICoursepic {
    /**
     * 添加课程图片
     * @param coursepic 课程图片
     * @return id
     */
    public java.lang.Integer addCoursepic(Coursepic coursepic);

    /**
     * 删除课程图片
     * @param picId
     */
    public void delCoursepicById(int picId);

    /**
     *更新课程图片
     * @param coursepic
     */
    public void updateCoursepic(Coursepic coursepic);

    /**
     * 根据课程图片ID获取课程图片
     * @param picId
     * @return
     */
    public Coursepic getCoursepicById(int picId);

    /**
     * 获取课程图片集合
     * @param queryCoursepicCondition
     */
    public List<Coursepic> getCoursepicList(QueryCoursepicCondition queryCoursepicCondition);
    
    /**
     * 获取课程图片主分类
     * @param queryCoursepicCondition
     * @return
     */
    public List<Coursepic> getMyCoursepicList(QueryCoursepicCondition queryCoursepicCondition);
    
    /**
     * 按课程名删除课程图片
     */
    public void deleteCoursepicByCourseId(int courseId);
}