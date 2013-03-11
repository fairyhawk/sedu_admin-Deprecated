package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.ad.condition.QueryCHANETOrderQueryCondition;
import com.shangde.edu.ad.dto.CHANETOrderQueryDTO;

public class CHANETServiceImpl extends BaseService implements CHANETService {

	@Override
	public List<CHANETOrderQueryDTO> orderQuery(QueryCHANETOrderQueryCondition condition) {
	
		
		return simpleDao.getForList("Contract_NS.queryCHANETOrderQuery", condition);
	}

}
