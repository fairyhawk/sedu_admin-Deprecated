package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryBrowseLogCondition;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.BrowseLog;
import com.shangde.edu.feed.service.IBrowseLog;

public class BrowseLogImpl extends BaseService implements IBrowseLog {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#addBrowseLog(com.shangde.edu.feed.domain.BrowseLog)
	 */
	public Integer addBrowseLog(BrowseLog browseLog) {
		return simpleDao.createEntity("Feed_Browse_Log_NS.createBrowseLog",
				browseLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getAdList(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public PageResult getBrowseLogList(
			QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getPageResult("Feed_Browse_Log_NS.getBrowseLogList",
				"Feed_Browse_Log_NS.getBrowseLogListCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogGroupCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getBrowseLogGroupCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_Browse_Log_NS.getBrowseLogGroupCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogDATEList(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public PageResult getBrowseLogDATEList(
			QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getPageResult(
				"Feed_Browse_Log_NS.getBrowseLogDATEList",
				"Feed_Browse_Log_NS.getBrowseLogDATEListCount",
				queryBrowseLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogDATEGroupCount(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public Integer getBrowseLogDATEGroupCount(
            QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getEntity(
				"Feed_Browse_Log_NS.getBrowseLogDATEGroupCount",
				queryBrowseLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogMONTHList(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public PageResult getBrowseLogMONTHList(
			QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getPageResult(
				"Feed_Browse_Log_NS.getBrowseLogMONTHList",
				"Feed_Browse_Log_NS.getBrowseLogMONTHListCount",
				queryBrowseLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogMONTHGroupCount(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public Integer getBrowseLogMONTHGroupCount(
            QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getEntity(
				"Feed_Browse_Log_NS.getBrowseLogMONTHGroupCount",
				queryBrowseLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogWEEKList(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public PageResult getBrowseLogWEEKList(
			QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getPageResult(
				"Feed_Browse_Log_NS.getBrowseLogWEEKList",
				"Feed_Browse_Log_NS.getBrowseLogWEEKListCount",
				queryBrowseLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IBrowseLog#getBrowseLogWEEKGroupCount(com.shangde.edu.feed.condition.QueryBrowseLogCondition)
	 */
	public Integer getBrowseLogWEEKGroupCount(
            QueryBrowseLogCondition queryBrowseLogCondition) {
		return simpleDao.getEntity(
				"Feed_Browse_Log_NS.getBrowseLogWEEKGroupCount",
				queryBrowseLogCondition);
	}

}
