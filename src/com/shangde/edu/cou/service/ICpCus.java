package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCpCusCondition;
import com.shangde.edu.cou.domain.CpCus;

/**
 * CpCus优惠券服务类
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
public interface ICpCus {
    /**
     * 添加优惠券
     * @param cpCus 要添加的优惠券
     * @return id
     */
    public java.lang.Integer addCpCus(CpCus cpCus);

    /**
     * 根据id删除优惠券
     */
    public void delCpCusById();

    /**
     * 更新优惠券
     * @param cpCus 要更新的优惠券
     */
    public void updateCpCus(CpCus cpCus);

    /**
     * 根据id获取优惠券
     * @return id
     */
    public CpCus getCpCusById();

    /**
     * 根据查询条件查询优惠券集合
     * @param queryCpCusCondition 查询条件
     * @return 结果集合
     */
    public List<CpCus> getCpCusList(QueryCpCusCondition queryCpCusCondition);

    /**
     * 根据学员id拿到一个优惠券
     * @param userId 学员id
     * @return
     */
	public CpCus getCpCusByCusId(int userId);
	
	/**
	 * 根据序列号删除优惠券关系
	 * @param queryCouponCondition 学员id，课程ids
	 * @return
	 */
	public void deleteCouponBySN(
			String SerialNumber);

	/**
	 * 根据序列号获取优惠券关系
	 * @param serialNumber
	 * @return
	 */
	public CpCus getCpCusBySN(String serialNumber);

	/**
	 * 根据用户id分页查询优惠券
	 * @param queryCpCusCondition
	 * @return
	 */
	public PageResult showUserCpCusList(PageQuery queryCpCusCondition);
    /**
     * 根据id删除优惠券
     */
    public void delCpCusByCusId(int cusId);
    
    /**
     * Yangning 2011/12/06 后台批量删除用户时级联删除CpCus
     * @param cusIds
     * @return
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer delBathCpCusByCusIds(String cusIds);
}