package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySubjectConditionAdv;
import com.shangde.edu.sys.domain.Subject;

public class SubjectAdvImpl extends BaseService implements ISubjectAdv {

	public List<Subject> getSubjectList() {
		return simpleDao.getForList("Subject_NS.getAllSubject", null);
	}

	public void updateSubject(Subject subject) {
		simpleDao.updateEntity("Subject_NS.updateSubject2", subject);
	}

	public void deleteSubject(int subjectId) {
		simpleDao.deleteEntity("Subject_NS.deleteSubjectById", subjectId);
	}

	public void addSubject(Subject subject) {
		simpleDao.createEntity("Subject_NS.createSubject", subject);
	}

	public Subject getSubjectById(int subjectId) {
		return simpleDao.getEntity("Subject_NS.getSubjectById", subjectId);
	}
	
	public PageResult querySubjectListByCondition(QuerySubjectConditionAdv querySubjectConditionAdv) {
		return simpleDao.getPageResult("Subject_NS.querySubjectListByCondition", "Subject_NS.querySubjectCountByCondition", querySubjectConditionAdv);
	}
}
