package com.shangde.edu.sys.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryUserWebFromCondition;
import com.shangde.edu.sys.condition.QueryWebFromAgentLogsCondition;
import com.shangde.edu.sys.dto.StatisticsWebFromDTO;

public interface IWebFromAgentLogService {

	public abstract PageResult statisticWebFromLog(
			QueryWebFromAgentLogsCondition queryCondition);

	public PageResult statisticUserWebFrom(
			QueryUserWebFromCondition queryCondition);
	
	public StatisticsWebFromDTO statisticWebFromLogOthers(
			QueryWebFromAgentLogsCondition queryCondition);

}