package com.shangde.edu.sys.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySubjectConditionAdv;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubjectAdv;

/**
 * Subject 管理.
 * 
 * @author ZHENG QIANG
 * @version 1.0
 */
public class SubjectAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(SubjectAction.class);

	private ISubjectAdv subjectAdv;
	private List<Subject> subjectList;
	private Subject subject;
	private Integer subjectId;
	private QuerySubjectConditionAdv querySubjectConditionAdv;
	private PageResult page;
	private String pageUrlParms;

	// 删除
	public String deleteSubject() {
		try {
			subjectAdv.deleteSubject(subjectId);
		} catch (Exception e) {
			logger.error("SubjectAction.deleteSubject", e);
			return ERROR;
		}
		return "redirectList";
	}

	// 更新
	public String updateSubject() {
		try {
			subjectAdv.updateSubject(subject);
		} catch (Exception e) {
			logger.error("SubjectAction.updateSubject", e);
			return ERROR;
		}
		return "redirectList";
	}

	// 保存
	public String saveSubject() {
		try {
			Date date = new Date();
			subject.setCreateTime(date); // 默认为当前时间
			subject.setTestTime(date); // 默认为当前时间
			subject.setUpdateTime(date); // 默认为当前时间
			subjectAdv.addSubject(subject);
		} catch (Exception e) {
			logger.error("SubjectAction.saveSubject", e);
			return ERROR;
		}
		return "redirectList";
	}

	// 更新初始化
	public String updateSubjectInit() {
		try {
			subject = subjectAdv.getSubjectById(subjectId);
		} catch (Exception e) {
			logger.error("SubjectAction.updateSubjectInit", e);
			return ERROR;
		}
		return "update";
	}

	// 保存初始化
	public String saveSubjectInit() {
		return "save";
	}

	// 查询
	public String querySubject() {
		try {
			String subjectName = querySubjectConditionAdv.getSubjectName();
			if (!StringUtil.isNullString(subjectName)) {
				subjectName = new String(subjectName.getBytes("ISO-8859-1"), "UTF-8");
				querySubjectConditionAdv.setSubjectName(subjectName);
			}
			page = subjectAdv.querySubjectListByCondition(querySubjectConditionAdv);
			pageUrlParms = this.getUrlParms();
		} catch (Exception e) {
			logger.error("SubjectAction.advQuery", e);
			return ERROR;
		}
		return "list";
	}

	// 重写了父类的getUrlParms方法。
	// 原因：使用get方式提交的parameter为乱码。首先要先转化为正确的UTF-8编码，再进行URLEncode操作。
	public String getUrlParms() {
		StringBuffer sbUrlParms = servletRequest.getRequestURL();
		sbUrlParms.append("?");
		Enumeration parNames = servletRequest.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=").append(
						URLEncoder.encode(new String(servletRequest.getParameter(parName).getBytes("ISO-8859-1"),
								"UTF-8"), "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ERROR;
			}
		}

		return sbUrlParms.toString();
	}

	// 默认执行查询
	public String execute() throws Exception {
		return querySubject();// 调用查询方法
	}

	// getters & setters.

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public QuerySubjectConditionAdv getQuerySubjectConditionAdv() {
		return querySubjectConditionAdv;
	}

	public void setQuerySubjectConditionAdv(
			QuerySubjectConditionAdv querySubjectConditionAdv) {
		this.querySubjectConditionAdv = querySubjectConditionAdv;
	}

	public PageResult getPage() {
		return page;
	}

	public void setPage(PageResult page) {
		this.page = page;
	}

	public String getPageUrlParms() {
		return pageUrlParms;
	}

	public void setPageUrlParms(String pageUrlParms) {
		this.pageUrlParms = pageUrlParms;
	}

	public void setSubjectAdv(ISubjectAdv subjectAdv) {
		this.subjectAdv = subjectAdv;
	}

}
