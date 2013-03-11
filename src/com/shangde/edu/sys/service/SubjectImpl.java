package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;

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
		return simpleDao.createEntity("Subject_NS.createSubject", subject);
	}

	public void delSubjectById(int subjectId) {
		simpleDao.deleteEntity("Subject_NS.deleteSubjectById", subjectId);
	}

	public void updateSubject(Subject subject) {
		simpleDao.updateEntity("Subject_NS.updateSubject", subject);
	}

	public Subject getSubjectById(int subjectId) {
		return simpleDao.getEntity("Subject_NS.getSubjectById", subjectId);
	}

	public List<Subject> getSubjectList(
			QuerySubjectCondition querySubjectCondition) {
		return simpleDao.getForList("Subject_NS.getSubjectList",
				querySubjectCondition);
	}

	/**
	 * @author 代长福
	 * 考试后台有用到
	 */
	public List<Subject> getSubjectListByUserId(int userId) {
		return simpleDao
				.getForList("Subject_NS.getSubjectListByUserId", userId);
	}
	
	
    /**
     * @author 代长福 
     * 考试后台用到
     */
	public List<Subject> getSubjectListByStatus(int status) {							
		return simpleDao
				.getForList("Subject_NS.getSubjectListByStatus", status);
	}

	public List<Subject> getAllSubject() {
		return simpleDao.getForList("Subject_NS.getAllSubject",null);
	}

	public List<Subject> getSubjectListForUnAss(int cusId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Subject_NS.getSubjectListForUnAss", cusId);
	}

	public List<Subject> getUnBuySubject(List<Integer> sids) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Subject_NS.getUnBuySubject", sids);
	}

	public List<Teacher> getAllTeacherject() {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Teacher_NS.getAllTeacher", null);
	}
		 
}
