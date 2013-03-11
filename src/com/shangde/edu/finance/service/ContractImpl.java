package com.shangde.edu.finance.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.MessageRemindDTO;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.finance.dto.CrmFinanceDTO;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractDTO;
import com.shangde.edu.finance.dto.ContractExcelDTO;
import com.shangde.edu.finance.dto.MonthDayForpayTypeDTO;

@SuppressWarnings("unchecked")
public class ContractImpl extends BaseServiceManyDb implements IContract {
	public java.lang.Integer addContract(Contract contract) {
		return simpleDao.createEntity("Contract_NS.createContract", contract);
	}

	public void delContractById(int id) {
		simpleDao.deleteEntity("Contract_NS.deleteContractById", id);
	}

	public void updateContract(Contract contract) {
		simpleDao.updateEntity("Contract_NS.updateContract", contract);
	}

	public Contract getContractById(int id) {
		return simpleDao.getEntity("Contract_NS.getContractById", id);
	}
	public Contract getContractByCus_Id(int cus_id) {
		return simpleDao.getEntity("Contract_NS.getContractByCus_Id", cus_id);
	}
	
	public PageResult getContractList(
			QueryContractCondition queryContractCondition) {

		return simpleDaoRead.getPageResult("Contract_NS.getContractList",
				"Contract_NS.getContractCount", queryContractCondition);
	}
	
	
	
	public List<Contract> getContractLists(QueryContractCondition queryContractCondition)
	{
		return simpleDao.getForList("Contract_NS.getContractLists",queryContractCondition);
	}

	//查询订单金额总计 getContractPriceSum
	public Integer getContractPriceSum(QueryContractCondition queryContractCondition) {
		Integer result=simpleDao.getEntity("Contract_NS.getContractPriceSum",queryContractCondition);
		if(result!=null)
		{
			return result.intValue();
		}else
		{
			return 0;
		}
	}
	/**
     * 网盟用查询
     */
    public PageResult getContractListwm(
            QueryContractCondition queryContractCondition) {
        return simpleDao.getPageResult("Contract_NS.getContractListwm",
                "Contract_NS.getContractCountwm", queryContractCondition);
    }
    
    /**
     * fanxin 2011-08-24
     * 
     * 网盟用查询
     */
    public PageResult getContractListwmCus(QueryContractCondition queryContractCondition){
    	return simpleDao.getPageResult("Contract_NS.getContractListwmCus",
                "Contract_NS.getContractCountwmCus", queryContractCondition);
    }
    public Integer getContractPriceSumwmCus(QueryContractCondition queryContractCondition) {
    	Integer result=simpleDao.getEntity("Contract_NS.getContractPriceSumwmCus",queryContractCondition);
    	
        if(result!=null)
        {
            return result.intValue();
        }else
        {
            return 0;
        }
    }
    
	//查询订单金额总计 getContractPriceSumwm 网盟用highso0004
    public Integer getContractPriceSumwm(QueryContractCondition queryContractCondition) {
    	Integer result =simpleDao.getEntity("Contract_NS.getContractPriceSumwm",queryContractCondition);
        if(result!=null)
        {
            return result.intValue();
        }else
        {
            return 0;
        }
    }
	
	public List<Customer> getContract(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getContract",
				queryContractCondition);
	}

	public Integer getUserContractCount(int loginUserId) {
		return simpleDao.getEntity("Contract_NS.getUserContractCount",
				loginUserId);
	}

	public String getStatus(CashRecord cr) {
		return simpleDao.getEntity("Contract_NS.getStatus", cr);
	}

	public Integer getContractNumber(QueryContractCondition queryContractCondition) {
        Integer number = simpleDao.getEntity("Contract_NS.getContractNumber",
				queryContractCondition);
		return number;
	}
	public Integer getContractNumberBySubject(QueryContractCondition queryContractCondition){
        Integer	number=simpleDao.getEntity("Contract_NS.getContractNumberBySubject", queryContractCondition);
		return number;
	}
	public PageResult getContractNumberBySubjectList(
			QueryContractCondition queryContractCondition) {

		return simpleDao.getPageResult("Contract_NS.getContractBySubjectList",
				"Contract_NS.getContractBySubjectCount", queryContractCondition);
	}
	public Integer getPayContractNumber(
            QueryContractCondition queryContractCondition) {
        Integer number = simpleDao.getEntity("Contract_NS.getPayContractNumber",
				queryContractCondition);
		return number;
	}

	public Integer getMonthContractNumber(String month) {
		return simpleDao.getEntity("Contract_NS.getMonthContractNumber", month);
	}

	public Integer getMonthPayContractNumber(String month) {
		return simpleDao.getEntity("Contract_NS.getMonthPayContractNumber",
				month);
	}

	public List getMonthDayContract(String month) {
		return simpleDao.getForList("Contract_NS.getMonthDayContract", month);
	}

	public List getMonthDayPayContract(String month) {
		return simpleDao
				.getForList("Contract_NS.getMonthDayPayContract", month);
	}

	public void delContractByCusId(int cusId) {
		simpleDao.deleteEntity("Contract_NS.deleteContractByCusId", cusId);
	}

	/**
	 * 查询订单是否支付成
	 */
	public Integer getIsoder(Contract ct) {

		return simpleDao.getEntity("Contract_NS.getIsoderbyid", ct);
	}

	public Contract getUserOderByid(Contract ct) {
		return simpleDao.getEntity("Contract_NS.getuseroderbyid", ct);
	}

	public List<Contract> getContractByList(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getContractByList",
				queryContractCondition);
	}

	public List<MessageRemindDTO> getEmailByContractStatus(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getEmailByContractStatus",
				queryContractCondition);
	}

	public List<Contract> getContractBystatus(int status) {
		
		return simpleDao.getForList("Contract_NS.getContractByStatus", status);
	}
	
	 public List<Contract> getContractCount(int cusId){
		 
		 return simpleDao.getForList("Contract_NS.getContractCountByCusId", cusId);
	 }
	 
	 public List<Contract> getContractByPayType(int payType){
		 
		 return simpleDao.getForList("Contract_NS.getContractByPayType", payType);
	 }
	public PageResult getContractCODList(QueryContractCondition queryContractCondition) {

			return simpleDao.getPageResult("Contract_NS.getContractCODList",
					"Contract_NS.getContractCODCount", queryContractCondition);
	}
	
	public PageResult getContractBankPayList(QueryContractCondition queryContractCondition) {

		return simpleDao.getPageResult("Contract_NS.getContractBankPayList",
				"Contract_NS.getContractBankPayCount", queryContractCondition);
	}
   
    
	 public PageResult getContractByCourseId(
			QueryContractCondition queryContractCondition) {
		 return simpleDao.getPageResult("Contract_NS.getContractByCourseId", "Contract_NS.getContractByCourseIdCount", queryContractCondition);
		
	}

	public void delContractExceptCtId(
			QueryContractCondition queryContractCondition) {
		simpleDao.deleteEntity("Contract_NS.delContractExceptCtId", queryContractCondition);
	}
	
	public List<Contract> getContractByFromAgentList(QueryContractCondition queryContractCondition){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		queryContractCondition.setPayStartTime(sdf.format(cal.getTime()));
		queryContractCondition.setPayEndTime(sdf.format(new Date()));
	  return  simpleDao.getForList("Contract_NS.getContractByFromAgent",queryContractCondition);
	}

	public List<Contract> queryOrderByList(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.queryOrderByList",
				queryContractCondition);
	}

	public List<CashRecordDTO> getContractSum(QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		
		return simpleDao.getForList("Contract_NS.getContractSum", queryContractCondition);
	}

	public List<CashRecordDTO> getYfContractSum(QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Contract_NS.getYfContractSum", queryContractCondition);
	}

	/**
	 * 货到付款导出excel
	 */
	public List<ContractExcelDTO> getCodContractLists(QueryContractCondition queryContractCondition) {
		return simpleDaoRead.getForList("Contract_NS.getContractCODListExcel",queryContractCondition);
	}

	public PageResult getContractSellWayByCusId(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Contract_NS.getContractAndSellWayByCusId", "Contract_NS.getContractAndSellWayCount", queryContractCondition);
	}
	
	public Integer getReconciliationAmout(QueryContractCondition queryContractCondition) {
		Integer res =simpleDaoRead.getEntity("Contract_NS.getReconciliationAmout",queryContractCondition);
		if (res != null) {
			return res.intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 为导出excle
	 * 查从库
	 */
	public List<ContractDTO> getContractDTOList(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDaoRead.getForList("Contract_NS.getContractDTOLists",queryContractCondition);
	}

	public List<Teacher> getTeacherBySellWayId(int sellWayId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Contract_NS.getTeacherBySellWayId", sellWayId);
	}
	
	public List<Contract> getContractListForFanli(QueryContractCondition queryContractCondition){
		
		return simpleDao.getForList("Contract_NS.getContractListForFanli",queryContractCondition);
	}

	public List<SellWay> getContractInfoByContractId(String contractId) {
		
		return simpleDao.getForList("Contract_NS.getSellNameByContractId", contractId);
	}

	public Integer getMonthPayContractSumMoney(String month) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Contract_NS.getMonthPayContractSumMoney",
				month);
	}

	public Integer getPayContractNumberByCondition(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getEntity("Contract_NS.getPayContractNumberByCondition", queryContractCondition);
	}

	public Integer getContractNumberByCondition(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Contract_NS.getContractNumberByCondition", queryContractCondition);
	}

	public Integer getPayContractSumMoneyByCondition(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Contract_NS.getPayContractSumMoneyByCondition", queryContractCondition);
	}
	
	public Integer delBathContractByCusIds(String cusIds){
		return simpleDao.delete("Contract_NS.delContractByCusIds", cusIds);
	}

	public PageResult contractAll(QueryContractCondition queryContractCondition) {
		return simpleDao.getPageResult("Contract_NS.contractAll","Contract_NS.getContractCountNew", queryContractCondition);
//		return simpleDao.getPageResult("Contract_NS.contractAll", queryContractCondition);
	}

	public PageResult contractAllSubject(QueryContractCondition queryContractCondition) {
		return simpleDaoRead.getPageResult("Contract_NS.contractAllSubject","Contract_NS.getContractCountNew", queryContractCondition);
	}

	public PageResult contractAllFirst(QueryContractCondition queryContractCondition) {
		return simpleDao.getPageResult("Contract_NS.contractAll","Contract_NS.getContractCountNewNew", queryContractCondition);
	}
	
	/**
	 * crm订单列表查询
	 * @author 王超
	 */
	public PageResult getCrmContract(QueryContractCondition queryContractCondition) {
		return simpleDao.getPageResult("Contract_NS.getCrmContractList","Contract_NS.getCrmContractCount", queryContractCondition);
	}
	
	public List<MonthDayForpayTypeDTO> getMontyDayForpayType(String day){
		List<MonthDayForpayTypeDTO> list=simpleDao.getForList("Contract_NS.getMontyDayForpayType", day);
		return list;
	}
	/**
	 * Yang 2012/02/22 modify sendMessage remove study store contract
	 *
     * @param queryContractCondition
     * @return
	 * Author:Yangning
	 * CreateDate:2012-2-22
	 */
	public Integer getContractNumberBySubjectToMsg(QueryContractCondition queryContractCondition){
        Integer	number;
        number = simpleDao.getEntity("Contract_NS.getContractNumberBySubjectToMsg", queryContractCondition);
        return number;
	}
	
	/**
	 * 获取crm 订单 数据 谢 
	 * @param cus_id
	 * @return
	 */
	public List<CrmFinanceDTO> getCRMFinanceByCusId(int cus_id){
		List<CrmFinanceDTO> list=simpleDaoRead.getForList("Contract_NS.getCrmFinanceByCusId", cus_id);
		return list;
	}
	
	/**
	 * 查询网盟订单列表
	 */
	public List<Contract> getcontractListForCPS(QueryContractCondition condition){
	    List<Contract> list = simpleDao.getForList("Contract_NS.getContractListForCPS", condition);
	    return list;
	}

	public int getPaidContractByCusId(int cusId) {
		return simpleDao.getEntity("Contract_NS.getPaidContractByCusId", cusId);
	}
}
