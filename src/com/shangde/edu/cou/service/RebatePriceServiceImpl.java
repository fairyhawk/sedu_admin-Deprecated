package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.RebatePrice;

public class RebatePriceServiceImpl extends BaseService implements
		RebatePriceService {

	/**
	 * 插入促销价格信息
	 * @param rebatePrice
	 */
	public void addRebatePrice(RebatePrice rebatePrice) {
		this.simpleDao.createEntity("RebatePrice_NS.createRebatePrice", rebatePrice);
		
	}

	/**
	 * 通过商品ID查询与其关联的促销价格
	 * @param voId
	 * @return
	 */
	public List<RebatePrice> getRebatePriceList(int sellId) {
		return this.simpleDao.getForList("RebatePrice_NS.getRebatePriceBySellId", sellId);
	}

	/**
	 *通过商品ID删除与商品所关联的促销价格
	 * @param voId
	 */
	public void deleteBySellId(int sellId) {
		this.simpleDao.delete("RebatePrice_NS.deleteRebatePriceBySellId", sellId);
		
	}

}
