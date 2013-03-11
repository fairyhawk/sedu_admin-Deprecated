package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.ad.condition.QueryCPS360OrderCheckCondition;
import com.shangde.edu.ad.condition.QueryCPS360OrderQueryCondition;
import com.shangde.edu.ad.dto.CPS360OrderCheckDTO;
import com.shangde.edu.ad.dto.CPS360OrderQueryDTO;

/**
 * 360CPS业务处理接口。
 * 
 * @author ZHENG QIANG
 */
public class CPS360ServiceImpl extends BaseService implements CPS360Service {

	/**
	 * 360CPS订单查询。
	 * 
	 * @param condition 360CPS订单查询的查询条件。
	 * @return 订单列表
	 */
	public List<CPS360OrderQueryDTO> orderQuery(QueryCPS360OrderQueryCondition condition) {
		return simpleDao.getForList("Contract_NS.queryCPS360OrderQuery", condition);
	}

	/**
	 * 360CPS对账查询。
	 * 
	 * @param condition 360CPS对账查询的查询条件。
	 * @return 订单列表
	 */
	public List<CPS360OrderCheckDTO> orderCheck(QueryCPS360OrderCheckCondition condition) {
		return simpleDao.getForList("Contract_NS.queryCPS360OrderCheck", condition);
	}

}