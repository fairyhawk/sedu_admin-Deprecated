package com.shangde.edu.feed.service.impl;

import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryReviewCondition;
import com.shangde.edu.feed.domain.Review;
import com.shangde.edu.feed.service.IReview;

/**
 * 微学习-留言模块接口实现类
 * 
 * @author Libg
 * 
 */
public class ReviewImpl extends BaseService implements IReview {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#addReview(com.shangde.edu.feed.domain.Review)
	 */
	public Integer addReview(Review review) {
		return simpleDao.createEntity("Review_NS.createReview", review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#delReviewById(java.lang.Integer)
	 */
	public Integer delReviewById(Integer id) {

		return simpleDao.delete("Review_NS.delReviewByid", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#getReviewById(java.lang.Integer)
	 */
	public Review getReviewById(Integer id) {
		return simpleDao.getEntity("Review_NS.getReviewById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#updateReview(com.shangde.edu.feed.domain.Review)
	 */
	public Integer updateReview(Review review) {
		return simpleDao.update("Review_NS.updateReview", review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#updateReviewCounts(java.util.Map)
	 */
	public Integer updateReviewCounts(Map<String, Integer> map) {
		return simpleDao.update("Review_NS.updateReviewCounts", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#getReviewList(com.shangde.edu.feed.condition.QueryReviewCondition)
	 */
	public PageResult getReviewList(QueryReviewCondition queryReviewCondition) {
		return simpleDao.getPageResult("Review_NS.getReviewList",
				"Review_NS.getReviewListPageCount", queryReviewCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#getReviewListBack(com.shangde.edu.feed.condition.QueryReviewCondition)
	 */
	public PageResult getReviewListBack(
			QueryReviewCondition queryReviewCondition) {
		return simpleDao.getPageResult("Review_NS.getReviewListBack",
				"Review_NS.getReviewListPageCountBack", queryReviewCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#updateReviewStatusDetail(com.shangde.edu.feed.domain.Review)
	 */
	public Integer updateReviewStatusDetail(Review review) {
		return simpleDao.update("Review_NS.updateReviewStatusDetail", review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#getTotalReviewNum()
	 */
	public Integer getTotalReviewNum() {
		return simpleDao.getEntity("Review_NS.getTotalReviewNum", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#updateAntilogNumber(java.lang.Integer)
	 */
	public Integer updateAntilogNumber(Integer id) {
		return simpleDao.update("Review_NS.updateAntilogNumber", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IReview#updateSupportNumber(java.lang.Integer)
	 */
	public Integer updateSupportNumber(Integer id) {
		return simpleDao.update("Review_NS.updateSupportNumber", id);
	}

	/* (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IReview#updateReviewStatus(com.shangde.edu.feed.condition.QueryReviewCondition)
	 */
	public Integer updateReviewStatus(QueryReviewCondition queryReviewCondition) {
		// TODO Auto-generated method stub
		return simpleDao.update("Review_NS.updateReviewStatus", queryReviewCondition);
	}
	
	

}
