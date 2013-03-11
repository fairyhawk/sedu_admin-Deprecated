package com.shangde.edu.dis.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryPlateCondition;

public interface IPlateManager {
	public PageResult GetPlate(QueryPlateCondition queryPlate);
}
