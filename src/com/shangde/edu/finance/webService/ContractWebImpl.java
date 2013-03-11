package com.shangde.edu.finance.webService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class ContractWebImpl extends BaseService implements IContractWeb {
	
	public List<Contract> getContractByFromAgentList(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		QueryContractCondition queryContractCondition = new QueryContractCondition();
		queryContractCondition.setPayStartTime(sdf.format(cal.getTime()));
		queryContractCondition.setPayEndTime(sdf.format(new Date()));
	  return  simpleDao.getForList("Contract_NS.getContractByFromAgent",queryContractCondition);
	}
}
