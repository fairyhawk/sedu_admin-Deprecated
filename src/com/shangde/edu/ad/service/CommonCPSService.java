package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.edu.ad.condition.QueryCommonOrderQueryCondition;
import com.shangde.edu.finance.domain.Contract;

public interface CommonCPSService {
	
	public abstract List<Contract> orderQuery(QueryCommonOrderQueryCondition condition);

}
