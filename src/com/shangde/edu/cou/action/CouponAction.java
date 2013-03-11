package com.shangde.edu.cou.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.ICoursesort;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CouponAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(CouponAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 活动服务对象
	 */
	private ICoupon couponService;
	
	/**
	 * 课程分类服务对象
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 活动实体
	 */
	private Coupon coupon = new Coupon();
	
	/**
	 * 活动查询条件
	 */
	private QueryCouponCondition queryCouponCondition;
	
	/**
	 * 活动list
	 */
	private List<Coupon> couponList = new ArrayList<Coupon>();
	
	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 添加优惠券
	 * @return String
	 * @thorows Exception
	 */
	public String addCoupon(){
		try {
			couponService.addCoupon(coupon);
		} catch(Exception e) {
			logger.error("CouponAction.addCoupon",e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 修改优惠券
	 * @return String
	 * @thorows Exception
	 */
	public String updateCoupon(){
		try {
			couponService.updateCoupon(coupon);
		} catch(Exception e) {
			logger.error("CouponAction.updateCoupon",e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 删除优惠券
	 * @return String
	 * @thorows Exception
	 */
	public String deleteCoupon(){
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					couponService.delCouponById(ids[i]);
				}
			}
		} catch(Exception e) {
			logger.error("CouponAction.deleteCoupon",e);
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showCouponList() {
		try {
			String cInfo = getQueryCouponCondition().getCInfo();
			if(cInfo != null) {
				getQueryCouponCondition().setCInfo(cInfo.trim());
			}
			setPage(couponService.getCouponListByCondition(getQueryCouponCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			logger.error("CouponAction.showCouponList",e);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 初始化添加页面
	 * @return String
	 * @thorows Exception
	 */
	public String initAddCoupon() {
		courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateCoupon() {
		try {
			coupon = couponService.getCouponById(coupon.getId());
		} catch(Exception e) {
			logger.error("CouponAction.initUpdateCoupon",e);
			return ERROR;
		}
		return "updatePage";
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public QueryCouponCondition getQueryCouponCondition() {
		if(queryCouponCondition == null) {
			queryCouponCondition = new QueryCouponCondition();
		}
		return queryCouponCondition;
	}

	public void setQueryCouponCondition(QueryCouponCondition queryCouponCondition) {
		this.queryCouponCondition = queryCouponCondition;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}
}
