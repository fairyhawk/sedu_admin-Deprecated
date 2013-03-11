package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;
import com.shangde.common.service.BaseService;

/**
 * LoginRecord服务实现类
 * User: guoqiang.liu
 * Date: 2010-11-17
 */
@SuppressWarnings("unchecked")
public class LoginRecordImpl extends BaseService implements ILoginRecord{
    public void addLoginRecord(LoginRecord loginRecord) {
    	simpleDao.createEntity("LoginRecord_NS.createLoginRecord",loginRecord);
    }

    public void delLoginRecordById(){
    }

    public void updateLoginRecord(LoginRecord loginRecord) {
        simpleDao.updateEntity("LoginRecord_NS.updateLoginRecord",loginRecord);
    }

    public LoginRecord getFirstLoginRecordByCusId(int cusId) {
    	return simpleDao.getEntity("LoginRecord_NS.getLoginRecordByCusId", cusId);
    }

    public List<LoginRecord> getLoginRecordList(QueryLoginRecordCondition queryLoginRecordCondition) {
        return simpleDao.getForList("LoginRecord_NS.getLoginRecordList",queryLoginRecordCondition);
    }
    public List<LoginRecord> getLoginRecordListASC(QueryLoginRecordCondition queryLoginRecordCondition) {
    	return simpleDao.getForList("LoginRecord_NS.getSecondLoginRecordByCusId",queryLoginRecordCondition);
    }
    

	public List<LoginRecord> getAddressNullLoginRecordList() {
		return simpleDao.getForList("LoginRecord_NS.getAddressNullLoginRecordList", null);
	}

	public void delLoginRecordByCusId(int cusId) {
		 simpleDao.deleteEntity("LoginRecord_NS.deleteLoginRecordByCusId",cusId);
	}

	public Integer delBathLoginRecordByCusIds(String cusIds) {
		return simpleDao.delete("LoginRecord_NS.deleteLoginRecordByCusIds", cusIds);
	}
	
	
}
