package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.condition.QueryKnowledgeCondition;
import com.shangde.common.service.BaseService;

/**
 * Knowledge
 * User: guoqiang.liu
 * Date: 2011-01-04
 */
@SuppressWarnings("unchecked")
public class KnowledgeImpl extends BaseService implements IKnowledge{
    public java.lang.Integer addKnowledge(Knowledge knowledge) {
return simpleDao.createEntity("Knowledge_NS.createKnowledge",knowledge);
    }

    public void delKnowledgeById(int kId){
        simpleDao.deleteEntity("Knowledge_NS.deleteKnowledgeById",kId);
    }

    public void updateKnowledge(Knowledge knowledge) {
        simpleDao.updateEntity("Knowledge_NS.updateKnowledge",knowledge);
    }

    public Knowledge getKnowledgeById(int kId) {
        return simpleDao.getEntity("Knowledge_NS.getKnowledgeById",kId);
    }

    public List<Knowledge> getKnowledgeList(QueryKnowledgeCondition queryKnowledgeCondition) {
        return simpleDao.getForList("Knowledge_NS.getKnowledgeList",queryKnowledgeCondition);
    }
    
    public List<Knowledge> getKnowledgeListByScId(QueryKnowledgeCondition queryKnowledgeCondition) {
        return simpleDao.getForList("Knowledge_NS.getKnowledgeListByScId",queryKnowledgeCondition);
    }

	public List<Knowledge> getKnowledgeListByPidOrIndex(
			QueryKnowledgeCondition queryKnowledgeCondition) {
		return simpleDao.getForList("Knowledge_NS.getKnowledgeListByPidOrIndex", queryKnowledgeCondition);
	}

	public void publishKnowledge(Knowledge knowledge) {
		simpleDao.updateEntity("Knowledge_NS.publishKnowledge", knowledge);
	}
    
    public Knowledge getKnowledgeByIndex(String kIndex) {
        return simpleDao.getEntity("Knowledge_NS.getKnowledgeByIndex",kIndex);
    }

	public Knowledge getKnowledgeLast(
			QueryKnowledgeCondition queryKnowledgeCondition) {
		
		return simpleDao.getEntity("Knowledge_NS.getKnowledgeLast",queryKnowledgeCondition);
	}
	
	/**
	 * author : hhq
	 * 根据epid查询跟试题有关系链的知识节点集合
	 */
    public List<Knowledge> getKnowledgeEpidList(int epid)
    {
    	return simpleDao.getForList("Knowledge_NS.getKnowledgeEpidList", epid);
    }
    
    /**
     * author:hhq
     * 递归查询
     */
    public List <Knowledge> getKnowledgediguiList(String index)
    {
    	return simpleDao.getForList("Knowledge_NS.getKnowledgediguiList", index);
    	
    }
    
}
