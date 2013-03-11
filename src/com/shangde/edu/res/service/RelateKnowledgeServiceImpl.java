package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.res.domain.RelateKnowledge;

public class RelateKnowledgeServiceImpl extends BaseService implements
		RelateKnowledgeService {

	/**
	 * 插入知识点
	 */
	public void addRelateKnowledge(RelateKnowledge relateKnowledge) {
		simpleDao.createEntity("RelateKnowledge_NS.createRelateKnowledge", relateKnowledge);
		
	}

	/**
	 * 通过视频ID查询与其关联的知识点
	 */
	public List<RelateKnowledge> getRelateKnowledgeList(int voId) {
		
		return simpleDao.getForList("RelateKnowledge_NS.getRelateKnowledgeByVoId", voId);
	}

	/**
	 * 通过视频ID删除与视频所关联的知识点
	 */
	public void deleteByVoId(int voId) {
		
		simpleDao.delete("RelateKnowledge_NS.deleteRelateKnowledgeByVoID", voId);
	}
	
}
