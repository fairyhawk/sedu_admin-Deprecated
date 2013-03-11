package com.shangde.edu.kno.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class PreNodeImpl extends BaseService implements IPreNode{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addPreNode(PreNode preNode) {
return simpleDao.createEntity("PreNode_NS.createPreNode",preNode);
    }

    public void delPreNodeById(int preNodeId){
        simpleDao.deleteEntity("PreNode_NS.deletePreNodeById",preNodeId);
    }

    public void updatePreNode(PreNode preNode) {
        simpleDao.updateEntity("PreNode_NS.updatePreNode",preNode);
    }

    public PreNode getPreNodeById(int preNodeId) {
        return simpleDao.getEntity("PreNode_NS.getPreNodeById",preNodeId);
    }

    public List<PreNode> getPreNodeList(QueryPreNodeCondition queryPreNodeCondition) {
        return simpleDao.getForList("PreNode_NS.getPreNodeList",queryPreNodeCondition);
    }
    
    public void addEntity(List<PreNode> preNodes){
    	simpleDao.batchExecuteSingleSql("PreNode_NS.createPreNode", preNodes, "insert", 99);
    }
    
    public void delPreNodes(List<Integer> preNodeIds){
    	simpleDao.batchExecuteSingleSql("PreNode_NS.deletePreNodeById", preNodeIds, "delete", 99);
    }

	public List<Integer> getPreNodeIdsByPreItemId(QueryPreNodeCondition pCondition) {
		return simpleDao.getForList("PreNode_NS.getPreNodeIdsByPreItemId", pCondition);
	}

	public List<PreNode> getPreNodeListByPreItemId(QueryPreNodeCondition pCondition) {		
		return simpleDao.getForList("PreNode_NS.getPreNodeListByPreItemId", pCondition);
	}

	public PageResult getPreNodePageList(
			QueryPreNodeCondition queryPreNodeCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("PreNode_NS.getPreNodePageListByPreItemId", "PreNode_NS.getPreNodeCount", queryPreNodeCondition);
	}

	public void updateEntity(List<PreNode> preNodes) {
		// TODO Auto-generated method stub
		simpleDao.batchExecuteSingleSql("PreNode_NS.updatePreNode", preNodes, "update", 99);
	}

	public int preNodeRelKnowledgeCount(int preNodeId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("PreNode_NS.preNodeRelKnowledgeCount", preNodeId);
	}

	public int getProNodeCount(QueryPreNodeCondition pnCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("PreNode_NS.getPreNodeCount", pnCondition);
	}

	public int getPreNodeCountByName(Map<String , Object> map) {
		// TODO Auto-generated method stub		
		return simpleDao.getEntity("PreNode_NS.getPreNodeCountByName", map);
	}
}
