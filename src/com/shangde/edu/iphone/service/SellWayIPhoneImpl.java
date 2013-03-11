package com.shangde.edu.iphone.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;

public class SellWayIPhoneImpl extends BaseService implements ISellWayIPhone {

	public PageResult getContractSellWayByCusId(
			QuerySellWayIPhoneCondition querySellWayIPhoneCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("ContractIPhone_NS.getContractAndSellWayByCusId", "ContractIPhone_NS.getContractAndSellWayCount", querySellWayIPhoneCondition);
	}

	public List<Teacher> getTeacherBySellWayId(int sellWayId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("ContractIPhone_NS.getTeacherBySellWayId", sellWayId);
	}

}
