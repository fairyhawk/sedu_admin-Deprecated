package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryUserWebFromCondition;
import com.shangde.edu.sys.condition.QueryWebFromAgentLogsCondition;
import com.shangde.edu.sys.dto.StatisticsWebFromDTO;

public class WebFromAgentLogService extends BaseServiceManyDb implements
		IWebFromAgentLogService {

	public PageResult statisticWebFromLog(
			QueryWebFromAgentLogsCondition queryCondition) {
		return simpleDaoRead.getPageResult(
				"WebFromAgentLog_NS.statisticWebFromLog",
				"WebFromAgentLog_NS.statisticCountWebFromLog", queryCondition);
	}

	public StatisticsWebFromDTO statisticWebFromLogOthers(
			QueryWebFromAgentLogsCondition queryCondition) {
		return simpleDao.getEntity(
				"WebFromAgentLog_NS.statisticWebFromLogOthers", queryCondition);
	}

	public PageResult statisticUserWebFrom(
			QueryUserWebFromCondition queryCondition) {
		return simpleDaoRead.getPageResult(
				"WebFromAgentLog_NS.statisticUserWebFrom",
				"WebFromAgentLog_NS.statisticUserWebFromCount", queryCondition);
	}

}
