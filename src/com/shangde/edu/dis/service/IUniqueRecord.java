package com.shangde.edu.dis.service;

import java.util.Map;

import com.shangde.edu.dis.domain.UniqueRecord;

public interface IUniqueRecord {

	/**
	 * 添加
	 * 
	 * @param uniqueRecord
	 * @return
	 */
	public Integer addUniqueRecord(UniqueRecord uniqueRecord);

	/**
	 * 获取
	 * 
	 * @param map
	 * @return
	 */
	public int getUniqueRecord(Map map);

	/**
	 * 删除
	 * 
	 * @param map
	 * @return
	 */
	public int delUniqueRecord(Map map);
}
