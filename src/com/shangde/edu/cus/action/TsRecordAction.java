package com.shangde.edu.cus.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;


/**
 * 
 * @author miaoshusen
 * @version 1.0
 */

public class TsRecordAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(TsRecordAction.class);
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 查询条件
	 */
	private QueryTsRecordCondition queryTsRecordCondition;
	/**
	 * 积分记录的实体对象
	 */
	private TsRecord tsRecord;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> customerList=new ArrayList<Customer>();
	
	/**
	 * 获得积分列表的页面
	 * 
	 * @return
	 */
	public String getTsRecordList() {
		try {
			if(tsRecord.getTsId()!=0){
				this.getQueryTsRecordCondition().setTsId(tsRecord.getTsId());
			}
			customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
			setPage(this.tsRecordService.getTsRecordPageList(getQueryTsRecordCondition()));
			setPageUrlParms();
			
		} catch (Exception ex) {
			logger.error("TsRecordAction.getTsRecordList",ex);
			return ERROR;
		}
		return "getTsRecordList";
	}



	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}


	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}


	public QueryTsRecordCondition getQueryTsRecordCondition() {
		if(this.queryTsRecordCondition == null){
			this.queryTsRecordCondition = new QueryTsRecordCondition();
		}
		return queryTsRecordCondition;
	}


	public void setQueryTsRecordCondition(
			QueryTsRecordCondition queryTsRecordCondition) {
		this.queryTsRecordCondition = queryTsRecordCondition;
	}



	public TsRecord getTsRecord() {
		return tsRecord;
	}



	public void setTsRecord(TsRecord tsRecord) {
		this.tsRecord = tsRecord;
	}



	public ICustomer getCustomerService() {
		return customerService;
	}



	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}



	public QueryCustomerCondition getQueryCustomerCondition() {
		if(this.queryCustomerCondition == null){
			this.queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}



	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}



	public List<Customer> getCustomerList() {
		return customerList;
	}



	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	

}
