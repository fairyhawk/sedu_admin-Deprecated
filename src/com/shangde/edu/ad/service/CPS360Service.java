package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.edu.ad.condition.QueryCPS360OrderCheckCondition;
import com.shangde.edu.ad.condition.QueryCPS360OrderQueryCondition;
import com.shangde.edu.ad.dto.CPS360OrderCheckDTO;
import com.shangde.edu.ad.dto.CPS360OrderQueryDTO;

/**
 * 360CPS业务处理接口。
 * 
 * @author ZHENG QIANG
 */
public interface CPS360Service {

	/**
	 * 360CPS订单查询。
	 * 
	 * @param condition 360CPS订单查询的查询条件。
	 * @return 订单列表
	 */
	public abstract List<CPS360OrderQueryDTO> orderQuery(QueryCPS360OrderQueryCondition condition);

	/**
	 * 360CPS对账查询。
	 * 
	 * @param condition 360CPS对账查询的查询条件。
	 * @return 订单列表
	 */
	public abstract List<CPS360OrderCheckDTO> orderCheck(QueryCPS360OrderCheckCondition condition);

}