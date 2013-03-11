package com.shangde.edu.alipay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.kqutil.KQMD5Util;
import com.shangde.common.vo.KQInfo;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;

public class KQAction extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 声名logger
	 */
	private Logger logger = LoggerFactory.getLogger(KQAction.class.getName());
	
	/**快钱参数信息类*/
	private KQInfo kQInfo = new KQInfo();
	
	/**模板服务对象*/
	private ITemplate templateService;
	
	/**订单服务对象*/
	private IContract contractService;
	
	/**学员服务对象*/
	private ICustomer customerService;

	/**流水服务对象*/
	private ICashRecord cashRecordService;
	
	/**消息接口服务对象*/
	private IMessage messageService;
	
	/**用户小心服务对象*/
	private IUserMsg userMsgService;

	/**短信服务对象*/
	private IsmsService smsService;
	
	/**头部模板内容*/
	private String headerHTML = "";
	
	/**尾部模板内容*/
	private String footerHTML = "";
	
	/**
	 * 售卖方式服务
	 */
	private ISellWay sellWayService ;
	/**
	 * 售卖方式实体
	 */
	private SellWay sWay ;
	/**
	 * 流水实体
	 */
	private CashRecord cashRecord ; 
	/**
	 * 流水条件查询
	 */
	private QueryCashRecordCondition queryCashRecordCondition ;
	
	/**
	 * 转向块钱
	 * @return
	 * @throws IOException
	 */
	public String goToKQ() throws IOException {
		DecimalFormat df = new DecimalFormat("#.00 ");
		String money=df.format(Float.parseFloat(getContractByParms(getLoginUserId()).getContractCutSumMoney().toString()));
		money=String.valueOf((Float.parseFloat(money)*100)).replace(".0", "");//快钱2相当于0.02
		getKQInfo().setOrderAmount(money);
		//getKQInfo().setOrderAmount("1") ;
		getKQInfo().setExt1(getLoginUserId() + "");
		if(getKQInfo().getBankId() != null && !getKQInfo().getBankId().trim().equals("")) {
			getKQInfo().setPayType("10");
		}
		getKQInfo().setRtnUrl("http://test.highso.org/alipay/KQ!returnURL.action");
		getKQInfo().setBgUrl("http://test.highso.org/alipay/KQ!returnURL.action");
		getKQInfo().setSignMsg(createSignMsg());
		return "goToKQ";
	}
	
	/**
	 * 快钱回调函数
	 * @return
	 */
	public String returnURL() {
			try {
				initReturnParms();
				getKQInfo().setMerchantSignMsgVal(createMerchantSignMsg());
				
				//初始化结果及地址
				getKQInfo().setRtnOk(1);
				//商家进行数据处理，并跳转会商家显示支付结果的页面
				///首先进行签名字符串验证
				if(getKQInfo().getSignMsg().toUpperCase().equals(getKQInfo().getMerchantSignMsgVal().toUpperCase())) {
					///接着进行支付结果判断
					if(Integer.parseInt(getKQInfo().getPayResult()) == 10) {
						//*  
						// 商户网站逻辑处理，比方更新订单支付状态为成功
						// 特别注意：只有signMsg.toUpperCase().equals(merchantSignMsg.toUpperCase())，且payResult=10，才表示支付成功！同时将订单金额与提交订单前的订单金额进行对比校验。
						//*
						//报告给快钱处理结果，并提供将要重定向的地址。
						proccessContractForPayOk();
						
						processTempContent();
						getKQInfo().setRtnUrl("http://test.highso.org/cus/cuslimit!toPayOk.action?msg=success!");
						//getKQInfo().setRtnUrl("http://highso.org.cn/cus/cuslimit!toPayOk.action?msg=success!");
					} else {
						getKQInfo().setRtnUrl("http://test.highso.org/cus/cuslimit!toPayOk.action?msg=false!");
						//getKQInfo().setRtnUrl("http://highso.org.cn/cus/cuslimit!toPayOk.action?msg=false!");
					}
				}else{
					getKQInfo().setRtnUrl("http://test.highso.org/cus/cuslimit!toPayOk.action?msg=error!");
					//getKQInfo().setRtnUrl("http://highso.org.cn/cus/cuslimit!toPayOk.action?msg=error!");
				}
				//以下报告给快钱处理结果，并提供将要重定向的地址
				getServletResponse().getOutputStream().print("<result>" + getKQInfo().getRtnOk() + "</result><redirecturl>" + getKQInfo().getRtnUrl() + "</redirecturl>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * 支付成功，处理订单数据
	 * @param returnType
	 */
	private void proccessContractForPayOk() {
		// 支付成功，在这里写入数据处理,通过订单号及用户id查找订单记录
		Contract ct = getContractByParms(getCusIdByParm());
		
		//说明正常在系统内查到订单
		if(ct != null){
			Customer cus = customerService.getCustomerById(ct.getCusId());
			
			//检查学员类型
			checkCusType(cus, ct);
			
			//增加访问次数
			ct.setCallSum(ct.getCallSum() + 1);
			
			//改变订单状态
			if(ct.getStatus() != Contract.CONTRACT_STATUS_PAIED){
				ct.setStatus(Contract.CONTRACT_STATUS_PAIED);//已付款
				ct.setPayTime(new Date());//记录支付时间
				ct.setRemark("快钱");//记录异步
				ct.setPayType(Contract.CONTRACT_PAY_TYPE_KQ);//快钱支付
				
				//初始化知识树
				initKpointTreeByContract(ct);
				
				//发送购买成功消息
				sendBuySuccMsgToUser(cus);
				
				//支付成功发送短信给客户
				sendBuySuccMsgToMobile(cus);
				//谢添加修改支付状态
				if(ct.getId()!=0)
				{
				String couponId=sellWayService.getContractForCouponIdById(String.valueOf(ct.getId()));
				if(!couponId.equals("")&&couponId!=null)
				{
				int couponTypeId=sellWayService.GetParentIdBycouponId(couponId);
				sellWayService.updateCouponForPayState(couponId, couponTypeId);
				}
				}
				//谢添加结束
			}
			contractService.updateContract(ct);
			//如果订单是升级课程的订单则把以前的订单流水状态改为无效
			upOld(ct) ;
		}
	}
	/**
	 * 判断订单是否是升级课程的订单，是，就把升级课程前的订单流水状态改为无效
	 * 系统目前是通过售卖方式来出售课程的。如：售卖方式A包含1,2,3课程，售卖方式B包含1,2,3,4,5,6课程，
	 * 当用户已经购买售卖方式A进行学习的时候，需要在学习结束之后学习4,5,6课程，而4,5,6课程在系统上没有
	 * 单独的售卖方式出售，他必须通过购买售卖方式B来学习剩下的课程，由于以前的课程是不能退的，充分考虑
	 * 用户的权益，当他购买售卖方式B的时候，减去售卖方式A的价值。当然他以前的订单也随之作废，将他以前的
	 * 订单的流水的状态改为无效。
	 */
	public void upOld(Contract ct) {
		//查询订单下的所有流水
		List<CashRecord> cdList = cashRecordService.getAllCash(ct.getContractId());
		Set settmp = new HashSet<String>(); 
		for (CashRecord cd : cdList) {
			//查询流水的售卖方式
			sWay = sellWayService.getSellWayById(cd.getPackId());
			if (settmp.contains(cd.getPackId())){
				continue;
			}
			settmp.add(cd.getPackId());
			//判断流水的售卖方式是否是可升级的
			if (!StringUtils.isEmpty(sWay.getSellMark())) {
				getQueryCashRecordCondition().setUserId(ct.getCusId());
				getQueryCashRecordCondition().setStatus(1);
				getQueryCashRecordCondition().setPackId(
						Integer.parseInt(sWay.getSellMark()));
				//查询出满足条件的流水
				List<CashRecord> cList = cashRecordService
						.getCashRecordByList(getQueryCashRecordCondition());
				if (cList != null && cList.size() != 0) {
					//改变流水的状态
					for(CashRecord cdClear: cList){
						cdClear.setStatus(0) ;
						cashRecordService.updateCashRecord(cdClear);
					}
				}
			} 
		}
	}
	
	/**
	 * 发送短信通知购买成功
	 * @param cusId
	 */
	private void sendBuySuccMsgToMobile(Customer cus) {
		if(cus.getMobile() != null && !"".equals(cus.getMobile().trim())){
			try {
				smsService.sendEx("【嗨学网】您已支付成功，进入[我的highso]，点击我的课程，开始学习吧！", cus.getMobile(), "", "", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送购买成功消息
	 */
	private void sendBuySuccMsgToUser(Customer cus) {
		Message msg = messageService.getMessageByKey("buy");
		if(msg != null && msg.getMsgId() > 0){
			User sender = new User();
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			int userId = this.getLoginUserId();
			if(cus != null){
				userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(), cus);
			}
		}
	}

	/**
	 * 根据订单初始化学员的知识树
	 */
	private void initKpointTreeByContract(Contract ct) {
		//通过订单id查询流水记录
		QueryCashRecordCondition qcrc = new QueryCashRecordCondition();
		qcrc.setCtId(ct.getId());
		List<CashRecord> crList = cashRecordService.getCashRecordByList(qcrc);//流水记录
		//在支付成功后修改流水状态
		getCashRecordService().updateCashReocrdStatus(crList);
	}

	/**
	 * 如果是临时学员或内部体验账号，那么删除他的临时信息（除了刚刚购支付的订单所对应的数据），并置状态为注册学员
	 * @param cus
	 * @param ct
	 */
	private void checkCusType(Customer cus, Contract ct) {
		if(cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP_MONTH) {
			customerService.recoverTempCustomer(ct.getCusId(), ct.getId());
			cus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
			customerService.updateCustomer(cus);
		}
	}

	/**
	 * 通过参数获取订单
	 * @return
	 */
	private Contract getContractByParms(int cusId) {
		QueryContractCondition qcc = new QueryContractCondition();
		qcc.setContractId(getKQInfo().getOrderId());
		qcc.setUserId(cusId);
		PageResult  ctList = contractService.getContractList(qcc);
		if(ctList != null && ctList.getPageResult() != null && ctList.getPageResult().size() > 0) {
			return (Contract)ctList.getPageResult().get(0);
		}
		return null;
	}
	
	/**
	 * 根据返回参数，获取用户id
	 * @return
	 */
	private int getCusIdByParm() {
		if(getKQInfo().getExt1() != null && !getKQInfo().getExt1().trim().equals("")) {
			try {
				return Integer.valueOf(getKQInfo().getExt1());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 生成访问快钱加密串，顺序不可变
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String createSignMsg() throws UnsupportedEncodingException {
		String signMsgVal = "";
		signMsgVal = appendParam(signMsgVal,"inputCharset", getKQInfo().getInputCharset());
		signMsgVal = appendParam(signMsgVal,"pageUrl", getKQInfo().getPageUrl());
		signMsgVal = appendParam(signMsgVal,"bgUrl", getKQInfo().getBgUrl());
		signMsgVal = appendParam(signMsgVal,"version", getKQInfo().getVersion());
		signMsgVal = appendParam(signMsgVal,"language", getKQInfo().getLanguage());
		signMsgVal = appendParam(signMsgVal,"signType", getKQInfo().getSignType());
		signMsgVal = appendParam(signMsgVal,"merchantAcctId", getKQInfo().getMerchantAcctId());
		signMsgVal = appendParam(signMsgVal,"payerName", getKQInfo().getPayerName());
		signMsgVal = appendParam(signMsgVal,"payerContactType", getKQInfo().getPayerContactType());
		signMsgVal = appendParam(signMsgVal,"payerContact", getKQInfo().getPayerContact());
		signMsgVal = appendParam(signMsgVal,"orderId", getKQInfo().getOrderId());
		signMsgVal = appendParam(signMsgVal,"orderAmount", getKQInfo().getOrderAmount());
		signMsgVal = appendParam(signMsgVal,"orderTime", getKQInfo().getOrderTime());
		signMsgVal = appendParam(signMsgVal,"productName", getKQInfo().getProductName());
		signMsgVal = appendParam(signMsgVal,"productNum", getKQInfo().getProductNum());
		signMsgVal = appendParam(signMsgVal,"productId", getKQInfo().getProductId());
		signMsgVal = appendParam(signMsgVal,"productDesc", getKQInfo().getProductDesc());
		signMsgVal = appendParam(signMsgVal,"ext1", getKQInfo().getExt1());
		signMsgVal = appendParam(signMsgVal,"ext2", getKQInfo().getExt2());
		signMsgVal = appendParam(signMsgVal,"payType", getKQInfo().getPayType());
		signMsgVal = appendParam(signMsgVal,"bankId", getKQInfo().getBankId());
		signMsgVal = appendParam(signMsgVal,"redoFlag", getKQInfo().getRedoFlag());
		signMsgVal = appendParam(signMsgVal,"pid", getKQInfo().getPid());
		signMsgVal = appendParam(signMsgVal,"key", getKQInfo().getKey());
		
		return KQMD5Util.md5Hex(signMsgVal.getBytes("UTF-8")).toUpperCase();
	}
	
	/**
	 * 获取快钱返回参数
	 */
	private void initReturnParms() {
		getKQInfo().setMerchantAcctId((String)getServletRequest().getParameter("merchantAcctId").trim());
		getKQInfo().setVersion((String)getServletRequest().getParameter("version").trim());
		getKQInfo().setLanguage((String)getServletRequest().getParameter("language").trim());
		getKQInfo().setSignType((String)getServletRequest().getParameter("signType").trim());
		getKQInfo().setPayType((String)getServletRequest().getParameter("payType").trim());
		getKQInfo().setBankId((String)getServletRequest().getParameter("bankId").trim());
		getKQInfo().setOrderId((String)getServletRequest().getParameter("orderId").trim());
		getKQInfo().setOrderTime((String)getServletRequest().getParameter("orderTime").trim());
		getKQInfo().setOrderAmount((String)getServletRequest().getParameter("orderAmount").trim());
		getKQInfo().setDealId((String)getServletRequest().getParameter("dealId").trim());
		getKQInfo().setBankDealId((String)getServletRequest().getParameter("bankDealId").trim());
		getKQInfo().setDealTime((String)getServletRequest().getParameter("dealTime").trim());
		getKQInfo().setPayAmount((String)getServletRequest().getParameter("payAmount").trim());
		getKQInfo().setFee((String)getServletRequest().getParameter("fee").trim());
		getKQInfo().setExt1((String)getServletRequest().getParameter("ext1").trim());
		getKQInfo().setExt2((String)getServletRequest().getParameter("ext2").trim());
		getKQInfo().setPayResult((String)getServletRequest().getParameter("payResult").trim());
		getKQInfo().setErrCode((String)getServletRequest().getParameter("errCode").trim());
		getKQInfo().setSignMsg((String)getServletRequest().getParameter("signMsg").trim());
	}
	
	/**
	 * 生成返回加密串，顺序不可变
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String createMerchantSignMsg() throws UnsupportedEncodingException {
		String merchantSignMsgVal = "";
		
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"merchantAcctId", getKQInfo().getMerchantAcctId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"version", getKQInfo().getVersion());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"language", getKQInfo().getLanguage());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"signType", getKQInfo().getSignType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payType", getKQInfo().getPayType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankId", getKQInfo().getBankId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderId", getKQInfo().getOrderId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderTime", getKQInfo().getOrderTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderAmount", getKQInfo().getOrderAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealId", getKQInfo().getDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankDealId", getKQInfo().getBankDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealTime", getKQInfo().getDealTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payAmount", getKQInfo().getPayAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"fee", getKQInfo().getFee());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext1", getKQInfo().getExt1());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext2", getKQInfo().getExt2());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payResult", getKQInfo().getPayResult());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"errCode", getKQInfo().getErrCode());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"key", getKQInfo().getKey());

		return KQMD5Util.md5Hex(merchantSignMsgVal.getBytes("UTF-8")).toUpperCase();
	}
	
	/**
	 * 将变量值不为空的参数组成字符串
	 */
	private String appendParam(String returnStr,String paramId,String paramValue) {
		if(returnStr != null && !returnStr.equals("")) {
			if(paramValue != null && !paramValue.equals("")) {
				returnStr = returnStr + "&" + paramId + "=" + paramValue;
			}
		} else {
			if(paramValue != null && !paramValue.equals("")) {
				returnStr = paramId + "=" + paramValue;
			}
		}	
		return returnStr;
	}

	public KQInfo getKQInfo() {
		if(kQInfo == null) {
			kQInfo = new KQInfo();
		}
		return kQInfo;
	}

	public void setKQInfo(KQInfo info) {
		kQInfo = info;
	}
	
	/**
	 * 获取前台静态模板
	 */
	private void processTempContent() {
		try {
			QueryTemplateCondition queryTemplateCondition = new QueryTemplateCondition();
			queryTemplateCondition.setTmpName("web_header_org");
			List<Template> tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				headerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}

			queryTemplateCondition.setTmpName("web_footer_org");
			tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				footerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	public String getHeaderHTML() {
		return headerHTML;
	}

	public void setHeaderHTML(String headerHTML) {
		this.headerHTML = headerHTML;
	}

	public String getFooterHTML() {
		return footerHTML;
	}

	public void setFooterHTML(String footerHTML) {
		this.footerHTML = footerHTML;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}
	
	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public SellWay getSWay() {
		return sWay;
	}

	public void setSWay(SellWay way) {
		sWay = way;
	}
	
	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if (queryCashRecordCondition == null) {
			queryCashRecordCondition = new QueryCashRecordCondition();
		}
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public CashRecord getCashRecord() {
		return cashRecord;
	}

	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
	}
	
	/**
		<div align="center" style="font-size=12px;font-weight: bold;color=red;">
			<form name="kqPay" action="https://www.99bill.com/gateway/recvMerchantInfoAction.htm" method="post">
				<input type="hidden" name="inputCharset" value="<%=inputCharset %>"/>
				<input type="hidden" name="bgUrl" value="<%=bgUrl %>"/>
				<input type="hidden" name="pageUrl" value="<%=pageUrl %>"/>
				<input type="hidden" name="version" value="<%=version %>"/>
				<input type="hidden" name="language" value="<%=language %>"/>
				<input type="hidden" name="signType" value="<%=signType %>"/>
				<input type="hidden" name="signMsg" value="<%=signMsg %>"/>
				<input type="hidden" name="merchantAcctId" value="<%=merchantAcctId %>"/>
				<input type="hidden" name="payerName" value="<%=payerName %>"/>
				<input type="hidden" name="payerContactType" value="<%=payerContactType %>"/>
				<input type="hidden" name="payerContact" value="<%=payerContact %>"/>
				<input type="hidden" name="orderId" value="<%=orderId %>"/>
				<input type="hidden" name="orderAmount" value="<%=orderAmount %>"/>
				<input type="hidden" name="orderTime" value="<%=orderTime %>"/>
				<input type="hidden" name="productName" value="<%=productName %>"/>
				<input type="hidden" name="productNum" value="<%=productNum %>"/>
				<input type="hidden" name="productId" value="<%=productId %>"/>
				<input type="hidden" name="productDesc" value="<%=productDesc %>"/>
				<input type="hidden" name="ext1" value="<%=ext1 %>"/>
				<input type="hidden" name="ext2" value="<%=ext2 %>"/>
				<input type="hidden" name="payType" value="<%=payType %>"/>
				<input type="hidden" name="bankId" value="<%=bankId %>"/>
				<input type="hidden" name="redoFlag" value="<%=redoFlag %>"/>
				<input type="hidden" name="pid" value="<%=pid %>"/>
					
				<input type="submit" name="submit" value="提交到快钱">
			</form>		
	*/
}
