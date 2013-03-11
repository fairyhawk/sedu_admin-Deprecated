package com.shangde.edu.feed.service;

import java.util.Map;

import com.shangde.edu.feed.domain.UniqueRecord;

/**
 * 微学习，针对唯一激活统计接口
 * 
 * 目前使用：任务激活，视频激活-针对同一个用户
 * 
 * @author Libg
 * 
 */
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
	 *
     * @param map
     * @return
	 */
	public Integer getUniqueRecord(Map<String, String> map);

	/**
	 * 删除
	 * 
	 *
     * @param map
     * @return
	 */
	public Integer delUniqueRecord(Map map);
}
