package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.HistoryPrice;
import com.shangde.edu.cou.condition.QueryHistoryPriceCondition;

/**
 * HistoryPrice历史价格接口
 * 课程历史价格接口
 * @return id
 */
public interface IHistoryPrice {
    /**
     * 添加历史价格
     * @param historyPrice 
     * @return id
     */
    public java.lang.Integer addHistoryPrice(HistoryPrice historyPrice);

    /**
     * 删除历史价格
     * @param id 
     */
    public void delHistoryPriceById(int id);
    /**
     * 更新历史价格
     * @param historyPrice
     */
    public void updateHistoryPrice(HistoryPrice historyPrice);

    /**
     * 根据ID获取历史价格
     * @param id 历史价格ID
     * @return 
     */
    public HistoryPrice getHistoryPriceById(int id);

    /**
     * 获取历史价格表
     * @param queryHistoryPriceCondition 查询历史价格条件
     * @return 历史价格集合
     */
    public List<HistoryPrice> getHistoryPriceList(QueryHistoryPriceCondition queryHistoryPriceCondition);
    
    /**
     * 按课程ID删除历史价格
     * @param courseId
     */
    public void deleteHistoryPriceByCourseId(int courseId);
    
    /**
     * @author chenshuai
     * 功能：根据课程ID获取课程历史价格表
     * @param args
     * @param queryHistoryPriceCondition
     * @return
     */
    public PageResult getHistoryListByCourseId(QueryHistoryPriceCondition queryHistoryPriceCondition);
    
    /**
     * @author cxs
     * 功能：查询指定时间指定课程的购买数量
     * @param queryHistoryPriceCondition
     * @return
     */
    public Integer getHistoryPriceCourseNum(QueryHistoryPriceCondition queryHistoryPriceCondition);
}