package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.TcCouRecord;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;


public interface ITcCouRecord {

    /**
     *  添加
     *  @param tcCouRecord
     */
    public java.lang.Integer addTcCouRecord(TcCouRecord tcCouRecord);
    /**
     *  根据id删除
     *  @param id
     */
    public void delTcCouRecordById(int id);

    /**
     *  添加
     *  @param tcCouRecord
     */
    public void updateTcCouRecord(TcCouRecord tcCouRecord);

    /**
     * 根据id查询
     *  @param id
     */
    public TcCouRecord getTcCouRecordById(int id);

    /**
     * 根据查询条件查询
     *  @param queryTcCouRecordCondition
     */
    public List<TcCouRecord> getTcCouRecordList(QueryTcCouRecordCondition queryTcCouRecordCondition);
}