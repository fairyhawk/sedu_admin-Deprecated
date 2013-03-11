package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.dis.condition.QueryVoteLogCondition;
import com.shangde.edu.dis.domain.VoteLog;

public interface IVoteLog {

	public Integer addVoteLog(VoteLog voteLog);

	/**
	 * 根据voteId删除
	 * 
	 * @param voteId
	 */
	public void delVoteLogByVoteId(int voteId);

	public void updateVoteLog(VoteLog voteLog);

	public VoteLog getVoteLogById();

	/**
	 * 根据voteId，cusId查询
	 * 
	 * @param map
	 * @return
	 */
	public VoteLog getVoteLogById(Map map);

	public List<VoteLog> getVoteLogList(
			QueryVoteLogCondition queryVoteLogCondition);

}