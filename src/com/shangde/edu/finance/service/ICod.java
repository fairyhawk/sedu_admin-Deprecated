package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Cod;
import com.shangde.edu.finance.condition.QueryCodCondition;

/**
 * Cod接口管理
 * User: guoqiang.liu
 * Date: 2011-03-09
 */
public interface ICod {
    /**
     * 添加Cod
     * @param cod 添加一个Cod
     * @return id
     */
    public java.lang.Integer addCod(Cod cod);

    /**
     * 删除一个cod
     * @param codId
     */
    public void delCodById(int codId);

    /**
     * 更新Cod
     * @param Cod cod
     */
    public void updateCod(Cod cod);

    /**
     * 根据codId查询
     * @param codId Integer
     * @return Cod
     */
    public Cod getCodById(int codId);

    /**
     * 根据查询条件查询
     * @param queryCodCondition
     * @return List<Cod>集合
     */
    public List<Cod> getCodList(QueryCodCondition queryCodCondition);
}