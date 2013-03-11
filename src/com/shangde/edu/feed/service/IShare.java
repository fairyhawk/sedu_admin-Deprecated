package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.Share;

public interface IShare {

	/**
	 * 添加
	 * 
	 *
     * @param share
     * @return
	 */
	public Integer addShare(Share share);

	/**
	 * 获取分享总次数，根据时间段查询
	 * 
	 *
     * @param queryAppStatCondition
     * @return
	 */
	public Integer getShareCount(QueryAppStatCondition queryAppStatCondition);

	/**
	 * 获取分享总次数用户分组，根据时间段查询,计算的是人数
	 * 
	 *
     * @param queryAppStatCondition
     * @return
	 */
	public Integer getShareUserGroupCount(
            QueryAppStatCondition queryAppStatCondition);

}
