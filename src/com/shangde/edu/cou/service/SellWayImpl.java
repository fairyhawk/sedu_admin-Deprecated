package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * SellWay优惠券接口
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
@SuppressWarnings("unchecked")
public class SellWayImpl extends BaseService implements ISellWay{
    public java.lang.Integer addSellWay(SellWay sellWay) {
return simpleDao.createEntity("SellWay_NS.createSellWay",sellWay);
    }

    public void delSellWayById(int sellId){
        simpleDao.deleteEntity("SellWay_NS.deleteSellWayById",sellId);
    }

    public void updateSellWay(SellWay sellWay) {
        simpleDao.updateEntity("SellWay_NS.updateSellWay",sellWay);
    }

    public SellWay getSellWayById(int sellId) {
        return simpleDao.getEntity("SellWay_NS.getSellWayById",sellId);
    }

    public List<SellWay> getSellWayList(QuerySellWayCondition querySellWayCondition) {
        return simpleDao.getForList("SellWay_NS.getSellWayList",querySellWayCondition);
    }
    //分页查询售卖方式
    public PageResult getSellWayInfoList(QuerySellWayCondition querySellWayCondition) {
    	return simpleDao.getPageResult("SellWay_NS.getSellWayInfoList", "SellWay_NS.getSellWayInfoListCount", querySellWayCondition);
    }

    //谢添加开始
    /**
	 * 获取优惠券id根据订单
	 */
	public String  getContractForCouponIdById(String contractId){
		String couponId=simpleDao.getEntity("FinanceCoupon_NS.getcontractForCouponIdById", contractId);
		return couponId;
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
    
  //根据couponId获取coupontypeid
  	public Integer GetParentIdBycouponId(String couponId){
          Integer couponTypeId=simpleDao.getEntity("FinanceCoupon_NS.getCouponTypeIdByCouponId", couponId);
  		return couponTypeId;
  	}
  	/**
  	 * 根据商品id获取课程
  	 * @param sellId
  	 * @return
  	 */
  	public List<Course> getCourseBySellId(int sellId){
  		 List<Course> list=simpleDao.getForList("Course_NS.getCourseBySellId", sellId);
  		 return list;
  	}
  	/**
  	 * 更改商品上架状态
  	 * @param sellId
  	 */
  	public void updatesShopState(int sellId){
  		simpleDao.update("SellWay_NS.updatesShopState", sellId);
  	}
  	
  	//谢添加结束
  	public List<Teacher> getTeacherByName(int subjectId){
  		return simpleDao.getForList("Teacher_NS.getTeacherByName", subjectId);
  	}

	public PageResult showSellWayListByWhere(
			QuerySellWayCondition querySellWayCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("SellWay_NS.showSellWayListByWhere", "SellWay_NS.showSellWayListByWhereCount", querySellWayCondition);
	}

	public List<SellWay> getSellWayBySubjectId(int subjectId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWay_NS.getSellWayBySubjectId", subjectId);
	}

	public PageResult showSellWayListRecord(QuerySellWayCondition querySellWayCondition) {
		return simpleDao.getPageResult("SellWay_NS.showSellWayListRecord", "SellWay_NS.getshowSellWayListRecordCount", querySellWayCondition);
	}
	/**
	 * 获取课程卡所属的商品信息
	 * @param cardCourseId
	 * @return
	 * @throws Exception
	 */
	public List<SellWay> getCardCourseSell(Integer cardMainId)
			throws Exception {
		return simpleDao.getForList("SellWay_NS.getCardCourseSell", cardMainId);
	}
}
