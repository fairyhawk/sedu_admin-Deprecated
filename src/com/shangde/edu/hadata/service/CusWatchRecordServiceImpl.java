package com.shangde.edu.hadata.service;

import java.util.List;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.edu.hadata.domain.CusWatchRecord;

public class CusWatchRecordServiceImpl extends BaseServiceManyDb implements
		CusWatchRecordService {

	/**
	 * 通过ID查询观看视频记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<CusWatchRecord> getCusWatchRecordList(Integer id) throws Exception {
		return simpleDaoHaData.getForList("CusWatchRecord_NS.getCusWatchRecordById", id);
	}

}
