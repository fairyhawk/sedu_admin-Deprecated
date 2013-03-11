package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.VideoStat;
import com.shangde.edu.stu.condition.QueryVideoStatCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class VideoStatImpl extends BaseService implements IVideoStat {
	
	public Integer addVideoStat(VideoStat videoStat) {
		return simpleDao.createEntity("VideoStat_NS.createVideoStat", videoStat);
	}

	public void delVideoStatById() {
	}

	public void updateVideoStat(VideoStat videoStat) {
		simpleDao.updateEntity("VideoStat_NS.updateVideoStat", videoStat);
	}

	public VideoStat getVideoStatById() {
		return null;
	}

	public List<VideoStat> getVideoStatList(
			QueryVideoStatCondition queryVideoStatCondition) {
		return simpleDao.getForList("VideoStat_NS.getVideoStatList",
				queryVideoStatCondition);
	}
}
