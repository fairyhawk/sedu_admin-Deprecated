package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryNoticeCondition;
import com.shangde.edu.feed.domain.Notice;

/**
 * 公告信息服务接口
 * 
 * @author Libg
 * 
 */
public interface INotice {

	/**
	 * 添加公告
	 *
     */
	public Integer addNotice(Notice notice);

	/**
	 * 修改公告
	 * 
	 * @return
	 */
	public Integer updateNotice(Notice notice);

	/**
	 * 根据id修改状态
	 * 
	 *
     * @param queryNoticeCondition
     * @return
	 */
	public Integer updateStatusById(QueryNoticeCondition queryNoticeCondition);

	/**
	 * 根据id删除数据
	 * 
	 *
     * @param id
     * @return
	 */
	public Integer deleteNotice(int id);

	/**
	 * 根据公告id取得数据
	 * 
	 * @param id
	 * @return
	 */
	public Notice getNoticeById(int id);

	/**
	 * 高级条件查询
	 * 
	 * @param queryNoticeCondition
	 * @return
	 */
	public PageResult getNoticePageList(
			QueryNoticeCondition queryNoticeCondition);

	/**
	 * 前台调用
	 * 
	 * @param queryNoticeCondition
	 * @return
	 */
	public List<Notice> getNoticeList(QueryNoticeCondition queryNoticeCondition);

}
