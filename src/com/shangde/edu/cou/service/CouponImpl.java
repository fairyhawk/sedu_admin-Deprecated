package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * Coupon服务实现类
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
@SuppressWarnings("unchecked")
public class CouponImpl extends BaseService implements ICoupon{
    public java.lang.Integer addCoupon(Coupon coupon) {
return simpleDao.createEntity("Coupon_NS.createCoupon",coupon);
    }

    public void delCouponById(int id){
        simpleDao.deleteEntity("Coupon_NS.deleteCouponById",id);
    }

    public void updateCoupon(Coupon coupon) {
        simpleDao.updateEntity("Coupon_NS.updateCoupon",coupon);
    }

    public Coupon getCouponById(int id) {
        return simpleDao.getEntity("Coupon_NS.getCouponById",id);
    }

    public List<Coupon> getCouponList(QueryCouponCondition queryCouponCondition) {
        return simpleDao.getForList("Coupon_NS.getCouponList",queryCouponCondition);
    }

	public PageResult getCouponListByCondition(PageQuery queryCouponCondition) {
		return simpleDao.getPageResult("Coupon_NS.getCouponListByCondition", "Coupon_NS.getCouponCountByCondition", queryCouponCondition);
	}

	public Integer getCouponCountByCus(int userId) {
		return simpleDao.getEntity("Coupon_NS.getCouponCountByCus", userId);
	}

	public List<Coupon> getCouponListByCusCou(
			QueryCouponCondition queryCouponCondition) {
		return simpleDao.getForList("Coupon_NS.getCouponListByCusCou",queryCouponCondition);
	}

	public PageResult getCouponListByCusId(QueryCouponCondition queryCouponCondition) {
		return simpleDao.getPageResult("Coupon_NS.getCouponListByCusId", "Coupon_NS.getCouponCountByCusId", queryCouponCondition);
	}

	public Coupon getCouponByPrice(int price) {
		return simpleDao.getEntity("Coupon_NS.getCouponByPrice", price);
	}

	public List<Coupon> getCouponListForTag(
			QueryCouponCondition queryCouponCondition) {
		return simpleDao.getForList("Coupon_NS.getCouponListForTag",queryCouponCondition);
	}
}
