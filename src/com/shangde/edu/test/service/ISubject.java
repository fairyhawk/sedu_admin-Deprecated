package com.shangde.edu.test.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.test.domain.Subject;



/**
 * 所学专业管理接口
 * @author guoqiang.liu
 */
public interface ISubject extends Serializable{
    /**
     * 添加所学专业
     * @param subject 要添加的所学专业
     * @return id
     */
    public java.lang.Integer addSubject(Subject subject);

    /**
     * 根据id删除一个所学专业
     * @param subjectId 要删除的id
     */
    public void delSubjectById(int subjectId);

    /**
     * 修改所学专业
     * @param subject 要修改的所学专业
     */
    public void updateSubject(Subject subject);

    /**
     * 根据id获取单个所学专业对象
     * @param subjectId 要查询的id
     * @return 所学专业
     */
    public Subject getSubjectById(int subjectId);

    /**
     * 根据用户id获取所学专业列表
     * @param userId 用户id
     * @return 所学专业列表
     */
    public List<Subject> getSubjectListByUserId(int userId);
    
    /**
     * 根据状态查询专业
     * @param status 状态
     * @return 所学专业列表
     */
    public List<Subject> getSubjectListByStatus(int status);
    /**
     * 查询所有的专业
     */
    public List<Subject> getAllSubject() ;
    /**
     * 根据用户ID查询用户所购包所属的专业list,评价中心使用
     * zhangjuqiang
     */
    public List<Subject> getSubjectListForUnAss(int cusId);
 
   
    
    public List<Subject> getUnBuySubject(List<Integer> sids);
}