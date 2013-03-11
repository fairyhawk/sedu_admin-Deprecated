package com.shangde.edu.cou.web;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;

public class SellWayWebAction extends CommonAction {
	/**
	 * 
	 * @return
	 */
	private QuerySellWayCondition querySellWayCondition;
	private int sellId;
	private ISellWay sellWayService;
	private SellWay sellWayWeb;
	public String BuyNow(){
		try {
			this.getQuerySellWayCondition().setSaleId(sellId);
			sellWayWeb=sellWayService.getSellWayById(sellId);
			this.setResult(new Result(true,null,null,sellWayWeb));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	public QuerySellWayCondition getQuerySellWayCondition() {
		return querySellWayCondition;
	}
	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public SellWay getSellWayWeb() {
		return sellWayWeb;
	}
	public void setSellWayWeb(SellWay sellWayWeb) {
		this.sellWayWeb = sellWayWeb;
	}
}
