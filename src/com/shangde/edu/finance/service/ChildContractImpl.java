package com.shangde.edu.finance.service;

import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.condition.QueryChildContractCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class ChildContractImpl extends BaseService implements IChildContract{
	
    public Integer addChildContract(ChildContract childContract) {
    	return simpleDao.createEntity("ChildContract_NS.createChildContract",childContract);
    }

    public void delChildContractById(){
    }

    public void updateChildContract(ChildContract childContract) {
        simpleDao.updateEntity("ChildContract_NS.updateChildContract",childContract);
    }

    public ChildContract getChildContractById(int id) {
    	return simpleDao.getEntity("ChildContract_NS.getChildContractById", id);
    }
    public ChildContract getChildContract(QueryChildContractCondition queryChildContractCondition){
    	return simpleDao.getEntity("ChildContract_NS.getChildContract", queryChildContractCondition);
    }
    public PageResult getChildContractList(PageQuery queryChildContractCondition) {
        return simpleDao.getPageResult("ChildContract_NS.getChildContractList","ChildContract_NS.getChildContractListCount",queryChildContractCondition);
    }

	public ChildContract getChildContractByNo(String subContractNo) {
		return simpleDao.getEntity("ChildContract_NS.getChildContractByNo", subContractNo);
	}
	
    public Integer updateChildContractPayOK(ChildContract childContract) {
        return  simpleDao.update("ChildContract_NS.updateChildContractPayOK",childContract);
    }
    public   QueryChildContractCondition getPayChildContract(QueryChildContractCondition queryChildContractCondition){
        return simpleDao.getEntity("ChildContract_NS.getPayChildContract", queryChildContractCondition);
    }
	
}
