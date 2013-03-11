package com.shangde.edu.velocity.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.velocity.condition.QueryVelocityCondition;

public class VelocityImpl extends BaseService implements IVelocity {

	public PageResult getAllVelocity(
			QueryVelocityCondition queryVelocityCondition) {
		
		return this.simpleDao.getPageResult("Velocity_NS.getAllVelocity","Velocity_NS.getAllVelocityCount",queryVelocityCondition);
	}

}
