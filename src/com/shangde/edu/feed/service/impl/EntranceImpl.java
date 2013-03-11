/**
 * 
 */
package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.Entrance;
import com.shangde.edu.feed.service.IEntrance;

/**
 * @author Libg
 * 
 */
public class EntranceImpl extends BaseService implements IEntrance {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IEntrance#addEntrance(com.shangde.edu.feed.domain.Entrance)
	 */
	public Integer addEntrance(Entrance entrance) {
		return simpleDao.createEntity("Feed_Entrance_NS.createEntrance",
				entrance);
	}
	
}
