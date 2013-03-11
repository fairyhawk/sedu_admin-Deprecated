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

public class TotolsScoreAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(TotolsScoreAction.class);
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 查询条件
	 */
	private QueryTotolsScoreCondition queryTotolsScoreCondition;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> customerList=new ArrayList<Customer>();
	
	/**
	 * 积分的实体对象
	 */
	private TotolsScore totolsScore;
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 查询条件
	 */
	private QueryTsRecordCondition queryTsRecordCondition;
	
	
	
	/**
	 * 获得积分列表的页面
	 * 
	 * @return
	 */
	public String getTotolsScoreList() {
		try {
			if(totolsScore!=null){
			if(totolsScore.getTsCurrent()!=0){
				this.getQueryTotolsScoreCondition().setTsCurrent(totolsScore.getTsCurrent());
			}
			if(totolsScore.getCusId()!=0){
				this.getQueryTotolsScoreCondition().setCusId(totolsScore.getCusId());
			}
			}
			customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
			setPage(this.totolsScoreService.getTotolsScorePageList(this.getQueryTotolsScoreCondition()));
			setPageUrlParms();
			
		} catch (Exception ex) {
			logger.error("TotolsScoreAction.getTotolsScoreList", ex);
			return ERROR;
		}
		return "getTotolsScoreList";
	}
	
	/**
	 * 删除积分连同积分下面的积分详细记录一起删除
	 * 
	 * @return
	 */
	public String delete() {
		try {
			if(totolsScore.getTsId()!=0){
				//删除积分详细记录表的记录
				this.getQueryTsRecordCondition().setTsId(totolsScore.getTsId());
				List <TsRecord>tsRecordList=this.tsRecordService.getTsRecordList(getQueryTsRecordCondition());
				for(int i=0;tsRecordList!=null&&i<tsRecordList.size();i++){
					if(tsRecordList.get(i).getTrId()!=0){
					this.tsRecordService.delTsRecordById(tsRecordList.get(i).getTrId());
					}
				}
				//删除积分表的记录
				this.totolsScoreService.delTotolsScoreById(totolsScore.getTsId());
				
			}
			
		} catch (Exception ex) {
			logger.error("TotolsScoreAction.delete", ex);
			return ERROR;
		}
		return "deleteSuccess";
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}


	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}


	public QueryTotolsScoreCondition getQueryTotolsScoreCondition() {
		if(this.queryTotolsScoreCondition == null){
			this.queryTotolsScoreCondition = new QueryTotolsScoreCondition();
		}
		return queryTotolsScoreCondition;
	}


	public void setQueryTotolsScoreCondition(
			QueryTotolsScoreCondition queryTotolsScoreCondition) {
		this.queryTotolsScoreCondition = queryTotolsScoreCondition;
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

}
