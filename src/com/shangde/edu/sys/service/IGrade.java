package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.domain.Grade;

/**
 * 入学年份管理接口
 * @author guoqiang.liu
 */
public interface IGrade {
    /**
     * 添加年级
     * @param grade 要添加的年级
     * @return id
     */
    public java.lang.Integer addGrade(Grade grade);

    /**
     * 根据id删除一个年级
     * @param gradeId 要删除的id
     */
    public void delGradeById(int gradeId);

    /**
     * 修改年级
     * @param grade 要修改的年级
     */
    public void updateGrade(Grade grade);

    /**
     * 根据id获取单个年级对象
     * @param gradeId 要查询的id
     * @return 年级
     */
    public Grade getGradeById(int gradeId);

    /**
     * 根据条件获取年级列表
     * @param queryGradeCondition 查询条件
     * @return 查询结果
     */
    public List<Grade> getGradeList(QueryGradeCondition queryGradeCondition);
    /**
     * 根据用户id 获取用户具有发布权限的年级列表
     * @param userId 用户id
     * @return 年级列表
     */
    public List<Grade> getGradeListByUserId(int userId);
    
    /**
     * 根据状态查询年级列表
     * @param status 状态
     * @return
     */
    public List<Grade> getGradeListByStatus(int status);
}