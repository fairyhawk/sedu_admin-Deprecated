package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryTopicCondition;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.dto.DisReTopicDTO;
import com.shangde.edu.dis.dto.TopicDTO;

/**
 * 话题接口实现类
 * 
 * @author Basil
 * 
 */
@SuppressWarnings("unchecked")
public class TopicImpl extends BaseService implements ITopic {

	public int updateTopicStatus(Map map) {
		return simpleDao.update("Topic_NS.batchUpdateTopicStatus", map);
	}
	
	public void updateTopicVoteCount(Map map) {
		simpleDao.updateEntity("Topic_NS.updateTopicVoteCount", map);
	}

	public void updateTopicRecCount(Map map) {

		simpleDao.updateEntity("Topic_NS.updateTopicRecCount", map);
	}

	public void updateTopicReplyCounts(Map map) {
		simpleDao.updateEntity("Topic_NS.updateTopicReplyCounts", map);
	}

	public Integer addTopic(Topic topic) {
		return simpleDao.createEntity("Topic_NS.createTopic", topic);
	}

	public int delTopicById(int id) {
		return simpleDao.delete("Topic_NS.delTopicById", id);
	}
	
	/**
	 * 获取热门话题
	 */
	public List<Topic> getHotTopicByDisId(Integer disId) {

		List<Topic> pageResult = simpleDao.getForList(
				"Topic_NS.getHotTopicByDisId", disId);

		for (int i = 0; pageResult != null && i < pageResult.size(); i++) {
			pageResult.get(i).setTitle(
					StringUtil.chop(pageResult.get(i).getTitle(), 10, "..."));
		}
		return pageResult;
	}
	
	public void updateTopicReplyTime(Map<String, Object> map) {
		simpleDao.updateEntity("Topic_NS.updateTopicReplyTime", map);
	}

	public PageResult getTopicDTOList(QueryTopicCondition queryTopicCondition) {
		
		PageResult pageResult = simpleDao.getPageResult("Topic_NS.getTopicDTOList","Topic_NS.getTopicCountByList", queryTopicCondition);
		
		List<TopicDTO> tDtoList = pageResult.getPageResult();
		for(TopicDTO tDto : tDtoList){
			try{
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(tDto.getContent()));
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(tDto.getContent()));
        	}catch (Exception e) {
        		logger.error("后台-内容标签过滤" + tDto.getId(), e);
			}
		}
		
		return pageResult;
	}

	public PageResult searchTopicList(QueryTopicCondition queryTopicCondition) {
		
        List<TopicDTO> resultList = simpleDao.getForList("Topic_NS.searchTopicList", queryTopicCondition);//获得记录集
        Integer totalRecord = simpleDao.getForList("Topic_NS.searchTopicCountByList", queryTopicCondition).size();//获得总记录数
        
        //内容标签过滤
        for(TopicDTO tDto : resultList){
        	try{
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(tDto.getContent()));
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(tDto.getContent()));
        	}catch (Exception e) {
        		logger.error("后台-内容标签过滤" + tDto.getId(), e);
			}
        }
        
        PageResult pageResult = new PageResult();
        pageResult.setCurrentPage(queryTopicCondition.getCurrentPage());//page中加入当前页
        pageResult.setPageResult(resultList);//加入结果集
        pageResult.setTotalRecord(totalRecord);//加入总记录数
        
        return pageResult;
	}

	public PageResult searchReplyTopicList(
			QueryTopicCondition queryTopicCondition) {
		
        List<TopicDTO> resultList = simpleDao.getForList("Topic_NS.searchReplyTopicList", queryTopicCondition);//获得记录集
        Integer totalRecord = simpleDao.getForList("Topic_NS.searchReplyTopicCountByList", queryTopicCondition).size();//获得总记录数
        //内容标签过滤
        for(TopicDTO tDto : resultList){
        	try{
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(tDto.getContent()));
        		tDto.setContent(com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(tDto.getContent()));
        	}catch (Exception e) {
        		logger.error("后台-内容标签过滤" + tDto.getId(), e);
			}
        }
        
        PageResult pageResult = new PageResult();
        pageResult.setCurrentPage(queryTopicCondition.getCurrentPage());//page中加入当前页
        pageResult.setPageResult(resultList);//加入结果集
        pageResult.setTotalRecord(totalRecord);//加入总记录数
        
        return pageResult;
	}

	
	
	
	/*****V1.1方法(method)声明区域 开始*****/
	
	
	public int getDisTopicCount(int disId) {
		return simpleDao.getEntity("Topic_NS.getDisTopicCount", disId);
	}
	
	/**
	 * 获取最新话题
	 * 
	 * @param QueryTopicCondition查询条件
	 */
	public List<Topic> getNewTopic(QueryTopicCondition queryTopicCondition) {

		List<Topic> pageResult = simpleDao.getForList("Topic_NS.getNewTopic",
				queryTopicCondition);

		/**********************/
		String contentTemp = null;
		int size = pageResult.size();
		for (int i = 0; i < size; i++) {
			contentTemp = pageResult.get(i).getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
			pageResult.get(i).setContent(contentTemp);
		}
		
		return pageResult;
	}
	
	/**
	 * 我发布的话题
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getMyTopic(QueryTopicCondition queryTopicCondition){
		List<Topic> topicList = null;
		try {
			topicList = simpleDao.getForList("Topic_NS.getMyTopic",queryTopicCondition);
			
			/**********************/
			String contentTemp = null;
			for (Topic topic : topicList) {
				contentTemp = topic.getContent();
				contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
				contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
				contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
				topic.setContent(contentTemp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topicList;
	}
	
	/**
	 * 我回复的话题
	 * @param queryTopicCondition
	 * @return
	 */
	public List<DisReTopicDTO> getReTopic(QueryTopicCondition queryTopicCondition){
		List<DisReTopicDTO> disReTopicList = simpleDao.getForList("Topic_NS.getReTopic", queryTopicCondition);
		String contentTemp = null;
		for (DisReTopicDTO drt : disReTopicList) {
			// 去标签 截取内容长度(回复的内容)
			/*	drt.setReTopicContent(StringUtil.chop(StringUtil.filterHTML(drt.getReTopicContent()), 20, "..."));	*/
			contentTemp = drt.getReTopicContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 20, "...");		//截取内容长度
			drt.setReTopicContent(contentTemp);

			// 去标签 截取内容长度(帖子的内容)
			/*	drt.setTopicContent(StringUtil.chop(StringUtil.filterHTML(drt.getTopicContent()), 20, "..."));	*/
			contentTemp = drt.getTopicContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 20, "...");		//截取内容长度
			drt.setTopicContent(contentTemp);
		}
		return disReTopicList;
	}
	/**
	 * 推荐的话题
	 * @param queryTopicCondition
	 * @return
	 */
	public List<Topic> getRecommend(QueryTopicCondition queryTopicCondition){
		List<Topic> topicList = simpleDao.getForList("Topic_NS.getRecommend", queryTopicCondition);
		
		/**********************/
		String contentTemp = null;
		for (Topic topic : topicList) {
			contentTemp = topic.getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
			topic.setContent(contentTemp);
		}
		
		return topicList;
	}
	
	/**
	 * 获取最新话题分页
	 * 
	 * @param QueryTopicCondition查询条件
	 */
	public PageResult getNewTopicList(QueryTopicCondition queryTopicCondition) {
		PageResult pr = simpleDao.getPageResult("Topic_NS.getNewTopicList", "Topic_NS.getNewTopicListCount", queryTopicCondition);
		
		List<Topic> pageResult = pr.getPageResult();
		
		/**********************/
		String contentTemp = null;
		int size = pageResult.size();
		for (int i = 0; pageResult != null && i < size; i++) {
			contentTemp = pageResult.get(i).getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
			pageResult.get(i).setContent(contentTemp);
		}
		
		return pr;
	}
	
	/**
	 * 我发布的话题更多页
	 */
	public PageResult getMyTopicList(QueryTopicCondition queryTopicCondition) {
		PageResult pr = simpleDao.getPageResult("Topic_NS.getMyTopicList", "Topic_NS.getMyTopicListCount",
				queryTopicCondition);
		List<Topic> pageResult = pr.getPageResult();
		
		/**********************/
		String contentTemp = null;
		int size = pageResult.size();
		for (int i = 0; pageResult != null && i < size; i++) {
			contentTemp = pageResult.get(i).getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
			pageResult.get(i).setContent(contentTemp);
		}
		
		return pr;
	}
	
	/**
	 * 根据用户ID返我回应的话题更多分页
	 */
	public PageResult getReTopicList(QueryTopicCondition queryTopicCondition) {
		PageResult pr = simpleDao.getPageResult("Topic_NS.getReTopicList",
				"Topic_NS.getReTopicListCount", queryTopicCondition);
		
		List<DisReTopicDTO> pageResult = pr.getPageResult();
		String contentTemp = null ;
		for (int i = 0; pageResult != null && i < pageResult.size(); i++) {
		/*	pageResult.get(i).setReTopicContent(StringUtil.chop(pageResult.get(i).getReTopicContent(), 20, "...")); 	// 去标签 截取内容长度
			pageResult.get(i).setTopicContent(StringUtil.chop(pageResult.get(i).getTopicContent(), 20, "..."));		// 去标签 截取内容长度	*/
			
			// 去标签 截取内容长度(回复的内容)
			contentTemp = pageResult.get(i).getReTopicContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 20, "...");		//截取内容长度
			pageResult.get(i).setReTopicContent(contentTemp);
			// 去标签 截取内容长度(话题的内容)
			contentTemp = pageResult.get(i).getTopicContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 20, "...");		//截取内容长度
			pageResult.get(i).setTopicContent(contentTemp);
			
		}
		return pr;
	}
	
	/**
	 * 推荐的话题更多页
	 * @param queryTopicCondition
	 * @return
	 */
	public PageResult getRecommendList(QueryTopicCondition queryTopicCondition) {
		PageResult pr = simpleDao.getPageResult("Topic_NS.getRecommendList",
				"Topic_NS.getRecommendListCount", queryTopicCondition);
		
		List<Topic> pageResult = pr.getPageResult();
		
		/**********************/
		String contentTemp = null;
		int size = pageResult.size();
		for (int i = 0; pageResult != null && i < size; i++) {
			contentTemp = pageResult.get(i).getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			contentTemp = StringUtil.chop(contentTemp, 200, "...");		//截取内容长度
			pageResult.get(i).setContent(contentTemp);
		}
		
		return pr;
	}
	
	/**
	 * 根据ID获取TOPIC
	 * 
	 */
	public Topic getTopicById(int id) {
		
		String contentTemp = null;
		Topic topic = null;
		
		topic = simpleDao.getEntity("Topic_NS.getTopicById", id);
		try{
			if(topic != null){
				contentTemp = topic.getContent();	
				contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
				contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
				topic.setContent(contentTemp);
			}
		}catch (Exception e) {
			logger.error("查看话题ID出错，或者内容过滤出错", e);
		}
		
		return topic;
	}
	
	
	
	public Topic getBackTopicById(int id) {
		return simpleDao.getEntity("Topic_NS.getTopicById", id);
	}

	/**
	 * 删除话题
	 */
	public int delTopic(Map map) {
		return simpleDao.delete("Topic_NS.delTopic", map);
	}
	
	/**
	 * 修改话题点击次数
	 */
	public void updateTopicClickCounts(Map map) {
		simpleDao.updateEntity("Topic_NS.updateTopicClickCounts", map);
	}

	/**
	 * 话题修改
	 */
	public void updateTopic(Topic topic) {
		simpleDao.updateEntity("Topic_NS.updateTopic", topic);
	}
	
	/*****V1.1方法(method)声明区域 结束*****/
}
