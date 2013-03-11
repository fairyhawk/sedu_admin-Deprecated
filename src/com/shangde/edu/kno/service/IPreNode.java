package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;


public interface IPreNode extends Serializable{

	/**
	 *@param preNode 
	 */
    public java.lang.Integer addPreNode(PreNode preNode);

    /**
     * 删除
     * @param int preNodeId
     */
    public void delPreNodeById(int preNodeId);

    /**
     * 更新
     * @param PreNode preNode
     */
    public void updatePreNode(PreNode preNode);

    /**
     * 根据preNodeId查询
     * @param PreNode preNode
     */
    public PreNode getPreNodeById(int preNodeId);


    /**
     * 根据查询条件
     * @param QueryPreNodeCondition 查询条件
     */
    public List<PreNode> getPreNodeList(QueryPreNodeCondition queryPreNodeCondition);
    
    /**
     * 批量插入操作
     * @param preNodes
     */
    public void addEntity(List<PreNode> preNodes);
    
    /**
     * 批量删除操作
     * @param preNodeIds: 预设项子结点的ID
     */
    public void delPreNodes(List<Integer> preNodeIds);
    /**
     * 批量修改操作
     * @param preNodes
     */
    public void updateEntity(List<PreNode> preNodes);
    
    /**
     * 分页查找
     */
    public PageResult getPreNodePageList(QueryPreNodeCondition queryPreNodeCondition);
    
    
    public List<PreNode> getPreNodeListByPreItemId(QueryPreNodeCondition pCondition);
    
    public List<Integer> getPreNodeIdsByPreItemId(QueryPreNodeCondition pCondition);
    
    public int preNodeRelKnowledgeCount(int preNodeId);
    
    public int getProNodeCount(QueryPreNodeCondition pnCondition);
    
    public int getPreNodeCountByName(Map<String , Object> map);
}