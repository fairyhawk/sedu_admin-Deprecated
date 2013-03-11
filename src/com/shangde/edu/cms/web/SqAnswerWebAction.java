package com.shangde.edu.cms.web;

import java.io.IOException;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.PreventInfusion;
import com.shangde.edu.cms.domain.SqAnswer;
import com.shangde.edu.cms.service.ISqAnswer;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IUncoupon;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.sms.service.IsmsService;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class SqAnswerWebAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISqAnswer sqAnswerService;
	private ICustomer customerService;
	private IUncoupon uncouponService;
	private ICoupon couponService;
	
	/**
	 * 短信服务对象
	 */
	private IsmsService smsService;
	private ICpCus cpCusService;
	private SqAnswer sqAnswer = new SqAnswer();
	private Customer customer = new Customer();
	private String sumValue;
	private String stateStr;
	private String ranPwd = "";
	
//	/**
//	 * 保存问卷答案并注册
//	 * @return
//	 * @throws Exception 
//	 * @throws IOException 
//	 */
//	public String submitSurvey() throws Exception {
//		if(sumValue != null  &&  !sumValue.trim().equals("")) {
//			String[] values = sumValue.split("#elemt:");
//			sqAnswer.setEmail(customer.getEmail());
//			for(int i=1; i<values.length; i++) {
//				String[] strs = values[i].split("#opt:");
//				if(strs.length > 1) {
//					sqAnswer.setSqId(Integer.valueOf(strs[0]));
//					sqAnswer.setAsContent(strs[1]);
//					sqAnswer.setAsId(0);
//					sqAnswerService.addSqAnswer(sqAnswer);
//				}
//			}
//		}
//		Customer cus = customerService.getCustomerByEmail(customer.getEmail());
//		if(cus == null) {
//			ranPwd = customerService.genericRandomPwd();
//			String pwd = MD5.getMD5(ranPwd.toString());
//			customer.setCusPwd(pwd);
//			customerService.addCustomer(customer);
//			customerService.sendRegEmail(customer);
//			stateStr = "reg";
//		}
//		QueryUncouponCondition queryUncouponCondition = new QueryUncouponCondition();
//		queryUncouponCondition.setId(SURVEY_COUPON_ID);
//		queryUncouponCondition.setEmail(customer.getEmail());
//		List<Uncoupon> uncpList = uncouponService.getUncouponList(queryUncouponCondition);
//		if(uncpList == null || uncpList.size() == 0) {
//			String serialNumber = DateUtil.getCouponTime() + customer.getCusId();
//			Uncoupon uncoupon = new Uncoupon();
//			uncoupon.setSerialNumber(serialNumber);
//			uncoupon.setId(SURVEY_COUPON_ID);
//			uncoupon.setEmail(customer.getEmail());
//			
//			uncouponService.addUncoupon(uncoupon);
//			this.sendSms(serialNumber);
//			stateStr += "reg_" + serialNumber;
//		}
//		processUncoupon();
//		return "surveySuccess";
//	}
	
	/**
	 * 保存问卷答案发送序列号至手机(不注册，只有手机号码信息)
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String submitSurvey() throws Exception {
		if(sumValue != null  &&  !sumValue.trim().equals("")) {
			String[] values = sumValue.split("#elemt:");
			sqAnswer.setEmail(customer.getEmail());
			for(int i=1; i<values.length; i++) {
				String[] strs = values[i].split("#opt:");
				if(strs.length > 1) {
					sqAnswer.setSqId(Integer.valueOf(strs[0]));
					sqAnswer.setAsContent(PreventInfusion.safeRep(strs[1]));
					sqAnswer.setAsId(0);
					sqAnswerService.addSqAnswer(sqAnswer);
				}
			}
		}
		String serialNumber = DateUtil.getCouponTime() +  + new java.util.Random().nextInt(100000);
		Uncoupon uncoupon = new Uncoupon();
		uncoupon.setSerialNumber(serialNumber);
		uncoupon.setId(couponService.getCouponByPrice(10).getId());
		uncoupon.setStatus(Uncoupon.UNCP_STATUS_USABLE);
		
		uncouponService.addUncoupon(uncoupon);
		sendSms(serialNumber);
		stateStr += "reg_" + serialNumber;
		return "surveySuccess";
	}
	
//	/**
//	 * 为注册用户检查是否有优惠券
//	 * @throws IOException 
//	 * @throws IOException 
//	 */
//	private void processUncoupon() {
//		//检查是否申请过优惠券，申请过的添加优惠券用户关系
//		List<Uncoupon> list = uncouponService.getUncouponListByEmail(customer.getEmail());
//		if(list != null  &&  list.size() > 0) {
//			for(int i=0; i<list.size(); i++) {
//				Uncoupon uncoupon = list.get(i);
//				CpCus cpCus = new CpCus();
//				cpCus.setCusId(customer.getCusId());
//				cpCus.setId(uncoupon.getId());
//				cpCus.setSerialNumber(uncoupon.getSerialNumber());
//				cpCus.setStatus(CpCus.CPCUS_DEFAULT_STATUS);
//				cpCusService.addCpCus(cpCus);
//			}
//		}
//	}

	/**
	 * 生成验证码并发送短信到用户手机
	 * @throws Exception
	 */
	public void sendSms(String content) throws Exception {
		//发送短信到用户手机
		smsService.sendEx("谢谢参加嗨学网的有奖调查活动，您已获得一张价值10元的优惠券，序列号为："
								+ content + "，请尽快登陆您的空间充入优惠券，祝学习愉快。", customer.getMobile(), "", "", "");
	}
	
	public String getSumValue() {
		return sumValue;
	}

	public void setSumValue(String sumValue) {
		this.sumValue = sumValue;
	}

	public void setSqAnswerService(ISqAnswer sqAnswerService) {
		this.sqAnswerService = sqAnswerService;
	}

	public SqAnswer getSqAnswer() {
		return sqAnswer;
	}

	public void setSqAnswer(SqAnswer sqAnswer) {
		this.sqAnswer = sqAnswer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public void setUncouponService(IUncoupon uncouponService) {
		this.uncouponService = uncouponService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getRanPwd() {
		return ranPwd;
	}

	public void setRanPwd(String ranPwd) {
		this.ranPwd = ranPwd;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}
}
