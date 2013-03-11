package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.edu.res.domain.VedioKnowledge;

public interface VedioKnowledgeService {
	/**
	 * 插入视频知识点
	 * @param vedioKnowledge
	 */
	public void addKnowledgePoint(VedioKnowledge vedioKnowledge);
	/**
	 * 通过void查询视频知识点列表
	 * @param voId
	 * @return
	 */
	public List<VedioKnowledge> getVedioKnowledgeList(int voId);
	/**
	 * 根据voId删除视频知识点
	 * @param voId
	 */
	public void deleteByVoId(int voId);
}
