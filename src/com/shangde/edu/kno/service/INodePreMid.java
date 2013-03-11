package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.kno.domain.NodePreMid;
import com.shangde.edu.kno.condition.QueryNodePreMidCondition;

/**
 * NodePreMid管理接口
 * User: guoqiang.liu
 * Date: 2011-08-31
 */
public interface INodePreMid extends Serializable {
    /**
     * 添加NodePreMid
     * @param nodePreMid 要添加的NodePreMid
     * @return id
     */
    public java.lang.Integer addNodePreMid(NodePreMid nodePreMid);

    /**
     * 根据id删除一个NodePreMid
     * @param id 要删除的id
     */
    public void delNodePreMidById(int id);

    /**
     * 修改NodePreMid
     * @param nodePreMid 要修改的NodePreMid
     */
    public void updateNodePreMid(NodePreMid nodePreMid);

    /**
     * 根据id获取单个NodePreMid对象
     * @param id 要查询的id
     * @return 年级
     */
    public NodePreMid getNodePreMidById(int id);

    /**
     * 根据ksnId获取单个NodePreMid对象
     * @param ksnId
     * @return
     */
    public NodePreMid getNodePreMidByKsnId(int ksnId);
    
    /**
     * 根据条件获取NodePreMid列表
     * @param queryNodePreMidCondition 查询条件
     * @return 查询结果
     */
    public List<NodePreMid> getNodePreMidList(QueryNodePreMidCondition queryNodePreMidCondition);
}