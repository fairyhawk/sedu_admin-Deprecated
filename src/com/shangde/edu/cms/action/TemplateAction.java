package com.shangde.edu.cms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.This;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.condition.QueryTmpHistory;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.domain.TemplateHistory;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cms.service.ITmpHistory;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class TemplateAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板服务对象
	 */
	private ITemplate templateService;
	
	private ITmpHistory tmpHistoryService;
	
	private TemplateHistory templateHistory = new TemplateHistory();
	
	private QueryTmpHistory queryTmpHistory;
	
	/**
	 * 模板实体
	 */
	private Template template = new Template();
	
	/**
	 * 模板查询条件
	 */
	private QueryTemplateCondition queryTemplateCondition;
	
	/**
	 * 模板list
	 */
	private List<Template> templateList = new ArrayList<Template>();

	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 添加模板
	 * @return String
	 * @thorows Exception
	 */
	public String addTemplate(){
		try {
			templateService.addTemplate(template);
			//添加模板记录
			templateHistory.setTmpId(template.getTmpId());
			templateHistory.setTmpContent(template.getTmpContent());
			templateHistory.setTmpName(template.getTmpName());
			templateHistory.setTmpType(template.getTmpType());
			templateHistory.setUserId(this.getLoginedUser().getUserId());
			templateHistory.setTmpTime(new Date());
			tmpHistoryService.add(templateHistory);
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 修改模板
	 * @return String
	 * @thorows Exception
	 */
	public String updateTemplate(){
		try {
			templateService.updateTemplate(template);
			//添加模板记录
			templateHistory.setTmpId(template.getTmpId());
			templateHistory.setTmpContent(template.getTmpContent());
			templateHistory.setTmpName(template.getTmpName());
			templateHistory.setTmpType(template.getTmpType());
			templateHistory.setUserId(this.getLoginedUser().getUserId());
			templateHistory.setTmpTime(new Date());
			tmpHistoryService.add(templateHistory);
			
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 删除模板
	 * @return String
	 * @thorows Exception
	 */
	public String deleteTemplate(){
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					templateService.delTemplateById(ids[i]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showTemplateList() {
		try {
			//根据模板名称，模板类型，模板内容查询模板
			String tmpName = getQueryTemplateCondition().getTmpName();
			String tmpType = getQueryTemplateCondition().getTmpType();
			String tmpContent = getQueryTemplateCondition().getTmpContent();
			if(tmpName != null) {
				getQueryTemplateCondition().setTmpName(tmpName.trim());
			}
			if(tmpType!=null) {
				getQueryTemplateCondition().setTmpType(tmpType.trim());
			}
			if(tmpContent!=null) {
				getQueryTemplateCondition().setTmpContent(tmpContent.trim());
			}
			setPage(templateService.getTemplateListByCondition(getQueryTemplateCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "list";
	}

	/**
	 * 初始化添加页面
	 * @return String
	 * @thorows Exception
	 */
	public String initAddTemplate() {
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateTemplate() {
		try {
			template = templateService.getTemplateById(template.getTmpId());
			
			//获取本模板的模板记录
			this.getQueryTmpHistory().setTmpId(template.getTmpId());
			setPage(tmpHistoryService.getByTmpIdToUpdate(queryTmpHistory));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "updatePage";
	}
	
	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public QueryTemplateCondition getQueryTemplateCondition() {
		if(queryTemplateCondition == null) {
			queryTemplateCondition = new QueryTemplateCondition();
		}
		return queryTemplateCondition;
	}

	public void setQueryTemplateCondition(
			QueryTemplateCondition queryTemplateCondition) {
		this.queryTemplateCondition = queryTemplateCondition;
	}

	public List<Template> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<Template> templateList) {
		this.templateList = templateList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setTmpHistoryService(ITmpHistory tmpHistoryService) {
		this.tmpHistoryService = tmpHistoryService;
	}

	public TemplateHistory getTemplateHistory() {
		return templateHistory;
	}

	public void setTemplateHistory(TemplateHistory templateHistory) {
		this.templateHistory = templateHistory;
	}

	public QueryTmpHistory getQueryTmpHistory() {
		if(queryTmpHistory==null){
			queryTmpHistory = new QueryTmpHistory();
		}
		return queryTmpHistory;
	}

	public void setQueryTmpHistory(QueryTmpHistory queryTmpHistory) {
		this.queryTmpHistory = queryTmpHistory;
	}
	
}
