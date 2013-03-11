package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.domain.Grade;

/**
 * Grade对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class GradeImpl extends BaseService implements IGrade {
	public java.lang.Integer addGrade(Grade grade) {
		return simpleDao.createEntity("Grade_NS.createGrade", grade);
	}

	public void delGradeById(int gradeId) {
		simpleDao.deleteEntity("Grade_NS.deleteGradeById", gradeId);
	}

	public void updateGrade(Grade grade) {
		simpleDao.updateEntity("Grade_NS.updateGrade", grade);
	}

	public Grade getGradeById(int gradeId) {
		return simpleDao.getEntity("Grade_NS.getGradeById", gradeId);
	}

	public List<Grade> getGradeList(QueryGradeCondition queryGradeCondition) {
		return simpleDao.getForList("Grade_NS.getGradeList",
				queryGradeCondition);
	}

	public List<Grade> getGradeListByUserId(int userId) {
		return simpleDao.getForList("Grade_NS.getGradeListByUserId", userId);
	}
	
	
	public List<Grade> getGradeListByStatus(int status) {
		return simpleDao.getForList("Grade_NS.getGradeListByStatus", status);
	}
}
