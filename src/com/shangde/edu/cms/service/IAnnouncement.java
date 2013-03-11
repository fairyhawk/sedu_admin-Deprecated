package com.shangde.edu.cms.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryAcmentCondition;
import com.shangde.edu.cms.domain.Announcement;

public interface IAnnouncement {

	/**
	 * 添加公告
	 * @return
	 */
	public Integer add(Announcement announcement);
	
	/**
	 * 删除公告
	 * @param id
	 * @return
	 */
	public int delete(String ids);
	
	/**
	 * 修改公告
	 * @param announcement
	 * @return
	 */
	public int update(Announcement announcement);
	
	/**
	 * 获取10条功能
	 * @return
	 */
	public List<Announcement> getTop10(String subjectIds);
	
	/**
	 * 分页获取所有公告
	 * @param pageQuery
	 * @return
	 */
	public PageResult getAll(PageQuery pageQuery);
	
	/**
	 * 根据ID查询单个公告信息
	 * @param id
	 * @return
	 */
	public Announcement getById(int id);
	
	/**
	 * 搜索公告
	 * @param queryAcmentCondition
	 * @return
	 */
	public PageResult search(QueryAcmentCondition queryAcmentCondition);
	
	/**
	 * 修改公告状态（可批量）
	 * @param map
	 * @return
	 */
	public int updateStatus(Map map);
	
	/**
	 * 上一篇
	 * @param map
	 * @return
	 */
	public Announcement getUp(Map map);
	
	/**
	 * 下一篇
	 * @param map
	 * @return
	 */
	public Announcement getDown(Map map);
	
	/**
	 * 修改点击数
	 * @param id
	 * @return
	 */
	public int updateClickNum(int id);
	
}
