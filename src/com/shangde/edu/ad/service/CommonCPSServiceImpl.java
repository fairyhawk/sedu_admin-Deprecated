package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.ad.condition.QueryCommonOrderQueryCondition;
import com.shangde.edu.finance.domain.Contract;

public class CommonCPSServiceImpl extends BaseService implements CommonCPSService {

	
	public List<Contract> orderQuery(QueryCommonOrderQueryCondition condition){
		return simpleDao.getForList("Contract_NS.queryCommonOrderQuery", condition);
	}

}
