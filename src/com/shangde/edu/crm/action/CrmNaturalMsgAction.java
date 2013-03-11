package com.shangde.edu.crm.action;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.crm.condition.QueryUsersCondition;
import com.shangde.edu.crm.service.IUsers;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class CrmNaturalMsgAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUsers usersService;
	private QueryUsersCondition queryUsersCondition;
	/**
	 * 专业集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	/**
	 * 专业服务
	 */
	private ISubject subjectService;
	
	public String toNaturalMsg(){
		try {
			subjectList = subjectService.getAllSubject();
			this.getQueryUsersCondition().setPageSize(20);
			this.setPage(usersService.getAllMsgList(queryUsersCondition));
			this.getPage().setPageSize(20);
			this.setPageUrlParms();
			return "toNaturalMsg";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}

	public IUsers getUsersService() {
		return usersService;
	}

	public void setUsersService(IUsers usersService) {
		this.usersService = usersService;
	}

	public QueryUsersCondition getQueryUsersCondition() {
		if(queryUsersCondition==null){
			queryUsersCondition=new QueryUsersCondition();
		}
		return queryUsersCondition;
	}

	public void setQueryUsersCondition(QueryUsersCondition queryUsersCondition) {
		this.queryUsersCondition = queryUsersCondition;
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
