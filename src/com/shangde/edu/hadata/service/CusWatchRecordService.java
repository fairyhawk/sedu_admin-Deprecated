package com.shangde.edu.hadata.service;

import java.util.List;

import com.shangde.edu.hadata.domain.CusWatchRecord;

public interface CusWatchRecordService {
	/**
	 * 通过ID查询观看视频记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<CusWatchRecord> getCusWatchRecordList(Integer id) throws Exception;
}
