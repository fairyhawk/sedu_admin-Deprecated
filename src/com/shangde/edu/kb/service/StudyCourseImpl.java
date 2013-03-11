package com.shangde.edu.kb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.kb.condition.QueryStudyCourseCondition;
import com.shangde.edu.kb.domain.StudyCourse;

/**
 * StudyCourse操作对象实现
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
@SuppressWarnings("unchecked")
public class StudyCourseImpl extends BaseService implements IStudyCourse{
    public java.lang.Integer addStudyCourse(StudyCourse studyCourse) {
return simpleDao.createEntity("StudyCourse_NS.createStudyCourse",studyCourse);
    }

    public void delStudyCourseById(int cId){
        simpleDao.deleteEntity("StudyCourse_NS.deleteStudyCourseById",cId);
    }

    public void updateStudyCourse(StudyCourse studyCourse) {
        simpleDao.updateEntity("StudyCourse_NS.updateStudyCourse",studyCourse);
    }

    public StudyCourse getStudyCourseById(int cId) {
        return simpleDao.getEntity("StudyCourse_NS.getStudyCourseById",cId);
    }

    public List<StudyCourse> getStudyCourseList(QueryStudyCourseCondition queryStudyCourseCondition) {
        return simpleDao.getForList("StudyCourse_NS.getStudyCourseList",queryStudyCourseCondition);
    }

	public List<StudyCourse> getStudyCourseByList(QueryStudyCourseCondition queryStudyCourseCondition) {
		List<StudyCourse> result = new ArrayList();
		StudyCourse sc = new StudyCourse();
		sc.setCId(0);
		sc.setCName("请选择");
		result.add(sc);
        Collection<StudyCourse> res= simpleDao.getForList("StudyCourse_NS.getStudyCourseByList",queryStudyCourseCondition);
		result.addAll(res);
		return result;
	}


	public StudyCourse getStudyCourseLast(int pId) {
		 return simpleDao.getEntity("StudyCourse_NS.getStudyCourseLast",pId);
	}
    
}
