/**
 * 
 */
package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.VideoWatchStat;
import com.shangde.edu.feed.service.IVideoWatchStat;

/**
 * 视频访问记录统计实现
 * 
 * @author Libg
 * 
 */
public class VideoWatchStatImpl extends BaseService implements IVideoWatchStat {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IVideoWatchStat#addVideoWatchStat(com.shangde.edu.feed.domain.VideoWatchStat)
	 */
	public Integer addVideoWatchStat(VideoWatchStat videoWatchStat) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("", videoWatchStat);
	}

}
