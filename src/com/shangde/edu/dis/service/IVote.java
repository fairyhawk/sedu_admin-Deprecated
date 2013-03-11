package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.Vote;
import com.shangde.edu.dis.condition.QueryVoteCondition;

public interface IVote {

	public java.lang.Integer addVote(Vote vote);

	public void delVoteById(int id);

	public void updateVote(Vote vote);

	public Vote getVoteById(int id);

	public Vote getVoteByTopicId(int topicId);

	public List<Vote> getVoteList(QueryVoteCondition queryVoteCondition);

}