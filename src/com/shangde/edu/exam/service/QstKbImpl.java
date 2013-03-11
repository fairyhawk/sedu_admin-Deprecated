package com.shangde.edu.exam.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.exam.domain.QstKb;

public class QstKbImpl extends BaseService implements IQstKb {
	public java.lang.Integer addQstKb(QstKb qstKb) {
		return simpleDao.createEntity("QstKb_NS.createQstKb", qstKb);
	}

}
