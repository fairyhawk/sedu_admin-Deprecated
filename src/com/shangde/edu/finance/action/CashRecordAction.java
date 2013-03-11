package com.shangde.edu.finance.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.bouncycastle.asn1.ocsp.Request;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.change.domain.ChangeSellWay;
import com.shangde.edu.change.service.IChange;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 流水管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class CashRecordAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(CashRecordAction.class);
	/**
	 * 声名流水的PO对象
	 */
	private CashRecord cashRecord = new CashRecord();
	/**
	 * 查询条件
	 */
	private String searchKey;

	/**
	 * 知识点查询条件
	 */
	private QueryCashRecordCondition queryCashRecordCondition;

	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;

	/**
	 * 声明用户的服务
	 */
	private ICustomer customerService;

	private QueryCustomerCondition queryCustomerCondition;
	private Customer customer;
	private List customerList = new ArrayList();
	private String stime;
	private String yestartTime;
	private String yeendTime;
	private String startTime;
	private String endTime;
	private String mail;
	private String startHH;
	private String endHH;
	private int subjectId;
	/**
	 * 课程分类查询条件
	 */
	private QueryCoursesortCondition queryCourseSortCondition;

	/**
	 * 课程分类集
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;

	/**
	 * 项目集合
	 */
	private List<Subject> subjectList;

	/**
	 * service
	 */
	private ISubject subjectService;

	/**
	 * 获得流水列表
	 * 
	 * @return String
	 * @throws Exception
	 */

	private ISellWay sellWayService;

	private List<CashRecordDTO> cashList;

	public static final String COOKIE_REMEMBERME_KEY = "sedu.cookie.ukey";

	/**
	 * dto Liming
	 */
	private CashRecordDTO cashRecordDTO;
	/**
	 * delayTime Liming
	 */
	private String delayTime;
	/**
	 * cousmerId Liming
	 */
	private String cousmerId;
	/**
	 * 流水查询条件
	 * Liming
	 */
	private  String contractId;
	private  String createTimeBegin;
	private  String createTimeEnd;
	private String email;
	private  String payTimeBegin;
	private  String payTimeEnd;
	private String contractsshopStatus;
	private String contractstatus;
	private String shopName;
	private String contractpackId;
	private String contractsubjectId;
	private String contractpackName;
	private String packId;
	/**
	 * 导出流水表标题EXCL
	 */
	private String exportName;
	private InputStream excelFile;
	
	
	/**
	 * 更换课程 王郑
	 */
	private SellWay sellWay;
	private IChange changeService;
	private ChangeSellWay  changeSellWay;
	private int pastSellWayId; //原来课程ID  
	private QuerySellWayCondition querySellWayCondition;
	
	
	
	/** 
	 * 给QueryCashRecordCondition赋查询条件
	 * Liming
	 */
	public void addQueryTou(){
		if(stime!=null&&stime.equals("stime")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.getQueryCashRecordCondition().setCreateTimeBegin(sdf.format(new Date()));
			this.getQueryCashRecordCondition().setCreateTimeEnd(sdf.format(new Date()));
			yestartTime=sdf.format(new Date()).trim();
			yeendTime=sdf.format(new Date()).trim();
			}
		if(this.getPackId()!=null && !this.getPackId().equals("")){
			this.getQueryCashRecordCondition().setPackId(Integer.parseInt(this.getPackId().trim()));
		}
		if(this.getContractId()!=null && !this.getContractId().equals("")){
			this.getQueryCashRecordCondition().setContractId(this.getContractId().trim());
		}
		if(this.getCreateTimeBegin()!=null && !this.getCreateTimeBegin().equals("")){
			yestartTime=this.getCreateTimeBegin().trim();
			this.getQueryCashRecordCondition().setCreateTimeBegin(this.getCreateTimeBegin().trim());
		}
		if(this.getCreateTimeEnd()!=null && !this.getCreateTimeEnd().equals("")){
			yeendTime=this.getCreateTimeEnd().trim();
			this.getQueryCashRecordCondition().setCreateTimeEnd(this.getCreateTimeEnd());
		}
		if(this.getEmail()!=null && !this.getEmail().equals("")){
			this.getQueryCashRecordCondition().setEmail(this.getEmail().trim());
		}
		if(this.getPayTimeBegin()!=null && !this.getPayTimeBegin().equals("")){
			this.getQueryCashRecordCondition().setPayTimeBegin(this.getPayTimeBegin().trim());
		}
		if(this.getPayTimeEnd()!=null && !this.getPayTimeEnd().equals("")){
			this.getQueryCashRecordCondition().setPayTimeEnd(this.getPayTimeEnd().trim());
		}
		if(this.getContractsshopStatus()!=null && !this.getContractsshopStatus().equals("")){
			this.getQueryCashRecordCondition().setCarecordshopStatus(Integer.parseInt(this.getContractsshopStatus().trim()));
		}
		if(this.getContractstatus()!=null && !this.getContractstatus().equals("")){
			this.getQueryCashRecordCondition().setCarecorstatus(Integer.parseInt(this.getContractstatus().trim()));
		}
		if(this.getShopName()!=null && !this.getShopName().equals("")){
			this.getQueryCashRecordCondition().setShopName(this.getShopName().trim());
		}
		if(this.getContractpackId()!=null && !this.getContractpackId().equals("")){
			this.getQueryCashRecordCondition().setPackId(Integer.parseInt(this.getContractpackId().trim()));
		}
		if(this.getContractsubjectId()!=null && !this.getContractsubjectId().equals("")){
			this.getQueryCashRecordCondition().setSubjectId(Integer.parseInt(this.getContractsubjectId().trim()));
		}
	}
	/**
	 * 给QueryCashRecordCondition赋查询条件
	 * Liming
	 */
	public void addQueryEnd(){
		if(packId==null){
			packId="0";
		}
		if(contractId==null){
			contractId="";
		}
		if(createTimeBegin==null){
			createTimeBegin="";
		}
		if(createTimeEnd==null){
			createTimeEnd="";
		}
		if(email==null){
			email="";
		}
		if(payTimeBegin==null){
			payTimeBegin="";
		}
		if(payTimeEnd==null){
			payTimeEnd="";
		}
		if(contractsshopStatus==null){
			contractsshopStatus="-1";
		}
		if(contractstatus==null){
			contractstatus="-1";
		}
		if(shopName==null){
			shopName="";
		}
		if(contractpackId==null){
			contractpackId="0";
		}
		if(contractsubjectId==null){
			contractsubjectId="0";
		}
		if(contractpackName==null){
			contractpackName="-请选择商品-";
		}
	}
	/**
	 * 新查看流水
	 * @return  xiejinglong
	 */
	public String getCashRecordList() {
		/*--杨宁修改 2011-12-1加入参数跳转至可退费商品操作页面--*/
		yestartTime="";
		yeendTime="";
		Boolean flag = false;
		try {
			String toReturn = getServletRequest().getParameter("toReturn");
			if(toReturn != null && toReturn.equals("yes")){
				flag = true;
			}
			addQueryTou();
			subjectList = subjectService.getAllSubject();
			if(queryCashRecordCondition==null ){
				queryCashRecordCondition=new QueryCashRecordCondition();
			}
			this.getQueryCashRecordCondition().setPageSize(30);
		  if( this.getQueryCashRecordCondition().getShopName()!=null){
			  this.getQueryCashRecordCondition().setShopName(this.getQueryCashRecordCondition().getShopName().trim());
		  }
		  if(this.getQueryCashRecordCondition().getEmail()!=null){
			  this.getQueryCashRecordCondition().setEmail(  this.getQueryCashRecordCondition().getEmail().trim());
		  }
			PageResult pageResultList = this.cashRecordService
					.getCashRecordListAndSellWay(getQueryCashRecordCondition());
			setPage(pageResultList);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			addQueryEnd();
		} catch (Exception e) {
			logger.error("CashRecordAction.getCashRecordList",e);
			return ERROR;
		}
		if(flag == true){
			return "listCashReturn";
		}
		return "listCashRecord";
	}
	
	
	/**

	 * 更换课程前  得到售卖方式列表
	 * 王郑
	 * @return
	 */
	public String showSellWayList() {
		try {
			subjectList = subjectService.getAllSubject();
			cashRecord = this.cashRecordService.getCashRecordById(cashRecord.getId());
			if(customer !=null){
				customer=this.customerService.getCustomerById(customer.getCusId());
			}
			setPage(this.getSellWayService().showSellWayListByWhere(this.getQuerySellWayCondition()));
			setPageUrlParms();
			return "changeSellWay";
		} catch (Exception e) {
			logger.error("SellWayAction.showSellWayList",e);
			return ERROR;
		}
	}
	
	public String updateCashRecordSellWay(){
		try {
			User user = getLoginedUser();
			if(customer!=null && customer.getCusId()!=0){
				if(cashRecord!=null && cashRecord.getId()!=0){
					cashRecord= this.cashRecordService.getCashRecordById(cashRecord.getId());
					if(sellWay!=null){
						sellWay=sellWayService.getSellWayById(sellWay.getSellId());
						cashRecord.setPackId(sellWay.getSellId());
						
						cashRecord.setCrInfo("更换《"+sellWay.getSellName()+"》");
					}
					cashRecord.setCusId(customer.getCusId());
					this.cashRecordService.updateCashRecordPackId(cashRecord);
					this.getChangeSellWay().setNewPackId(sellWay.getSellId());
					this.getChangeSellWay().setCusId(customer.getCusId());
					this.getChangeSellWay().setContractId(cashRecord.getContractId());
					this.getChangeSellWay().setCashId(cashRecord.getId());
					this.getChangeSellWay().setType(1);
					this.getChangeSellWay().setUserName(user.getLoginName());
					
					this.getChangeSellWay().setUpdateTime(new Date());
					this.getChangeSellWay().setPackId(pastSellWayId);
					this.changeService.addChangeSellWay(changeSellWay);
					
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "json";
	}
	
	

	/**
	 * 导出EXCEL表格
	 * @return String
	 * Liming
	 */
	public String exportCSV() {
		try {
			addQueryTou();
			String expName="流水列表_" + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
			addQueryTou();
			this.getQueryCashRecordCondition().setPageSize(5000);
			PageResult result = this.cashRecordService
							.getCashRecordListAndSellWay(getQueryCashRecordCondition());
			List<CashRecordDTO> cashlist = result.getPageResult();
			//商品销售记录编号、商品名称、隶属订单编号、用户email、所属项目、商品原始价格、成交价格、减免价格、支付状态、商品状态、
			//支付方式、创建时间、付款时间、到期时间、延期时间、停止服务时间；
			List<List<String>> list = new ArrayList<List<String>>();
			List<String> headList = new ArrayList<String>();
			headList.add("ID");
			headList.add("商品销售记录编号");
			headList.add("商品名称");
			headList.add("隶属订单编号");
			headList.add("用户email");
			headList.add("所属项目");
			headList.add("商品原始价格(元)");
			headList.add("成交价格(元)");
			headList.add("使用截止时间");
			headList.add("减免价格(元)");
			headList.add("支付状态");
			headList.add("商品状态");
			headList.add("支付方式");
			headList.add("创建时间");
			headList.add("付款时间");
			headList.add("到期时间");
			headList.add("延期时间(天)");
			headList.add("停止服务时间");
			list.add(headList);
			
			// 头结束
			for (int i = 0; i < cashlist.size(); i++) {
				CashRecordDTO cashRecordDTO = cashlist.get(i);
				List<String> small = new ArrayList<String>();
				small.add(String.valueOf(cashRecordDTO.getId()));
				small.add(String.valueOf(cashRecordDTO.getPackId()));
				small.add(cashRecordDTO.getPackName());
				small.add(cashRecordDTO.getContractId());
				small.add(cashRecordDTO.getEmail());
				small.add(cashRecordDTO.getSubjectName());
				small.add(String.valueOf(cashRecordDTO.getPrice()));
				if(cashRecordDTO.getCashRecordPrice()==null){
					small.add(String.valueOf("0.0"));
				}else{
					small.add(String.valueOf(cashRecordDTO.getCashRecordPrice()));
				}
				small.add(DateUtil.formatDate(cashRecordDTO.getValidityTime(), "yyyy-MM-dd HH:mm:ss"));
				if(cashRecordDTO.getReliefPrice()==null){
					small.add(String.valueOf("0.0"));
				}else{
					small.add(String.valueOf(cashRecordDTO.getReliefPrice()));
				}
				//商品状态
				if(cashRecordDTO.getShopStatus()==0){
					small.add("未激活");
				}
				if(cashRecordDTO.getShopStatus()==1){
					small.add("已激活");
				}
				if(cashRecordDTO.getShopStatus()==2){
					small.add("已延期");
				}
				if(cashRecordDTO.getShopStatus()==3){
					small.add("已关闭");
				}
				//支付状态
				if(cashRecordDTO.getStatus()==0){
					small.add("未支付");
				}
				if(cashRecordDTO.getStatus()==1){
					small.add("已支付");
				}
				if(cashRecordDTO.getStatus()==2){
					small.add("已取消");
				}
				if(cashRecordDTO.getStatus()==3){
					small.add("退费");
				}
				//支付方式
				if(cashRecordDTO.getType()==0){
					small.add("赠送");
				}
				if(cashRecordDTO.getType()==1){
					small.add("支付宝");
				}
				if(cashRecordDTO.getType()==2){
					small.add("货到付款");
				}
				if(cashRecordDTO.getType()==3){
					small.add("网银在线");
				}
				if(cashRecordDTO.getType()==4){
					small.add("快钱");
				}
				if(cashRecordDTO.getType()==5){
					small.add("代理商开通");
				}
				if(cashRecordDTO.getType()==9){
					small.add("课程卡");
				}
				small.add(DateUtil.formatDate(cashRecordDTO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(cashRecordDTO.getShopPayTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(cashRecordDTO.getValidityTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(cashRecordDTO.getDelayTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(cashRecordDTO.getStopTime(), "yyyy-MM-dd HH:mm:ss"));
				list.add(small);
			}
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			addQueryEnd();
			return "exportcoupon";
		} catch (Exception ex) {
			logger.error("CashRecordAction.getCashRecordList",ex);
			return ERROR;
		}

	}
	public String getSellWaysBySubId() {
		try {
			List<SellWay> list = cashRecordService
					.getSellWayBySubjectId(subjectId);
			System.out.println(list.size());
			setResult(new Result(false, null, null, list));
			return "json";
		} catch (Exception e) {
			logger.error("CashRecordAction.getSellWaysBySubId",e);
			return ERROR;
		}

	}
	/*
	 * fanxin 2011-08-09
	 * 
	 * （无需登陆后台）查看订单信息
	 */
	public String getCashRecordListNoLogin() {
		try {
			if (startTime != null && !"".equals(startTime)) {
				startTime = startTime + startHH;
				this.getQueryCashRecordCondition()
						.setCreateTimeBegin(startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				endTime = endTime + endHH;
				this.getQueryCashRecordCondition().setCreateTimeEnd(endTime);
			}
			if (cashRecord.getContractId() != null
					&& !"".equals(cashRecord.getContractId().trim())) {
				this.getQueryCashRecordCondition().setContractId(
						cashRecord.getContractId().trim());
			}
			if (cashRecord.getCtId() != 0) {
				this.getQueryCashRecordCondition()
						.setCtId(cashRecord.getCtId());
			}
			if (cashRecord.getType() != 0) {
				this.getQueryCashRecordCondition()
						.setType(cashRecord.getType());
			}
			if (cashRecord.getCusId() != 0) {
				this.getQueryCashRecordCondition().setUserId(
						cashRecord.getCusId());
			}
			if (mail != null && !"".equals(mail)) {
				Customer customer = this.customerService
						.getCustomerByEmail(mail.trim());
				if (customer != null) {
					if (customer.getCusId() != 0) {
						this.getQueryCashRecordCondition().setUserId(
								customer.getCusId());
					}
				} else {
					this.getQueryCashRecordCondition().setUserId(1); // 添加一个数据库里不会出现的用户id
				}
			}

			this.getQueryCashRecordCondition().setPageSize(30);

			PageResult pageResultList = this.cashRecordService
					.getCashRecordListAndSellWay(getQueryCashRecordCondition());
			this.cashList = pageResultList.getPageResult();
			pageResultList.setPageResult(getCashList());
			setPage(pageResultList);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			if (startTime != null && !startTime.equals("")) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));
			}
			if (endTime != null && !endTime.equals("")) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));
			}

		} catch (Exception e) {
			logger.error("CashRecordAction.getCashRecordListNoLogin",e);
			return ERROR;
		}
		return "getCashRecordListNoLogin";
	}

	private List<CashRecordDTO> getCashList() {

		if (this.cashList.size() != 0) {
			for (int i = 0; i < this.cashList.size(); i++) {
				SellWay sellWay = sellWayService.getSellWayById(this.cashList
						.get(i).getPackId());
				if (sellWay != null) {
					this.cashList.get(i).setPackName(sellWay.getSellName());
				}
			}
		}
		return this.cashList;
	}

	// 给客服人员看的
	public String getUserCashRecordList() {
		try {

			if (cashRecord.getCtId() != 0) {
				this.getQueryCashRecordCondition()
						.setCtId(cashRecord.getCtId());
			}
			if (cashRecord.getCusId() != 0) {
				this.getQueryCashRecordCondition().setUserId(
						cashRecord.getCusId());
			}

			this.getQueryCashRecordCondition().setPageSize(30);
			setPage(this.cashRecordService
					.getCashRecordList(getQueryCashRecordCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
		} catch (Exception e) {
			logger.error("CashRecordAction.getUserCashRecordList",e);
			return ERROR;
		}
		return "userlistCashRecord";
	}

	public String openContract() {

		return "success";
	}
//李明开始
	/*
	 * 查看单个商品
	 * @return String
	 * Liming
	 */
	public String getSingleCommodity(){
		try {
			getQueryCashRecordCondition().setId(Integer.parseInt(getQueryCashRecordCondition().getPcId()));
			cashRecordDTO=cashRecordService.getSingleCommodity(getQueryCashRecordCondition());
		} catch (Exception e) {
			logger.error("CashRecordAction.getSingleCommodity",e);
			return ERROR;
		}
		return "getSingleCommodity";
	}
	/*
	 * json 修改延期时间
	 * @return String
	 * Liming
	 */
	public String createStopTime(){
		try {
			 queryCashRecordCondition=new QueryCashRecordCondition();
			 int cousmerdd=Integer.parseInt(cousmerId.trim());
			 getQueryCashRecordCondition().setId(cousmerdd);
			 getQueryCashRecordCondition().setDelayTimeString(delayTime);
			cashRecordService.updateStopTime(getQueryCashRecordCondition());
			cashRecordDTO=cashRecordService.getSingleCommodity(getQueryCashRecordCondition());
			setResult(new Result(false,delayTime,null,null));
		
		} catch (Exception e) {
			logger.error("CashRecordAction.getSingleCommodity",e);
			setResult(new Result(false,"error",null,null));		
		}
		return "json";
	}

	/**
	 * 后台关闭课程,只关闭课程状态，不关闭支付状态
	 * @return
	 */
	public String closeCashbyId(){
		String forward = "success";
		int result = 0;
		try{
			if(queryCashRecordCondition != null && queryCashRecordCondition.getId() > 0 && queryCashRecordCondition.getContractId() != null && queryCashRecordCondition.getContractId().trim().length() > 0){
				ChangeSellWay changeSellWay = new ChangeSellWay();
				changeSellWay.setCashId(queryCashRecordCondition.getId());
				changeSellWay.setType(ChangeSellWay.CLOSE_CASH_RECORD_SHOPTYPE);
				changeSellWay.setCusId(queryCashRecordCondition.getCusId());
				changeSellWay.setContractId(queryCashRecordCondition.getContractId());
				changeSellWay.setUserName(this.getLoginedUser().getUserName());
				changeSellWay.setUpdateTime(new Date());
				result = cashRecordService.updateCashRedord4Close(queryCashRecordCondition, changeSellWay);
				if(result <= 0){
					forward = "invalid";
					setMessage("此流水不存在!");
				}
			}else{
				forward = "invalid";
				setMessage("输入参数不合法!");
			}
		}catch(Exception e){
			logger.error("CashRecordAction",e);
			forward = ERROR;
		}
		return forward;
	}
	
	//李明结束

	public String contract1() {
		Date date = new Date();

		return "changeSuccess";
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public CashRecord getCashRecord() {
		return cashRecord;
	}

	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
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

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public QueryCoursesortCondition getQueryCourseSortCondition() {
		return queryCourseSortCondition;
	}

	public void setQueryCourseSortCondition(
			QueryCoursesortCondition queryCourseSortCondition) {
		this.queryCourseSortCondition = queryCourseSortCondition;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public void setCashList(List<CashRecordDTO> cashList) {
		this.cashList = cashList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}



	public CashRecordDTO getCashRecordDTO() {
		return cashRecordDTO;
	}

	public void setCashRecordDTO(CashRecordDTO cashRecordDTO) {
		this.cashRecordDTO = cashRecordDTO;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	public String getCousmerId() {
		return cousmerId;
	}

	public void setCousmerId(String cousmerId) {
		this.cousmerId = cousmerId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) throws UnsupportedEncodingException {
			this.contractId=URLDecoder.decode(contractId, "UTF-8").replace(" ", "");
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UnsupportedEncodingException {
		this.email=URLDecoder.decode(email, "UTF-8").replace(" ", "");
	}
	public String getContractstatus() {
		return contractstatus;
	}
	public void setContractstatus(String contractstatus) {
		this.contractstatus = contractstatus;
	}
	public String getContractpackId() {
		return contractpackId;
	}
	public void setContractpackId(String contractpackId) {
		this.contractpackId = contractpackId;
	}
	public String getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getPayTimeBegin() {
		return payTimeBegin;
	}
	public void setPayTimeBegin(String payTimeBegin) {
		this.payTimeBegin = payTimeBegin;
	}
	public String getPayTimeEnd() {
		return payTimeEnd;
	}
	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) throws UnsupportedEncodingException {
		this.shopName=URLDecoder.decode(shopName, "UTF-8").replace(" ", "");
	}
	public String getContractsubjectId() {
		return contractsubjectId;
	}
	public void setContractsubjectId(String contractsubjectId) {
		this.contractsubjectId = contractsubjectId;
	}
	public String getContractsshopStatus() {
		return contractsshopStatus;
	}
	public void setContractsshopStatus(String contractsshopStatus) {
		this.contractsshopStatus = contractsshopStatus;
	}
	public String getContractpackName() {
		return contractpackName;
	}
	public void setContractpackName(String contractpackName) {
		this.contractpackName = contractpackName;
	}
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}
	public String getPackId() {
		return packId;
	}
	public void setPackId(String packId) {
		this.packId = packId;
	}

	public SellWay getSellWay() {
		return sellWay;
	}
	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public IChange getChangeService() {
		return changeService;
	}
	public void setChangeService(IChange changeService) {
		this.changeService = changeService;
	}
	public ChangeSellWay getChangeSellWay() {
		if(changeSellWay ==null){
			changeSellWay = new ChangeSellWay();
		}
		return changeSellWay;
	}
	public void setChangeSellWay(ChangeSellWay changeSellWay) {
		this.changeSellWay = changeSellWay;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public int getPastSellWayId() {
		return pastSellWayId;
	}
	public void setPastSellWayId(int pastSellWayId) {
		this.pastSellWayId = pastSellWayId;
	}
	public QuerySellWayCondition getQuerySellWayCondition() {
		if(querySellWayCondition ==null){
			querySellWayCondition = new QuerySellWayCondition();
		}
		return querySellWayCondition;
	}
	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}
	public String getYestartTime() {
		return yestartTime;
	}
	public void setYestartTime(String yestartTime) {
		this.yestartTime = yestartTime;
	}
	public String getYeendTime() {
		return yeendTime;
	}
	public void setYeendTime(String yeendTime) {
		this.yeendTime = yeendTime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
