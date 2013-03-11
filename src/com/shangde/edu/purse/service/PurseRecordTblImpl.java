package com.shangde.edu.purse.service;

import java.math.BigDecimal;
import java.util.List;
import com.shangde.edu.purse.domain.Record;
import com.shangde.edu.purse.condition.QueryRecordCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * PurseRecordTbl
 * User: guoqiang.liu
 * Date: 2012-04-16
 */
@SuppressWarnings("unchecked")
public class PurseRecordTblImpl extends BaseService implements IPurseRecordTbl{

	/**
	 * 查询交易列表
	 * @param queryRecordCondition
	 * @return
	 */
	public PageResult getTransactionRecordList(
			QueryRecordCondition queryRecordCondition) {
		return	simpleDao.getPageResult("PurseRecordTbl_NS.getPurseRecordTblList", 
											"PurseRecordTbl_NS.getPurseRecordTblCount", queryRecordCondition);
		 
	}

	/**
	 * 查询交易详细信息
	 * @param record
	 * @return
	 */
	public Record getPurseRecordInfo(Record record) {
		return simpleDao.getEntity("PurseRecordTbl_NS.getPurseRecordInfo", record);
	}

	/**
	 * 查询金额合计
	 * type（1：充值，2：退课，3：退费，4：支付订单）
	 * @param type
	 * @return
	 */
	public BigDecimal getMoneySum(Integer type,QueryRecordCondition queryRecordCondition) {
		Integer recordType=queryRecordCondition.getRecordType();
		queryRecordCondition.setRecordType(type);
		BigDecimal moneySum=simpleDao.getEntity("PurseRecordTbl_NS.getMoneySum", queryRecordCondition);
		queryRecordCondition.setRecordType(recordType);
		return moneySum;
	}
  
}
