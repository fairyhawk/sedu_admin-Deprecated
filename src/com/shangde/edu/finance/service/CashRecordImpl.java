package com.shangde.edu.finance.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.change.domain.ChangeSellWay;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;


@SuppressWarnings("unchecked")
public class CashRecordImpl extends BaseServiceManyDb implements ICashRecord{
	
    public java.lang.Integer addCashRecord(CashRecord cashRecord) {
    	return simpleDao.createEntity("CashRecord_NS.createCashRecord",cashRecord);
    }

    public void delCashRecordById(int id){
        simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordById",id);
    }

    public void updateCashRecord(CashRecord cashRecord) {
        simpleDao.updateEntity("CashRecord_NS.updateCashRecord",cashRecord);
    }

    public CashRecord getCashRecordById(int id) {
        return simpleDao.getEntity("CashRecord_NS.getCashRecordById",id);
    }

    public PageResult getCashRecordList(QueryCashRecordCondition queryCashRecordCondition) {
        return simpleDao.getPageResult("CashRecord_NS.getCashRecordList","CashRecord_NS.getCashRecordCount",queryCashRecordCondition);
    }
    public List<CashRecord> getCashRecordByList(QueryCashRecordCondition queryCashRecordCondition) {
		return simpleDao.getForList("CashRecord_NS.getCashRecordByList", queryCashRecordCondition);
	}
    public List<CashRecord> getSimpleCashRecordByList(
			QueryCashRecordCondition queryCashRecordCondition) {
		return  simpleDao.getForList("CashRecord_NS.getSimpleCashRecordByList", queryCashRecordCondition);
	}

    public List<CashRecord> getAllCash(String strId) {
		return simpleDao.getForList("CashRecord_NS.getAllCash", strId);
	}

    public List<Integer> getCashPackId(String strId) {
    	return simpleDao.getForList("CashRecord_NS.getCashPackId", strId);
	}

    /**
	 * 查询此用户是否已经购买此书
	 * @return
	 */
    public Integer getshu(CashRecord aa) {
    	return simpleDao.getEntity("CashRecord_NS.getShuid", aa);
    }

	public void delCashRecordByCusId(int cusId) {
		 simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordByCusId",cusId);
	}
	
	public String getUseroderbyid(CashRecord cr) {
		return simpleDao.getEntity("CashRecord_NS.getuseroderid",cr);
	}

	public Integer getSendCount(CashRecord cr) {
		return simpleDao.getEntity("CashRecord_NS.getSendCount", cr);
	}

	public void delCashRecordExceptCtId(QueryCashRecordCondition queryCashRecordCondition){
		 simpleDao.deleteEntity("CashRecord_NS.delCashRecordExceptCtId", queryCashRecordCondition);
	}
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(QueryCashRecordCondition queryCashRecordCondition){
		return simpleDao.getForList("CashRecord_NS.getCashRecordByWebFromAgentList", queryCashRecordCondition);
	}
	/**
	 * 根据订单ID删除流水
	 * @param ctId
	 */
	public void delCashRecordByCtId(int ctId)
	{
		simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordByCtId", ctId);
	}
	/**
	 * 购买成功后修改流水状态
	 */
	public void updateCashReocrdStatus(List<CashRecord> cashRecordList)
	{
		try{
			for(int i=0;cashRecordList!=null&&cashRecordList.size()!=0&&i<cashRecordList.size();i++)
			{
				CashRecord cashRecordTemp=cashRecordList.get(i);
				cashRecordTemp.setStatus(1);
				updateCashRecord(cashRecordTemp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  fanxin  2011-11-01
	 * 后台货到付款 退费
	 * @param cr
	 */
	public void updateCashReocrdStatusByCtId(CashRecord cr){
		simpleDao.update("CashRecord_NS.updateCashReocrdStatusByCtId", cr);
	}
	
	/**
	 * 何海强 考试查询用
	 *
     * @param cr
     * @return
	 */
	public Integer getcashexam(CashRecord cr){
		
		return simpleDao.getEntity("CashRecord_NS.getcashexam", cr);
	}
	/**
	 *zyy 
	 *导出查从库 
	 * 
	 */
	public PageResult getCashRecordListAndSellWay(QueryCashRecordCondition queryCashRecordCondition){
		return simpleDaoRead.getPageResult("CashRecord_NS.getCashRecordListAndSellWay","CashRecord_NS.getCashRecordCount",queryCashRecordCondition);
	}

	public List<CashRecord> getCashRecordByCtIdForFanli(String contractId) {
		
		return simpleDao.getForList("CashRecord_NS.getCashRecordByCtIdForFanli", contractId);
	}
	
	//谢添加开始
	public List<SellWay> getSellWayBySubjectId(int subjectId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWay_NS.getSellWayBySubjectId", subjectId);
	}
	//谢添加结束
	//李明开始
	/*
	 * 查看单个订单
	 * @param commodityCondition
	 * @return commodit
	 * Liming
	 */
	public CashRecordDTO getSingleCommodity(QueryCashRecordCondition queryCashRecordCondition){
		return simpleDao.getEntity("CashRecord_NS.getSingleCommodity", queryCashRecordCondition);
	}
	/*
	 * json 修改延期时间
	 * @return String
	 * Liming
	 */
	public void updateStopTime(QueryCashRecordCondition queryCashRecordCondition){
		simpleDao.updateEntity("CashRecord_NS.updateStopTime", queryCashRecordCondition);
	}
	//李明结束
	
	//王迪开始
	//
	public void updateCashRecordStatus(Map<String,String> contractMap) {
		simpleDao.updateEntity("CashRecord_NS.updateCashRecordStatus",contractMap);
	}
	//王迪结束

	public PageResult getCashRecordDTOByCusId(
			QueryCashRecordCondition queryCashRecordCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("CashRecord_NS.getCashRecordListByCusId", "CashRecord_NS.getCashRecordCountByCusId", queryCashRecordCondition);
	}

	
	/**
	 * 更换课程，修改流水packId 售卖方式Id
	 */
	public void updateCashRecordPackId(CashRecord cashRecord) {
		
		simpleDao.update("CashRecord_NS.updateCashRecordPackId", cashRecord);
		
	}

	/*--Yangning 2011-12-6 后台删除用户时级联批量删除流水    begin--*/
	public Integer delBatchCashRecordByCusIds(String ids) {
		return simpleDao.delete("CashRecord_NS.delCashRecordByCusIds", ids);
	}
	/*--Yangning 2011-12-6 后台删除用户时级联批量删除流水   end--*/
	
	@Override
	public int updateCashRedord4Close(QueryCashRecordCondition queryCashRecordCondition,ChangeSellWay changeSellWay) {
		int result = simpleDao.update("CashRecord_NS.updateCash4Close", queryCashRecordCondition);
		if(result > 0){
			simpleDao.createEntity("ChangeSellWay_NS.addChangeSellWay", changeSellWay);
		}
		return result;
	}
}
