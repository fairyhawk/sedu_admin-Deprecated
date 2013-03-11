package com.shangde.edu.finance.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryWaybillCondition;
import com.shangde.edu.finance.domain.Waybill;


public interface WaybillService {
	/**
	 * 添加或者更新运单
	 * @param waybill
	 */
	public void addOrUpdateWaybill(Waybill waybill);
	/**
	 * 查询运单列表
	 * @return
	 */
	public PageResult getWaybillList(QueryWaybillCondition queryWaybillCondition);
	/**
	 * 查询运单信息
	 * @param waybill
	 * @return
	 */
	public Waybill getWaybill(Waybill waybill);
	/**
	 * 校验运单号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isWaybillCodeRepeat(Waybill waybill);
}
