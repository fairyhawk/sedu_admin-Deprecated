package com.shangde.edu.freshnews.service;


import java.text.NumberFormat;
import java.util.*;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.domain.ActionRecordByExam;
import com.shangde.edu.hadata.domain.CusWatchRecord;
import com.shangde.edu.hadata.service.CusWatchRecordService;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;


public class ActionRecordImpl extends BaseService implements IActionRecord{
	private CusWatchRecordService cusWatchRecordService;
	private ICustomer customerService;//客户service
	private ISubject subjectService;//专业service
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public CusWatchRecordService getCusWatchRecordService() {
		return cusWatchRecordService;
	}

	public void setCusWatchRecordService(CusWatchRecordService cusWatchRecordService) {
		this.cusWatchRecordService = cusWatchRecordService;
	}

	/**
	 * 根据relateId查询ActionReply
	 * @param relateId
	 * @return
	 */
	public ActionRecord getActionRecordByProblemId(Integer relateId) {
		return simpleDao.getEntity("ActionRecord_NS.getActionRecordByProblemId", relateId);
	}

	 /**
     * 更新新鲜事记录
     * @param actionRecord
     */
	public void updateActionRecord(ActionRecord actionRecord) {
		simpleDao.updateEntity("ActionRecord_NS.updateActionRecord", actionRecord);
	}

	/**
     * 通过定时查询出的学员观看视频的记录 来增加新鲜事
     * @param cusWatchRecord
     */
	public void addActionRecordByWatchRecord() throws Exception {
		//在hadata库中获取学员观看视频记录
		List<CusWatchRecord> cusWatchRecordList=cusWatchRecordService.getCusWatchRecordList(getBeginId(1));
		if(cusWatchRecordList!=null&&cusWatchRecordList.size()>0){
			for(CusWatchRecord cusWatchRecord : cusWatchRecordList){
				//保存新鲜事
				if(cusWatchRecord.getStartTime().compareTo(new Date())<=0){
					addActionRecord(fillActionRecordByWatchRecord(cusWatchRecord));
				}
			}
			//用cusWatchRecordList中CusWatchRecord主键的最大值更新表begin_id_tbl中的begin_id 1表示观看视频
			updateBeginId(cusWatchRecordList.get(0).getId(), 1);
		}
	}
	/**
	 * 通过学员观看视频的记录填充新鲜事
	 * @param cusWatchRecord
	 * @param actionRecord
	 */
	private ActionRecord fillActionRecordByWatchRecord(CusWatchRecord cusWatchRecord){
		ActionRecord actionRecord=new ActionRecord();
		Customer customer=customerService.getCustomerById(cusWatchRecord.getCusId());
		actionRecord.setContent(cusWatchRecord.getVideoName());
		actionRecord.setCreateTime(cusWatchRecord.getStartTime());
		actionRecord.setCurrentType(2);//观看视频
		actionRecord.setCusEmail(cusWatchRecord.getEmail());
		actionRecord.setCusId(cusWatchRecord.getCusId());
		actionRecord.setOtherInfo(cusWatchRecord.getWatchAllTime().toString());
		actionRecord.setSubjectId(cusWatchRecord.getSubjectId());
		actionRecord.setUpdateTime(cusWatchRecord.getStartTime());
		if(customer!=null){
			actionRecord.setHeadImg(customer.getPhoto());
		}
		return actionRecord;
	}

	  /**
     * 添加新鲜事记录
     * @param actionRecord
     * @return
     * @throws Exception
     */
	public Integer addActionRecord(ActionRecord actionRecord) throws Exception {
		return simpleDao.createEntity("ActionRecord_NS.createActionRecord", actionRecord);
	}

	 /**
     * 定时查询学员注册信息来增加新鲜事
     * @throws Exception
     */
	public void addActionRecordByRegister() throws Exception {
		//查询学员注册信息
		List<Customer> customerList=simpleDao.getForList("ActionRecord_NS.getCustomerList", getBeginId(2));
		if(customerList!=null&&customerList.size()>0){
			for(Customer customer:customerList){
				addActionRecord(fillRegisterData(customer));
			}
			//更新begin_id_tbl中type为3的begin_id，2表示为学员注册
			updateBeginId(customerList.get(0).getCusId(),2);
		}
	}
	/**
	 * 通过学员注册信息填充到新鲜事中
	 * @param customer
	 * @param actionRecord
	 */
	private ActionRecord fillRegisterData(Customer customer){
		ActionRecord actionRecord=new ActionRecord();
		Subject subject=simpleDao.getEntity("ActionRecord_NS.getSubjectById", customer.getSubjectId());
		if(subject!=null){
			actionRecord.setContent("【"+subject.getSubjectName()+"】");
		}
		actionRecord.setCreateTime(customer.getRegTime());
		actionRecord.setCurrentType(3);//注册
		actionRecord.setCusEmail(customer.getEmail());
		actionRecord.setCusId(customer.getCusId());
		actionRecord.setCusName(customer.getCusName());
		actionRecord.setSubjectId(customer.getSubjectId());
		actionRecord.setUpdateTime(customer.getRegTime());
		return actionRecord;
	}
	/**
	 * 通过不同的类型获取需要插入到新鲜事数据的起始ID  type{2:学员注册,3:购买课程,4:考试}
	 * @param type
	 * @return
	 */
	private Integer getBeginId(Integer type){
		return simpleDao.getEntity("ActionRecord_NS.getBeginId", type);
	}
	/**
	 * 通过不同的类型的新鲜事来更新起始ID
	 * @param type
	 * @return
	 */
	private void updateBeginId(Integer beginId,Integer type){
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("beginId", beginId);
		map.put("type", type);
		simpleDao.updateEntity("ActionRecord_NS.updateBeginId", map);
	}

	/**
     * 定时查询订单信息来增加新鲜事
     * @throws Exception
     */
	public void addActionRecordByOrder() throws Exception {
		//查询订单编号集合用于查询订单流水
		List<Contract> contractList=simpleDao.getForList("ActionRecord_NS.getContractList", getBeginId(3));
		if(contractList!=null&&contractList.size()>0){
			for(Contract contract : contractList){
				addActionRecord(fillActionRecordByOrder(contract));
			}
			//更新begin_id_tbl中type为3的begin_id，3表示为学员注册
			updateBeginId(contractList.get(0).getId(),3);
		}
	}
	/**
	 * 根据订单信息填充新鲜事
	 * @param contract
	 * @return
	 */
	private ActionRecord fillActionRecordByOrder(Contract contract){
		ActionRecord actionRecord=new ActionRecord();
		Customer customer=customerService.getCustomerById(contract.getCusId());
		actionRecord.setCreateTime(contract.getCreateTime());
		actionRecord.setUpdateTime(contract.getCreateTime());
		actionRecord.setCurrentType(4);//4表示下单
		if(customer!=null){
			actionRecord.setCusEmail(customer.getEmail());
			actionRecord.setCusId(customer.getCusId());
			actionRecord.setCusName(customer.getCusName());
		}
		//获取订单流水信息
		List<CashRecord> cashRecordList=simpleDao.getForList("ActionRecord_NS.getCashRecordList", contract.getContractId());
		//循环订单流水，目的是将商品保存到content中
		if(cashRecordList!=null&&cashRecordList.size()>0){
			StringBuilder stringBuilder=new StringBuilder();
			for(CashRecord cashRecord : cashRecordList){
				stringBuilder.append(cashRecord.getCrInfo()+",");
			}
			actionRecord.setContent(stringBuilder.deleteCharAt(stringBuilder.length()-1).toString());
			actionRecord.setSubjectId(cashRecordList.get(0).getCrSubjectId());
		}
		return actionRecord;
	}

	 /**
     * 定时查询考试信息来增加新鲜事
     * @throws Exception
     */
	public void addActionRecordByExam() throws Exception {
		List<ActionRecordByExam> actionRecordByExamList=simpleDao.getForList("ActionRecord_NS.getActionRecordByExamList", getBeginId(4));//type为4表示获取begin_id_tbl表中type=4的begin_id
		NumberFormat percent = NumberFormat.getPercentInstance(); //获取百分比实例
    	percent.setMaximumFractionDigits(2);//设置保留小数点为2
		if(actionRecordByExamList!=null&&actionRecordByExamList.size()>0){
			for(ActionRecordByExam actionRecordByExam : actionRecordByExamList){
				addActionRecord(fillActionRecordByExam(percent,actionRecordByExam));
			}
			updateBeginId(actionRecordByExamList.get(0).getUserEpId(), 4);
		}
	}
	/**
	 * 根据考试信息填充新鲜事
	 * @param actionRecordByExam
	 * @return
	 */
	private ActionRecord fillActionRecordByExam(NumberFormat percent,ActionRecordByExam actionRecordByExam){
		ActionRecord actionRecord = new ActionRecord();
		actionRecord.setContent("【"+actionRecordByExam.getExamName()+"】的试卷，正确率为:"+percent.format(actionRecordByExam.getAccuracy()));
		actionRecord.setCreateTime(actionRecordByExam.getCreateTime());
		actionRecord.setCurrentType(5);//5表示考试
		actionRecord.setCusEmail(actionRecordByExam.getEmail());
		actionRecord.setCusId(actionRecordByExam.getCusId());
		actionRecord.setCusName(actionRecordByExam.getCusName());
		actionRecord.setUpdateTime(actionRecordByExam.getCreateTime());
		actionRecord.setSubjectId(actionRecordByExam.getSubjectId());
		return actionRecord;
	}
}
