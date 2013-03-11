package com.shangde.common.task;

import java.util.List;

import com.shangde.common.vo.MessageRemindDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.sms.service.IsmsService;

/**
 * 定时任务
 * @author zhouZhiQiang
 *
 */
public class MessageRemindQuartzTask {
	
	private IsmsService smsService;
	private ICustomer customerService;
	private IContract contractService;
	
	/**
	 * 
	 * @author zhouzhiqiang
	 * 功能：给下订单后60分钟未付款的学员发送短信提醒
	 * @param args
	 */
	protected void messageRemind(){
		try {
			QueryContractCondition queryContractCondition = new QueryContractCondition();
			//未付款状态为0;
			queryContractCondition.setStatus(0);
			List<MessageRemindDTO> messageRemindDTOList = contractService.getEmailByContractStatus(queryContractCondition);
			for(int i=0; i<messageRemindDTOList.size(); i++) {
				try {
					MessageRemindDTO messageRemindDTO = messageRemindDTOList.get(i);
					smsService.sendEx("【嗨学网】您在嗨学网订购的课程，订单优惠还将为您保留2天，请及时登陆http://" + messageRemindDTO.getCusFromUrl() + "，体验最具性价比的课程吧。", messageRemindDTO.getMobile(), "", "", "");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
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
}
