package com.shangde.edu.feed.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryVideoLogCondition;
import com.shangde.edu.feed.domain.VideoLog;

/**
 * VideoLog管理接口
 * 
 * User: guoqiang.liu
 * 
 * Date: 2011-09-07
 */
public interface IVideoLog {
	/**
	 * 添加VideoLog
	 * 
	 *
     * @param videoLog
     *            要添加的VideoLog
     * @return id
	 */
	public Integer addVideoLog(VideoLog videoLog);

	/**
	 * 根据id删除一个VideoLog
	 *
     * @param id
     */
	public Integer delVideoLogById(int id);

	/**
	 * 修改VideoLog
	 *
     * @param videoLog
     *            要修改的VideoLog
     */
	public Integer updateVideoLog(VideoLog videoLog);

	/**
	 * 根据id获取单个VideoLog对象
	 * 
	 * @return 年级
	 */
	public VideoLog getVideoLogById(int id);

	/**
	 * 根据videoId获取单个VideoLog对象
	 * 
	 * @return 年级
	 */
	public VideoLog getVideoLogByVideoId(int videoId);

	/**
	 * 根据条件获取VideoLog列表
	 * 
	 * @param queryVideoLogCondition
	 * @return
	 */
	public PageResult getVideoLogList(
			QueryVideoLogCondition queryVideoLogCondition);

	/**
	 * 修改激活次数
	 * 
	 *
     * @param videoId
     * @return
	 */
	public Integer updateActiveNum(int videoId);

	/**
	 * 修改点击次数(观看次数)
	 * 
	 *
     * @param videoId
     * @return
	 */
	public Integer updateClickNum(int videoId);

	/**
	 * 根据专业修改点击购买次数
	 * 
	 *
     * @param subjectId
     * @return
	 */
	public Integer updateClickBuyNum(int subjectId);

	/**
	 * 修改点击购买成功次数 BuyNum购买成功次数
	 * 
	 *
     * @param videoId
     * @return
	 */
	public Integer updateBuyNum(int videoId);

}