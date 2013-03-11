package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryTopicReplyCondition;
import com.shangde.edu.dis.domain.TopicReply;

public interface ITopicReply {
	public Integer addTopicReply(TopicReply topicReply);

	public int delTopicReplyById(int id);

	/**
	 * 根据条件分页查询话题回复信息
	 * 
	 * @param QueryTopicReplyCondition
	 * @return
	 */
	public PageResult getPageTopicReplyList(
			QueryTopicReplyCondition QueryTopicReplyCondition);

	/**
	 * 根据条件分页查询话题回复信息
	 * 
	 * @param QueryTopicReplyCondition
	 * @return
	 */
	public PageResult getFrontPageTopicReplyList(
			QueryTopicReplyCondition queryTopicReplyCondition);

	/**
	 * 获取数据记录数
	 * 
	 * @return
	 */
	public int getCount();

	/** ***V1.1方法(method)声明区域 开始**** */

	public List<TopicReply> getReplyCountList(int topicId);

	/**
	 * 根据id取得数据
	 * 
	 * @return
	 */
	public TopicReply getTopicReplyById(int id);

	/**
	 * 删除一条话题回复
	 * 
	 * @param map
	 * @return
	 */
	public int delTopicReply(Map map);

	/** ***V1.1方法(method)声明区域 结束**** */

}