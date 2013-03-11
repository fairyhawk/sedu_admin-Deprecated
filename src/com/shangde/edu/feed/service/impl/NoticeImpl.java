package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryNoticeCondition;
import com.shangde.edu.feed.domain.Notice;
import com.shangde.edu.feed.service.INotice;

/**
 * 公告服务接口实现层
 * 
 * @author Libg
 * 
 */
public class NoticeImpl extends BaseService implements INotice {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#addNotice(com.shangde.edu.feed.domain.Notice)
	 */
	public Integer addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("Feed_NOTICE_NS.createNotice", notice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#deleteNotice(int)
	 */
	public Integer deleteNotice(int id) {
		// TODO Auto-generated method stub
		return simpleDao.delete("Feed_NOTICE_NS.deleteNotice", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#getNoticeById(int)
	 */
	public Notice getNoticeById(int id) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_NOTICE_NS.getNoticeById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#getNoticeList(com.shangde.edu.feed.condition.QueryNoticeCondition)
	 */
	public List<Notice> getNoticeList(QueryNoticeCondition queryNoticeCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Feed_NOTICE_NS.getNoticeList",
				queryNoticeCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#getNoticePageList(com.shangde.edu.feed.condition.QueryNoticeCondition)
	 */
	public PageResult getNoticePageList(
			QueryNoticeCondition queryNoticeCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Feed_NOTICE_NS.getNoticePageList",
				"Feed_NOTICE_NS.getNoticePageCountList", queryNoticeCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#updateNotice(com.shangde.edu.feed.domain.Notice)
	 */
	public Integer updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return simpleDao.update("Feed_NOTICE_NS.updateNotice", notice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.INotice#updateStatusById(com.shangde.edu.feed.condition.QueryNoticeCondition)
	 */
	public Integer updateStatusById(QueryNoticeCondition queryNoticeCondition) {
		// TODO Auto-generated method stub
		return simpleDao.update("Feed_NOTICE_NS.updateStatusById",
				queryNoticeCondition);
	}

}
