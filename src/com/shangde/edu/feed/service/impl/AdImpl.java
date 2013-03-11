package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryAdCondition;
import com.shangde.edu.feed.domain.Ad;
import com.shangde.edu.feed.service.IAd;

public class AdImpl extends BaseService implements IAd {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAd#addAd(com.shangde.edu.feed.domain.Ad)
	 */
	public int addAd(Ad ad) {
		return simpleDao.createEntity("Feed_Ad_NS.createAd", ad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAd#getAdById(java.lang.Integer)
	 */
	public Ad getAdById(Integer id) {
		return simpleDao.getEntity("Feed_Ad_NS.getAdById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAd#getAdList(com.shangde.edu.feed.condition.QueryAdCondition)
	 */
	public PageResult getAdList(QueryAdCondition queryAdCondition) {
		return simpleDao.getPageResult("Feed_Ad_NS.getAdList",
				"Feed_Ad_NS.getAdListCount", queryAdCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAd#updateAd(com.shangde.edu.feed.domain.Ad)
	 */
	public int updateAd(Ad ad) {
		return simpleDao.update("Feed_Ad_NS.updateAd", ad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAd#getAdAllList(com.shangde.edu.feed.condition.QueryAdCondition)
	 */
	public List<Ad> getAdAllList(QueryAdCondition queryAdCondition) {
		// TODO Auto-generated method stub
		return simpleDao
				.getForList("Feed_Ad_NS.getAdAllList", queryAdCondition);
	}

}
