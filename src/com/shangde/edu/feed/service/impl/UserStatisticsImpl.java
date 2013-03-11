package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryUserStatisticsCondition;
import com.shangde.edu.feed.dto.UserStatisticsDTO;
import com.shangde.edu.feed.service.IUserStatistics;
import com.shangde.edu.feed.utils.Utils;

public class UserStatisticsImpl extends BaseService implements IUserStatistics {

	public PageResult getUserStatisticslist(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		PageResult ps = simpleDao.getPageResult("FEED_User_Statistics_NS.getUserList", "FEED_User_Statistics_NS.getUserListCount", queryUserStatisticsCondition);
		List<UserStatisticsDTO> userStatisticsList = ps.getPageResult();
		for(UserStatisticsDTO userStatisticsDTO:userStatisticsList){
			queryUserStatisticsCondition.setUserId(userStatisticsDTO.getId());
			userStatisticsDTO.setWatchCourseNum((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getWatchCourseNum", queryUserStatisticsCondition));
			userStatisticsDTO.setWatchVideoNum((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getWatchVideoNum", queryUserStatisticsCondition));
			String watchTime ="0";
			if(simpleDao.getEntity("FEED_User_Statistics_NS.getWatchAllTime", queryUserStatisticsCondition)!=null){
				 watchTime = Utils.formatMinutes((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getWatchAllTime", queryUserStatisticsCondition));
			}
			userStatisticsDTO.setWatchAllTime(watchTime);
			userStatisticsDTO.setDownloadVideoNum((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getDownLoadVideoNum", queryUserStatisticsCondition));
			userStatisticsDTO.setCollectionVideoNum((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getCollectionVideoNum", queryUserStatisticsCondition));
			userStatisticsDTO.setQuestionNum((Integer)simpleDao.getEntity("FEED_User_Statistics_NS.getQuestionNum", queryUserStatisticsCondition));
		}
		return ps;
	}

	public PageResult getWatchCourseList(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		return simpleDao.getPageResult("FEED_User_Statistics_NS.getWatchCourseList", "FEED_User_Statistics_NS.getWatchCourseListCount", queryUserStatisticsCondition);
	}

	public PageResult getCollectionVideoList(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		return simpleDao.getPageResult("FEED_User_Statistics_NS.getCollectionVideoList", "FEED_User_Statistics_NS.getCollectionVideoListCount", queryUserStatisticsCondition);
	}

	public PageResult getDownloadVideoList(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		return simpleDao.getPageResult("FEED_User_Statistics_NS.getDownloadVideoList", "FEED_User_Statistics_NS.getDownloadVideoListCount", queryUserStatisticsCondition);
	}

	public PageResult getWatchVideoList(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		return simpleDao.getPageResult("FEED_User_Statistics_NS.getWatchVideoList", "FEED_User_Statistics_NS.getWatchVideoListCount", queryUserStatisticsCondition);
	}
}
