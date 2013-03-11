package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.Download;

/**
 * 下载服务接口
 * 
 * @author Libg
 * 
 */
public interface IDownload {

	/**
	 * 添加下载
	 * 
	 *
     * @param download
     * @return
	 */
	public Integer addDownload(Download download);

	/**
	 * 获取下载总次数，根据时间段查询
	 * 
	 * @return
	 */
	public Integer getDownloadCount(QueryAppStatCondition queryAppStatCondition);

	/**
	 * 获取下载总次数用户分组，根据时间段查询,计算的是人数
	 * 
	 * @return
	 */
	public Integer getDownloadUserGroupCount(
            QueryAppStatCondition queryAppStatCondition);
}
