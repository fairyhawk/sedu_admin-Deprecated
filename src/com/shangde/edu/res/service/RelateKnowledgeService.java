package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.edu.res.domain.RelateKnowledge;

public interface RelateKnowledgeService {
	/**
	 * 插入知识点信息
	 * @param relateKnowledge
	 */
	public void addRelateKnowledge(RelateKnowledge relateKnowledge);
	/**
	 * 通过视频ID查询与其关联的知识点
	 * @param voId
	 * @return
	 */
	public List<RelateKnowledge> getRelateKnowledgeList(int voId);
	/**
	 *通过视频ID删除与视频所关联的知识点
	 * @param voId
	 */
	public void deleteByVoId(int voId);
}
