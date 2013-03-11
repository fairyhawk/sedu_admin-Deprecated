package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.condition.QueryTopicReplyCondition;
import com.shangde.edu.dis.domain.TopicReply;

@SuppressWarnings("unchecked")
public class TopicReplyImpl extends BaseService implements ITopicReply {

	public int getCount() {
		return simpleDao.getEntity("TopicReply_NS.getCount", null);
	}

	public PageResult getFrontPageTopicReplyList(QueryTopicReplyCondition queryTopicReplyCondition) {
		
		PageResult pr = simpleDao.getPageResult(
				"TopicReply_NS.getFrontTopicReplyList",
				"TopicReply_NS.getFrontTopicReplyCountByList",
				queryTopicReplyCondition);
		
		/**********************/
		List<TopicReply> pageResult = pr.getPageResult();
		String contentTemp = null;
		for (int i = 0; i < pageResult.size(); i++) {
			contentTemp = pageResult.get(i).getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			pageResult.get(i).setContent(contentTemp);
		}
		return pr;
	}

	public Integer addTopicReply(TopicReply topicReply) {
		return simpleDao.createEntity("TopicReply_NS.createTopicReply",
				topicReply);
	}

	public int delTopicReplyById(int id) {
		return simpleDao.delete("TopicReply_NS.delTopicReplyById", id);
	}

	public PageResult getPageTopicReplyList(
			QueryTopicReplyCondition QueryTopicReplyCondition) {
		return simpleDao.getPageResult("TopicReply_NS.getTopicReplyList",
				"TopicReply_NS.getTopicReplyCountByList",
				QueryTopicReplyCondition);

	}

	
	
	/*****V1.1方法(method)声明区域 开始*****/
	
	/**
	 * 回复列表
	 */
	public List<TopicReply> getReplyCountList(int topicId) {

		List<TopicReply> topicReplyList = simpleDao.getForList("TopicReply_NS.getReplyCountList", topicId);
		
		String contentTemp = null;
		for (TopicReply reply : topicReplyList) {
			contentTemp = reply.getContent();
			contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
			contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
			reply.setContent(contentTemp);
		}
		return topicReplyList;
	}
	
	/**
	 * 根据id取得对象
	 */
	public TopicReply getTopicReplyById(int id) {
		
		TopicReply topicReply = simpleDao.getEntity("TopicReply_NS.getTopicReplyById", id);
		
		String contentTemp = topicReply.getContent();
		contentTemp = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(contentTemp);//去掉所有"<*>"符号
		contentTemp = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(contentTemp);//替换特殊字符
		topicReply.setContent(contentTemp);
		
		return topicReply;
	}
	
	/**
	 * 删除一条话题回复
	 * @param map
	 * @return
	 */
	public int delTopicReply(Map map) {
		return simpleDao.delete("TopicReply_NS.delTopicReply", map);
	}
	
	/*****V1.1方法(method)声明区域 结束*****/

}
