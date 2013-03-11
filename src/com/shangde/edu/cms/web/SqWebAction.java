package com.shangde.edu.cms.web;

import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.condition.QuerySurveyQstCondition;
import com.shangde.edu.cms.domain.SurveyQst;
import com.shangde.edu.cms.service.ISurveyQst;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class SqWebAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISurveyQst surveyQstService;
	
	private SurveyQst surveyQst = new SurveyQst();
	
	private int[] sqTypes;
	
	private String[] sqContents;
	
	private String[] sqOptions;
	
	private QuerySurveyQstCondition querySurveyQstCondition;
	
	private List<SurveyQst> surveyQstList;
	
	private int[] ids;
	
	/**
	 * 初始化问卷调查
	 * @return
	 */
	public String initSurvey() {
		surveyQstList = surveyQstService.getSurveyQstList(getQuerySurveyQstCondition());
		return "initSurvey";
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
