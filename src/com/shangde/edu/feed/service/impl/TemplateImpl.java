package com.shangde.edu.feed.service.impl;

import java.io.Serializable;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTemplateCondition;
import com.shangde.edu.feed.domain.Template;
import com.shangde.edu.feed.service.ITemplate;

/**
 * 模板接口实现
 * 
 */
@SuppressWarnings("unchecked")
public class TemplateImpl extends BaseService implements ITemplate,
		Serializable {

	/**
	 * @see com.shangde.edu.feed.service.ITemplate#addTemplate(Template)
	 */
	public Integer addTemplate(Template template) {
		return simpleDao.createEntity("Feed_Template_NS.createTemplate",
				template);
	}

	/**
	 * @see com.shangde.edu.feed.service.ITemplate#delTemplateById(int)
	 */
	public Integer delTemplateById(int id) {
		return simpleDao.delete("Feed_Template_NS.deleteTemplateById", id);
	}

	/**
	 * @see com.shangde.edu.feed.service.ITemplate#updateTemplate(Template)
	 */
	public Integer updateTemplate(Template template) {
		return simpleDao.update("Feed_Template_NS.updateTemplate", template);
	}

	/**
	 * @see com.shangde.edu.feed.service.ITemplate#getTemplateById(int)
	 */
	public Template getTemplateById(int id) {
		return simpleDao.getEntity("Feed_Template_NS.getTemplateById", id);
	}

	/**
	 * @see com.shangde.edu.feed.service.ITemplate#getTemplateList()
	 */
	public List<Template> getTemplateList() {
		return simpleDao.getForList("Feed_Template_NS.getTemplateList", null);
	}
	
	/**
	 * 分页查询
	 */
	public PageResult getTemplatePageList(QueryTemplateCondition queryTemplateCondition) {
		return  simpleDao.getPageResult("Feed_Template_NS.getTemplatePageList", "Feed_Template_NS.getTemplateListPageCount", queryTemplateCondition);
	}
}
