package com.shangde.edu.test.service;

import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.test.domain.Subject;


/**
 * Subject对象操作实现类
 * @author guoqiang.liu
 */
public class SubjectImpl extends BaseService implements ISubject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addSubject(Subject subject) {
		subject.setUpdateTime(new Date());
		return simpleDao.createEntity("testSubject_NS.createSubject", subject);
	}

	public void delSubjectById(int subjectId) {
		simpleDao.deleteEntity("testSubject_NS.deleteSubjectById", subjectId);
	}

	public void updateSubject(Subject subject) {
		simpleDao.updateEntity("testSubject_NS.updateSubject", subject);
	}

	public Subject getSubjectById(int subjectId) {
		return simpleDao.getEntity("testSubject_NS.getSubjectById", subjectId);
	}


	/**
	 * @author 代长福
	 * 考试后台有用到
	 */
	public List<Subject> getSubjectListByUserId(int userId) {
		return simpleDao
				.getForList("testSubject_NS.getSubjectListByUserId", userId);
	}
	
	
    /**
     * @author 代长福 
     * 考试后台用到
     */
	public List<Subject> getSubjectListByStatus(int status) {							
		return simpleDao
				.getForList("testSubject_NS.getSubjectListByStatus", status);
	}

	public List<Subject> getAllSubject() {
		return simpleDao.getForList("testSubject_NS.getAllSubject",null);
	}

	public List<Subject> getSubjectListForUnAss(int cusId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("testSubject_NS.getSubjectListForUnAss", cusId);
	}

	public List<Subject> getUnBuySubject(List<Integer> sids) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("testSubject_NS.getUnBuySubject", sids);
	}


		 
}
