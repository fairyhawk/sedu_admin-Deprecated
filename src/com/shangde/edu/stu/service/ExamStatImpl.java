package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.ExamStat;
import com.shangde.edu.stu.condition.QueryExamStatCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class ExamStatImpl extends BaseService implements IExamStat {
	
	public Integer addExamStat(ExamStat examStat) {
		return simpleDao.createEntity("ExamStat_NS.createExamStat", examStat);
	}

	public void delExamStatById() {
	}

	public void updateExamStat(ExamStat examStat) {
		simpleDao.updateEntity("ExamStat_NS.updateExamStat", examStat);
	}

	public ExamStat getExamStatById() {
		return null;
	}

	public List<ExamStat> getExamStatList(
			QueryExamStatCondition queryExamStatCondition) {
		return simpleDao.getForList("ExamStat_NS.getExamStatList",
				queryExamStatCondition);
	}
}
