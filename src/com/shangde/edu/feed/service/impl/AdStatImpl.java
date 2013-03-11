package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryAdStatCondition;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.AdStat;
import com.shangde.edu.feed.service.IAdStat;

public class AdStatImpl extends BaseService implements IAdStat {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#addAd(com.shangde.edu.feed.domain.AdStat)
	 */
	public Integer addAd(AdStat adStat) {
		return simpleDao.createEntity("Feed_AdStat_NS.createAdStat", adStat);
	}

	public PageResult getAdList(
			QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getPageResult("Feed_AdStat_NS.getAdStatList",
				"Feed_AdStat_NS.getAdStatListCount", queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatGroupCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getAdStatGroupCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatGroupCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatAndAdIdGroupCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getAdStatAndAdIdGroupCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatAndAdIdGroupCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatAndAdIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getAdStatAndAdIdCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatAndAdIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatDATEList(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public PageResult getAdStatDATEList(
			QueryAdStatCondition queryAdStatCondition) {
		return simpleDao.getPageResult("Feed_AdStat_NS.getAdStatDATEList",
				"Feed_AdStat_NS.getAdStatDATEListCount", queryAdStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatDATEGroupCount(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public Integer getAdStatDATEGroupCount(QueryAdStatCondition queryAdStatCondition) {
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatDATEGroupCount",
				queryAdStatCondition);// 获得总记录数
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatDATEAndAdIdGroupCount(int)
	 */
	public Integer getAdStatDATEAndAdIdGroupCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_AdStat_NS.getAdStatDATEAndAdIdGroupCount", adId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatDATEAndAdIdCount(int)
	 */
	public Integer getAdStatDATEAndAdIdCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatDATEAndAdIdCount",
				adId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatMONTHList(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public PageResult getAdStatMONTHList(
			QueryAdStatCondition queryAdStatCondition) {
		return simpleDao.getPageResult("Feed_AdStat_NS.getAdStatMONTHList",
				"Feed_AdStat_NS.getAdStatMONTHListCount", queryAdStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatMONTHGroupCount(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public Integer getAdStatMONTHGroupCount(
            QueryAdStatCondition queryAdStatCondition) {

		// return
		// simpleDao.getForList("Feed_AdStat_NS.getAdStatMONTHGroupCount",queryAdStatCondition).size();//
		// 获得总记录数
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatMONTHGroupCount",
				queryAdStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatMONTHAndAdIdGroupCount(int)
	 */
	public Integer getAdStatMONTHAndAdIdGroupCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_AdStat_NS.getAdStatMONTHAndAdIdGroupCount", adId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatMONTHAndAdIdCount(int)
	 */
	public Integer getAdStatMONTHAndAdIdCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatMONTHAndAdIdCount",
				adId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatWEEKList(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public PageResult getAdStatWEEKList(
			QueryAdStatCondition queryAdStatCondition) {
		return simpleDao.getPageResult("Feed_AdStat_NS.getAdStatWEEKList",
				"Feed_AdStat_NS.getAdStatWEEKListCount", queryAdStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatWEEKGroupCount(com.shangde.edu.feed.condition.QueryAdStatCondition)
	 */
	public Integer getAdStatWEEKGroupCount(QueryAdStatCondition queryAdStatCondition) {
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatWEEKGroupCount",
				queryAdStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatWEEKAndAdIdGroupCount(int)
	 */
	public Integer getAdStatWEEKAndAdIdGroupCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_AdStat_NS.getAdStatWEEKAndAdIdGroupCount", adId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAdStat#getAdStatWEEKAndAdIdCount(int)
	 */
	public Integer getAdStatWEEKAndAdIdCount(int adId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AdStat_NS.getAdStatWEEKAndAdIdCount",
				adId);
	}

}
