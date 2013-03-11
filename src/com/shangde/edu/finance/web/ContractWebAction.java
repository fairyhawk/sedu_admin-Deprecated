package com.shangde.edu.finance.web;


import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.GetHttpMessage;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.ICustomer;
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

/**
 * 订单管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class ContractWebAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 声名订单的PO对象
	 */
	private Contract contract=new Contract();
	/**
	 * 查询条件
	 */
	private String searchKey;

	/**
	 * 知识点查询条件
	 */
	private QueryContractCondition queryContractCondition;
	
	/**
	 * 售卖方式查询条件
	 * 
	 */
	private QuerySellWayCondition querySellWayCondition;
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	private ICashRecord cashRecordService;
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List <Customer>customerList=new ArrayList<Customer>(); 
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	private List<Course> courseList;
	/**
	 * 声名logger
	 */
	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 课程分类服务Service
	 */
	private ICoursesort coursesortService; 
	
	private Customer customer;
	
	/**
	 * 用户id
	 * 
	 */
	private int userid;
	/**
	 * 课程id
	 */
	private int cousid;
	/**
	 * 表单编号
	 */
	private String oderid;
	
	private List<CourseSortListDTO> courseSortListDTOList;
	/**
	 * 订单条数
	 */
	private int sumCount;
	/**
	 * 切换参数
	 */
	private int location;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 亿起发返回订单数据服务
	 */
	private GetHttpMessage getMsg ;
	
	/**
	 * 短信服务
	 */
	private IsmsService smsService;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;

	private Address address;
	
	private IAddress addressService;
	
	private boolean changeAddress = false;
	private int type;//初始化值
	
	//优惠券id
	private String  couponId;
	
	/**
	 * 应付价格
	 */
	private String paymoney;
	
	/**
	 * 货到付款激活码
	 */
	private String contractCDkey;
	
	private IUserMsg userMsgService;
	
	private IMessage messageService;
	
	private int addressId;
	private QuerySellCouCondition querySellCouCondition;
	private ISellCou sellCouService;
	private ISellWay sellWayService ;
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	/**
	 * 根据用户id和包id查询订单
	 */
	@SuppressWarnings("unused")
	public String GetUseroder()
	{
		
		try{
			CashRecord cr=new CashRecord();
			Contract ct=new Contract();
			cr.setCusId(userid);
			cr.setPackId(cousid);
			String oderid1 = cashRecordService.getUseroderbyid(cr);//查询订单号
			ct.setCusId(userid);
			ct.setContractId(oderid1);
			
			
			Contract ctorder=new Contract();
			ctorder=contractService.getUserOderByid(ct);//查询订单整条记录
			
			String status = ctorder.getContractId()+","+ctorder.getContractCutSumMoney();
			setResult(new Result<String>(false,status, null, status));
			return "getuseroder";
		} catch (Exception e) {
		logger.error("前台/购物车/订单查询/Exception:"+e.getMessage());
		e.printStackTrace();
		return ERROR;
		
		}
		
		
	}
	/**
	 * 查询表单是否成功
	 * 
	 */
	@SuppressWarnings("unused")
	public String getIsoder()
	{
		try{
			Contract ct=new Contract();
			ct.setCusId(userid);
			ct.setContractId(oderid);
			
			
			String status =new Integer(contractService.getIsoder(ct)).toString();
			if("1".equals(status)){
				int userId = getLoginUserId() ;
				if(userId!=0){
					getQueryContractCondition().setUserId(userId) ;
				}
				ct = contractService.getContractByCus_Id(userId) ;
				//String[] GA_result={ct.getContractId(),ct.getContractCutSumMoney().toString()} ;
				//List<CashRecord> cash_list = cashRecordService.getAllCash(ct.getContractId()) ;
				List<Integer> cashPackId = cashRecordService.getCashPackId(ct.getContractId()) ;
				StringBuffer msg = new StringBuffer();
				msg.append(ct.getContractId()+","+ct.getContractCutSumMoney()+";") ;
				for(Integer i : cashPackId){
					int sku = sellWayService.getSellWayById(i).getSellId() ;
					String info = sellWayService.getSellWayById(i).getSellName() ;
					float price = sellWayService.getSellWayById(i).getSellPrice() ;
					msg.append(sku+","+info+","+price+"#");
				}
				//System.out.println(msg);
				setResult(new Result(false, msg.toString(), null,"")) ;
			}else{
				setResult(new Result(false,"0", null,0)) ;
			}
			
			return "getoder";
		} catch (Exception e) {
		logger.error("前台/购物车/订单查询/Exception:"+e.getMessage());
		e.printStackTrace();
		return ERROR;
		
		}
		
	}
	
	/**
	 * 根据课程id和用户id获取订单状态
	 * 
	 */
	public String getStatus()
	{
		try {
			CashRecord cr =new CashRecord();
			cr.setCourseId(cousid);
			cr.setCusId(userid);
			String status = contractService.getStatus(cr);
			setResult(new Result<String>(false, status, null, status));
			return "getstatus";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "ERROR";
		}
	}
	/**
	 * 对未支付的、非赠送的订单执行取消操作  货到付款的订单 3为取消，其他支付方式的订单 4为取消
	 * @return
	 */
	private void delContractAndCashRecord()
	{
		try{
			
			Contract ctTemp=contractService.getContractById(contract.getId());
			//判断订单是否为已付，或者赠送，如果是这两种情况则不删除
			if(ctTemp.getPayType()!=0&&ctTemp.getStatus()!=1&&ctTemp.getCusId()==getLoginUserId())
			{	
				if(ctTemp.getPayType()==2)
				{
					ctTemp.setStatus(3);
				}else
				{
					ctTemp.setStatus(4);
				}
				contractService.updateContract(ctTemp);			
//				//删除该订单下的流水
//				cashRecordService.delCashRecordByCtId(contract.getId());
//				//删除这个订单
//				contractService.delContractById(contract.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得订单列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static final String COOKIE_REMEMBERME_KEY="sedu.cookie.ukey"; 
	
	public String getContractList() {
		try {
			/**
			if(type==0)
			{
				delContractAndCashRecord();
				type=1;
			}**/
			
			HttpServletRequest request = ServletActionContext.getRequest();
			
			if(request.getParameter("ty") != null){
				String ty = request.getParameter("ty");
				if(ty.equals("0") || Integer.parseInt(ty.trim()) == 0){
					delContractAndCashRecord();
				}
			}
			
			
			if (searchKey != null && !"".equals(searchKey.trim())) {
				getQueryContractCondition().setSearchKey(searchKey.trim());
			}
			int userId = getLoginUserId();
			if(userId != 0){
				getQueryContractCondition().setUserId(userId);
			}
			//获得订单列表
			setPage(contractService.getContractList(queryContractCondition));
			setPageUrlParms();
			//订单条数
			sumCount = contractService.getUserContractCount(userId);
			//前台用户列表
			//customerList = customerService.getCustomerList(getQueryCustomerCondition());
			//学员中心左侧功能条 
			setCourseSortListDTOList(userId);
		} catch (Exception e) {
			logger.error("前台/用户中心/我的订单/Exception:"+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return "listContract";
	}
	public String getContractById(){
		int userId = getLoginUserId() ;
		if(userId!=0){
			getQueryContractCondition().setUserId(userId) ;
		}
		Contract ct = contractService.getContractById(userId) ;
		String[] GA_result={ct.getContractId(),ct.getContractCutSumMoney().toString()} ;
		setResult(new Result(false, "success", null, GA_result)) ;
		return "json" ;
	}
	
	public String getCashByContractId(){
		int userId = getLoginUserId() ;
		if(userId!=0){
			getQueryContractCondition().setUserId(userId) ;
		}
		Contract ct = contractService.getContractById(userId) ;
		List<CashRecord> cash_list = cashRecordService.getAllCash(ct.getContractId()) ;
		String[] GA_Item = new String[cash_list.size()] ;
		for(int i =0 ;i<cash_list.size() ;i++){
			GA_Item[i] = cash_list.get(i).getCourseId()+","+ cash_list.get(i).getPrice()+","+cash_list.get(i).getCrInfo();
			
		}
		setResult(new Result(false, "success", null, GA_Item)) ;
		return "json" ;
	}
	/**
	 * 货到付款生成订单和流水
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String makeContractAndCashRecord() {
		try {
			String courseOrder = URLDecoder.decode(CookieHandler.getCookieValueByName(servletRequest, "courseOrder"), "utf-8").trim();//存值形式[0]课程id,[1]每一本书的价格,[2]包id
			if(courseOrder != null && !courseOrder.trim().equals("")) {
				
				//如果是临时学员，那么删除他的临时信息，并置状态为注册学员
				Customer tempCus = customerService.getCustomerById(getLoginUserId());
				if(tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP||tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP) {
					customerService.recoverTempCustomer(getLoginUserId());
					tempCus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
					customerService.updateCustomer(tempCus);
				}
				
				//保存订单和流水
				String[] returnInfos = saveContractAndCashRecords(courseOrder);
				
				//清除生成订单所用的cookie中的数据
				clearCookieContractInfo();
				//下单成功发送短信给客户
				sendSmsConfirmContract();

				//设置返回数据
				setResult(new Result(false, "success", null, returnInfos));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 给用户发送订单确认短信
	 */
	public void sendSmsConfirmContract() {
		try {
			Customer customer = customerService.getCustomerById(getLoginUserId());
			if (customer.getMobile() != null && !"".equals(customer.getMobile().trim())) {
				smsService.sendEx("【嗨学网】您的订单已成功提交，我们会在24小时内和您联系确认并发货，请保持手机开通", customer.getMobile(), "", "", "");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成订单和流水
	 * @param courseOrder
	 * @param cusId
	 * @return
	 */
	private String[] saveContractAndCashRecords(String courseOrder) {
		Date date = new Date();
		String get_order = String.valueOf(getLoginUserId()) + date.getTime() + "";
		String webFrom = CookieHandler.getCookieValueByName(servletRequest, "webFrom");
		String webAgent = CookieHandler.getCookieValueByName(servletRequest, "webAgent");
		String src = null ;
		String wi = null ;
		String strCid = null ;
		Integer cid = null ;
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "SRC"))){
			src = CookieHandler.getCookieValueByName(servletRequest, "SRC") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "WI"))){
			wi = CookieHandler.getCookieValueByName(servletRequest, "WI") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "CID"))){
			strCid = CookieHandler.getCookieValueByName(servletRequest, "CID") ;
		}
		if(changeAddress) {
			addressId = saveAddress();
		}
		
		// 记录到订单表中
		Contract newcontract = new Contract();
		
		String contractFromUrl = servletRequest.getServerName();
		newcontract.setContractFromUrl(contractFromUrl);
		newcontract.setContractId(get_order);
		newcontract.setCusIdAddress(addressId);
		newcontract.setCusId(getLoginUserId());
		newcontract.setCreateTime(date);
		newcontract.setContractFrom("前台用户");
		newcontract.setPayType(2);// 1支付宝 2货到付款
		newcontract.setStatus(0);// 2情况下，如果　0为未激活　1为激活
		newcontract.setCouponMoney(0);
		newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);// 0代表没有使用 1代表使用
		newcontract.setContractCDkey(getRandomNum(16));//生成16位激活码
		
		int ctId = contractService.addContract(newcontract);
		float totalPrice= saveCashRecords(courseOrder, get_order, ctId, getLoginUserId());
		//谢修改部分开始
		String youhuijuan =ServletActionContext.getRequest().getParameter("payprice");// 优惠卷面值  谢修改些句
		int couponTypeId=0;
		DecimalFormat df = new DecimalFormat("#.00 ");
		if (youhuijuan == null || youhuijuan.trim().equals("")) {
			paymoney = totalPrice + ""; // 应付总价
			// youhuijuan = "0";
			newcontract
					.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);
		} else {
			paymoney = df.format(totalPrice
					- Float.parseFloat(youhuijuan))
					+ ""; // 订单总价-优惠价格=应付金额
			newcontract
					.setUseCoupon(Contract.CONTRACT_USE_COUPON_YES);
		}
		if (!couponId.equals("0") && couponId != null) {
			 couponTypeId = sellCouService
					.GetParentIdBycouponId(couponId);// 谢添加
			sellCouService.updateCouponForState(couponId,
					couponTypeId);// 谢添加更改优惠券的状态
		}
		newcontract.setContractCutSumMoney(Float.parseFloat(df
				.format(Float.parseFloat(paymoney))));// 优惠后总金额
		newcontract.setContractSumMoney(Float.parseFloat(df
				.format(totalPrice)));// 优惠前总金额
		newcontract.setCouponMoney(Float.parseFloat(df.format(Float
				.parseFloat(youhuijuan))));// 优惠额
		//谢修改部分结束
		if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")) {
			newcontract.setWebFrom(webFrom);
		}
		
		if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null")) {
			newcontract.setWebAgent(webAgent);
		}
		
		
		/**
		 * 查询Cookies是否有亿起发的信息，有则添加到订单中
		 */
		if(null!=src&&!"".equals(src)){
			newcontract.setSrc(src);
			if(null!=wi&&!"".equals(wi)){
				newcontract.setWi(wi) ;
			}
			if(null!=strCid&&!"".equals(strCid.trim())){
				cid = Integer.parseInt(strCid) ;
				newcontract.setCid(cid) ;
			}
			//给亿起发返订单数据
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(date);
			String url = "cid="+cid+"&wi="+wi+"&on="+get_order+"&ta=1&pp="+totalPrice+"&sd="+dateTime+"&encoding=utf-8" ;
			String flag = this.getGetMsg().getHttpMessageFromGet(url);
			if("0".equals(flag)){
				logger.info("成功发送亿起发订单数据") ;
			}else if("1".equals(flag)){
				logger.info("缺少必要参数") ;
			}else if("2".equals(flag)){
				logger.info("参数格式错误") ;
			}
		}
		
		/**
		 * 为第三方网站进行订单推送
		 * add by caowei
		 * 2011-07-18
		 */
		try{
			String sendData = "";
			int siteCode = 0;
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")){
				if(webFrom.trim().equals("51fanli")){
					siteCode = 1;
				}
			}
			if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && siteCode != 0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sendDate = sdf.format(date);
				HashMap detailMap = (HashMap) this.getContractDetail(courseOrder);
				String pks = (String) detailMap.get("pks");//售卖方式的字符串
				String prices = (String) detailMap.get("prices");//售卖方式价格的字符串
				String pksNum = (String) detailMap.get("pksNum");//售卖方式的个数
				String comm = (String)detailMap.get("comm");//佣金
				if(siteCode == 1){//返利网
					String m_id = "highso";//由返利网提供
					String k = "123456";//由返利网提供
					sendData = "otime=" + sendDate + "&o_cd=" + get_order
							+ "&m_id=" + m_id + "&k=" + k + "&u_id=" + webAgent
							+ "&p_cd=" + pks + "&c_cd="+comm+"&it_cnt=" + pksNum
							+ "&price=" + prices + "&comm="+comm+"&username=abcd";
					logger.info(sendData);
				}
				String res = this.getGetMsg().sendContractToOtherSiteFromGet(siteCode, sendData.trim());
			}
		}catch(Exception e){
			logger.info("推送订单异常！");
		}
		
		
		//对应网盟过来的订单
		String wmFlag="wm";
		if(null  != webAgent && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && webAgent.trim().equals(wmFlag)) {
			newcontract.setWebAgent(webAgent); 
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null") ) {
				newcontract.setWebFrom(webFrom);
			}
		}
		contractService.updateContract(newcontract);
		//在优惠券中添加订单 谢添加开始
		Map map=new HashMap();
		map.put("contarctId", newcontract.getId());
		map.put("couponId", couponId);
		sellCouService.updateCouponForContractId(map);
		couponTypeId=sellCouService.GetParentIdBycouponId(couponId);
		sellCouService.updateCouponForPayState(couponId, couponTypeId);//改变优惠券状态
		//谢添加结束
		logger.info("前台/用户中心/我的订单/" + "用户：" + getLoginUserId() + "订单号：" + get_order 
						+ "订单来源:前台用户　下单时间：" + date + "订单状态：等待付款/成功");
		return new String[]{get_order, totalPrice + ""};
	}
	
	/**
	 * 获取订单的明细，包含售卖方式、单价
	 * @param courseOrder
	 * @return
	 */
	public Map<String,String> getContractDetail(String courseOrder){
		
		Map<String,String> detailMap = new HashMap<String,String>();
		ArrayList packList = new ArrayList();
		String[] courses = courseOrder.split("#");
		for(int j=1; j<courses.length; j++){
			String[]crInfo=courses[j].split(",");
			int packId=new Integer(crInfo[2]);
			this.getQuerySellCouCondition().setSellId(packId);
			List<SellCou> sellCouList=sellCouService.getSellCouList(this.getQuerySellCouCondition());
			for(int i=0;i<sellCouList.size();i++){
				packList.add(sellCouList.get(i).getSellId());
			}
		}
		packList = (ArrayList) this.filterList(packList);
		String pks = "";
		String prices = "";
		String pksNum = "";
		String comm = "";
		for (int i = 0; i < packList.size(); i++) {
			pks += packList.get(i) + "|_|";
			SellWay sw = sellWayService.getSellWayById((Integer) packList.get(i));
			prices += sw.getSellPrice()+"|_|";
			pksNum += "1|_|";
			comm += "0|_|";
//			sellWayService.getSellWayById(38);
			//prices += sellWayService.getSellWayById(Integer.parseInt((String) packList.get(i))).getSellPrice() + "|_|";
		}
		detailMap.put("pks", pks);
		detailMap.put("prices", prices);
		detailMap.put("pksNum", pksNum);
		detailMap.put("comm", comm);
		return detailMap;
	}
	
	/**
	 * 记录流水
	 * @param ctId
	 * @return
	 */
	private float saveCashRecords(String courseOrder, String get_order, int ctId, int cusId) {
		//记录到流水表中
		CashRecord cashRecord = null;
		Date date = new Date();
		float totalPrice = 0;
		Course course = null;
		Set<String> stmpset = new HashSet<String>();
		String[] body = courseOrder.split("#");
		for(int j=1; j<body.length; j++){
			String[]crInfo=body[j].split(",");
			
			if (stmpset.contains(crInfo[2])){
				continue;
			}else{
				stmpset.add(crInfo[2]);
			}
			
			int packId=new Integer(crInfo[2]);
			this.getQuerySellCouCondition().setSellId(packId);
			List<SellCou> sellCouList=sellCouService.getSellCouList(this.getQuerySellCouCondition());
			for(int i=0;sellCouList!=null&&i<sellCouList.size();i++)
			{
				cashRecord=new CashRecord();
				//获取course对象
				course=courseService.getCourseById(sellCouList.get(i).getCourseId());
				totalPrice+=sellCouList.get(i).getSellCouPrice();								//总价累计
				cashRecord.setCusId(getLoginUserId());						//用户ID
				cashRecord.setPrice(sellCouList.get(i).getSellCouPrice());						//课程价格
				cashRecord.setContractId(get_order);						//订单号
				cashRecord.setType(1);										//购买类型 1前台购买，2后台赠送 3后台修复
				cashRecord.setCtId(ctId);									//定单号
				cashRecord.setPackId(packId);								//包ID
				cashRecord.setCourseId(course.getCourseId());
				cashRecord.setCrInfo("购买《" + course.getTitle() + "》");		//该流水的课程名
				cashRecord.setCreateTime(new Date());						//生成流水的时间
				cashRecordService.addCashRecord(cashRecord);				//添加到流水表中
				logger.info("前台/个人中心/消费记录/" + "用户:" + getLoginUserId() + "购买:" + course.getTitle() + "金额：" 
						+ crInfo[1] + "订单号为:" + get_order + "创建时间" + date + "/成功");
			}
		}
		
		
	/**	
		for(int i=1; i<body.length; i++){
			cashRecord = new CashRecord();
			String[] crInfo = body[i].split(",");
			
			course = courseService.getCourseById(new Integer(crInfo[0]));
			totalPrice += course.getPrice();
			
			cashRecord.setCusId(cusId);
			cashRecord.setPrice(course.getPrice());
			cashRecord.setCourseId(course.getCourseId());
			cashRecord.setContractId(get_order);
			cashRecord.setType(1);//１代表前台购买
			cashRecord.setCtId(ctId);//把订单id记录流水表中
			cashRecord.setPackId(new Integer(crInfo[2]));
			cashRecord.setCrInfo("购买《" + course.getTitle() + "》");
			cashRecord.setCreateTime(new Date());
			
			cashRecordService.addCashRecord(cashRecord);
			
			logger.info("前台/个人中心/消费记录/" + "用户:" + cusId + "购买:" + course.getTitle() + "金额：" 
							+ crInfo[1] + "订单号为:" + get_order + "创建时间" + date + "/成功");
		}
	*/
		return totalPrice;
	}
	
	/**
	 * 清除生成订单所用的cookie中的数据
	 */
	private void clearCookieContractInfo() {
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "courses");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "totalPrice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "courseOrder");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "payprice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "totalPrice1");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "zongjia");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "coursesdan");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "coursesbao");
	}
	
	/**
	 * 保存地址
	 * @return
	 */
	private int saveAddress() {
		address.setCusId(getLoginUserId());
		return addressService.addAddress(address);
	} 
	
	   /**
     * 保存地址
     * @return
     */
    public String saveAddressSF() {
           try {
               address.setCusId(getLoginUserId());
               addressService.addAddress(address);
               this.setResult(new Result<String>(false,"success",null,""));
           } catch (Exception e) {
               this.setResult(new Result<String>(false,"error",null,""));
               logger.error("更新学员收获地址错误！", e);
           }
         return "json";
    } 
	/**
	 * 随机生成激活码
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 62;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
	
	/**
	 * 查询货到付款的订单
	 */
    public String getContractCount(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	int cusId=this.getLoginUserId();
    	List<Contract> contractList=this.contractService.getContractCount(cusId);
    	String contractIdStr = "";
    	for(int i=contractList.size()-1; i>-1; i--) {
    		Contract ct = contractList.get(i);
    		if(ct.getCdkeySum() != null) {
    			String[] cdkeySumStrs = ct.getCdkeySum().split(",");
    			if(cdkeySumStrs.length > 0 && cdkeySumStrs[0].equals(format.format(new Date())) && Integer.valueOf(cdkeySumStrs[1]) > 4) {
    				continue;
    				//contractList.remove(ct);
    			}
    		}
    		contractIdStr += ct.getContractId();
    		if(i != 0) {
    			contractIdStr += ",";
    		}
    	}
		this.setResult(new Result<Object>(true, contractIdStr, null, null));
    	return "json";
    }
    
    /**
     * 初始化课程　货到付款的方式
     */
    public String gotoCOD(){
    	Date date=new Date();
    	int userId=this.getLoginUserId();
    	getQueryContractCondition().setUserId(userId);
    	PageResult  ctList = contractService.getContractList(getQueryContractCondition());
    	if(ctList == null || ctList.getPageResult() == null || ctList.getPageResult().size() == 0) {
    		setResult(new Result(false, "fail", null, null));
    		return "json";
    	}
    	
    	String[] cdkeySumStrs = null;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Contract ct = (Contract)ctList.getPageResult().get(0);
    	if(ct.getCdkeySum() != null && ct.getCdkeySum().split(",").length > 0) {
	    	cdkeySumStrs = ct.getCdkeySum().split(",");
	    	if(cdkeySumStrs[0].equals(format.format(new Date())) && Integer.valueOf(cdkeySumStrs[1]) >= 5) {
	    		setResult(new Result(false, "moreTimes", null, null));
	    		return "json";
	    	}
    	}
    	
    	getQueryContractCondition().setContractCDkey(contractCDkey);
    	ctList = contractService.getContractList(getQueryContractCondition());
		if(ctList!=null && ctList.getPageResult().size()>0){
			ct=(Contract)ctList.getPageResult().get(0);
			if(ct.getStatus()!=1){
				ct.setStatus(1);//已激活
				ct.setPayTime(date);
				this.contractService.updateContract(ct);
				//通过订单id查询流水记录
				QueryCashRecordCondition qcrc = new QueryCashRecordCondition();
				qcrc.setUserId(userId);
				qcrc.setCtId(ct.getId());
				List<CashRecord> crList = this.cashRecordService.getCashRecordByList(qcrc);//流水记录
				//将此货到付款激活的订单中的流水状态改为 1 可用状态
				for(int i=0;i<crList.size();i++)
				{
					crList.get(i).setStatus(1);
				}
				this.getCashRecordService().updateCashReocrdStatus(crList);
				//发送购买成功消息
				Message msg = messageService.getMessageByKey("buy");
				if(msg != null && msg.getMsgId() > 0){
					User sender = new User();
					sender.setUserId(1);
					sender.setUserName("超级管理员");
					Customer cus = customerService.getCustomerById(userId);
					if(cus != null){
						userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(), cus);
					}
				}
				//支付成功发送短信给客户
				Customer cusTemp=this.customerService.getCustomerById(userId);
				if(cusTemp.getMobile()!=null&&!"".equals(cusTemp.getMobile().trim())){
					try {
						this.smsService.sendEx("【嗨学网】您的课程已激活成功，点击我的课程，即可开始学习！", cusTemp.getMobile(), "", "", "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				setResult(new Result(false, "success", null, null));
			} else {
				setResult(new Result(false, "hasAct", null, null));
			}
		}else{
			if(cdkeySumStrs == null || cdkeySumStrs.length<=1 || !cdkeySumStrs[0].equals(format.format(new Date()))) {
				ct.setCdkeySum(format.format(new Date()) + ",1");
			} else {
				ct.setCdkeySum(cdkeySumStrs[0] + "," + (Integer.valueOf(cdkeySumStrs[1]) + 1));
			}
			contractService.updateContract(ct);
			setResult(new Result(false, "fail", null, null));
		}
		return "json";
	}
    
	/**
	 * 设置左侧工具栏
	 * 
	 * @param userId
	 */
	protected void setCourseSortListDTOList(int userId){
		Customer customer = customerService.getCustomerById(userId);
		
		if(customer.getIsComplete() != 1 ||(customer.getIsComplete() == 1 && customerService.isComplete(userId) >= 0)){
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}
		
		if(userId != 0){
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}
	}
	
	public String openContract(){
		
		return "success";
	}

	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public QueryContractCondition getQueryContractCondition() {
		if (queryContractCondition == null) {
			queryContractCondition = new QueryContractCondition();
		}

		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}
	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}
	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCousid() {
		return cousid;
	}

	public void setCousid(int cousid) {
		this.cousid = cousid;
	}

	public String getOderid() {
		return oderid;
	}

	public void setOderid(String oderid) {
		this.oderid = oderid;
	}
	
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	public IsmsService getSmsService() {
		return smsService;
	}
	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}
	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}
	public IKpoint getKpointService() {
		return kpointService;
	}
	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public IAddress getAddressService() {
		return addressService;
	}
	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}
	public Log getLogger() {
		return logger;
	}
	public void setLogger(Log logger) {
		this.logger = logger;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static String getCOOKIE_REMEMBERME_KEY() {
		return COOKIE_REMEMBERME_KEY;
	}
	public boolean isChangeAddress() {
		return changeAddress;
	}
	public void setChangeAddress(boolean changeAddress) {
		this.changeAddress = changeAddress;
	}
	public String getContractCDkey() {
		return contractCDkey;
	}
	public void setContractCDkey(String contractCDkey) {
		this.contractCDkey = contractCDkey;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public IUserMsg getUserMsgService() {
		return userMsgService;
	}
	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}
	public IMessage getMessageService() {
		return messageService;
	}
	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}
	public QuerySellCouCondition getQuerySellCouCondition() {
		if(querySellCouCondition==null)
		{
			querySellCouCondition=new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}
	public void setQuerySellCouCondition(QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}
	public ISellCou getSellCouService() {
		return sellCouService;
	}
	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}
	public GetHttpMessage getGetMsg() {
		return getMsg;
	}
	public void setGetMsg(GetHttpMessage getMsg) {
		this.getMsg = getMsg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 工具方法-用于对List中的元素进行剔重
	 * @param list
	 * @return
	 */
	public List filterList(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}	

	public QuerySellWayCondition getQuerySellWayCondition() {
		return querySellWayCondition;
	}
	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
  
}
