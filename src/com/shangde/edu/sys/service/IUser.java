package com.shangde.edu.sys.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.User;

/**
 * 用户管理接口
 * 
 * @author guoqiang.liu
 */
public interface IUser extends Serializable{
	/**
	 * 添加用户
	 * 
	 * @param user
	 *            要添加的用户
	 * @return id
	 */
	public java.lang.Integer addUser(User user);

	/**
	 * 根据id删除一个用户
	 * 
	 * @param userId
	 *            要删除的id
	 */
	public void delUserById(int userId);

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            要修改的用户
	 */
	public void updateUser(User user);

	/**
	 * 根据id获取单个用户对象
	 * 
	 * @param userId
	 *            要查询的id
	 * @return 年级
	 */
	public User getUserById(int userId);

	/**
	 * 根据登录名获取用户对象
	 * 
	 * @param loginname
	 *            登录名
	 * @return 用户对象
	 */
	public User getUserByLoginname(String loginname);

	/**
	 * 根据条件获取用户列表
	 * 
	 * @param queryUserCondition
	 *            查询条件
	 * @return 查询结果
	 */
	public PageResult getUserList(QueryUserCondition queryUserCondition);
	
	public List<User> getUsetByList(QueryUserCondition queryUserCondition);

	/**
	 * 根据groupId获取用户列表
	 * 
	 * @param queryUserCondition
	 *            查询条件
	 * @return 查询结果
	 */
	public PageResult getUserListByGroupId(QueryUserCondition queryUserCondition);

	public PageResult getUserListByKey(QueryUserCondition queryUserCondition);

	public User getUserDtoByUserId(int userId);

	public List<Function> getUserFunctionMap(List<Role> roleList);
	
	public List<Function> getUserFunction(List<Role> roleList);

	public void updateUserPwd(User user);
	
	public void updateContentRole(User user);

	public String getUserCountByLoginName(String loginName);

	public void addUserStu(User user, String[] classIds, String[] gradeIds,
			String[] subjectIds, int roleId);

	public void updateUserStr(User user, String[] classIds, String[] gradeIds,
			String[] subjectIds);

	/**
	 * 检查用户名是否可用
	 * @param queryUserCondition 用户名
	 * @return 是否可用
	 */
	public boolean checkLoginName(QueryUserCondition queryUserCondition);
	
	/**
	 * 查询所有用户
	 * @param queryUserCondition
	 * @return
	 */
	public PageResult getAllUserList(QueryUserCondition queryUserCondition);
	
}