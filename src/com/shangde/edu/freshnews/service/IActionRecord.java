package com.shangde.edu.freshnews.service;


import com.shangde.edu.freshnews.domain.ActionRecord;

public interface IActionRecord {
	/**
	 * 根据relateId查询ActionReply
	 * @param relateId
	 * @return
	 */
    ActionRecord getActionRecordByProblemId(Integer relateId);
    /**
     * 更新新鲜事记录
     * @param actionRecord
     */
    void updateActionRecord(ActionRecord actionRecord);
    /**
     * 通过定时查询出的学员观看视频的记录 来增加新鲜事
     * @param cusWatchRecord
     */
    void addActionRecordByWatchRecord() throws Exception;
    /**
     * 添加新鲜事记录
     * @param actionRecord
     * @return
     * @throws Exception
     */
    Integer addActionRecord(ActionRecord actionRecord) throws Exception;
    /**
     * 定时查询学员注册信息来增加新鲜事
     * @throws Exception
     */
    void addActionRecordByRegister() throws Exception;
    /**
     * 定时查询订单信息来增加新鲜事
     * @throws Exception
     */
    void addActionRecordByOrder() throws Exception;
    /**
     * 定时查询考试信息来增加新鲜事
     * @throws Exception
     */
    void addActionRecordByExam() throws Exception;
}