package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.edu.cou.domain.RebatePrice;



public interface RebatePriceService {

	/**
	 * 插入促销价格信息
	 * @param rebatePrice
	 */
	public void addRebatePrice(RebatePrice rebatePrice);
	/**
	 * 通过商品ID查询与其关联的促销价格
	 * @param voId
	 * @return
	 */
	public List<RebatePrice> getRebatePriceList(int sellId);
	/**
	 *通过商品ID删除与商品所关联的促销价格
	 * @param voId
	 */
	public void deleteBySellId(int sellId);
}
