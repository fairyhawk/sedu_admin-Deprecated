package com.shangde.edu.ad.service;

import java.util.List;

import com.shangde.edu.ad.condition.QueryCHANETOrderQueryCondition;
import com.shangde.edu.ad.dto.CHANETOrderQueryDTO;

public interface CHANETService {
	
	public abstract List<CHANETOrderQueryDTO> orderQuery(QueryCHANETOrderQueryCondition condition);

}
