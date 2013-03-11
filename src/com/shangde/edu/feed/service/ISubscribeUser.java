package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.edu.feed.condition.QuerySubscribeUserCondition;
import com.shangde.edu.feed.domain.SubscribeUser;

/**
 * 订阅用户接口
 * 
 * @author Libg
 * 
 */
public interface ISubscribeUser {

	/**
	 * 添加
	 * 
	 * @param subscribeUser
	 * @return
	 */
	public Integer addSubscribeUser(SubscribeUser subscribeUser);

	/**
	 * 删除对象根据id
	 * 
	 * @param id
	 * @return
	 */
	public int delSubscribeUserById(int id);

	/**
	 * 修改对象
	 * 
	 * @param subscribeUser
	 * @return
	 */
	public int updateSubscribeUser(SubscribeUser subscribeUser);

	/**
	 * 根据id查询记录
	 * 
	 * @return
	 */
	public SubscribeUser getSubscribeUserById();

	/**
	 * 查询所有数据
	 * 
	 * @param querySubscribeUserCondition
	 * @return
	 */
	public List<SubscribeUser> getSubscribeUserList(
			QuerySubscribeUserCondition querySubscribeUserCondition);

	/**
	 * 查询购买用户
	 * 
	 * @param subjectId
	 * @return
	 */
	public List<SubscribeUser> getCustomerBuyList(int subjectId);

	/**
	 * 查询注册用户
	 * 
	 * @param subjectId
	 * @return
	 */
	public List<SubscribeUser> getCustomerRegList(int subjectId);

}