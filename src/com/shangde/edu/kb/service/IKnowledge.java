package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.condition.QueryKnowledgeCondition;

/**
 * Knowledge接口管理
 * User: guoqiang.liu
 * Date: 2011-01-04
 */
public interface IKnowledge {
    /**
     * @param knowledge 
     * @return id
     */
    public java.lang.Integer addKnowledge(Knowledge knowledge);

    /**
     * 根据kid查询
     * @param kId
     */
    public void delKnowledgeById(int kId);

    /**
     * @param knowledge Knowledge
     */
    public void updateKnowledge(Knowledge knowledge);

    /**
     * @param kId
     * @return Knowledge
     */
    public Knowledge getKnowledgeById(int kId);

    /**
     * @param queryKnowledgeCondition 查询条件
     * @return List<Knowledge>
     */
    public List<Knowledge> getKnowledgeList(QueryKnowledgeCondition queryKnowledgeCondition);
    
    public List<Knowledge> getKnowledgeListByScId(QueryKnowledgeCondition queryKnowledgeCondition);
    
    public Knowledge getKnowledgeByIndex(String kIndex);
    /**
     * 根据专业，索引获取课程信息
     * @param queryKnowledgeCondition
     * @return
     */
    public List<Knowledge> getKnowledgeListByPidOrIndex(QueryKnowledgeCondition queryKnowledgeCondition);
    
    /**
     * 根据kid发布课程
     * @param knowledge
     */
    public void publishKnowledge(Knowledge knowledge);
    /**
     * 查询最后一条
     * @param knowledge
     */
    public Knowledge getKnowledgeLast(QueryKnowledgeCondition queryKnowledgeCondition);
    
	/**
	 * author : hhq
	 * 根据epid查询跟试题有关系链的知识节点集合
	 */
    public List<Knowledge> getKnowledgeEpidList(int epid);
    
    /**
     * author:hhq
     * 递归查询
     */
    public List <Knowledge> getKnowledgediguiList(String index);
}