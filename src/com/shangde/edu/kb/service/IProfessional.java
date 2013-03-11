package com.shangde.edu.kb.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;

/**
 * Professional管理接口
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public interface IProfessional extends Serializable {
    /**
     * 添加Professional
     * @param professional 要添加的Professional
     * @return id
     */
    public java.lang.Integer addProfessional(Professional professional);

    /**
     * 删除Professional
     * @param pId 要删除的id
     */
    public void delProfessionalById(int pId);

    /**
     * 修改Professional޸
     * @param professional 要修改的Professional
     */
    public void updateProfessional(Professional professional);

    /**
     * 根据id获取单个Professional对象
     * @param pId 查询id
     * @return 专业
     */
    public Professional getProfessionalById(int pId);

    /**
     * 分页查询
     * @param queryProfessionalCondition 查询条件
     * @return 分页查询结果
     */
    public List<Professional> getProfessionalList(QueryProfessionalCondition queryProfessionalCondition);
    
    /**返回专业列表list
     * 
     */
    public List<Professional> getProfessionalByList(QueryProfessionalCondition queryProfessionalCondition);
    
    /**
     * 查询最后一条专业记录
     */
    public Professional getProfessionalLast();
    
}