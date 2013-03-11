package com.shangde.edu.finance.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.change.domain.ChangeSellWay;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;


public interface ICashRecord {
	/**
     * 添加流水
     */
    public java.lang.Integer addCashRecord(CashRecord cashRecord);

	/**
     * 删除流水
     */
    public void delCashRecordById(int id);

	/**
     * 更新流水
     */
    public void updateCashRecord(CashRecord cashRecord);
    
    /**
     * 更换课程操作 修改流水售卖方式
     */
    
    public void updateCashRecordPackId(CashRecord cashRecord);

	/**
     * 根据id获得流水对象
     */
    public CashRecord getCashRecordById(int id);
    
	/**
     * 分页查询
     */
    public PageResult getCashRecordList(QueryCashRecordCondition queryCashRecordCondition);

    /**
	 * 查询此用户是否已经购买此书
	 * @return
	 */
    public Integer getshu(CashRecord aa);
    
    /**
     * 条件查询
     */
    public List<CashRecord> getCashRecordByList(QueryCashRecordCondition queryCashRecordCondition);
    public List<CashRecord> getSimpleCashRecordByList(QueryCashRecordCondition queryCashRecordCondition);
    
    /**
     * 查询所有流水
     * @return
     */
    public List<CashRecord> getAllCash(String strId);
    
    
    public List<Integer> getCashPackId(String strId);
	/**
     * 删除流水
     */
    public void delCashRecordByCusId(int cusId);

    /**
     * 查询订单编号
     */
    public String getUseroderbyid(CashRecord cr);

    /**
     * 
     */
    public Integer getSendCount(CashRecord cr);

    /**
     * 删除学员的流水，但保留传入得订单相关的流水
     * @param cusId
     * @param ctId
     */
	public void delCashRecordExceptCtId(QueryCashRecordCondition queryCashRecordCondition);
	/**
	 * 为董元提供的方法
	 */
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(QueryCashRecordCondition queryCashRecordCondition);
	/**
	 * 支付成功后，修改流水状态 status 0 无效，1 有效
	 * @param cashRecordList
	 */
	public void updateCashReocrdStatus(List<CashRecord> cashRecordList);
	
	/**
	 *  fanxin  2011-11-01
	 *  后台货到付款 退费
	 * @param 
	 */
	public void updateCashReocrdStatusByCtId(CashRecord cr);
	
	/**
	 * 根据订单ID删除流水
	 * @param ctId
	 */
	public void delCashRecordByCtId(int ctId);
	/**
	 * 何海强 考试查询用
	 *
     * @param cr
     * @return
	 */
	public Integer getcashexam(CashRecord cr);
	
	/**
	 * 订单流水 添加售卖方式名字显示
	 * @param queryCashRecordCondition
	 * @return
	 */
	public PageResult getCashRecordListAndSellWay(QueryCashRecordCondition queryCashRecordCondition);
	
	/**
	 * 获取指定订单编号下的流水列表
	 * 
	 * @param contractId
	 * 			订单编号
	 * @return
	 * 			流水列表
	 */
	public List<CashRecord> getCashRecordByCtIdForFanli(String contractId);
	
	/**
	 * 根据项目id找到其下商品
	 * @param subjectId
	 * @return
	 */
	public List<SellWay> getSellWayBySubjectId(int subjectId);
	
	//李明开始
	/*
	 * 查看单个订单
	 * @param commodityCondition
	 * @return commodity
	 * Liming
	 */
	public CashRecordDTO getSingleCommodity(QueryCashRecordCondition queryCashRecordCondition);
	/*
	 * json 修改延期时间
	 * @return String
	 * Liming
	 */
	public void updateStopTime(QueryCashRecordCondition queryCashRecordCondition);

	//李明结束
	
	//王迪开始
	/**
	 * 更新流水状态（按照订单编号）
	 * @param contractMap 
	 * wd
	 */
	public void updateCashRecordStatus(Map<String,String> contractMap);
	//王迪结束
	
	
	/**
	 * 更换课程  根据cusId 得到用户后买课程流水
	 * 王郑
	 */
	
	public PageResult getCashRecordDTOByCusId(QueryCashRecordCondition queryCashRecordCondition);
	
	/**
	 * 后台批量删除用户时删除对应流水
	 * @param ids
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-15
	 */
	public Integer delBatchCashRecordByCusIds(String ids);
	
	/**
	 * 后台更新流水，关闭课程,写入操作记录
	 * @param ids
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-05-16
	 */
	public int updateCashRedord4Close(QueryCashRecordCondition queryCashRecordCondition,ChangeSellWay changeSellWay);
}