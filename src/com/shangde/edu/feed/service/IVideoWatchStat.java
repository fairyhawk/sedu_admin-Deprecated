package com.shangde.edu.feed.service;

import com.shangde.edu.feed.domain.VideoWatchStat;

/**
 * 视频观看记录统计，服务
 * 
 * @author Libg
 * 
 */
public interface IVideoWatchStat {

	/**
	 * 添加
	 * 
	 *
     * @param videoWatchStat
     * @return
	 */
	public Integer addVideoWatchStat(VideoWatchStat videoWatchStat);

}
