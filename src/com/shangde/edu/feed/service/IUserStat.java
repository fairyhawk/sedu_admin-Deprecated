package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryUserStatCondition;
import com.shangde.edu.feed.domain.UserStat;
import com.shangde.edu.feed.dto.UserStatDTO;

public interface IUserStat {

	/**
	 * 添加
	 * 
	 *
     * @param userStat
     * @return
	 */
	public Integer addUserStat(UserStat userStat);

	/**
	 * 修改
	 * 
	 *
     * @param userStat
     * @return
	 */
	public Integer updateUserStat(UserStat userStat);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public UserStat getUserStatById(Integer id);

	/**
	 * 根据cusId,email条件查询
	 * 
	 * @param queryUserStatCondition
	 * @return
	 */
	public UserStat getUserStatCountByCusIdEmail(
			QueryUserStatCondition queryUserStatCondition);
	
	/**
	 * 统计 分页
	 * creator longjl
	 */
    public PageResult getUserStatList(QueryUserStatCondition queryUserStatCondition);
    
    /**
     * 统计条件分页
     * creator longjl
     */
    public PageResult getUserStatListBYSreach(QueryUserStatCondition queryUserStatCondition);
    
    
    /**
     * 导出用户统计所有信息
     */
    public List<UserStatDTO> exportUserStatistics();
    
    /**
     * 根据条件 导出用户统计信息
     */
    public List<UserStatDTO> exportSearchUserStatList(QueryUserStatCondition queryUserStatCondition);
}
