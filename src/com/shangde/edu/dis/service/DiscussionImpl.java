package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.Discussion;
import com.shangde.edu.dis.dto.DisCustomerDTO;
import com.shangde.edu.dis.dto.DisDTO;
import com.shangde.edu.dis.condition.QueryDiscussionCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * Discussion User: guoqiang.liu Date: 2011-05-14
 */
@SuppressWarnings("unchecked")
public class DiscussionImpl extends BaseService implements IDiscussion {

	public Integer addDiscussion(Discussion discussion) {
		return simpleDao.createEntity("Discussion_NS.createDiscussion",
				discussion);
	}

	public void delDiscussionById() {
	}

	public PageResult searchDisList(
			QueryDiscussionCondition queryDiscussionCondition) {

		return simpleDao.getPageResult("Discussion_NS.searchDisList",
				"Discussion_NS.searchDisCountByList", queryDiscussionCondition);
	}

	public void updateDiscussion(Discussion discussion) {
		simpleDao.updateEntity("Discussion_NS.updateDiscussion", discussion);
	}

	public Discussion getDiscussionById(int id) {

		return simpleDao.getEntity("Discussion_NS.getDiscussionById", id);
		// return new Discussion();
	}

	public List<Discussion> getDiscussionList(
			QueryDiscussionCondition queryDiscussionCondition) {
		return simpleDao.getForList("Discussion_NS.getDiscussionList",
				queryDiscussionCondition);
	}

	public PageResult getDiscussionListByCondition(
			PageQuery queryDiscussionCondition) {

		return simpleDao.getPageResult(
				"Discussion_NS.getCustomerListByCondition",
				"Discussion_NS.getCustomerCountByCondition",
				queryDiscussionCondition);

	}

	/**
	 * 根据分页获取全部小组信息
	 */
	public PageResult getPageDiscussionList(
			QueryDiscussionCondition queryDiscussionCondition) {

		PageResult pr = simpleDao.getPageResult(
				"Discussion_NS.getDiscussionByList",
				"Discussion_NS.getDiscussionCount", queryDiscussionCondition);

		return pr;

	}

	public List<DisCustomerDTO> findCustomerByDisId(int disId) {
		return simpleDao.getForList("Discussion_NS.findCustomerByDisId", disId);
	}

	public List<Discussion> getAllDiscussion() {

		return simpleDao.getForList("Discussion_NS.getDiscussionList", null);
	}

	public List<Discussion> getDisBySubjectId(int subjectId) {
		return simpleDao.getForList("Discussion_NS.getDisListBySubjectId",
				subjectId);
	}

	/**
	 * 查询全部小组，并统计小组成员数量
	 * 
	 * @param queryDiscussionCondition
	 * @return
	 */
	public PageResult getPageDisByCountUser(
			QueryDiscussionCondition queryDiscussionCondition) {
		PageResult pr = simpleDao.getPageResult(
				"Discussion_NS.getPageDisByCountUser",
				"Discussion_NS.getDiscussionCount", queryDiscussionCondition);

		return pr;
	}

	/** ***V1.1方法(method)声明区域 开始**** */

	public List<DisDTO> getDisOrganizeList(int cusId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Discussion_NS.getDisOrganizeList", cusId);
	}

}
