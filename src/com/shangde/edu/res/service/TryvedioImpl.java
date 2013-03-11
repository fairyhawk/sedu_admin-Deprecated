package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.condition.QueryTryvedioCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class TryvedioImpl extends BaseService implements ITryvedio {
	public java.lang.Integer addTryvedio(Tryvedio tryvedio) {
		return simpleDao.createEntity("Tryvedio_NS.createTryvedio", tryvedio);
	}

	public void delTryvedioById(int id) {
		simpleDao.deleteEntity("Tryvedio_NS.deleteTryvedioById", id);
	}

	public void updateTryvedio(Tryvedio tryvedio) {
		simpleDao.updateEntity("Tryvedio_NS.updateTryvedio", tryvedio);
	}

	public Tryvedio getTryvedioById(int id) {
		return simpleDao.getEntity("Tryvedio_NS.getTryvedioById", id);
	}

	public List<Tryvedio> getTryvedioList(QueryTryvedioCondition queryTryvedioCondition) {
		return simpleDao.getForList("Tryvedio_NS.getTryvedioList",queryTryvedioCondition);
	}
}
