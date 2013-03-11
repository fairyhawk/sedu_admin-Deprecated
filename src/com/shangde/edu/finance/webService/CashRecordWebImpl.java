package com.shangde.edu.finance.webService;

import java.util.List;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.common.service.BaseService;

/**
 * CashRecord
 * User: guoqiang.liu
 * Date: 2010-08-13
 */
@SuppressWarnings("unchecked")
public class CashRecordWebImpl extends BaseService implements ICashRecordWeb{
	
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(int contractId){
		QueryCashRecordCondition queryCashRecordCondition=new QueryCashRecordCondition();
		queryCashRecordCondition.setCtId(contractId);
		
		return simpleDao.getForList("CashRecord_NS.getCashRecordByWebFromAgentListWeb", queryCashRecordCondition);
	}

}
