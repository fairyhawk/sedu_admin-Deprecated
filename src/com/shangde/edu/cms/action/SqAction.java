package com.shangde.edu.cms.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.edu.cms.condition.QuerySurveyQstCondition;
import com.shangde.edu.cms.domain.SurveyQst;
import com.shangde.edu.cms.service.ISurveyQst;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class SqAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SqAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 问卷调查服务类
	 */
	private ISurveyQst surveyQstService;

	/**
	 * 问卷调查类
	 */
	private SurveyQst surveyQst = new SurveyQst();

	/**
	 * 问题类型
	 */
	private int[] sqTypes;
	
	/**
	 * 问题内容
	 */
	private String[] sqContents;
	
	/**
	 * 问题选项
	 */
	private String[] sqOptions;
	
	/**
	 * 查询问题条件
	 */
	private QuerySurveyQstCondition querySurveyQstCondition;
	
	/**
	 * 问题列表
	 */
	private List<SurveyQst> surveyQstList;
	
	/**
	 * id数组
	 */
	private int[] ids;
	
	/**
	 * 分页查询问卷调查
	 * @return
	 */
	public String showSurveyList() {
		try {
			//准备查询条件
			String surveyName = getQuerySurveyQstCondition().getSurveyName();
			if(surveyName != null) {
				getQuerySurveyQstCondition().setSurveyName(surveyName.trim());
			}
			setPage(surveyQstService.getSurveyListByCondition(getQuerySurveyQstCondition()));
			setPageUrlParms();
			return "showSurveyList";
		} catch (Exception e) {
			logger.error("SqAction.showSurveyList", e);
			return ERROR;
		}
	}
	
	/**
	 * 生成问卷调查页
	 * @return
	 */
	public String genericSurvey()  throws Exception{
		try {
			//查询问卷问题列表
			surveyQstList = surveyQstService.getSurveyQstListForTag(querySurveyQstCondition);
			//准备生成路径
			String realPath = getRealPath("/static/web/other") + File.separator + "survey_qst.shtml";
			//将问题列表转化为json字符串
			String jsonString = JsonUtil.getJsonString4JavaCollection(surveyQstList);
			//将传化后的字符串写入文件
			FileUtils.writeFile(realPath, jsonString, true);
			return "changeSuccess";
		} catch (Exception e) {
			logger.error("SqAction.genericSurvey", e);
			return "changeSuccess";
		}
	}
	
	/**
	 * 初始化添加问卷页面
	 * @return
	 */
	public String initAddSurvey(){
		return "initAddSurvey";
	}
	
	/**
	 * 添加问卷调查
	 * @return
	 */
	public String addSurvey(){
		try {
			if(sqTypes != null  &&  sqTypes.length > 0) {
				surveyQst.setCreateTime(new Date());
				for(int i=0; i<sqTypes.length; i++) {
					//将回车换成html标签<br>
					surveyQst.setSqContent(sqContents[i].replaceAll("\r\n", "<br>"));
					surveyQst.setSqOptions(sqOptions[i].replaceAll("\r\n", "<br>"));
					surveyQst.setSqType(sqTypes[i]);
					surveyQst.setSqId(0);
					
					surveyQstService.addSurveyQst(surveyQst);
				}
			}
		} catch (Exception e) {
			logger.error("SqAction.addSurvey", e);
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 *  初始化修改问卷页面
	 * @return
	 */
	public String initUpdateSurvey(){
		try {
			surveyQstList = surveyQstService.getSurveyQstList(querySurveyQstCondition);
			for(int i=0; i<surveyQstList.size(); i++) {
				SurveyQst surveyQst = surveyQstList.get(i);
				//将html标签<br>换回回车字符
				surveyQst.setSqContent(surveyQst.getSqContent().replace("<br>", "\r\n"));
				surveyQst.setSqOptions(surveyQst.getSqOptions().replace("<br>", "\r\n"));
			}
		} catch (Exception e) {
			logger.error("SqAction.initUpdateSurvey", e);
			return ERROR;
		}
		return "initUpdateSurvey";
	}
	
	/**
	 * 修改调查问卷的问题
	 * @return
	 */
	public String updateSurvey(){
		try {
			for(int i=0; i<sqTypes.length; i++) {
				//如果问卷问题类型为3，说明此问题要删除
				if(sqTypes[i] == 3) {
					surveyQstService.delSurveyQstById(ids[i]);
				} else {
					//将回车换成html标签<br>
					surveyQst.setSqContent(sqContents[i].replaceAll("\r\n", "<br>"));
					surveyQst.setSqOptions(sqOptions[i].replaceAll("\r\n", "<br>"));
					surveyQst.setSqType(sqTypes[i]);
					//ids为数据库已有数据，如果i在已有数据范围内，那么就修改，否则就添加问题
					if(ids.length>i) {
						surveyQst.setSqId(ids[i]);
						surveyQstService.updateSurveyQst(surveyQst);
					} else {
						surveyQst.setSqId(0);
						surveyQstService.addSurveyQst(surveyQst);
					}
				}
			}
		} catch (Exception e) {
			logger.error("SqAction.updateSurvey", e);
			return ERROR;
		}
		return "changeSuccess";
	}

	public void setSurveyQstService(ISurveyQst surveyQstService) {
		this.surveyQstService = surveyQstService;
	}

	public SurveyQst getSurveyQst() {
		return surveyQst;
	}

	public void setSurveyQst(SurveyQst surveyQst) {
		this.surveyQst = surveyQst;
	}

	public int[] getSqTypes() {
		return sqTypes;
	}

	public void setSqTypes(int[] sqTypes) {
		this.sqTypes = sqTypes;
	}

	public String[] getSqContents() {
		return sqContents;
	}

	public void setSqContents(String[] sqContents) {
		this.sqContents = sqContents;
	}

	public String[] getSqOptions() {
		return sqOptions;
	}

	public void setSqOptions(String[] sqOptions) {
		this.sqOptions = sqOptions;
	}

	public QuerySurveyQstCondition getQuerySurveyQstCondition() {
		if(querySurveyQstCondition == null) {
			querySurveyQstCondition = new QuerySurveyQstCondition();
		}
		return querySurveyQstCondition;
	}

	public void setQuerySurveyQstCondition(
			QuerySurveyQstCondition querySurveyQstCondition) {
		this.querySurveyQstCondition = querySurveyQstCondition;
	}

	public List<SurveyQst> getSurveyQstList() {
		return surveyQstList;
	}

	public void setSurveyQstList(List<SurveyQst> surveyQstList) {
		this.surveyQstList = surveyQstList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}
}
