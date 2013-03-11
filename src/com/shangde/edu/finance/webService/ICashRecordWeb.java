package com.shangde.edu.finance.webService;

import java.util.List;

import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;


public interface ICashRecordWeb {
	/**
	 * 为董元提供的方法
	 */
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(int contractId);
	
}