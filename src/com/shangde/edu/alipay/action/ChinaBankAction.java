package com.shangde.edu.alipay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import beartool.MD5;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.ChinaBankInfo;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
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

public class ChinaBankAction extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn/
	 * 登陆后在上面的导航栏里可能找到“B2C”，在二级导航栏里有“MD5密钥设置”
	 * 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
	 */
	public static String SC_CHINA_BANK_KEY = "shangde^2011";
	
	/**
	 * 信息类
	 */
	private ChinaBankInfo chinaBankInfo;
	
	/**
	 * 订单服务对象
	 */
	private IContract contractService;
	
	/**
	 * 模板服务对象
	 */
	private ITemplate templateService;

	/**
	 * 流水服务对象
	 */
	private ICashRecord cashRecordService;
	
	/**
	 * 学员知识点关系类服务对象
	 */
	private ICusCouKpoint  cusCouKpointService;

	/**
	 * 知识点服务对象
	 */
	private IKpoint kpointService;
	
	/**
	 * 学员服务对象
	 */
	private ICustomer customerService;
	
	/**
	 * 消息接口服务对象
	 */
	private IMessage messageService;
	
	/**
	 * 用户小心服务对象
	 */
	private IUserMsg userMsgService;

	/**
	 * 短信服务对象
	 */
	private IsmsService smsService;
	
	/**
	 * MD5加密工具对象
	 */
	private MD5 md5 = new MD5();

	/**
	 * 头部模板内容
	 */
	private String headerHTML = "";
	
	/**
	 * 尾部模板内容
	 */
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
	
	public String goToChinaBank() throws IOException {
		// 初始化定义参数
		getChinaBankInfo().setV_mid("22077726");
		getChinaBankInfo().setV_url("http://test.highso.org/alipay/chinaBank!returnUrl.action");
		//根据订单号及登陆用户id获取订单金额
		getChinaBankInfo().setV_amount(getContractByParms(getLoginUserId()).getContractCutSumMoney() + "");
		getChinaBankInfo().setV_moneytype("CNY");
		getChinaBankInfo().setV_md5info(md5.getMD5ofStr(getMD5TextBefore())); //网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写
		getChinaBankInfo().setRemark1(getLoginUserId() + "");
		return "goToChinaBank";
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
	 * 同步返回调用方法
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String returnUrl() throws UnsupportedEncodingException {
		//获取参数
		initReturnParms();
		
		String v_md5text = md5.getMD5ofStr(getMD5TextAfter()).toUpperCase();
				
		if (getChinaBankInfo().getV_md5info() != null && getChinaBankInfo().getV_md5info().equals(v_md5text)) {
			if ("30".equals(getChinaBankInfo().getV_pstatus())) {
				getOut().print("支付失败");
			} else if ("20".equals(getChinaBankInfo().getV_pstatus())){
				proccessContractForPayOk("同步");
				
				processTempContent();
				return "cbReturn";
			}
		} else {
			getOut().print("校验失败,数据可疑");
		}
		return null;
	}

	/**
	 * 异步方式回调方法
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String notifyUrl() throws UnsupportedEncodingException {
		//获取参数
		initReturnParms();

		String v_md5text = md5.getMD5ofStr(getMD5TextAfter()).toUpperCase();
		
		if (getChinaBankInfo().getV_md5info() != null && getChinaBankInfo().getV_md5info().equals(v_md5text)) {
			if ("20".equals(getChinaBankInfo().getV_pstatus())) {
				proccessContractForPayOk("异步");
				
				getOut().print("ok"); // 告诉服务器验证通过,停止发送
				
				processTempContent();
			}
		} else {
			getOut().print("error");
		}
		return null;
	}
	
	/**
	 * 支付成功，处理订单数据
	 * @param returnType
	 */
	private void proccessContractForPayOk(String returnType) {
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
				ct.setRemark(returnType);//记录异步
				ct.setPayType(Contract.CONTRACT_PAY_TYPE_CB);//网银在线
				
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
		qcc.setContractId(getChinaBankInfo().getV_oid());
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
		if(getChinaBankInfo().getRemark1() != null && 
				!getChinaBankInfo().getRemark1().trim().equals("") && 
				getChinaBankInfo().getRemark1().split(",").length > 1) {
			try {
				return Integer.valueOf(getChinaBankInfo().getRemark1());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
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
		this.getCashRecordService().updateCashReocrdStatus(crList);
	}

	/**
	 * 根据课程初始化知识树
	 * @param courseId
	 */
	private void initKpointTreeByCashRecord(CashRecord cashRecord) {
		//查询某课程的知识点列表
		QueryKpointCondition queryKpointCondition = new QueryKpointCondition();
		queryKpointCondition.setCourseId(cashRecord.getCourseId());
		List<Kpoint> kpList = kpointService.getKpointListByCourseId(queryKpointCondition);
		
		//循环列表初始化用户知识树
		for(int j = 0; kpList != null && j < kpList.size(); j++){
			CusCouKpoint cck = new CusCouKpoint();
			cck.setCourseId(cashRecord.getCourseId());
			cck.setCusId(cashRecord.getCusId());
			cck.setPointId(kpList.get(j).getPointId());
			cck.setRdState(CusCouKpoint.CUSCOUKPOINT_DEFAULT_STATUS);
			cusCouKpointService.addCusCouKpoint(cck);
		}
	}
	
	/**
	 * 获取返回参数
	 */
	private void initReturnParms() {
		getChinaBankInfo().setV_oid(getServletRequest().getParameter("v_oid"));					// 订单号
		getChinaBankInfo().setV_pmode(getServletRequest().getParameter("v_pmode"));				// 支付方式中文说明，如"中行长城信用卡"
		getChinaBankInfo().setV_pstatus(getServletRequest().getParameter("v_pstatus")); 		// 支付结果，20支付完成；30支付失败；
		getChinaBankInfo().setV_pstring(getServletRequest().getParameter("v_pstring"));			// 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
		getChinaBankInfo().setV_amount(getServletRequest().getParameter("v_amount"));			// 订单实际支付金额
		getChinaBankInfo().setV_moneytype(getServletRequest().getParameter("v_moneytype"));		// 币种
		getChinaBankInfo().setV_md5info(getServletRequest().getParameter("v_md5str"));			// MD5校验码
		getChinaBankInfo().setRemark1(getServletRequest().getParameter("remark1"));				// 备注1
		getChinaBankInfo().setRemark2(getServletRequest().getParameter("remark2"));				// 备注2
	}
	
	/**
	 * 拼凑加密串
	 * @return
	 */
	private String getMD5TextBefore() {
		return getChinaBankInfo().getV_amount() + 				
					getChinaBankInfo().getV_moneytype() + 
					getChinaBankInfo().getV_oid() + 
					getChinaBankInfo().getV_mid() +
					getChinaBankInfo().getV_url() + 
					SC_CHINA_BANK_KEY;
	}
	
	/**
	 * 拼凑加密串
	 * @return
	 */
	private String getMD5TextAfter() {
		return getChinaBankInfo().getV_oid() + 
					getChinaBankInfo().getV_pstatus() + 
					getChinaBankInfo().getV_amount() + 
					getChinaBankInfo().getV_moneytype() + 
					SC_CHINA_BANK_KEY;
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

	public ChinaBankInfo getChinaBankInfo() {
		if(chinaBankInfo == null) {
			chinaBankInfo = new ChinaBankInfo();
		}
		return chinaBankInfo;
	}
	
	public void setChinaBankInfo(ChinaBankInfo chinaBankInfo) {
		this.chinaBankInfo = chinaBankInfo;
	}

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
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

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public static String getSC_CHINA_BANK_KEY() {
		return SC_CHINA_BANK_KEY;
	}

	public static void setSC_CHINA_BANK_KEY(String sc_china_bank_key) {
		SC_CHINA_BANK_KEY = sc_china_bank_key;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public MD5 getMd5() {
		return md5;
	}

	public void setMd5(MD5 md5) {
		this.md5 = md5;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
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
}
