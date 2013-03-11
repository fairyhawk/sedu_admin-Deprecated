package com.shangde.edu.dis.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryPlateCondition;

public class PlateManagerImpl extends BaseService implements IPlateManager {
	public PageResult GetPlate(QueryPlateCondition queryPlate) {
		return simpleDao.getPageResult("dis_dis_area_tbl.getplate",
				"dis_dis_area_tbl.getplateCounter", queryPlate);
	}
}
