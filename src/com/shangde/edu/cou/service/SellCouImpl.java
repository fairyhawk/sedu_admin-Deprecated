package com.shangde.edu.cou.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.dto.SellCouDTO;
import com.shangde.edu.finance.domain.Coupon;

/**
 *
 */
@SuppressWarnings("unchecked")
public class SellCouImpl extends BaseService implements ISellCou {
	public java.lang.Integer addSellCou(SellCou sellCou) {
		return simpleDao.createEntity("SellCou_NS.createSellCou", sellCou);
	}

	public void delSellCouById(int id) {
		simpleDao.deleteEntity("SellCou_NS.deleteSellCouById", id);
	}

	public void updateSellCou(SellCou sellCou) {
		simpleDao.updateEntity("SellCou_NS.updateSellCou", sellCou);
	}

	public SellCou getSellCouById(int id) {
		return simpleDao.getEntity("SellCou_NS.getSellCouById", id);
	}

	public List<SellCou> getSellCouList(
			QuerySellCouCondition querySellCouCondition) {
		return simpleDao.getForList("SellCou_NS.getSellCouList",
				querySellCouCondition);
	}

	public void delSellCouBySellId(int sellId) {
		simpleDao.deleteEntity("SellCou_NS.deleteSellCouBySellId", sellId);

	}

	public SellCouDTO getSellCouDTOByCourseId(
			QuerySellCouCondition querySellCouCondition) {

		return simpleDao.getEntity("SellCou_NS.getSellCouDTOById",
				querySellCouCondition);
	}

	//谢添加开始
	public Coupon GetCouponByCode(String code) {
		return simpleDao.getEntity("FinanceCoupon_NS.getCouponByCode", code);
	}

	public boolean GetSellWayByCode(String subjectId, String sellId) {
		Map map = new HashMap();
		map.put("sellId", sellId);
		map.put("subjectId", subjectId);
		Integer val = simpleDao.getEntity("FinanceCoupon_NS.getSellWayForCode", map);
		if (val == 0)
			return false;
		else
			return true;
	}
	
	public Integer getCouponState(String Id) {
	//只取状态因为如果已支付肯定状态早已改变
        Integer val = simpleDao.getEntity("FinanceCoupon_NS.getCouponState", Id);
		return val;
	}
	
	//根据couponId获取coupontypeid
	public Integer GetParentIdBycouponId(String couponId){
        Integer couponTypeId=simpleDao.getEntity("FinanceCoupon_NS.getCouponTypeIdByCouponId", couponId);
		return couponTypeId;
	}
	public void updateCouponForState(String Id,int couponTypeId){
		simpleDao.update("FinanceCoupon_NS.updateCouponForState", Id);
		simpleDao.update("FinanceCoupon_NS.updateCouponTypeForUseSumById", couponTypeId);
	}
	/**
	 * 在优惠券中加入订单
	 * @param map
	 */
     public void updateCouponForContractId(Map map){
    	 simpleDao.update("FinanceCoupon_NS.updateCouponForContractId", map); 
     }
     
     /**
      * 更改支付状态
      * @param Id
      * @param couponTypeId
      */
     public void updateCouponForPayState(String Id,int couponTypeId){
 		simpleDao.update("FinanceCoupon_NS.updateCouponForPayState", Id);
 		simpleDao.update("FinanceCoupon_NS.updateCouponTypeForPaySumById", couponTypeId);
 	}
     
     /**
      * 重置支付状态
      * @param Id
      * @param couponTypeId
      */
     public void updateCouponForStateReset(String Id,int couponTypeId){
 		simpleDao.update("FinanceCoupon_NS.updateCouponForStateReset", Id);
 	}
     
     /**
 	 * 获取优惠券id根据订单
 	 */
 	public String  getContractForCouponIdById(String contractId){
 		String couponId=simpleDao.getEntity("FinanceCoupon_NS.getcontractForCouponIdById", contractId);
 		return couponId;
 	}
 	public Coupon getCouponById(int id) {
         return simpleDao.getEntity("FinanceCoupon_NS.getCouponByCodeById",id);
     }
 	/**
 	 * 根据售卖方式id获取项目id
 	 *
      * @param sellId
      * @return
 	 */
 	public Integer getSubjectIdBySellId(int sellId){
 		return simpleDao.getEntity("FinanceCoupon_NS.getSubjectIdBySellId", sellId);
 	}
     //谢添加结束
}
