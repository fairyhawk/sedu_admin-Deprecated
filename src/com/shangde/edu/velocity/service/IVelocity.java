package com.shangde.edu.velocity.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.velocity.condition.QueryVelocityCondition;

public interface IVelocity {
	
	public PageResult getAllVelocity(QueryVelocityCondition queryVelocityCondition);

}
