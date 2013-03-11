package com.shangde.edu.count.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.edu.count.condition.QueryContractCountCondition;
import com.shangde.edu.count.dto.ContractCountNewDTO;
import com.shangde.edu.count.dto.ContractDTO;

public class ContractCountImpl extends BaseService implements IContractCount{

	public List<ContractDTO> getCountractCount(QueryContractCountCondition queryContractCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("ContractCount_NS.getContractCountSum", queryContractCountCondition);
	}

	public List<ContractDTO> getWFCountractCount(
			QueryContractCountCondition queryContractCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("ContractCount_NS.getWfContractSum", queryContractCountCondition);
	}

	public Integer getCustomerCount(QueryContractCountCondition queryContractCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("ContractCount_NS.getCustomerCount", queryContractCountCondition);
	}

	public Integer getCustomerContractCount(QueryContractCountCondition queryContractCountCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("ContractCount_NS.getCustomerContractCount", queryContractCountCondition);
	}

	public List<ContractCountNewDTO> getCountractCountAll(QueryContractCountCondition queryContractCountCondition) {
		return simpleDao.getForList("ContractCount_NS.getCountractCountAll", queryContractCountCondition);
	}

}
