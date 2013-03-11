package com.shangde.edu.purse.service;


import java.math.BigDecimal;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.purse.condition.QueryRecordCondition;
import com.shangde.edu.purse.domain.Record;

/**
 * PurseRecordTbl
 * User: guoqiang.liu
 * Date: 2012-04-16
 */
public interface IPurseRecordTbl {
	/**
	 * 查询交易列表
	 * @param queryRecordCondition
	 * @return
	 */
	PageResult getTransactionRecordList(QueryRecordCondition queryRecordCondition);
	/**
	 * 查询交易详细信息
	 * @param record
	 * @return
	 */
	Record getPurseRecordInfo(Record record);
	/**
	 * 查询金额合计
	 * type（1：充值，2：退课，3：退费，4：支付订单）
	 * @param type
	 * @return
	 */
	BigDecimal getMoneySum(Integer type,QueryRecordCondition queryRecordCondition);
}