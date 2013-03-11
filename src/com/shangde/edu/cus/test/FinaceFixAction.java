package com.shangde.edu.cus.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.MD5;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerDTO;
import com.shangde.edu.cus.domain.SubjectCustomerDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IStudyPlan;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.sys.service.IUserGradeSubjectRole;

/**  
 * 
 * @author chenshuai
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FinaceFixAction extends CommonAction{
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	/**
	 * 用户查询条件
	 */
	private QueryCustomerCondition queryCustomerCondition;


	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	
	/**
	 * 修复订单
	 * @return String
	 * @thorows Exception
	 */
	public String fixFinanceContract(){
		try {
			customerService.fixFinance();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 修复用户type等于1的。//查询有课程而没有流水的 补上订单流水
	 */
	public String cusTypeFix(){
		try{
			customerService.cusTypeFix();
			
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}
}
