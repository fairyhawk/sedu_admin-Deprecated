package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryAbnormalCustomerCondition;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;
import com.shangde.edu.cus.domain.FreezeLog;
import com.shangde.edu.cus.dto.CustomerFreezeStatusDTO;
import com.shangde.edu.cus.dto.FreezeLogDetialDTO;
import com.shangde.edu.cus.dto.LoginRecordDetailDTO;

public class FreezeLogImpl extends BaseService implements IFreezeLog {

	public void saveFreezeLog(FreezeLog freezeLog) {
		simpleDao.createEntity("FreezeLog_NS.saveFreeLog", freezeLog);
	}

	public PageResult searchAbnormalCustomer(
			QueryAbnormalCustomerCondition condition) {
		return simpleDao.getPageResult("FreezeLog_NS.searchAbnormalCustomer",
				"FreezeLog_NS.searchAbnormalCustomerCount", condition);
	}

	public void updateCustomerFreezeStatus(
			CustomerFreezeStatusDTO customerFreezeStatusDTO) {
		simpleDao.updateEntity("FreezeLog_NS.updateCustomerFreezeStatus",
				customerFreezeStatusDTO);
	}

	public List<LoginRecordDetailDTO> searchLoginRecordDetail(
			QueryLoginRecordCondition condition) {
		return simpleDao.getForList("FreezeLog_NS.searchLoginRecordDetail",
				condition);
	}

	public List<FreezeLogDetialDTO> searchFreezeLogDetial(Integer cusId) {
		return simpleDao
				.getForList("FreezeLog_NS.searchFreezeLogDetail", cusId);
	}

}
