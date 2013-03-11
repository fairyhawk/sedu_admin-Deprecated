package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.condition.QueryVoteDetailCondition;
import com.shangde.edu.dis.domain.VoteDetail;


@SuppressWarnings("unchecked")
public class VoteDetailImpl extends BaseService implements IVoteDetail {

	public int getVoteDetailCountByVoteId(int voteId) {

		return simpleDao.getEntity("VoteDetail_NS.getVoteDetailCountByVoteId",
				voteId);
	}

	public void updateVoteDetailCount(int id) {
		// TODO Auto-generated method stub
		simpleDao.updateEntity("VoteDetail_NS.updateVoteDetailCount", id);
	}

	public boolean editBatchDetail(List<VoteDetail> list) {

		boolean flag = false;
		try {
			for (VoteDetail voteDetail : list) {
				if (getVoteDetailById(voteDetail.getId()) != null) {
					updateVoteDetail(voteDetail);
				} else {
					addVoteDetail(voteDetail);
				}
			}

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public VoteDetail getVoteDetailById(int id) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("VoteDetail_NS.getVoteDetailById", id);
	}

	public Integer addVoteDetail(VoteDetail voteDetail) {
		return simpleDao.createEntity("VoteDetail_NS.createVoteDetail",
				voteDetail);
	}

	public boolean addBatchDetail(List<VoteDetail> list) {

		boolean flag = false;
		int count = 0;
		int size = list.size();
		int tempId;
		try {
			for (VoteDetail vd : list) {
				tempId = simpleDao.createEntity(
						"VoteDetail_NS.createVoteDetail", vd);
				if (tempId > 0) {
					++count;
				}
			}
			/**
			 * 当成功的结果等于原数据大小时，返回true
			 */
			if (count == size) {
				flag = true;
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

		return flag;
	}

	public void deleteVoteDetailByVoteId(int id) {

		simpleDao.deleteEntity("VoteDetail_NS.deleteVoteDetailByVoteId", id);
	}

	public void updateVoteDetail(VoteDetail voteDetail) {
		simpleDao.updateEntity("VoteDetail_NS.updateVoteDetail", voteDetail);
	}

	public VoteDetail getVoteDetailById() {
		return new VoteDetail();
	}

	public List<VoteDetail> getVoteDetailList(
			QueryVoteDetailCondition queryVoteDetailCondition) {
		return simpleDao.getForList("VoteDetail_NS.getVoteDetailList",
				queryVoteDetailCondition);
	}

	public List<VoteDetail> getVoteDetailListByVoteId(int voteId) {

		return simpleDao.getForList("VoteDetail_NS.getVoteDetailListByVoteId",
				voteId);
	}
}
