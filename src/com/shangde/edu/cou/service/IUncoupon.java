package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.condition.QueryUncouponCondition;

/**
 * Uncoupon管理接口
 * User: guoqiang.liu
 * Date: 2010-09-07
 */
public interface IUncoupon {
    /**
     * 添加Uncoupon
     * @param uncoupon 要添加的Uncoupon
     * @return id
     */
    public java.lang.Integer addUncoupon(Uncoupon uncoupon);

    /**
     * 根据id删除一个Uncoupon
     * @param uncpId 要删除的id
     */
    public void delUncouponById(int uncpId);

    /**
     * 修改Uncoupon
     * @param uncoupon 要修改的Uncoupon
     */
    public void updateUncoupon(Uncoupon uncoupon);

    /**
     * 根据id获取单个Uncoupon对象
     * @param uncpId 要查询的id
     * @return 年级
     */
    public Uncoupon getUncouponById(int uncpId);

    /**
     * 根据条件获取Uncoupon列表
     * @param queryUncouponCondition 查询条件
     * @return 查询结果
     */
    public List<Uncoupon> getUncouponList(QueryUncouponCondition queryUncouponCondition);

    /**
     * 根据email获取优惠券
     * @param email
     * @return
     */
	public List<Uncoupon> getUncouponListByEmail(String email);

	/**
	 * 检查邮箱是否已经申请过本次活动的优惠券
	 * @param uncoupon
	 * @return
	 */
	public boolean checkHasApplyed(Uncoupon uncoupon);

	/**
	 * 根据学列号查询优惠券
	 * @param serialNumber
	 */
	public Uncoupon getUncouponBySerialNumber(String serialNumber);
}