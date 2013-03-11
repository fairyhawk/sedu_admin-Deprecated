package com.shangde.edu.dis.service;

import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.domain.UniqueRecord;

public class UniqueRecordImpl extends BaseService implements IUniqueRecord {

	public Integer addUniqueRecord(UniqueRecord uniqueRecord) {
		return simpleDao.createEntity("UniqueRecord_NS.createUniqueRecord",
				uniqueRecord);
	}

	public int delUniqueRecord(Map map) {
		return simpleDao.delete("UniqueRecord_NS.delUniqueRecord", map);
	}

	public int getUniqueRecord(Map map) {
		return simpleDao.getEntity("UniqueRecord_NS.getUniqueRecord", map);
	}

}
