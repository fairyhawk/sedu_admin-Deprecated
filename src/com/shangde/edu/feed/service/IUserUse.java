package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.UserUse;

public interface IUserUse {

	/**
	 * 添加
	 * 
	 *
     * @param userUse
     * @return
	 */
	public Integer addUserUse(UserUse userUse);

	/**
	 * 条件查询
	 * 
	 * 需要三个参数 startTime,endTime,type[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 * 
	 *
     * @param queryStatCommonCondition;
     * @return
	 */
	public Integer getUserUseCount(QueryStatCommonCondition queryStatCommonCondition);

	public Integer getUserUseGroupCount(
            QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 根据时间段,type,fromId查询
	 * 
	 *
     * @param queryStatCommonCondition
     * @return
	 */
	public Integer getUserUseAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 日数据量查询
	 * 
	 * 需要 type参数[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 * 
	 *
     * @param type
     *
     * @return
	 */
	public Integer getUserUseDATECount(int type);

	/**
	 * 日数据查询
	 * 
	 * @return
	 */
	public Integer getUserUseDATEGroupCount();

	/**
	 * 日数据查询，条件type=2/4,fromId=(num),根据渠道查询
	 * 
	 *
     * @param queryStatCommonCondition
     * @return
	 */
	public Integer getUserUseDATEAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 周数据量查询
	 * 
	 * 需要 type参数[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 * 
	 *
     * @param type
     * @return
	 */
	public Integer getUserUseWEEKCount(int type);

	/**
	 * 周数据查询
	 * 
	 * @return
	 */
	public Integer getUserUseWEEKGroupCount();

	/**
	 * 周数据查询，条件type=2/4,fromId=(num),根据渠道查询
	 * 
	 *
     * @param queryStatCommonCondition
     * @return
	 */
	public Integer getUserUseWEEKAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 月数据量查询
	 * 
	 * 需要 type参数[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 * 
	 *
     * @param type
     * @return
	 */
	public Integer getUserUseMONTHCount(int type);

	/**
	 * 月数据查询
	 * 
	 * @return
	 */
	public Integer getUserUseMONTHGroupCount();

	/**
	 * 月数据查询，条件type=2/4,fromId=(num),根据渠道查询
	 * 
	 *
     * @param queryStatCommonCondition
     * @return
	 */
	public Integer getUserUseMONTHAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition);

}
