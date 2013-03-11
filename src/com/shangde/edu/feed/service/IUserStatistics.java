package com.shangde.edu.feed.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryUserStatisticsCondition;

public interface IUserStatistics {
	/**
	 * 获取所有的用户统计数据
	 * @return
	 */
	public PageResult getUserStatisticslist(QueryUserStatisticsCondition queryUserStatisticsCondition);
	/**
	 * 获取观看课程详情
	 * @param queryUserStatisticsCondition
	 * @return
	 */
	public PageResult getWatchCourseList(QueryUserStatisticsCondition queryUserStatisticsCondition);
	
	/**
	 * 获取观看视频详情
	 * @param queryUserStatisticsCondition
	 * @return
	 */
	public PageResult getWatchVideoList(QueryUserStatisticsCondition queryUserStatisticsCondition);
	
	/**
	 * 获取下载视频详情
	 * @param queryUserStatisticsCondition
	 * @return
	 */
	public PageResult getDownloadVideoList(QueryUserStatisticsCondition queryUserStatisticsCondition);
	
	/**
	 * 获取收藏视频详情
	 * @param queryUserStatisticsCondition
	 * @return
	 */
	public PageResult getCollectionVideoList(QueryUserStatisticsCondition queryUserStatisticsCondition);
}
