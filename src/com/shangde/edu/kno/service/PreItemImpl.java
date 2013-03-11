package com.shangde.edu.kno.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.kno.domain.PreItem;
import com.shangde.edu.kno.condition.QueryPreItemCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class PreItemImpl extends BaseService implements IPreItem{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addPreItem(PreItem preItem) {
return simpleDao.createEntity("PreItem_NS.createPreItem",preItem);
    }

    public void delPreItemById(int preId){
        simpleDao.deleteEntity("PreItem_NS.deletePreItemById",preId);
    }

    public void updatePreItem(PreItem preItem) {
        simpleDao.updateEntity("PreItem_NS.updatePreItem",preItem);
    }

    public PreItem getPreItemById(int preId) {
        return simpleDao.getEntity("PreItem_NS.getPreItemById",preId);
    }

    public List<PreItem> getPreItemList(QueryPreItemCondition queryPreItemCondition) {
        return simpleDao.getForList("PreItem_NS.getPreItemList",queryPreItemCondition);
    }
    public PageResult getPreItemPageList(QueryPreItemCondition pCondition){
    	return simpleDao.getPageResult("PreItem_NS.getPreItemPageList", "PreItem_NS.getPreItemCount", pCondition);
    }

	public int preItemRelKnowledgeCount(int preItemId) {
		
		return simpleDao.getEntity("PreItem_NS.preItemRelKnowledgeCount", preItemId);
	}

	public int getPreItemCountByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("PreItem_NS.getPreItemCountByName", map);
	}
}
