/**
 * 
 */
package com.shangde.edu.cou.webservice;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.sys.domain.Subject;

/**
 * @author	caowei
 * @date	2011-8-8
 * @desc	
 */
public class SellWayWSImpl extends BaseService implements ISellWayWS {

	public List<SellWay> getSellWayInfoList(int subjectId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWay_NS.getSellWayForAgent", subjectId);
	}

}
