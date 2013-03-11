package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.condition.QueryStudyCourseCondition;

/**
 * StudyCourse管理接口
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public interface IStudyCourse {
    /**
     * 添加studyCourse
     * @param studyCourse 要添加的studyCourse
     * @return id
     */
    public java.lang.Integer addStudyCourse(StudyCourse studyCourse);

    /**
     * 删除studyCourse
     * @param cId 要删除的id
     */
    public void delStudyCourseById(int cId);

    /**
     * 修改studyCourse޸ StudyCourse
     * @param studyCourse 要修改的studyCourse
     */
    public void updateStudyCourse(StudyCourse studyCourse);

    /**
     * 根据id获取单个studyCourse对象
     * @param cId 查询id
     * @return 学习课程
     */
    public StudyCourse getStudyCourseById(int cId);

    /**
     * 分页查询
     * @param queryStudyCourseCondition 查询条件
     * @return 分页查询结果
     */
    public List<StudyCourse> getStudyCourseList(QueryStudyCourseCondition queryStudyCourseCondition);
    
    /**
     * 查询
     */
    public List<StudyCourse> getStudyCourseByList(QueryStudyCourseCondition queryStudyCourseCondition);
    
    /**
     * 查询最后一条
     */
    public StudyCourse getStudyCourseLast(int pId);
    
}