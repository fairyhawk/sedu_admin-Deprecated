package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QuerySimpleDicCondition;
import com.shangde.edu.sys.domain.SimpleDic;

/**
 * 简单字典表管理接口
 * @author guoqiang.liu
 */
public interface ISimpleDic {
    /**
     * 添加简单字典表
     * @param simpleDic 要添加的简单字典表
     */
    public void addSimpleDic(SimpleDic simpleDic);

    /**
     * 根据id删除一个简单字典表
     * @param dicId 字典id
     * @param tableName 表名称
     * @param columnName 字段名称
     */
    public void delSimpleDicById(int dicId,String tableName,String columnName);

    /**
     * 修改简单字典表
     * @param simpleDic 要修改的简单字典表
     */
    public void updateSimpleDic(SimpleDic simpleDic);

    /**
     * 根据id获取单个简单字典表对象
     * @param dicId 字典id
     * @param tableName 表名称
     * @param columnName 字段名称
     * @return 年级
     */
    public SimpleDic getSimpleDicById(int dicId,String tableName,String columnName);

    /**
     * 根据条件获取简单字典表列表
     * @param querySimpleDicCondition 查询条件
     * @return 查询结果
     */
    public List<SimpleDic> getSimpleDicList(QuerySimpleDicCondition querySimpleDicCondition);
}