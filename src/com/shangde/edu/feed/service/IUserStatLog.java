/**
 * 
 */
package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.domain.UserStatLog;

/**
 * 用户使用微学习，详细记录，服务接口
 * 
 * @author Libg
 * 
 */
public interface IUserStatLog {

	/**
	 * 添加
	 * 
	 *
     * @param userStatLog
     * @return
	 */
	public Integer addUserStatLog(UserStatLog userStatLog);

	/**
	 * 根据cusId,email,pubdate查询,返回记录数
	 * 
	 *
     * @param queryUserStatLogCondition
     * @return
	 */
	public Integer getByCusIdEmailDateCount(
            QueryUserStatLogCondition queryUserStatLogCondition);
}
