package com.shangde.edu.feed.service.impl;

import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.UniqueRecord;
import com.shangde.edu.feed.service.IUniqueRecord;

/**
 * 唯一记录，实现类
 * 
 * @author Libg
 * 
 */
public class UniqueRecordImpl extends BaseService implements IUniqueRecord {

	public Integer addUniqueRecord (UniqueRecord uniqueRecord) {
		return simpleDao.createEntity(
				"Feed_UniqueRecord_NS.createUniqueRecord", uniqueRecord);
	}

	public Integer delUniqueRecord(Map map) {
		return simpleDao.delete("Feed_UniqueRecord_NS.delUniqueRecord", map);
	}

	public Integer getUniqueRecord(Map map) {
		return simpleDao.getEntity("Feed_UniqueRecord_NS.getUniqueRecord", map);
	}

}
