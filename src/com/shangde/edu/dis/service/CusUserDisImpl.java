package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.domain.CusUserDis;
import com.shangde.edu.dis.dto.SubjectDTO;

public class CusUserDisImpl extends BaseService implements ICusUserDis {

	public List<SubjectDTO> getMyBuySubject(Integer cusId) {
		return simpleDao.getForList("CusUserDis_NS.getMyBuySubject", cusId);
	}

	public Integer addCusUserDis(CusUserDis cusUserDis) {
		return simpleDao.createEntity("CusUserDis_NS.createCusUserDis",
				cusUserDis);
	}

	public List<CusUserDis> findCusUserDisByCusId(Integer cusId) {
		return simpleDao.getForList("CusUserDis_NS.findCusUserDisByCusId",
				cusId);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param cusUserDis
	 */
	public void updateCusUserDis(CusUserDis cusUserDis) {
		simpleDao.updateEntity("CusUserDis_NS.updateCusUserDis", cusUserDis);
	}

	/**
	 * 添加或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(CusUserDis entity) {
		if (entity.getId() == null)
			addCusUserDis(entity);
		else
			updateCusUserDis(entity);
	}

}
