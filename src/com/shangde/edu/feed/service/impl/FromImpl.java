/**
 * 
 */
package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.From;
import com.shangde.edu.feed.service.IFrom;

/**
 * @author Libg
 * 
 */
public class FromImpl extends BaseService implements IFrom {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IFrom#addFrom(com.shangde.edu.feed.domain.From)
	 */
	public Integer addFrom(From from) {
		return simpleDao.createEntity("Feed_From_NS.createFrom", from);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IFrom#getFromCount(int)
	 */
	public Integer getFromCount(int id) {
		return simpleDao.getEntity("Feed_From_NS.getFromCount", id);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryFromList()
	 */
	public List<From> queryFromList() {
		return simpleDao.getForList("Feed_From_NS.queryFromList", null);
	}
	
}
