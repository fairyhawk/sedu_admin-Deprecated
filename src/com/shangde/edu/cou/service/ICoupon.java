package com.shangde.edu.cou.service;

import java.util.Collection;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.edu.cou.domain.Coupon;

/**
 * Coupon管理接口
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
public interface ICoupon {
    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     * @return id
     */
    public java.lang.Integer addCoupon(Coupon coupon);

    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    public void delCouponById(int id);

    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon);

    /**
     * 根据id获取单个Coupon对象
     * @param id 要查询的id
     * @return 年级
     */
    public Coupon getCouponById(int id);

    /**
     * 根据条件获取Coupon列表
     * @param queryCouponCondition 查询条件
     * @return 查询结果
     */
    public List<Coupon> getCouponList(QueryCouponCondition queryCouponCondition);

    /**
     * 分页查询优惠券
     * @param queryCouponCondition
     * @return 查询结果
     */
	public PageResult getCouponListByCondition(
			PageQuery queryCouponCondition);

	/**
	 * 根据学员id查询优惠券数量
	 *
     * @param userId 学员id
     * @return
	 */
	public Integer getCouponCountByCus(int userId);

	/**
	 * 根据学员id和课程ids查询优惠券list
	 * @param queryCouponCondition 学员id，课程ids
	 * @return
	 */
	public List<Coupon> getCouponListByCusCou(
			QueryCouponCondition queryCouponCondition);

	/**
	 * 根据用户id查询优惠券列表
	 * @param loginUserId
	 * @return
	 */
	public PageResult getCouponListByCusId(QueryCouponCondition queryCouponCondition);

    /**
     * 根据优惠金额获取优惠券
     * @param price 金额
     * @return
     */
    public Coupon getCouponByPrice(int price);

    /**
     * 为静态化获取优惠券列表
     * @param queryCouponCondition
     * @return
     */
	public List<Coupon> getCouponListForTag(
			QueryCouponCondition queryCouponCondition);
}