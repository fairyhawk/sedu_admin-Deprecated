package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryAbnormalCustomerCondition;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;
import com.shangde.edu.cus.domain.FreezeLog;
import com.shangde.edu.cus.dto.CustomerFreezeStatusDTO;
import com.shangde.edu.cus.dto.FreezeLogDetialDTO;
import com.shangde.edu.cus.dto.LoginRecordDetailDTO;

public interface IFreezeLog {

	public abstract void saveFreezeLog(FreezeLog freezeLog);

	public abstract PageResult searchAbnormalCustomer(
			QueryAbnormalCustomerCondition condition);

	public abstract void updateCustomerFreezeStatus(
			CustomerFreezeStatusDTO customerFreezeStatusDTO);

	public List<LoginRecordDetailDTO> searchLoginRecordDetail(
			QueryLoginRecordCondition condition);

	public List<FreezeLogDetialDTO> searchFreezeLogDetial(Integer cusId);

}