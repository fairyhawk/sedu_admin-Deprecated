package com.shangde.edu.purse.service;

import java.math.BigDecimal;
import java.util.List;
import com.shangde.edu.purse.domain.Money;
import com.shangde.edu.purse.condition.QueryMoneyCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


public class PurseMoneyTblImpl extends BaseService implements IPurseMoneyTbl{

	/**
	 * 查询账户列表
	 * @param queryMoneyCondition
	 * @return
	 */
	public PageResult getPurseMoneyList(QueryMoneyCondition queryMoneyCondition) {
		return simpleDao.getPageResult("PurseMoneyTbl_NS.getPurseMoneyTblList", 
				"PurseMoneyTbl_NS.getPurseMoneyTblCount", queryMoneyCondition);
	}

	/**
	 * type（2：包含正常和冻结的，1：冻结）
	 * @param type
	 * @return
	 */
	public BigDecimal getMoneySum(Integer type,QueryMoneyCondition queryMoneyCondition) {
		Integer statusTemp=queryMoneyCondition.getStatus();
		if(type==1){
			queryMoneyCondition.setStatus(type);
		}
		BigDecimal moneySum=simpleDao.getEntity("PurseMoneyTbl_NS.getMoneySum", queryMoneyCondition);
		queryMoneyCondition.setStatus(statusTemp);
		return moneySum;
	}
   
}
