package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.res.domain.VedioKnowledge;

public class VedioKnowledgeServiceImpl extends BaseService implements VedioKnowledgeService {
	/**
	 * 插入视频知识点
	 */
	public void addKnowledgePoint(VedioKnowledge vedioKnowledge) {
		simpleDao.createEntity("VedioKnowledge_NS.createVedioKnowledge",vedioKnowledge);
	}

	/**
	 * 通过void查询视频知识点列表
	 */
	public List<VedioKnowledge> getVedioKnowledgeList(int voId) {
		return simpleDao.getForList("VedioKnowledge_NS.getVedioByVoId", voId);
	}

	/**
	 * 根据voId删除视频知识点
	 */
	public void deleteByVoId(int voId) {
		simpleDao.delete("VedioKnowledge_NS.deleteVedioKnowledgeByVoID", voId);
		
	}

}
