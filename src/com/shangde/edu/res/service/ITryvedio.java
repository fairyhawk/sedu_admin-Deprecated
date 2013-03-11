package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.condition.QueryTryvedioCondition;


public interface ITryvedio {
  
	/**
	 *添加
	 * @param Tryvedio tryvedio
	 * @return Integer
	 */
    public java.lang.Integer addTryvedio(Tryvedio tryvedio);
	/**
	 *	删除
	 * @param id
	 * @return void
	 */
    public void delTryvedioById(int id);
	/**
	 *	更新
	 * @param Tryvedio tryvedio
	 * @return void
	 */
    public void updateTryvedio(Tryvedio tryvedio);

    /**
	 *	根据id查询
	 * @param id
	 * @return Tryvedio
	 */
    public Tryvedio getTryvedioById(int id);
    /**
     * 根据查询条件查询
     *@param QueryTryvedioCondition
     */
    public List<Tryvedio> getTryvedioList(QueryTryvedioCondition queryTryvedioCondition);
    
}