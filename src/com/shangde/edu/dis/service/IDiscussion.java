package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryDiscussionCondition;
import com.shangde.edu.dis.domain.Discussion;
import com.shangde.edu.dis.dto.DisCustomerDTO;
import com.shangde.edu.dis.dto.DisDTO;

/**IDiscussion
 * User: guoqiang.liu Date: 2011-05-14
 */
public interface IDiscussion {
	/**
	 * 添加 Discussion
	 * 
	 * @param discussion
	 * @return id
	 */
	public Integer addDiscussion(Discussion discussion);

	/**
	 */
	public void delDiscussionById();

	/**
	 * 
	 * @param discussion
	 */
	public void updateDiscussion(Discussion discussion);

	/**
	 * 
	 * @return
	 */
	public Discussion getDiscussionById(int id);

	/**
	 * 
	 * @param queryDiscussionCondition
	 */
	public List<Discussion> getDiscussionList(
			QueryDiscussionCondition queryDiscussionCondition);

	/**
	 * 分页查询
	 * 
	 * @param queryDiscussionCondition
	 *            查询条件
	 * @return 分页结果
	 */
	public PageResult getDiscussionListByCondition(
			PageQuery queryDiscussionCondition);

	public PageResult getPageDiscussionList(
			QueryDiscussionCondition queryDiscussionCondition);

	/**
	 * 根据小组ID查询小组成员
	 * 
	 */
	public List<DisCustomerDTO> findCustomerByDisId(int disId);

	/**
	 * 获取所有小组列表，可用状态
	 * @return
	 */
	public List<Discussion> getAllDiscussion();

	/**
	 * 根据专业ID取小组信息
	 * 
	 * @return
	 */
	public List<Discussion> getDisBySubjectId(int subjectId);

	/**
	 * 查询全部小组，并统计小组成员数量
	 * 
	 * @param queryDiscussionCondition
	 * @return
	 */
	public PageResult getPageDisByCountUser(
			QueryDiscussionCondition queryDiscussionCondition);

	/**
	 * 后台小组查询
	 * 
	 * @param queryDiscussionCondition
	 * @return
	 */
	public PageResult searchDisList(
			QueryDiscussionCondition queryDiscussionCondition);

	/** ***V1.1方法(method)声明区域 开始**** */

	/**
	 * 获取自己所在的小组 by 用户id
	 * 
	 * @param cusId
	 * @return List<DisDTO>
	 */
	public List<DisDTO> getDisOrganizeList(int cusId);

}