package com.shangde.edu.finance.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryWaybillCondition;
import com.shangde.edu.finance.domain.Waybill;

public class WaybillServiceImpl extends BaseService implements WaybillService {

	/**
	 * 添加或者更新运单
	 * @param waybill
	 */
	public void addOrUpdateWaybill(Waybill waybill) {
		if(waybill.getWaybillId()==null){
			this.simpleDao.createEntity("Waybill_NS.createWaybill", waybill);
		}else{
			this.simpleDao.updateEntity("Waybill_NS.updateWaybill", waybill);
		}
		
	}

	/**
	 * 查询运单列表
	 * @return
	 */
	public PageResult getWaybillList(QueryWaybillCondition queryWaybillCondition) {
		return this.simpleDao.getPageResult("Waybill_NS.getWaybillList", "Waybill_NS.getWaybillListCount", queryWaybillCondition);
	}

	/**
	 * 查询运单信息
	 * @param waybill
	 * @return
	 */
	public Waybill getWaybill(Waybill waybill) {
		return this.simpleDao.getEntity("Waybill_NS.getWaybillById", waybill);
	}

	/**
	 * 校验运单号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isWaybillCodeRepeat(Waybill waybill) {
		return simpleDao.getEntity("Waybill_NS.isWaybillCodeRepeat", waybill);
	}
	

}
