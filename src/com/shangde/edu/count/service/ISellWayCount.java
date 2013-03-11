package com.shangde.edu.count.service;

import java.util.List;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.count.condition.QueryContractCountCondition;
import com.shangde.edu.count.condition.QuerySellWayCountCondition;
import com.shangde.edu.count.dto.ContractCountNewDTO;
import com.shangde.edu.count.dto.SellWayCountNewDTO;
import com.shangde.edu.count.dto.SellWayDTO;

public interface ISellWayCount {

	
	/**
	 * 得到售卖方式所有理解，统计售卖方式信息
	 * @return
	 */
	public List<SellWayDTO> getSellWayCount(QuerySellWayCountCondition querySellWayCountCondition);
	
	/**
	 * 得到免费试听意外的售卖方式
	 * @return 售卖方式集合
	 */
	public List<SellWay> getSellWayBySubjectId(QuerySellWayCountCondition querySellWayCountCondition);
	
	
	/**
	 * 得到售卖方式所有未付信息
	 */
	
	public List<SellWayDTO> getWFSellWayCount(QuerySellWayCountCondition querySellWayCountCondition);
	
	
	/**
	 * 得到当前专业注册人数中 有多少人下单为当前售卖方式
	 * @param querySellWayCountCondition
	 * @return
	 */
	public Integer getCustomerContractCountBySellId(QuerySellWayCountCondition querySellWayCountCondition);
	
	/**
	 * 商品统计优化
	 * wd
	 */
	public List<SellWayCountNewDTO> getSellWayNew(QuerySellWayCountCondition querySellWayCountCondition); 
}
