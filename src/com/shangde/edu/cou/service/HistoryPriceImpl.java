package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryHistoryPriceCondition;
import com.shangde.edu.cou.domain.HistoryPrice;

/**
 * HistoryPrice 课程历史价格
 * User: guoqiang.liu
 * Date: 2010-08-10
 */
@SuppressWarnings("unchecked")
public class HistoryPriceImpl extends BaseService implements IHistoryPrice{
	/**
     * 添加历史价格
     * @param historyPrice 
     * @return id
     */
    public java.lang.Integer addHistoryPrice(HistoryPrice historyPrice) {
    	return simpleDao.createEntity("HistoryPrice_NS.createHistoryPrice",historyPrice);
    }
    
    /**
     * 删除历史价格
     * @param id 
     */
    public void delHistoryPriceById(int id){
        simpleDao.deleteEntity("HistoryPrice_NS.deleteHistoryPriceById",id);
    }
    /**
     * 
     *更新历史价格
     *@param historyPrice 
     */
    public void updateHistoryPrice(HistoryPrice historyPrice) {
        simpleDao.updateEntity("HistoryPrice_NS.updateHistoryPrice",historyPrice);
    }
    
    /**
     * 根据ID获取历史价格
     * @param id 历史价格ID
     * @return 
     */
    public HistoryPrice getHistoryPriceById(int id) {
        return simpleDao.getEntity("HistoryPrice_NS.getHistoryPriceById",id);
    }
    
    /**
     * 获取历史价格表
     * @param queryHistoryPriceCondition 查询历史价格条件
     * @return 历史价格集合
     */
    public List<HistoryPrice> getHistoryPriceList(QueryHistoryPriceCondition queryHistoryPriceCondition) {
        return simpleDao.getForList("HistoryPrice_NS.getHistoryPriceList",queryHistoryPriceCondition);
    }
    
    /**
     * 删除课程历史价格
     */
    public void deleteHistoryPriceByCourseId(int courseId){
    	simpleDao.deleteEntity("HistoryPrice_NS.deleteHistoryPriceByCourseId", courseId);
    }
    
    /**
     * @author chenshuai
     * 功能：根据课程ID获取课程历史价格表
     * @param queryHistoryPriceCondition
     * @return
     */
    public PageResult getHistoryListByCourseId(QueryHistoryPriceCondition queryHistoryPriceCondition){
    	return simpleDao.getPageResult("HistoryPrice_NS.getHistoryListByCourseId", "HistoryPrice_NS.getHistoryListCountByCourseId", queryHistoryPriceCondition);
    }
    
    /**
     * @author cxs
     * 功能：查询指定时间指定课程的购买数量
     * @param queryHistoryPriceCondition
     * @return
     */
    public Integer getHistoryPriceCourseNum(QueryHistoryPriceCondition queryHistoryPriceCondition){
    	return simpleDao.getEntity("HistoryPrice_NS.getHistoryPriceCourseNum", queryHistoryPriceCondition);
    }
}
