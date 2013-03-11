package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.dis.domain.VoteLog;
import com.shangde.edu.dis.condition.QueryVoteLogCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class VoteLogImpl extends BaseService implements IVoteLog {

	public VoteLog getVoteLogById(Map map) {

		return simpleDao.getEntity("VoteLog_NS.getVoteLogByVoteId", map);
	}

	public Integer addVoteLog(VoteLog voteLog) {
		return simpleDao.createEntity("VoteLog_NS.createVoteLog", voteLog);
	}

	public void delVoteLogByVoteId(int voteId) {
		simpleDao.deleteEntity("VoteLog_NS.delVoteLogByVoteId", voteId);
	}

	public void updateVoteLog(VoteLog voteLog) {
		simpleDao.updateEntity("VoteLog_NS.updateVoteLog", voteLog);
	}

	public VoteLog getVoteLogById() {
		return new VoteLog();
	}

	public List<VoteLog> getVoteLogList(
			QueryVoteLogCondition queryVoteLogCondition) {
		return simpleDao.getForList("VoteLog_NS.getVoteLogList",
				queryVoteLogCondition);
	}
}
