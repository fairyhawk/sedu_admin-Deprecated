package com.shangde.edu.crm.service;

import java.util.List;

import com.shangde.edu.crm.condition.QueryRecordCondition;
import com.shangde.edu.crm.domain.Record;
import com.shangde.edu.crm.dto.RecordDTO;


/**
 * Record接口管理
 * User:guoqiang.liu
 * Date: 2011-11-03
 */
public interface IRecord {
   
	/**
	 * @param record
	 *	@return id 
	 */
    public java.lang.Integer addRecord(Record record);

    /**
     * @param id
     * 删除
     */
    public void delRecordById(int id);

    
    /**
  	  *更新
      */
    public void updateRecord(Record record);

    /**
     *根据id查 
     */
    public Record getRecordById(int id);

    /**
     *
     *@param queryRecordCondition
     *
     */
    public List<Record> getRecordList(QueryRecordCondition queryRecordCondition);
    
    /**
    *
    *@param crmChanceId
    *根据chanceId查
    */
    public List<RecordDTO> getRecordDTOListByChanceId(int crmChanceId);
    /**
    *
    *@param crmChanceId
    *根据chanceId查
    */
    public List<RecordDTO> getRecordListByChanceId(int crmChanceId);
}