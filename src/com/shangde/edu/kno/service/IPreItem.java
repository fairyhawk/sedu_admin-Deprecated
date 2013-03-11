package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.kno.domain.PreItem;
import com.shangde.edu.kno.condition.QueryPreItemCondition;


public interface IPreItem extends Serializable{

	/**
	 *@param preItem PreItem
	 *@return id 
	 */
    public java.lang.Integer addPreItem(PreItem preItem);

    /**
	 *@param preItem PreItem
	 *@return id 
	 */
    public void delPreItemById(int preId);

    /**
     * 更新
	 *@param preItem PreItem
	 *@return id 
	 */
    public void updatePreItem(PreItem preItem);

    /**
	 *@param preItem PreItem
	 *@return id 
	 */
    public PreItem getPreItemById(int preId);

    /**
	 *@param preItem PreItem
	 *@return id 
	 */
    public List<PreItem> getPreItemList(QueryPreItemCondition queryPreItemCondition);
    
    public PageResult getPreItemPageList(QueryPreItemCondition pCondition);
    /**
     * 查询此预设项是否和知识树已有关联
     * @param preItemId
     * @return
     */
    public int preItemRelKnowledgeCount(int preItemId);
    
    public int getPreItemCountByName(Map<String, Object> map);
}