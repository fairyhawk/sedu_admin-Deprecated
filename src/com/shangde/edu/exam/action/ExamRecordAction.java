package com.shangde.edu.exam.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class ExamRecordAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ExamRecordAction.class);
	/**
	 * 变量
	 */
	
	/**
	 * 考试历史表的查询条件
	 */
	private QueryExampaperRecordCondition queryExampaperRecordCondition;
	
	/**
	 * 考试历史表服务 
	 */
	private IExampaperRecord exampaperRecordService;
	
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 *  方法
	 */
	/**
	 * 成绩查询方法
	 */
	public String ExamRecordAll(){
		
		try {
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			if(queryExampaperRecordCondition.getTiaojian()==5){
				String key=queryExampaperRecordCondition.getSearchKey();
				if(key!=""){
				String []aa=queryExampaperRecordCondition.getSearchKey().split("%");
				queryExampaperRecordCondition.setSearchKey(URLDecoder.decode(aa[0].toString().trim(), "UTF-8"));
				}
			}else
			{
				String sek=queryExampaperRecordCondition.getSearchKey();
				if(sek!=null){
				queryExampaperRecordCondition.setSearchKey(URLDecoder.decode(queryExampaperRecordCondition.getSearchKey().trim(), "UTF-8"));
				}
				
			}
			
			this.getQueryExampaperRecordCondition().setPageSize(25);
			setPage(exampaperRecordService.GetExampaperRecordAll(this.getQueryExampaperRecordCondition()));
			this.getPage().setPageSize(25);
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("ExamRecordAction.ExamRecordAll",e);
			return ERROR;
		}
		
		return "ExamRecordAll";
	}
	
	public QueryExampaperRecordCondition getQueryExampaperRecordCondition() {
		if(queryExampaperRecordCondition==null){
			queryExampaperRecordCondition=new QueryExampaperRecordCondition();
		}
		return queryExampaperRecordCondition;
	}

	public void setQueryExampaperRecordCondition(
			QueryExampaperRecordCondition queryExampaperRecordCondition) {
		this.queryExampaperRecordCondition = queryExampaperRecordCondition;
	}


	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}


	public void setExampaperRecordService(IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
}

