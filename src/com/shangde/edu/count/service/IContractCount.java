package com.shangde.edu.count.service;

import java.util.List;
import com.shangde.edu.count.condition.QueryContractCountCondition;
import com.shangde.edu.count.dto.ContractCountNewDTO;
import com.shangde.edu.count.dto.ContractDTO;

public interface IContractCount {
	
	
	/**
	 * 当前方法得到所有订单总数  包括  已付 未付 赠送 货到付款
	 * @return 返回所有订单总和
	 */
	public List<ContractDTO> getCountractCount(QueryContractCountCondition queryContractCountCondition);
	
	
	/**
	 * 当前方法得到所有已付订单总数
	 * @param queryContractCountCondition 订单总数附加条件
	 * @return 返回已付订单集合
	 */
	public List<ContractDTO> getWFCountractCount(QueryContractCountCondition queryContractCountCondition);
	
	/**
	 * 得到当前专业注册人数
	 */
	public Integer getCustomerCount(QueryContractCountCondition queryContractCountCondition);
	
	/**
	 * 得到当前注册人数中  有多少下了单子  计算下单转化率应用
	 */

	public Integer getCustomerContractCount(QueryContractCountCondition queryContractCountCondition);
	
	/**
	 * 订单统计总
	 * wd
	 */
	public List<ContractCountNewDTO> getCountractCountAll(QueryContractCountCondition queryContractCountCondition);
}
