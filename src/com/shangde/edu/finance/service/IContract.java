package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.vo.MessageRemindDTO;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.finance.dto.CrmFinanceDTO;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractDTO;
import com.shangde.edu.finance.dto.ContractExcelDTO;
import com.shangde.edu.finance.dto.MonthDayForpayTypeDTO;


public interface IContract {

	/**
     * 添加订单
     */
    public java.lang.Integer addContract(Contract contract);

	/**
     * 删除订单
     */
    public void delContractById(int id);

	/**
     * 更新订单
     */
    public void updateContract(Contract contract);

	/**
     * 根据id获得订单对象
     */
    public Contract getContractById(int id);
    public Contract getContractByCus_Id(int cus_id) ;
    /**
     * 根据status获得订单对象
     */
    public List<Contract> getContractBystatus(int status);

	/**
     * 分页查询
     */
    public PageResult getContractList(QueryContractCondition queryContractCondition);
    /**
     * 网盟用查询
     */
    public PageResult getContractListwm(QueryContractCondition queryContractCondition);
   
    /**
     * fanxin 2011-08-24
     * 
     * 网盟用查询
     */
    public PageResult getContractListwmCus(QueryContractCondition queryContractCondition);

	public Integer getContractPriceSumwmCus(QueryContractCondition queryContractCondition);
	
    
    
    /**
     * 分页查询
     */
    public PageResult getContractCODList(QueryContractCondition queryContractCondition);
    
    /**
     * 分页查询
     */
    public PageResult getContractBankPayList(QueryContractCondition queryContractCondition);   
    
    
	/**
     * 根据查询条件获得List
     */
    public List<Customer> getContract(QueryContractCondition queryContractCondition);

    /**
     * 获取用户订单张数
     *
     * @param loginUserId
     * @return
     */
	public Integer getUserContractCount(int loginUserId);
	
	/**
	 *根据用户id和课程id获取订单状态
	 */
	 public String getStatus(CashRecord cr);
	/**
	 * 按时间段统计订单量
	 * @return
	 */
	public Integer getContractNumber(QueryContractCondition queryContractCondition);
	public Integer getContractNumberBySubject(QueryContractCondition queryContractCondition);
	public PageResult getContractNumberBySubjectList(QueryContractCondition queryContractCondition);
	/**
	 * 按时间段统计已付款订单量
	 * @return
	 */
	public Integer getPayContractNumber(QueryContractCondition queryContractCondition);
	/**
	 *统计每个月的订单量
	 * @return
	 */
	public Integer getMonthContractNumber(String month);
	/**
	 *统计每个月的已付订单量
	 * @return
	 */
	public Integer getMonthPayContractNumber(String month);
	
	/**
	 * 统计每天订单量 金额总和
	 */
	public Integer getMonthPayContractSumMoney(String month);
	
	/**
	 * 统计每天的订单量,月下面的日期
	 * @return
	 */
	public List getMonthDayContract(String month);
	/**
	 * 统计每天的已付订单量,月下面的日期
	 * @return
	 */
	public List getMonthDayPayContract(String month);
	/**
     * 删除订单
     */
    public void delContractByCusId(int cusId);
    
    /**
     *查询订单是否支付成 
     */
    public Integer getIsoder(Contract ct);
    
   /**
    * 
    * 查询一条订单数据
    */
  
    public Contract  getUserOderByid (Contract ct);
    
    /**
     * 
     * 根据条件查询订单list
     */
    public List<Contract> getContractByList(QueryContractCondition queryContractCondition);
    public List<Contract> queryOrderByList(QueryContractCondition queryContractCondition);
     
    public List<MessageRemindDTO> getEmailByContractStatus(QueryContractCondition queryContractCondition);
    
    /**
     * 根据用户id查询货到付款的订单
     * 
     */
    public List<Contract> getContractCount(int cusId);
    /**
     * 查出支付类型的订单
     * 
     */
    public List<Contract> getContractByPayType(int payType);
    /***
     * 	根据课程ID查订单
     */
    public PageResult getContractByCourseId(QueryContractCondition queryContractCondition);

    /**
     * 删除学员的订单，但保留传入得订单
     * @param queryContractCondition
     */
	public void delContractExceptCtId(
			QueryContractCondition queryContractCondition);
	/**
	 * 查询订单金额总计
	 *
     * @param queryContractCondition
     * @return
	 */
	public Integer getContractPriceSum(QueryContractCondition queryContractCondition);
	/**
     * 查询订单金额总计网盟用
     *
     * @param queryContractCondition
     * @return
     */
	public Integer getContractPriceSumwm(QueryContractCondition queryContractCondition);
	

	/**
	 * 订单导出excel的查询语句
	 * @param queryContractCondition
	 * @return
	 */
	public List<Contract> getContractLists(QueryContractCondition  queryContractCondition);
	
	/**
	 * 为导出excle提供方法
	 */
	public List<ContractDTO> getContractDTOList(QueryContractCondition queryContractCondition);
	
	/**
	 * 货到付款导出excle查询
	 * @param queryContractCondition
	 * @return
	 */
	public List<ContractExcelDTO> getCodContractLists(QueryContractCondition  queryContractCondition);
	/**
	 * 为董元提供的接口
	 */
	public List<Contract> getContractByFromAgentList(QueryContractCondition queryContractCondition);
	
	/**
	 * 根据专业ID 得到当前专业订单总数
	 * @param queryContractCondition
	 * @return
	 */
	public List<CashRecordDTO> getContractSum(QueryContractCondition queryContractCondition);
	
	/**
	 * 根据专业ID 得到当前专业已付订单总数
	 * @param queryContractCondition
	 * @return
	 */
	public List<CashRecordDTO> getYfContractSum(QueryContractCondition queryContractCondition);
	
	
//	/**
//	 * 根据专业ID 支付方式ID 得到当前支付方式订单总数
//	 */
//	public int getContractSumByPayType(QueryContractCondition queryContractCondition);
//	
//	/**
//	 * 根据专业ID 支付方式ID 得到当前支付方式支付总数
//	 */
//	public int getYfContractSumByPayType(QueryContractCondition queryContractCondition);
	
	/**
	 * 获取对账的金额
	 * 对账金额为已经完成购买流程的订单的金额。即支付方式为支付宝、网银在线、快钱和货到付款的订单
	 * 购买流程成功结束的订单的总金额的合计。
	 *
     * @param queryContractCondition
     * @return
	 */
	public Integer getReconciliationAmout(QueryContractCondition queryContractCondition);
	
	/**
	 * 获取返利网数据信息
	 * 
	 * @param queryContractCondition
	 * 				返利网查询条件
	 * @return
	 * 				订单的集合
	 */
	public List<Contract> getContractListForFanli(QueryContractCondition queryContractCondition);
	
	/**
	 * 得到订单详情页 售卖方式名
	 */
	
	public List<SellWay> getContractInfoByContractId(String contractId);
	
	/**
	 * 按条件查询  已付订单数量
	 */
	
	public Integer getPayContractNumberByCondition(QueryContractCondition queryContractCondition);
	
	/**
	 * 按条件查询 总订单量
	 */
	public Integer getContractNumberByCondition(QueryContractCondition queryContractCondition);
	
	/**
	 * 按条件查询 订单金额总数
	 */
	public Integer getPayContractSumMoneyByCondition(QueryContractCondition queryContractCondition);
	
	/**
	 * Yangning 2011/12/6 批量删除用户时级联删除 邮箱
	 * @param cusId
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
    public Integer delBathContractByCusIds(String cusId);
    
    /**
     * 订单优化所有
     */
    public PageResult contractAll(QueryContractCondition queryContractCondition);
    
    /**
     * 订单优化（有项目）
     * wd
     */
    public PageResult contractAllSubject(QueryContractCondition queryContractCondition);
    
    /**
	 * crm订单列表查询
	 * @author 王超
	 */
	public PageResult getCrmContract(QueryContractCondition queryContractCondition);
    
    
    /**
     * 订单优化初次
     */
    public PageResult contractAllFirst(QueryContractCondition queryContractCondition);
    

	/**
	 * 统计每天各个支付方式的总金额
	 * @param day
	 * @return
	 */
	public List<MonthDayForpayTypeDTO> getMontyDayForpayType(String day);
	
	/**
	 * Yangning 2012/02/22 sendMessage remove study store contract
	 *
     * @param condition
     * @return
	 * Author:Yangning
	 * CreateDate:2012-2-22
	 */
	public Integer getContractNumberBySubjectToMsg(QueryContractCondition condition);
	
	/**
	 * 获取crm 订单 数据 谢 
	 * @param cus_id
	 * @return
	 */
	public List<CrmFinanceDTO> getCRMFinanceByCusId(int cus_id);
	
	/**
     * 获取网盟推广数据
     * @param QueryContractCondition condition
     * @return List<Contract>
     */
    public List<Contract> getcontractListForCPS(QueryContractCondition condition);
    
    /**
     * 临时用户查看已付款订单数量
     * @param cusId
     * @return
     */
    public int getPaidContractByCusId(int cusId); 
}
