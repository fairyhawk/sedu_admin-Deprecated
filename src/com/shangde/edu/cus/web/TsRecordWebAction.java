package com.shangde.edu.cus.web;


import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;


/**
 * 
 * @author miaoshusen
 * @version 1.0
 */

public class TsRecordWebAction extends CommonAction {
	
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	
	private ITotolsScore totolsScoreService;
	/**
	 * 查询条件
	 */
	private QueryTsRecordCondition queryTsRecordCondition;
	/**
	 * 积分记录的实体对象
	 */
	private TsRecord tsRecord;
	
	private TotolsScore totolsScore;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> customerList=new ArrayList<Customer>();
	/**
	 * 切换参数
	 */
	private int location;
	
	/**
	 * 获得积分列表的页面
	 * 
	 * @return
	 */
	public String getTsRecordListByCus() {
		try {
			int userId = getLoginUserId();
			totolsScore = totolsScoreService.getTotolsScore(userId);
			getQueryTsRecordCondition().setCusId(userId);
			queryTsRecordCondition.setTrType(1);
			setPage(tsRecordService.getTsRecordListByCus(queryTsRecordCondition));
			setPageUrlParms();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "getTsRecordListByCus";
	}
	/**
	 * 跳转到帮助页面
	 * 
	 * @return
	 */
	public String getTsRecordHelp(){
		try {
			PageResult p=new PageResult();
			p.setCurrentPage(1);
			this.setPage(p);
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "getTsRecordListByCus";
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

	public TotolsScore getTotolsScore() {
		return totolsScore;
	}

	public void setTotolsScore(TotolsScore totolsScore) {
		this.totolsScore = totolsScore;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
