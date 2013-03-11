package com.shangde.edu.cou.service;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCouFreeGiveCondition;

import com.shangde.edu.cou.domain.CouFreeGive;



@SuppressWarnings("unchecked")
public class CouFreeGiveImpl extends BaseServiceManyDb implements ICouFreeGive{

	public Integer addCouFreeGive(CouFreeGive couFreeGive) {
		
		return simpleDao.createEntity("CouFreeGive_NS.createCouFreeGive", couFreeGive);
	}

	public void delCouFreeGiveById(int couFreeGiveId) {
		simpleDao.deleteEntity("CouFreeGive_NS.deleteCouFreeGiveById", couFreeGiveId);
		
	}

	public CouFreeGive getCouFreeGiveById(int couFreeGiveId) {
		
		return simpleDao.getEntity("CouFreeGive_NS.getCouFreeGiveById", couFreeGiveId);
	}

	public List<CouFreeGive> getCouFreeGiveList(
			QueryCouFreeGiveCondition queryCouFreeGiveCondition) {
		
		return simpleDao.getForList("CouFreeGive_NS.getCouFreeGiveList", queryCouFreeGiveCondition);
	}

	public void updateCouFreeGive(CouFreeGive couFreeGive) {
		simpleDao.updateEntity("CouFreeGive_NS.updateCouFreeGive", couFreeGive);
		
	}

	public PageResult getCouFreeGiveByCondition(
			QueryCouFreeGiveCondition queryCouFreeGiveCondition) {
		
		return simpleDaoRead.getPageResult("CouFreeGive_NS.getCouFreeGiveByCondition","CouFreeGive_NS.getCouFreeGiveByConditionCount", queryCouFreeGiveCondition);
	}
	


}
