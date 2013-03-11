package com.shangde.common.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.tmp.domain.TmpStUser;
import com.shangde.edu.tmp.service.ITmpStUserService;

/**
 * 
 * @author Yangning
 *	定时删除前台临时开通用户
 */
public class DayTaskTmpQuartzTask {
	
	
	private static final Logger logger = Logger.getLogger(DayTaskTmpQuartzTask.class);
	private IContract contractService;
	private QueryCustomerCondition queryCustomerCondition;
	private ICustomer customerService;
	
	private ITmpStUserService tmpStUserService;
	
	
	/**
	 *定时删除前台临时开通用户 
	 */
	@SuppressWarnings("unchecked")
	public void tmpCustomerEveryDay(){
		try{
			logger.info("tmpCustomerEveryDay___");
			Date endTime = new Date();
			List<TmpStUser> tmpList = tmpStUserService.getTmpStUserNotValid(endTime);
			logger.info("tmpCustomerEveryDay___2");
			StringBuffer sb = new StringBuffer();
			//没有可删除用户跳过
			if(tmpList != null && tmpList.size() > 0){
				for (TmpStUser tmpStUser : tmpList) {
					//查看用户是否已有支付成功或已退费的订单
					int paidCount = tmpStUserService.getPaidContractByCusId(tmpStUser.getCusId());
					if(paidCount == 0){
						sb.append(tmpStUser.getCusId());
						sb.append(",");
					}
				}
				
				if(sb.length() > 2){
					logger.info("tmpCustomerEveryDay___3");
					sb = sb.delete(sb.length() - 1, sb.length());
					//更新临时表用户状态
					
					//防止删除非临时用户
					int count = tmpStUserService.getCustomerNotTempCount(sb.toString());
					logger.info("tmpCustomerEveryDay___4" + count);
					//冻结用户
					tmpStUserService.deleteCusBycusIds(sb.toString());
					logger.info("del ids" + sb.toString());
					tmpStUserService.updateStatTmpStuserByCusIdBath(sb.toString());
				}
		}
		}catch(Exception e){
			logger.error("DayTaskTmpQuartzTask.tmpCustomerEveryDay", e);
		}
	}
	
	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}


	public QueryCustomerCondition getQueryCustomerCondition() {
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public ITmpStUserService getTmpStUserService() {
		return tmpStUserService;
	}

	public void setTmpStUserService(ITmpStUserService tmpStUserService) {
		this.tmpStUserService = tmpStUserService;
	}

}
