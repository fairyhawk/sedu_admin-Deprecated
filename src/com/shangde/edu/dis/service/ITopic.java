package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryTopicCondition;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.dto.DisReTopicDTO;

public interface ITopic {
	/**
	 * 
	 * @param topic
	 * @return id
	 */
	public Integer addTopic(Topic topic);

	/**
	 * 根据小组Id查义执门话题
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getHotTopicByDisId(Integer disId);

	/**
	 * 根据id删除帖子
	 * 
	 * @param map
	 */
	public int delTopic(Map map);

	/**
	 * 修改推荐次数字段
	 * 
	 * @param map
	 * 
	 * map的key = incremental表示增量值,id记录id
	 * 
	 */
	public void updateTopicRecCount(Map map);

	/**
	 * 修改回复次数字段
	 * 
	 * @param map
	 * 
	 * map的key = incremental表示增量值,id记录id
	 * 
	 */
	public void updateTopicReplyCounts(Map map);

	/**
	 * 修改回复时间
	 * 
	 * @param map
	 * 
	 * map的key = replyTime(Date类型)表示回复时间,id记录id
	 */
	public void updateTopicReplyTime(Map<String, Object> map);

	/**
	 * 修改投票次数字段
	 * 
	 * @param map
	 * 
	 * map的key = incremental表示增量值,id记录id
	 * 
	 */
	public void updateTopicVoteCount(Map map);

	/**
	 * 取topic dto数据
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult getTopicDTOList(QueryTopicCondition queryTopicCondition);

	/**
	 * 
	 * @param map
	 *            map的key = status=状态值,ids=[id,id,id,]
	 * @return
	 */
	public int updateTopicStatus(Map map);

	/**
	 * 多条件查询,根据帖子表为主表查询
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult searchTopicList(QueryTopicCondition queryTopicCondition);

	/**
	 * 多条件查询,帖子表与回复表同时为主
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult searchReplyTopicList(
			QueryTopicCondition queryTopicCondition);

	/** ***V1.1方法(method)声明区域 开始**** */

	/**
	 * 查询某小组所有话题数量
	 */
	public int getDisTopicCount(int disId);

	/**
	 * 修改点击次数字段
	 * 
	 * @param map
	 * 
	 * map的key = incremental表示增量值,id记录id
	 * 
	 */
	public void updateTopicClickCounts(Map map);

	/**
	 * TOP 最新的话题
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getNewTopic(QueryTopicCondition queryTopicCondition);

	/**
	 * 我发布的话题更多页
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult getMyTopicList(QueryTopicCondition queryTopicCondition);

	/**
	 * 我发布的话题
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getMyTopic(QueryTopicCondition queryTopicCondition);

	/**
	 * 我回复的话题
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public List<DisReTopicDTO> getReTopic(
			QueryTopicCondition queryTopicCondition);

	/**
	 * 推荐的话题
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getRecommend(QueryTopicCondition queryTopicCondition);

	/**
	 * 最新的话题点更多分页查询
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult getNewTopicList(QueryTopicCondition queryTopicCondition);

	/**
	 * 根据用户ID返我回应的话题更多分页
	 * 
	 * @param queryProblemCondition
	 * @return
	 */
	public PageResult getReTopicList(QueryTopicCondition queryTopicCondition);

	/**
	 * 推荐的话题更多页
	 * 
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult getRecommendList(QueryTopicCondition queryTopicCondition);

	/**
	 * 根据ID获取TOPIC
	 * 
	 * @return
	 */
	public Topic getTopicById(int id);

	/**
	 * 后台查询，不参与特殊字符过滤，原文展示
	 * @param id
	 * @return
	 */
	public Topic getBackTopicById(int id);

	/**
	 * 删除话题
	 * 
	 * @param id
	 * @return
	 */
	public int delTopicById(int id);

	/**
	 * 话题修改
	 * 
	 * @param topic
	 */
	public void updateTopic(Topic topic);

	/** ***V1.1方法(method)声明区域 结束**** */
}
