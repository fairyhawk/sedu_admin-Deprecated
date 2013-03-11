package com.shangde.edu.purse.service;

import java.math.BigDecimal;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.purse.domain.Money;
import com.shangde.edu.purse.condition.QueryMoneyCondition;

/**
 * PurseMoneyTbl����ӿ�
 * User: guoqiang.liu
 * Date: 2012-04-16
 */
public interface IPurseMoneyTbl {
	/**
	 * 查询账户列表
	 * @param queryMoneyCondition
	 * @return
	 */
	PageResult getPurseMoneyList(QueryMoneyCondition queryMoneyCondition);
	/**
	 * type（2：包含正常和冻结的，1：冻结）
	 * @param type
	 * @return
	 */
	BigDecimal getMoneySum(Integer type,QueryMoneyCondition queryMoneyCondition);
 
}