package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.VoteDetail;
import com.shangde.edu.dis.condition.QueryVoteDetailCondition;

public interface IVoteDetail {

	public Integer addVoteDetail(VoteDetail voteDetail);

	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	public boolean addBatchDetail(List<VoteDetail> list);

	public void deleteVoteDetailByVoteId(int voteId);

	public void updateVoteDetail(VoteDetail voteDetail);

	/**
	 * 批量修改or添加,修改是查询，如果当前id存在，则修改对象，否则添加
	 * 
	 * @param list
	 * @return
	 */
	public boolean editBatchDetail(List<VoteDetail> list);

	/**
	 * 根据id取得数据
	 * 
	 * @param id
	 * @return
	 */
	public VoteDetail getVoteDetailById(int id);

	/**
	 * 根据voteId取得数据
	 * 
	 * @param voteId
	 * @return
	 */
	public List<VoteDetail> getVoteDetailListByVoteId(int voteId);

	public List<VoteDetail> getVoteDetailList(
			QueryVoteDetailCondition queryVoteDetailCondition);

	/**
	 * 修改记录投票次数 累加
	 * 
	 * @param id
	 */
	public void updateVoteDetailCount(int id);

	/**
	 * 获取某投票项个数
	 * 
	 * @param voteId
	 * @return
	 */
	public int getVoteDetailCountByVoteId(int voteId);

}