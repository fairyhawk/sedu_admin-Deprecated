package com.shangde.edu.exam.service;

import com.shangde.common.service.BaseService;


public class OptRecordImpl extends BaseService implements IOptRecord{

	public void delOptRecordByCusId(int cusId) {
		simpleDao.deleteEntity("OptRecord_NS.deleteOptRecordByCusId", cusId);
	}

	public Integer delBathOptRecordByCusIds(String cusIds) {
		return simpleDao.delete("OptRecord_NS.deleteOptRecordByCusIds", cusIds);
	}
    
    /**
     * 删除根据试题ID
     * @param qstId
     */
    public void deleteOptRecordByQstId(int qstId){
    	simpleDao.deleteEntity("OptRecord_NS.deleteOptRecordByQstId", qstId);
    }
}
