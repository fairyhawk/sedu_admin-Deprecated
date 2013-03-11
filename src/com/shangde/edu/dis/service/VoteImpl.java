package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.Vote;
import com.shangde.edu.dis.condition.QueryVoteCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class VoteImpl extends BaseService implements IVote {
	public Vote getVoteByTopicId(int topicId) {

		return simpleDao.getEntity("Vote_NS.getVoteByTopicId", topicId);
	}

	public java.lang.Integer addVote(Vote vote) {
		return simpleDao.createEntity("Vote_NS.createVote", vote);
	}

	public void delVoteById(int id) {
		simpleDao.deleteEntity("Vote_NS.deleteVoteById", id);
	}

	public void updateVote(Vote vote) {
		simpleDao.updateEntity("Vote_NS.updateVote", vote);
	}

	public Vote getVoteById(int id) {
		return simpleDao.getEntity("Vote_NS.getVoteById", id);
	}

	public List<Vote> getVoteList(QueryVoteCondition queryVoteCondition) {
		return simpleDao.getForList("Vote_NS.getVoteList", queryVoteCondition);
	}
}
