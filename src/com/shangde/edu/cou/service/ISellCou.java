package com.shangde.edu.cou.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.dto.SellCouDTO;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.finance.domain.Coupon;

/**
 * sellcout 接口
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
public interface ISellCou {
	/**
	 * sellcout 
	 * @param sellCou 
	 * @return id
	 */
    public java.lang.Integer addSellCou(SellCou sellCou);

    /**
	 * 删除sellcout 
	 * @param id 
	 */
    public void delSellCouById(int id);

    /**
     * 通过售卖方式删除关联
     * @param sellId
     */
    public void delSellCouBySellId(int sellId);
    /**
	 * 更新优惠券
	 * @param sellCou
	 */
    public void updateSellCou(SellCou sellCou);

    /**
	 *根据id查询优惠券
	 * @param id
	 */
    public SellCou getSellCouById(int id);

    /**
	 *根据查询条件查询优惠券
	 * @param querySellCouCondition
	 */
    public List<SellCou> getSellCouList(QuerySellCouCondition querySellCouCondition);
    /**
     * 通过courseID查找售卖方式下的课程详细信息
     * @param courseId
     * @return
     */
    public SellCouDTO getSellCouDTOByCourseId(QuerySellCouCondition querySellCouCondition);
    
    //谢添加
    /**
     * 获取优惠券
     * @param code
     * @return
     */
    public Coupon GetCouponByCode(String code);
    
    /**
     * 根据项目和售卖方式 判断是否存在
     * @param subjectId
     * @param sellId
     * @return
     */
    public boolean GetSellWayByCode(String subjectId, String sellId) ;
    
    /**
     * 根据id判断优惠券状态
     *
     * @param Id
     * @return
     */
    public Integer getCouponState(String Id) ;
    
    /**
     * 更新优惠券状态
     * @param Id
     */
    public void updateCouponForState(String Id,int couponTypeId);
    
  //根据couponId获取coupontypeid
  	public Integer GetParentIdBycouponId(String couponId);
  	
  	 /**
     * 更改支付状态
     * @param Id
     * @param couponTypeId
     */
  	 public void updateCouponForPayState(String Id,int couponTypeId);
  	/**
	 * 在优惠券中加入订单
	 * @param map
	 */
     public void updateCouponForContractId(Map map);
     
     /**
      * 重置支付状态
      * @param Id
      * @param couponTypeId
      */
     public void updateCouponForStateReset(String Id,int couponTypeId);
     
     /**
 	 * 获取优惠券id根据订单
 	 */
 	public String  getContractForCouponIdById(String contractId);
 	/**
 	 * 获取优惠券
 	 * @param id
 	 * @return
 	 */
 	public Coupon getCouponById(int id);
 	
	/**
 	 * 根据售卖方式id获取项目id
 	 *
     * @param sellId
     * @return
 	 */
 	public Integer getSubjectIdBySellId(int sellId);
 	//谢添加结束
    
}