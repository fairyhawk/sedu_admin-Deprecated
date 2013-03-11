package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.condition.QueryUncouponCondition;
import com.shangde.common.service.BaseService;

/**
 * Uncoupon
 * User:guoqiang.liu
 * Date:2010-09-07
 */
@SuppressWarnings("unchecked")
public class UncouponImpl extends BaseService implements IUncoupon{
    public java.lang.Integer addUncoupon(Uncoupon uncoupon) {
return simpleDao.createEntity("Uncoupon_NS.createUncoupon",uncoupon);
    }

    public void delUncouponById(int uncpId){
        simpleDao.deleteEntity("Uncoupon_NS.deleteUncouponById",uncpId);
    }

    public void updateUncoupon(Uncoupon uncoupon) {
        simpleDao.updateEntity("Uncoupon_NS.updateUncoupon",uncoupon);
    }

    public Uncoupon getUncouponById(int uncpId) {
        return simpleDao.getEntity("Uncoupon_NS.getUncouponById",uncpId);
    }

    public List<Uncoupon> getUncouponList(QueryUncouponCondition queryUncouponCondition) {
        return simpleDao.getForList("Uncoupon_NS.getUncouponList",queryUncouponCondition);
    }

	public List<Uncoupon> getUncouponListByEmail(String email) {
		return simpleDao.getForList("Uncoupon_NS.getUncouponListByEmail",email);
	}

	public boolean checkHasApplyed(Uncoupon uncoupon) {
		Integer count = simpleDao.getEntity("Uncoupon_NS.checkHasApplyed",uncoupon);
		if(count == null  ||  count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Uncoupon getUncouponBySerialNumber(String serialNumber) {
		return simpleDao.getEntity("Uncoupon_NS.getUncouponBySerialNumber", serialNumber);
	}
}
