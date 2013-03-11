package com.shangde.edu.cms.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.condition.QueryTmpHistory;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.domain.TemplateHistory;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cms.service.ITmpHistory;

/**
 * 后台模板管理；模板历史记录
 * @author 代长福
 *
 */
public class TmpHistoryAction extends CommonAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板service
	 */
	private ITemplate templateService;
	
	/**
	 * 模板记录service
	 */
	private ITmpHistory tmpHistoryService;

	/**
	 * 模板记录查询条件
	 */
	private QueryTmpHistory queryTmpHistory;
	
	/**
	 * 模板记录实体类
	 */
	private TemplateHistory history;
	
	/**
	 * 模板记录ID
	 */
	private int id;
	
	/**
	 * 模板记录ID拼接的字符串
	 */
	private String ids;
	
	/**
	 * 模板ID
	 */
	private int[] tmpId;

	/**
	 * 模板ID拼接的字符串
	 */
	private String tmpIds;

	/**
	 * 根据时间查询模板记录时的结束时间
	 */
	private String endTime;
	
	/**
	 * ajax请求返回的json数据
	 */
	private int jsonResult;

	public ITmpHistory getTmpHistoryService() {
		return tmpHistoryService;
	}

	public void setTmpHistoryService(ITmpHistory tmpHistoryService) {
		this.tmpHistoryService = tmpHistoryService;
	}
	
	
	public QueryTmpHistory getQueryTmpHistory() {
		return queryTmpHistory;
	}

	public void setQueryTmpHistory(QueryTmpHistory queryTmpHistory) {
		this.queryTmpHistory = queryTmpHistory;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public int getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(int jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	public int[] getTmpId() {
		return tmpId;
	}

	public void setTmpId(int[] tmpId) {
		this.tmpId = tmpId;
	}
	
	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	public String getTmpIds() {
		return tmpIds;
	}

	public void setTmpIds(String tmpIds) {
		this.tmpIds = tmpIds;
	}

	public TemplateHistory getHistory() {
		return history;
	}

	public void setHistory(TemplateHistory history) {
		this.history = history;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取所有模板记录
	 * @return
	 */
	public String getAll(){
		setPage(tmpHistoryService.getAll(queryTmpHistory));
		setPageUrlParms();
		return "tmpHistory_list";
	}
	
	/**
	 * 搜索模板记录
	 * @return
	 */
	public String search(){
		try {
			if(queryTmpHistory.getTmpName()!=null){
				queryTmpHistory.setTmpName(URLDecoder.decode(queryTmpHistory.getTmpName(), "UTF-8"));
			}
			if(endTime!=null){
				queryTmpHistory.setEndTime(endTime+" 23:59:59");
			}
			setPage(tmpHistoryService.search(queryTmpHistory));
			setPageUrlParms();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "tmpHistory_list";
	}
	
	/**
	 * 删除模板记录（可批量）
	 * @return
	 */
	public String delete(){
		try {
			tmpHistoryService.delete(ids);	//删除
			jsonResult = 1;
		} catch (Exception e) {
			jsonResult = 0;
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 还原模板记录（可批量）
	 * @return
	 */
	public String restore(){
		try {
			Template tmp = null;
			boolean flag = true ;
			tmpIds = "";
			List<Template> tmpList = new ArrayList<Template>();
			for (int i = 0; i < tmpId.length; i++) {					//首先判断原模板是否还存在
				tmp = templateService.getTemplateById(tmpId[i]);
				if(tmp == null){
					flag = false;
					tmpIds = tmpIds + tmpId[i] + " ";
				}else{
					tmpList.add(tmp);
				}
			}
			List<TemplateHistory> tmpHistoryList = new ArrayList<TemplateHistory>();
			if(flag){													//如果原模板存在进行还原处理
				tmpHistoryList = tmpHistoryService.getBysds(ids);
			}else{
				return "restore"; // return error 提示;
			}
			String tmpName = null ; 	//模板名称
			String tmpContent = null ;	//模板内容
			String tmpType = null;		//模板类型
			Date tmpTime = null;
			for (Template template : tmpList) {
				for (TemplateHistory templateHistory : tmpHistoryList) {
					if (tmpTime == null
							&& (template.getTmpId() == templateHistory.getTmpId())) {				//第一次直接赋值
						tmpName = templateHistory.getTmpName();
						tmpContent = templateHistory.getTmpContent();
						tmpType = templateHistory.getTmpType();//第二次如果修改时间大于第一次则重新赋值
						tmpTime = templateHistory.getTmpTime();
						
					} else if (tmpTime!=null&&(template.getTmpId() == templateHistory.getTmpId())&&(tmpTime.getTime()) < (templateHistory.getTmpTime().getTime())) {
						tmpName = templateHistory.getTmpName();
						tmpContent = templateHistory.getTmpContent();
						tmpType = templateHistory.getTmpType();
						tmpTime = templateHistory.getTmpTime();
					}
				}
				template.setTmpName(tmpName);
				template.setTmpContent(tmpContent);
				template.setTmpType(tmpType);
				tmpTime = null ;				//重新初始化
			}
			for (Template template : tmpList) {							//循环还原修改
				templateService.updateTemplate(template);
			}
			jsonResult = 1;
		} catch (Exception e) {
			jsonResult = 0;
			e.printStackTrace();
		}
		return "restore";
	}
	
	/**
	 * 根据ID查询模板记录信息
	 * @return
	 */
	public String getById(){
		try {
			history = tmpHistoryService.getById(id);
			jsonResult = 1;
		} catch (Exception e) {
			jsonResult = 0;
			e.printStackTrace();
		}
		return "history" ;
	}
}
