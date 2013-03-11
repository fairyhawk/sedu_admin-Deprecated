package com.shangde.edu.cms.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryTmpHistory;
import com.shangde.edu.cms.domain.TemplateHistory;

public class TmpHistoryImpl extends BaseService implements ITmpHistory {

	public Integer add(TemplateHistory templateHistory) {
		return simpleDao
				.createEntity("TemplateHistory_NS.add", templateHistory);
	}

	public PageResult getAll(QueryTmpHistory queryTmpHistory) {
		return simpleDao.getPageResult("TemplateHistory_NS.getAll",
				"TemplateHistory_NS.getAllCount", queryTmpHistory);
	}

	public TemplateHistory getById(int id) {
		return simpleDao.getEntity("TemplateHistory_NS.getById", id);
	}

	
	public PageResult getByTmpId(QueryTmpHistory queryTmpHistory) {
		return simpleDao.getPageResult("TemplateHistory_NS.getByTmpId",
				"TemplateHistory_NS.getByTmpId", queryTmpHistory);
	}

	public PageResult search(QueryTmpHistory queryTmpHistory) {
		return simpleDao.getPageResult("TemplateHistory_NS.search",
				"TemplateHistory_NS.searchCount", queryTmpHistory);
	}
	public int delete(String ids) {
		return simpleDao.delete("TemplateHistory_NS.delete", ids);
	}
	
	public List<TemplateHistory> getBysds(String ids) {
		return simpleDao.getForList("TemplateHistory_NS.getBysds", ids);
	}
	public PageResult getByTmpIdToUpdate(QueryTmpHistory queryTmpHistory) {
		return simpleDao.getPageResult("TemplateHistory_NS.getByTmpIdToUpdate",
				"TemplateHistory_NS.getByTmpIdToUpdateCount", queryTmpHistory);
	}
}
