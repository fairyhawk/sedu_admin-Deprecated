package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.Download;
import com.shangde.edu.feed.service.IDownload;

public class DownloadImpl extends BaseService implements IDownload {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IDownload#addDownload(com.shangde.edu.feed.domain.Download)
	 */
	public Integer addDownload(Download download) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("Feed_Download_NS.createDownload",
				download);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IDownload#getDownloadCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	public Integer getDownloadCount(QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_Download_NS.getDownloadCount",
				queryAppStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IDownload#getDownloadUserGroupCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	public Integer getDownloadUserGroupCount(
            QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_Download_NS.getDownloadUserGroupCount",
				queryAppStatCondition);
	}

}
