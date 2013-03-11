package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;


public interface ITsRecord {
  
    public java.lang.Integer addTsRecord(TsRecord tsRecord);

    public void delTsRecordById(int trId);

    public void updateTsRecord(TsRecord tsRecord);

    public TsRecord getTsRecordById(int trId);

    public List<TsRecord> getTsRecordList(QueryTsRecordCondition queryTsRecordCondition);
    
    public PageResult getTsRecordPageList(QueryTsRecordCondition queryTsRecordCondition);

	public PageResult getTsRecordListByCus(
			QueryTsRecordCondition queryTsRecordCondition);
	public void delTsRecordByCusId(int cusId);
	
	/**
	 * Yangning 2011/12/06 后台批量删除用户时级联删除TsRecord
	 * @param cusIds
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
	public Integer delBathTsRecordByCusIds(String cusIds);
	
}