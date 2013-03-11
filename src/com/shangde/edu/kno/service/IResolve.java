package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.kno.domain.Resolve;
import com.shangde.edu.kno.condition.QueryResolveCondition;

/**
 * Resolve管理接口
 * User: guoqiang.liu
 * Date: 2011-09-08
 */
public interface IResolve extends Serializable{
    /**
     * 添加Resolve
     * @param resolve 要添加的Resolve
     * @return id
     */
    public java.lang.Integer addResolve(Resolve resolve);

    /**
     * 根据id删除一个Resolve
     * @param resId 要删除的id
     */
    public void delResolveById(int resId);

    /**
     * 修改Resolve
     * @param resolve 要修改的Resolve
     */
    public void updateResolve(Resolve resolve);

    /**
     * 根据id获取单个Resolve对象
     * @param resId 要查询的id
     * @return 年级
     */
    public Resolve getResolveById(int resId);
    
    /**
     * 根据ksnId获取resolve对象列表
     * @param ksnId
     * @return
     */
    public List<Resolve> getResolveListByKsnId(int ksnId);

    /**
     * 根据条件获取Resolve列表
     * @param queryResolveCondition 查询条件
     * @return 查询结果
     */
    public List<Resolve> getResolveList(QueryResolveCondition queryResolveCondition);
    
    /**
     * 查找首选解析的节点
     * @param ksnId
     * @return
     */
    public Resolve getResolveFirstByKsnId(int ksnId);
}