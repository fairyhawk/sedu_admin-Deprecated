package com.shangde.edu.kno.service;

import java.util.List;
import com.shangde.edu.kno.domain.NodePreMid;
import com.shangde.edu.kno.condition.QueryNodePreMidCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class NodePreMidImpl extends BaseService implements INodePreMid{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addNodePreMid(NodePreMid nodePreMid) {
return simpleDao.createEntity("NodePreMid_NS.createNodePreMid",nodePreMid);
    }

    public void delNodePreMidById(int id){
        simpleDao.deleteEntity("NodePreMid_NS.deleteNodePreMidById",id);
    }

    public void updateNodePreMid(NodePreMid nodePreMid) {
        simpleDao.updateEntity("NodePreMid_NS.updateNodePreMid",nodePreMid);
    }

    public NodePreMid getNodePreMidById(int id) {
        return simpleDao.getEntity("NodePreMid_NS.getNodePreMidById",id);
    }

    public List<NodePreMid> getNodePreMidList(QueryNodePreMidCondition queryNodePreMidCondition) {
        return simpleDao.getForList("NodePreMid_NS.getNodePreMidList",queryNodePreMidCondition);
    }
    
    /**
     * 根据ksnId获取单个NodePreMid对象
     * @param ksnId
     * @return
     */
    public NodePreMid getNodePreMidByKsnId(int ksnId){
    	return simpleDao.getEntity("NodePreMid_NS.getNodePreMidByKsnId", ksnId);
    }
}
