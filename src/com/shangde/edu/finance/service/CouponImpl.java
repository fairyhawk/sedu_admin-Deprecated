package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryCouponCondition;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Cooperation;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.CouponRecordInfo;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.sys.domain.Subject;

/**
 * Coupon服务实现类
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
@SuppressWarnings("unchecked")
public class CouponImpl extends BaseServiceManyDb implements ICoupon{
    public java.lang.Integer addCoupon(Coupon coupon) {
return simpleDao.createEntity("FinanceCoupon_NS.addCreateCoupon",coupon);
    }

    public void delCouponById(int id){
        simpleDao.deleteEntity("FinanceCoupon_NS.deleteCouponById",id);
    }

    public void updateCoupon(Coupon coupon) {
        simpleDao.updateEntity("FinanceCoupon_NS.updateCoupon",coupon);
    }

    public Coupon getCouponById(int id) {
        return simpleDao.getEntity("FinanceCoupon_NS.getCouponByCodeById",id);
    }

    public PageResult getCouponList(QueryCouponCondition queryCouponCondition) {
        return simpleDao.getPageResult("FinanceCoupon_NS.getCouponList","FinanceCoupon_NS.getCouponListCount",queryCouponCondition);
    }
    
    //优惠券编码条件查询
    public PageResult getCouponListByWhere(QueryCouponCondition queryCouponCondition) {
        return simpleDaoRead.getPageResult("FinanceCoupon_NS.getCouponCodeListByWhere","FinanceCoupon_NS.getCouponCodeListCountByWhere",queryCouponCondition);
    }

    public List<Coupon> getCouponListAll(int couponTypeId) {
        return simpleDao.getForList("FinanceCoupon_NS.getCouponCodeListAll", couponTypeId);
    }
    
	public PageResult getCouponTypeListByCondition(PageQuery queryCouponCondition) {
		return simpleDao.getPageResult("CouponType_NS.getCouponTypeList", "CouponType_NS.getCouponTypeListCount", queryCouponCondition);
	}
	
	public PageResult getCouponTypeListByWhere(PageQuery queryCouponCondition) {
		return simpleDao.getPageResult("CouponType_NS.getCouponTypeListByWhere", "CouponType_NS.getCouponTypeListByWhereCount", queryCouponCondition);
	}

	public Integer getCouponCountByCus(int userId) {
		return simpleDao.getEntity("FinanceCoupon_NS.getCouponCountByCus", userId);
	}

	public List<Coupon> getCouponListByCusCou(
			QueryCouponCondition queryCouponCondition) {
		return simpleDao.getForList("FinanceCoupon_NS.getCouponListByCusCou",queryCouponCondition);
	}

	public PageResult getCouponListByCusId(QueryCouponCondition queryCouponCondition) {
		return simpleDao.getPageResult("FinanceCoupon_NS.getCouponListByCusId", "FinanceCoupon_NS.getCouponCountByCusId", queryCouponCondition);
	}

	public Coupon getCouponByPrice(int price) {
		return simpleDao.getEntity("FinanceCoupon_NS.getCouponByPrice", price);
	}

	public List<Coupon> getCouponListForTag(
			QueryCouponCondition queryCouponCondition) {
		return simpleDao.getForList("FinanceCoupon_NS.getCouponListForTag",queryCouponCondition);
	}
	public void UpdateCouponType(Coupontype couponType){
		simpleDao.updateEntity("CouponType_NS.updateCouponType", couponType);
	}
	public Coupontype GetCouponTypeById(int id){
		return simpleDao.getEntity("CouponType_NS.getCouponTypeListById", id);
	}
	public Coupon GetCouponByCode(String code){
		return simpleDao.getEntity("FinanceCoupon_NS.getCouponByCode", code);
	}
	
	public Contract getContractById(int contractId){
		return simpleDao.getEntity("Contract_NS.getContractById", contractId);
	}
	
	public List<String> GetSellWayNameByContractId(String contractId){
		return simpleDao.getForList("FinanceCoupon_NS.getSellWayNameById", contractId);
	}
	
	public void updateGQCoupon(){
	    simpleDao.updateEntity("CouponType_NS.updateGQCouponType", null);
	    simpleDao.updateEntity("FinanceCoupon_NS.couponGQ",null);
	}
	
	public void updateZFCouponByParentId(int parentId){
		simpleDao.updateEntity("FinanceCoupon_NS.ZFCouponByParentId", parentId);
	}
	
	public List<String> GetCusMObileForSubjectId(int subId){
		List<String> list=simpleDao.getForList("FinanceCoupon_NS.getCusMobileForSubId", subId);
		return list;
	}
	
	//李明开始
	public List<Cooperation> selectCollaborateMen(){
		return simpleDao.getForList("Cooperation_NS.getselectCollaborateMen", null);
	}
	/**
	 * 添加优惠券
	 * @return 
	 */
	public Integer addCreateCoupon(Coupontype coupontype){
		return simpleDao.createEntity("CouponType_NS.addCreateCoupon",coupontype);
	}
	/**
	 * 根据ID查询所对应的优惠券
	 */
	public Coupontype getSingleCoupon(int couponId){
		return simpleDao.getEntity("CouponType_NS.getSingleCoupon", couponId);
	}
	/**
	 * 添加合作商
     * @param cooperation
     */

	public Integer addConperation(Cooperation cooperation){
		return simpleDao.createEntity("CouponType_NS.addCreateCooperation", cooperation);
	}
	/**
	 * 添加编码
     * @param coupon
     */
	public Integer addCouPon(Coupon coupon){
		return simpleDao.createEntity("FinanceCoupon_NS.addCreateCoupon", coupon);
	}
	/**
	 * 根据type查询所对应的coding
	 * @param codingId
	 */
	public List<Coupon> getcouponSinge(int codingId){
		return simpleDao.getForList("FinanceCoupon_NS.getCouPonSinge", codingId);
	}
	/**
	 *  修改updateCouponType
	 * return list
	 */
	public void updateCouponType(Coupontype coupontype){
		simpleDao.update("CouponType_NS.updateCouponType", coupontype);
	}
	 /**
     * 查询单个的合伙人
	 * @param integer 
     * @param 
     * @return entity；
     */
	public Cooperation selectSingleMen(Integer integer){
		return simpleDao.getEntity("Cooperation_NS.selectSingleMen", integer);
	}

	public void getSingleCouponn(int conponTypeId) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 查询单个的项目
	 * @param subjectId
	 */
	public Subject getsingleSubjectName(int subjectId){
		return simpleDao.getEntity("CouponType_NS.getsingleSubjectName", subjectId);
	}

	/**
	 * 输入合作商名字进行校验
	 */
	public Cooperation checkConponName(String company){
		return simpleDao.getEntity("Cooperation_NS.checkConponName", company);
	}
	/**
	 * 输入合作商名字进行校验
	 */
	public Cooperation checkConponCoding(String companyCooe){
		return simpleDao.getEntity("Cooperation_NS.checkConponCoding", companyCooe);
	}
	/**
	 * 在生成编码的时候，给数据库中的编码生策划给你时间添加时间
	 * 
	 */
	public void updateCouponTypeGenerateTime(Coupontype coupontype){
		simpleDao.update("CouponType_NS.updateCouponTypeGenerateTime", coupontype);
	}
	/**
	 * 检验是否已近生成了又会变吗；
	 * 
	 */

	public List<Cooperation> checkConponCoding(int codingId){
		return simpleDao.getForList("FinanceCoupon_NS.checkConponID", codingId);
	}
	//李明结束

	public Coupon getCouponByContractId(int contractId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("FinanceCoupon_NS.getCouponByContractId", contractId);
	}

	public void addCouponRecordInfo(CouponRecordInfo couponRecordInfo) {
		 simpleDao.createEntity("FinanceCoupon_NS.addCouponRecordInfo", couponRecordInfo);
	}
	/**
	 * 查询所有的发送短信记录
	 * @param queryCouponCodeCondition
	 * @return PageResult
	 * Liming
	 */
	public PageResult getCouponRecordInfo(
			QueryCouponCondition queryCouponCodeCondition) {
		return simpleDao.getPageResult("FinanceCoupon_NS.getCouponRecordInfo", "FinanceCoupon_NS.getCouponRecordInfoCount", queryCouponCodeCondition);
	}
	
	public Contract getFinanceByCouponId(int couponId){
		return simpleDao.getEntity("Contract_NS.getFinanceByCouponId", couponId);
	}
	
}
