package com.shangde.edu.feed.action;

import java.util.Date;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.condition.QueryTemplateCondition;
import com.shangde.edu.feed.domain.Template;
import com.shangde.edu.feed.service.ITemplate;

/**
 * 
 * 模板action
 * 
 * @author Libg
 * 
 */
public class TemplateAction extends CommonAction {
	private static Logger logger = LoggerFactory.getLogger(TemplateAction.class);
	/** 服务接口 */
	private ITemplate feedTempageService;
	/** domain接口 */
	private Template template;
	/** 常量 */
	private int id;
	private String videoIds;// 视频id组合“id,id,id”
	private List videoList;// 视频集合
	private List<Template> templateList;//所有模板列表
	private String jsonResult;// json返回值
	private String msg;//修改成功或失败(信息)页面
	/** 查询domain接口 */
	private QueryTemplateCondition 	queryTemplateCondition;
	
	
	/**
	 * 查询所有模板列表
	 * tempList
	 * @author  longjl
	 * @return
	 */
	public String templateList(){
		try {
			int pageSize = 25;
			this.getQueryTemplateCondition().setPageSize(pageSize);
			setPage(feedTempageService.getTemplatePageList(queryTemplateCondition));			
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "template_list";//返回集合页面
	}

	/**
	 * 添加模板
	 * @author longjl
	 * @return
	 */
	public String templateAdd(){
		try {
			if(template != null){
				String fileName = System.currentTimeMillis()+"";//系统时间作为唯一标识
				template.setFileName(fileName);
				template.setModified(new Date());
				template.setPubdate(new Date());
				int count = this.feedTempageService.addTemplate(template);
				if(count >0){
					msg="添加模板成功！";
				}else{
					msg="添加模板失败！";
				}
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "msg";
	}
	
	
	/**
	 * 删除模板
	 * @author longjl
	 * @return
	 */
	public String delTemplate(){
		try {
			int count =	this.feedTempageService.delTemplateById(id);
			if(count>0){
				jsonResult = "1";
			}else{
				jsonResult = "0";
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "jsonResult";
	}
	
	/**
	 * 根据编号查询对象
	 * @author longjl
	 * @return
	 */
	public String editTemplate(){
		try {
			template = this.feedTempageService.getTemplateById(getId());
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "template_edit";
	}
	
	/**
	 * 编辑模板
	 * @author longjl
	 * @return
	 */
	public String doEditTemplate(){
		try {
			template.setModified(new Date());
			int count = this.feedTempageService.updateTemplate(template);
			if(count >0){
				msg="编辑模板成功！";
			}else{
				msg="编辑模板失败！";
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "msg";
	}
	
	/**
	 * 预览模板
	 * @author longjl
	 * @return
	 */
	public String previewTemplate(){
			try {
				template = this.feedTempageService.getTemplateById(getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "template_preview";
	}
	
	public ITemplate getFeedTempageService() {
		return feedTempageService;
	}

	public void setFeedTempageService(ITemplate feedTempageService) {
		this.feedTempageService = feedTempageService;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

	public List getVideoList() {
		return videoList;
	}

	public void setVideoList(List videoList) {
		this.videoList = videoList;
	}
	
	public QueryTemplateCondition getQueryTemplateCondition() {
		return queryTemplateCondition;
	}
	
	public void setQueryTemplateCondition(
			QueryTemplateCondition queryTemplateCondition) {
		this.queryTemplateCondition = queryTemplateCondition;
	}
	
	public void setTemplateList(List<Template> templateList) {
		this.templateList = templateList;
	}
	
	public String getJsonResult() {
		return jsonResult;
	}
	
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
