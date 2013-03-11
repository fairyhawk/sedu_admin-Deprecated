package com.shangde.edu.finance.webService;

import java.util.List;

import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.Contract;


public interface IContractWeb {

	/**
	 * 为董元提供的接口
	 */
	public List<Contract> getContractByFromAgentList();
}
