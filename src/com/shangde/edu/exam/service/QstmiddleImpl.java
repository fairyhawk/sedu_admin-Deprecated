package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.domain.Qstmiddle;

/**
 * Qstmiddle 方法实现 User: 何海强 Date: 2011-05-19
 */
public class QstmiddleImpl extends BaseService implements IQstmiddle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addQstmiddle(Qstmiddle qstmiddle) {
		return simpleDao
				.createEntity("Qstmiddle_NS.createQstmiddle", qstmiddle);
	}

	public void delQstmiddleById(int eqId) {
		simpleDao.deleteEntity("Qstmiddle_NS.deleteQstmiddleById", eqId);
	}

	public List<Qstmiddle> getQstmiddleList(
			QueryQstmiddleCondition queryQstmiddleCondition) {
		return simpleDao.getForList("Qstmiddle_NS.getQstmiddleList",
				queryQstmiddleCondition);
	}
	
	/**
	 * 跳转到试题添加页面时，查询试题信息
	 * @return
	 */
	public List<Qstmiddle> getQstmiddleToAddQstList(
			QueryQstmiddleCondition queryQstmiddleCondition) {
		return simpleDao.getForList("Qstmiddle_NS.getQstmiddleToAddQstList",
				queryQstmiddleCondition);
	}

	public List<Qstmiddle> getQstbyQstid(int qstid) {

		return simpleDao.getForList("Qstmiddle_NS.getQstbyQstid", qstid);
	}
	
	@Override
	public List<Qstmiddle> getQstmiddleById(List<Integer> idList) {
		return simpleDao.getForList("Qstmiddle_NS.getQstmiddleById", idList);
	}
}
