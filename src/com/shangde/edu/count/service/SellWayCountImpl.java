package com.shangde.edu.count.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.count.condition.QuerySellWayCountCondition;
import com.shangde.edu.count.dto.SellWayCountNewDTO;
import com.shangde.edu.count.dto.SellWayDTO;


public class SellWayCountImpl extends BaseService implements ISellWayCount {

	public List<SellWayDTO> getSellWayCount(QuerySellWayCountCondition querySellWayCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWayCount_NS.getSellWayCountBySellWayId", querySellWayCountCondition);
	}

	public List<SellWay> getSellWayBySubjectId(QuerySellWayCountCondition querySellWayCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWayCount_NS.getSellWayBySubjectId", querySellWayCountCondition);
	}

	public List<SellWayDTO> getWFSellWayCount(
			QuerySellWayCountCondition querySellWayCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWayCount_NS.getWFSellWayCountBySellWayId", querySellWayCountCondition);
	}

	public Integer getCustomerContractCountBySellId(
			QuerySellWayCountCondition querySellWayCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("SellWayCount_NS.getSellWayCustomerContractCount", querySellWayCountCondition);
	}

	public List<SellWayCountNewDTO> getSellWayNew(QuerySellWayCountCondition querySellWayCountCondition) {
		return simpleDao.getForList("SellWayCount_NS.getSellWayNew", querySellWayCountCondition);
	}
}
