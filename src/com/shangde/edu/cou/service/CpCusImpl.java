package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.CpCus;
import com.shangde.edu.cou.condition.QueryCpCusCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * CpCus优惠券学员中间表服务类
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
@SuppressWarnings("unchecked")
public class CpCusImpl extends BaseService implements ICpCus{
    public  java.lang.Integer addCpCus(CpCus cpCus) {
    	return simpleDao.createEntity("CpCus_NS.createCpCus",cpCus);
    }

    public void delCpCusById(){
    	
    }

    public void updateCpCus(CpCus cpCus) {
        simpleDao.updateEntity("CpCus_NS.updateCpCus",cpCus);
    }

    public CpCus getCpCusById() {
		return null;
    }

    public List<CpCus> getCpCusList(QueryCpCusCondition queryCpCusCondition) {
        return simpleDao.getForList("CpCus_NS.getCpCusList",queryCpCusCondition);
    }

	public CpCus getCpCusByCusId(int userId) {
		return simpleDao.getEntity("CpCus_NS.getCpCusByCusId",userId);
	}

	public void deleteCouponBySN(String SerialNumber) {
		simpleDao.deleteEntity("CpCus_NS.deleteCouponBySN", SerialNumber);
	}

	public CpCus getCpCusBySN(String serialNumber) {
		return simpleDao.getEntity("CpCus_NS.getCpCusBySN",serialNumber);
	}

	public PageResult showUserCpCusList(PageQuery queryCpCusCondition) {
		return simpleDao.getPageResult("CpCus_NS.getUserCpCusList", "CpCus_NS.getUserCpCusCount", queryCpCusCondition);
	}

	public void setCpCusUsed(String serialNumber) {
		
	}
	 public void delCpCusByCusId(int cusId){
		 simpleDao.deleteEntity("CpCus_NS.delCpCusByCusId", cusId);
		 
	 }
	 
	 public Integer delBathCpCusByCusIds(String cusIds){
		 return simpleDao.delete("CpCus_NS.delCpCusByCusIds", cusIds);
	 }
}
