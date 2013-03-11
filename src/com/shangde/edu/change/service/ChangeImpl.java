package com.shangde.edu.change.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.change.domain.ChangeSellWay;

public class ChangeImpl extends BaseService implements IChange{

	/**
	 * 添加换课记录
	 * @author wangzheng
	 */
	public void addChangeSellWay(ChangeSellWay changeSellWay) {
		// TODO Auto-generated method stub
		this.simpleDao.createEntity("ChangeSellWay_NS.addChangeSellWay", changeSellWay);
		
	}
}
