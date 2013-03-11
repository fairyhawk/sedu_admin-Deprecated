package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.StudyPlan;
import com.shangde.edu.cus.condition.QueryStudyPlanCondition;
import com.shangde.common.service.BaseService;

/**
 * StudyPlan服务类
 * User: guoqiang.liu
 * Date: 2010-08-24
 */
@SuppressWarnings("unchecked")
public class StudyPlanImpl extends BaseService implements IStudyPlan{
    public java.lang.Integer addStudyPlan(StudyPlan studyPlan) {
return simpleDao.createEntity("StudyPlan_NS.createStudyPlan",studyPlan);
    }

    public void delStudyPlanById(int id){
        simpleDao.deleteEntity("StudyPlan_NS.deleteStudyPlanById",id);
    }

    public void updateStudyPlan(StudyPlan studyPlan) {
        simpleDao.updateEntity("StudyPlan_NS.updateStudyPlan",studyPlan);
    }

    public StudyPlan getStudyPlanById(int id) {
        return simpleDao.getEntity("StudyPlan_NS.getStudyPlanById",id);
    }

    public List<StudyPlan> getStudyPlanList(QueryStudyPlanCondition queryStudyPlanCondition) {
        return simpleDao.getForList("StudyPlan_NS.getStudyPlanList",queryStudyPlanCondition);
    }

	public List<Integer> getMonthStudyPlan(
			QueryStudyPlanCondition queryStudyPlanCondition) {
		return simpleDao.getForList("StudyPlan_NS.getMonthStudyPlan",queryStudyPlanCondition);
	}

	public StudyPlan getStudyPlanByDate(
			QueryStudyPlanCondition queryStudyPlanCondition) {
		return simpleDao.getEntity("StudyPlan_NS.getStudyPlanByDate",queryStudyPlanCondition);
	}

	public void delStudyPlanByCusId(int cusId) {
		 simpleDao.deleteEntity("StudyPlan_NS.deleteStudyPlanByCusId",cusId);
	}
	
	 public Integer delBathStudyPlanByCusIds(String ids){
		 return simpleDao.delete("StudyPlan_NS.deleteStudyPlanByCusIds", ids);
	 }
	
}
