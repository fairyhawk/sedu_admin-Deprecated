package com.shangde.edu.exam.service;


/**
 * OptRecord 选项记录
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IOptRecord {
    /**
     * 删除根据用户id
     */
    public void delOptRecordByCusId(int cusId);
    
    /**
     * 删除根据试题ID
     * @param qstId
     */
    public void deleteOptRecordByQstId(int qstId);
    
    /**
     * Yangning 2011/12/6 后台批量删除用户时批量删除OptRecord
     * @param cusIds
     * @return
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer delBathOptRecordByCusIds(String cusIds);
}