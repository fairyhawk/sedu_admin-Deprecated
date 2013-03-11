package com.shangde.edu.finance.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.highso.core.utils.DateUtil;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.change.domain.ChangeSellWay;
import com.shangde.edu.change.service.IChange;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.dto.CustomerCountDTO;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.IArea;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Cod;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.dto.ContractDTO;
import com.shangde.edu.finance.dto.ContractExcelDTO;
import com.shangde.edu.finance.dto.CrmFinanceDTO;
import com.shangde.edu.finance.dto.MonthDayForpayTypeDTO;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.ICod;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.finance.service.ICoupon;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.sys.service.IUserGradeSubjectRole;
/**
 * 订单管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class ContractAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ContractAction.class);
	private String downloadFileNames;
	private InputStream downloadStreams;
	
	private InputStream excelFile; 
	/**
	 * 声名订单的PO对象
	 */
	private static Logger log= Logger.getLogger(ContractAction.class);
	private Contract contract=new Contract();
	/**
	 * 查询条件
	 */
	private String startTime;
	private String endTime;
	private String yestartTime;
	private String yeendTime;
	private String payStartTime;
	private String payEndTime;
	private String mail;
	private String startHH;
	private String endHH;
	private String fkStartHH;
	private String fkEndHH;
	private String sellNames="";
	private String sale;
	private String stime;

	/**
	 * 知识点查询条件
	 */
	private QueryContractCondition queryContractCondition;
	private QueryContractCondition qwm;
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	/**
	 * 用户的服务
	 */
	private ICustomer customerService;
	private Customer customer;
	private List customerList=new ArrayList();
	private QueryCustomerCondition queryCustomerCondition;
	private List<Contract> contractTempList=new ArrayList<Contract>();
	private List<ContractExcelDTO> contractHDFKTempList=new ArrayList<ContractExcelDTO>();
	private List<ContractDTO> contractDTOList;
	
	 
	private IChange changeService;

	public IChange getChangeService() {
		return changeService;
	}

	public void setChangeService(IChange changeService) {
		this.changeService = changeService;
	}
	
	/**
	 * 知识点查询条件
	 */
	private QueryCashRecordCondition queryCashRecordCondition;

	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;
	/**
	 * 服务
	 */
	private ICusCouKpoint  cusCouKpointService;

	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	
	/**
	 * 
	 */
	private QuerySellWayCondition querySellWayCondition;
	
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;

	/**
	 * 后台用户
	 */
	private User user;
	private IUser userService;
	/**
	 * 角色服务
	 */
	private IUserGradeSubjectRole userGradeSubjectRoleService;
	
	private int roleId;
	/**
	 * 状态参数
	 */
	private String status;
	/**
	 * 支付类型做参数
	 */
	private int payType=-1;
	/**
	 *步骤
	 */
	private int contractStep;
	
	private int subjectId=-1;
	
	private int coupon=-1;
	
	private int sellId=-1;
	/**
	 * 货到付款表服务　
	 */
	private ICod codService;
	
	private List<Course> courseList=new ArrayList<Course>();
	private List<Subject> subjectList=new ArrayList<Subject>();
	private ISubject subjectService;
	private ICourse courseService;
	private Course course;
	private List<Contract> contractList=new ArrayList<Contract>();
	/**
	 * 地址的服务
	 */
	private IAddress addressService;
	/**
	 * 地址的对象
	 */
	private Address address;
	/**
	 * 地址串
	 */
	private String addressStr;
	/**
	 * 省市区
	 */
	private IArea areaService;
	/**
	 * 课程List
	 */
	private List <CourseDTO>newCourseList=new ArrayList<CourseDTO>();
	/**
	 * 配置文件
	 */
	private ConfigService configurator;
	/**
	 * 推广来源
	 */
	private List<String> webFromStr;
	/**
	 * 销售代理商
	 */
	private List<String> webAgentStr;
	
	private List<CashRecord> cashRecordList=new ArrayList<CashRecord>();
	/**
	 * 查询订单总计
	 */
	private int contractPriceSum=0;
	private String exportType;
	private int fromURLType;

	/**
	 * 查询售卖方式集合
	 */
	private List<SellWay> sellWayList=new ArrayList<SellWay>();
	private ISellWay sellWayService;
	private SellWay sellWay;
	private ISellCou sellCouService;
	private List<MonthDayForpayTypeDTO> monthDayList=null;
	/**
	 * 优惠卷
	 */
	
	private ICoupon financeCouponService;
	
	private Coupon coup;
	
	/**
	 * 用户登录地点
	 */
	private List<LoginRecord> loginRecordList=new ArrayList<LoginRecord>();
	private ILoginRecord loginRecordService;
	public static final String COOKIE_REMEMBERME_KEY = "sedu.cookie.ukey";	
	
	private String first;
	private String years="";
	private String months="";
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * 查看订单列表的条件
	 */	
	private void contractTJ() {
		try {
			
			this.getQueryContractCondition().setSubjectId(this.subjectId);
			QuerySubjectCondition querySubjectCondition=new QuerySubjectCondition();
		
			if(stime!=null&&stime.equals("stime")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryContractCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
				this.getQueryContractCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
				yestartTime=sdf.format(new Date()).trim();
				yeendTime=sdf.format(new Date()).trim();
				}
			 subjectList=subjectService.getSubjectList(querySubjectCondition);
			//扩展，如果需要用专业查询时
			 if(coupon!=-1){
				 this.getQueryContractCondition().setCoupon(coupon);
			 }
			 if(sellId!=-1){
				 this.getQueryContractCondition().setSellId(sellId);
			 }
			 if(course!=null&&course.getCourseId()!=0)
			 {			
				 queryContractCondition.setCourseId(course.getCourseId());
				 //根据课程ID查订单
				 queryContractCondition.setPageSize(30);
				 setPage(contractService.getContractByCourseId(queryContractCondition));
				 setPageUrlParms();
				 if(getPage()!=null){
					   getPage().setPageSize(30);
				 }				
			 }else
			 {
				if (startTime != null && !"".equals(startTime)) {
					yestartTime=startTime;
					startTime=startTime+startHH;
					
					this.getQueryContractCondition().setStartTime(startTime.trim());
				}
				if(endTime!=null &&!"".equals(endTime)){
					yeendTime=endTime;
					endTime=endTime+endHH;
					this.getQueryContractCondition().setEndTime(endTime.trim());
				}
				if (payStartTime != null && !"".equals(payStartTime)) {
					payStartTime=payStartTime+fkStartHH;
					this.getQueryContractCondition().setPayStartTime(payStartTime);
				}
				if(payEndTime!=null &&!"".equals(payEndTime)){
					payEndTime=payEndTime+fkEndHH;
					this.getQueryContractCondition().setPayEndTime(payEndTime);
				}
				if(contract.getContractFromUrl()!=null&&!"".equals(contract.getContractFromUrl().trim())){
					if(contract.getContractFromUrl().equals("1")){
						this.getQueryContractCondition().setContractFromUrl("highso.org.cn");
					}else if(contract.getContractFromUrl().equals("2")){
						this.getQueryContractCondition().setContractFromUrl("highso.cn");
					}else if(contract.getContractFromUrl().equals("3"))
					{
						this.getQueryContractCondition().setContractFromUrl("highso.org");
					}
					else if(contract.getContractFromUrl().equals("4"))
					{
						this.getQueryContractCondition().setContractFromUrl("highso.com.cn");
					}else if(contract.getContractFromUrl().equals("5"))
					{
						this.getQueryContractCondition().setContractFromUrl("highso.net.cn");
					}
					// 新增域名 haixue.com / Modified by ZHENG QIANG
					else if (contract.getContractFromUrl().equals("6")) {
						this.getQueryContractCondition().setContractFromUrl("haixue.com");
					}
				}
				if(contract.getWebFrom()!=null&&!"".equals(contract.getWebFrom().trim())){
					this.getQueryContractCondition().setWebFrom(contract.getWebFrom().trim());
				}
				if(contract.getWebAgent()!=null&&!"".equals(contract.getWebAgent().trim())){
					this.getQueryContractCondition().setWebAgent(contract.getWebAgent().trim());
				}
				if(status!=null&&!"".equals(status)){
					this.getQueryContractCondition().setNewStatus(status);
				}
				if(contract.getContractId()!=null&&!"".equals(contract.getContractId().trim())){
					this.getQueryContractCondition().setContractId(contract.getContractId().trim());
				}
				if(contract.getContractFrom()!=null&&!"".equals(contract.getContractFrom().trim())){
					this.getQueryContractCondition().setContractFrom(contract.getContractFrom().trim());
				}
				if(contract.getCusId()!=0){
					this.getQueryContractCondition().setUserId(contract.getCusId());
				}
				this.getQueryContractCondition().setPayType(payType);
				if(mail!=null&&!"".equals(mail)){
					Customer customer = this.customerService.getCustomerByEmail(mail.trim());
					if(customer!=null){
						if(customer.getCusId()!=0){
							this.getQueryContractCondition().setUserId(customer.getCusId());
						}
					}else {
						this.getQueryContractCondition().setUserId(1);  //添加一个数据库里不会出现的用户id
					}
				}
				//推广来源 
				webFromStr=this.configurator.getWebFromList();
				//代理商销售
				webAgentStr=this.configurator.getWebAgentList();
			 }
		} catch (Exception e) {
			logger.error("ContractAction.contractTJ", e);
		}
	}
	//查询后将时间转变成正常类型
	private void initDateTJ() {
		if(payStartTime!=null&&!"".equals(payStartTime)&&fkStartHH!=null){
			 payStartTime=payStartTime.substring(0,payStartTime.indexOf(fkStartHH));
		 }if(payEndTime!=null&&!"".equals(payEndTime)&&fkEndHH!=null){
			 payEndTime=payEndTime.substring(0,payEndTime.indexOf(fkEndHH));
		 }if(startTime!=null&&!"".equals(startTime)&&startHH!=null){
			 startTime=startTime.substring(0,startTime.indexOf(startHH));
		 }if(endTime!=null&&!"".equals(endTime)&&endHH!=null){
			 endTime=endTime.substring(0,endTime.indexOf(endHH));
		 }
	}
	/**
	 * 查看订单列表
	 * @return
	 */
	public String getContractList() {
		try{
			//查询条件方法
			yestartTime="";
			yeendTime="";
			contractTJ();
			contractPriceSum = this.contractService.getReconciliationAmout(queryContractCondition);
			this.getQueryContractCondition().setPageSize(30);
			PageResult pageResult =null;
			if("first".equals(first)){
				pageResult=this.contractService.contractAllFirst(queryContractCondition);
			}else{
				if(this.getQueryContractCondition().getSubjectId()==0||this.getQueryContractCondition().getSubjectId()==-1){
					pageResult= this.contractService.contractAll(this.getQueryContractCondition());
				}else{
					pageResult=this.contractService.contractAllSubject(queryContractCondition);
				}						
			}
			if(this.subjectId!=0&&this.subjectId!=-1){
				sellWayList = this.sellWayService.getSellWayBySubjectId(this.subjectId);
			}
			coupon=this.getQueryContractCondition().getCoupon();
			setPage(pageResult);
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			 }
			 initDateTJ();
			 
		}catch (Exception e) {
			logger.error("ContractAction.getContractList", e);
			return ERROR;
		}
		 return "listContract";
	}
	
	/**
	 * 查看订单列表
	 * @return
	 */
	/*public String getCRMContractList() {
		try{
			//查询条件方法
			yestartTime="";
			yeendTime="";
			contractTJ();
			contractPriceSum = this.contractService.getReconciliationAmout(queryContractCondition);
			this.getQueryContractCondition().setPageSize(30);
			PageResult pageResult =null;
			if("first".equals(first)){
				pageResult=this.contractService.contractAllFirst(queryContractCondition);
			}else{
				if(this.getQueryContractCondition().getSubjectId()==0||this.getQueryContractCondition().getSubjectId()==-1){
					pageResult= this.contractService.contractAll(this.getQueryContractCondition());
				}else{
					pageResult=this.contractService.contractAllSubject(queryContractCondition);
				}						
			}
			if(this.subjectId!=0&&this.subjectId!=-1){
				sellWayList = this.sellWayService.getSellWayBySubjectId(this.subjectId);
			}
			coupon=this.getQueryContractCondition().getCoupon();
			List<ContractDTO> crmContractList=pageResult.getPageResult();
			for(ContractDTO crmDTO:crmContractList)
			{
				int cus_id=crmDTO.getCusId();
				List<CrmFinanceDTO> crmFinaceDtoList=contractService.getCRMFinanceByCusId(cus_id);
				if(crmFinaceDtoList.size()>=1){
				CrmFinanceDTO crmFinance=new CrmFinanceDTO();
				crmFinance.setLastRecordTime(crmFinaceDtoList.get(crmFinaceDtoList.size()-1).getRecordTime());//获取最后指派的时间 
				crmFinance.setRecordTime(crmFinaceDtoList.get(0).getRecordTime());//获取首次指派的时间 
				crmFinance.setOrigin(crmFinaceDtoList.get(0).getOrigin());//来源
				//crmFinance.setUserId(crmFinaceDtoList.get(0).getUserId());
				crmFinance.setUserName(crmFinaceDtoList.get(0).getUserName());//获取首次指派的销售 
				crmFinance.setLastUserName(crmFinaceDtoList.get(crmFinaceDtoList.size()-1).getUserName());//获取最后指派的销售 
				crmDTO.setCrmFinance(crmFinance);
				}
			}
			setPage(pageResult);
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			 }
			 initDateTJ();
			 
		}catch (Exception e) {
			logger.error("ContractAction.getContractList", e);
			return ERROR;
		}
		 return "crmListContract";
	}*/
	
	
	/**
	 * 查看crm订单列表
	 * @author 王超
	 * @return
	 */
	public String getCRMContractList() {
		try{
			//查询条件方法
			yestartTime="";
			yeendTime="";
			contractTJ();
			contractPriceSum = this.contractService.getReconciliationAmout(queryContractCondition);
			this.getQueryContractCondition().setPageSize(30);
			PageResult pageResult =this.contractService.getCrmContract(queryContractCondition);
			if(this.getQueryContractCondition().getSubjectId()>0){
				sellWayList = this.sellWayService.getSellWayBySubjectId(this.subjectId);
			}
			coupon=this.getQueryContractCondition().getCoupon();
			setPage(pageResult);
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			 }
			 initDateTJ();
			 
		}catch (Exception e) {
			logger.error("ContractAction.getContractList", e);
			return ERROR;
		}
		 return "crmListContract";
	}
	
	/* 
	 * fanxin  2011-08-10  
	 * 添加属性 regNum （统计通过某个推广网站或站长注册人数）
	 */
	
	private int regNum;
	
	public int getRegNum() {
		return regNum;
	}

	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}
	
	//推 广 网 盟  已下订单学员量

	private int contractNum;
	private int notContractNum;
	
	private int PayNum;
	private int PaySucNum;
	private int PayfalNum;

	public int getContractNum() {
		return contractNum;
	}

	public void setContractNum(int contractNum) {
		this.contractNum = contractNum;
	}

	public int getNotContractNum() {
		return notContractNum;
	}
	
	public void setNotContractNum(int notContractNum) {
		this.notContractNum = notContractNum;
	}

	public int getPayNum() {
		return PayNum;
	}

	public void setPayNum(int payNum) {
		PayNum = payNum;
	}

	public int getPaySucNum() {
		return PaySucNum;
	}

	public void setPaySucNum(int paySucNum) {
		PaySucNum = paySucNum;
	}

	public int getPayfalNum() {
		return PayfalNum;
	}

	public void setPayfalNum(int payfalNum) {
		PayfalNum = payfalNum;
	}
	/**
     * 查看订单列表
     * @return
     */
    public String getwml() {
        try{
            //查询条件方法
        	getQueryContractCondition();
        	
        	//防止为空时查询出数据
        	if(getQwm().getWebFrom()==null || getQwm().getWebFrom().trim().equals("")){
        		getQwm().setWebFrom("liuqg");
        	}
        	this.setQueryContractCondition(getQwm());
            contractTJ();
            //查询金额总计(统计当前条件下订单总金额)
            contractPriceSum=this.contractService.getContractPriceSumwm(getQwm());
            this.getQwm().setPageSize(30);
            setPage(this.contractService
                    .getContractListwm(getQwm()));
            setPageUrlParms();
             if(getPage()!=null){
                   getPage().setPageSize(30);
             }
             //还原时间
             initDateTJ();
             
             //fanxin -  2011-08-12 页面统计人数
			/* 
			 getQueryCustomerCondition().setCusWebAgent(contract.getWebAgent());
			 getQueryCustomerCondition().setCusWebFrom(contract.getWebFrom());
			 regNum = customerService.getCustomerRegNum(queryCustomerCondition);
             */
        }catch (Exception e) {
        	logger.error("ContractAction.getwml", e);
			return ERROR;
        }
         return "wmlistContract";
    }
    
    /**
     * 查看订单列表
     fanxin -  2011-08-24 页面统计人数 
     * @return
     */ 
    public String getwmlcus() {
        try{
        	//查询条件方法
        	getQueryContractCondition();
        	
        	//防止为空时查询出数据
        	if(getQwm().getWebFrom()==null || getQwm().getWebFrom().trim().equals("")){
        		getQwm().setWebFrom("liuqg");
        	}
        	this.setQueryContractCondition(getQwm());
            contractTJ();
            //查询金额总计(统计当前条件下订单总金额)
            contractPriceSum=this.contractService.getContractPriceSumwmCus(getQwm());
            this.getQwm().setPageSize(30);
            setPage(this.contractService.getContractListwmCus(getQwm()));
            setPageUrlParms();
             if(getPage()!=null){
                   getPage().setPageSize(30);
             }
             //还原时间
             initDateTJ();
             
        }catch (Exception e) {
        	logger.error("ContractAction.getwmlcus", e);
			return ERROR;
        }
         return "getwmlcus";
    }
    
    
  //fanxin -  2011-08-22 页面统计人数
     public String catchRegNum(){
    	 try{
			 String cusWebFrom =  getQueryCustomerCondition().getCusWebFrom();
			 String cusWebAgent = getQueryCustomerCondition().getCusWebAgent();
			 String startTime = getQueryCustomerCondition().getStartTime();
			 String endTime = getQueryCustomerCondition().getEndTime();
			 
			 if((cusWebFrom==null&&cusWebAgent==null&&startTime==null&&endTime==null)||(getQueryCustomerCondition()==null)){
				 regNum = 0;
				 contractNum = 0;
				 contractNum = 0;
				 PayNum = 0;
				 PaySucNum = 0;
				 PayfalNum = 0;
			 }else{
				 regNum = customerService.getCustomerRegNumCount(queryCustomerCondition);
				 contractNum = customerService.getContractNum(queryCustomerCondition);
				 notContractNum = regNum - contractNum;
				 PayNum = customerService.getPayNum(queryCustomerCondition);
				 PaySucNum = customerService.getPaySucNum(queryCustomerCondition);
				 PayfalNum = PayNum -PaySucNum;
			 }
    	 }catch(Exception e){
    		 logger.error("ContractAction.catchRegNum", e);
 			 return ERROR;
    	 }
		return "catchRegNum";
     }
    
    
	/**
	 * 导出订单到excel
	 * @return
	 * @throws InterruptedException 
	 */
	public String exportExcelFile()
	{
		
		try{
			//查询条件
		 	contractTJ();
		 	//初始化一个集合用来存放查询出的订单列表
			//根据条件查询订单列表
		 	contractDTOList=this.contractService.getContractDTOList(getQueryContractCondition());
		 	//初始化时间条件
			createExcelFile(1);
			initDateTJ();
		}catch(Exception e){
			logger.error("ContractAction.exportExcelFile", e);
			 return ERROR;
		}
		return "exportExcelqb";
	}
	
	
	/**
	 * 导出订单到excel
	 * @return
	 * @throws InterruptedException 
	 */
	public String exportCRMExcelFile()
	{
		
		try{
			//查询条件
		 	contractTJ();
		 	//初始化一个集合用来存放查询出的订单列表
			//根据条件查询订单列表
		 	contractDTOList=this.contractService.getContractDTOList(getQueryContractCondition());
			for(ContractDTO crmDTO:contractDTOList)
			{
				int cus_id=crmDTO.getCusId();
				List<CrmFinanceDTO> crmFinaceDtoList=contractService.getCRMFinanceByCusId(cus_id);
				if(crmFinaceDtoList.size()>=1){
				CrmFinanceDTO crmFinance=new CrmFinanceDTO();
				crmFinance.setLastRecordTime(crmFinaceDtoList.get(crmFinaceDtoList.size()-1).getRecordTime());//获取最后指派的时间 
				crmFinance.setRecordTime(crmFinaceDtoList.get(0).getRecordTime());//获取首次指派的时间 
				crmFinance.setOrigin(crmFinaceDtoList.get(0).getOrigin());//来源
				//crmFinance.setUserId(crmFinaceDtoList.get(0).getUserId());
				crmFinance.setUserName(crmFinaceDtoList.get(0).getUserName());//获取首次指派的销售 
				crmFinance.setLastUserName(crmFinaceDtoList.get(crmFinaceDtoList.size()-1).getUserName());//获取最后指派的销售 
				crmDTO.setCrmFinance(crmFinance);
				}
			}
		 	//初始化时间条件
			createExcelFile(66);
			initDateTJ();
		}catch(Exception e){
			logger.error("ContractAction.exportExcelFile", e);
			 return ERROR;
		}
		return "exportExcelqb";
	}
	/** 生成excel
	 * @param 导出类型 0 普通 1 货到付款
	 */
	 private void createExcelFile(int type) 
	 {
		 //格式化时间
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 List<Subject> subList = this.subjectService.getAllSubject();
		 //运费
		 float yf=10;
		 //下次修改内容
		 String [] headName={
				 "订单号"
				 ,"电子邮箱"
				 ,"注册手机"
				 ,"注册项目"
				 ,"注册域名"
				 ,"登录地区"
				 ,"注册时间"
				 ,"下单时间"
				 ,"完成支付时间"
				 ,"下单webagent"
				 ,"下单webfrom"
				 ,"下单域名"
				 ,"交易方式"
				 ,"交易状态"
				 ,"订单金额"
				 ,"实收金额"
				 ,"优惠金额"
				 ,"运费"
				 ,"是否使用优惠券"
				 ,"购买课程名"
				 ,"所属项目"
				 ,"配送地址"
				 ,"电话"
				 };
		 if(type==66){//如果是crm 需要导出的订单
		 	String crmHeadName []={
					 "订单号"
					 ,"电子邮箱"
					 ,"注册手机"
					 ,"注册项目"
					 ,"注册域名"
					 ,"登录地区"
					 ,"注册时间"
					 ,"下单时间"
					 ,"完成支付时间"
					 ,"下单webagent"
					 ,"下单webfrom"
					 ,"下单域名"
					 ,"交易方式"
					 ,"交易状态"
					 ,"订单金额"
					 ,"实收金额"
					 ,"优惠金额"
					 ,"运费"
					 ,"是否使用优惠券"
					 ,"购买课程名"
					 ,"所属项目"
					 ,"配送地址"
					// ,"电话"
					 ,"创建销售"
					 ,"机会创建时间"
					 ,"场景"
					 ,"最后指派销售"
					 ,"最后指派时间"
					 };
		 	headName=crmHeadName;
		 }
				 HSSFWorkbook workbook = new HSSFWorkbook(); 
				 HSSFSheet sheet = workbook.createSheet("sheet1");  
				               // 创建表头 
					 HSSFRow row = sheet.createRow(0);			
					 HSSFCell cell = row.createCell((short) 0);
					 for(int i=0;i<headName.length;i++)
					 {
						 cell = row.createCell((short) i);  
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
						 cell.setCellValue(headName[i]);
					 }		      
					 // 创建数据行              
					 for(int i=1,j=0;contractDTOList!=null && j<contractDTOList.size();i++,j++){
						
						 if(contractDTOList.get(j).getPayType()==2)
						 {
							 yf=10;
						 }else
						 {
							 yf=0;
						 }
						 row = sheet.createRow(i);  
						 //订单号
						 cell = row.createCell((short) 0);
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
						 cell.setCellValue(contractDTOList.get(j).getContractId());
						 //用户名 电子邮箱
						 cell = row.createCell((short) 1);  
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);             
						 cell.setCellValue(contractDTOList.get(j).getEmail());
						 //用户注册电话
						 cell = row.createCell((short) 2);  
                         cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);             
                         cell.setCellValue(contractDTOList.get(j).getMobile());
                         
						 //注册项目
						 cell = row.createCell((short) 3);  
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						 //SubjectId为0特殊处理
						 String tempSn = "";
						 if(contractDTOList.get(j).getSubjectId() != 0){
							for (int k = 0; k < subList.size(); k++) {
								if(subList.get(k).getSubjectId()==contractDTOList.get(j).getSubjectId()){
									 tempSn = subList.get(k).getSubjectName();
								}
							}
						 }
						 
						 cell.setCellValue(tempSn);
						 //注册域名
						 cell = row.createCell((short) 4);              
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						 cell.setCellValue(contractDTOList.get(j).getCusFromUrl());   
						 //注册地点
						// LoginRecord loginTemp=loginRecordService.getFirstLoginRecordByCusId(contractDTOList.get(j).getCusId());
						 cell = row.createCell((short) 5);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						// cell.setCellValue(loginTemp!=null?loginTemp.getAddress():"");  
						 cell.setCellValue(""); 
						 //注册时间
						 cell = row.createCell((short) 6);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(sdf.format(contractDTOList.get(j).getRegTime()));   
						 //下单时间
						 cell = row.createCell((short) 7);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  					
						 cell.setCellValue(sdf.format(contractDTOList.get(j).getCreateTime()));
						 //支付时间
						 String payTime="";
						 if(contractDTOList.get(j).getPayTime()!=null)
						 {
							 payTime=sdf.format(contractDTOList.get(j).getPayTime());
						 }
						 cell = row.createCell((short) 8);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(payTime);
						 //下单webagent
						 cell = row.createCell((short) 9);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getWebAgent());
						 //下单webfrom
						 cell = row.createCell((short) 10);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getWebFrom());
						 //下单域名
						 cell = row.createCell((short) 11);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getContractFromUrl());
						 //交易方式
						 String payType="";
						 String payStatus="";
						 int payT=contractDTOList.get(j).getPayType();
						 int payS=contractDTOList.get(j).getStatus();
						 if(payT==0)
						 {
							 payType="赠送";
							 if(payS==0)
							 {
								 payStatus="赠送";
							 }else if(payS==4)
							 {
								 payStatus="修复";
							 }
						 }else if(payT==1)
						 {
							 payType="支付宝";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==2)
						 {
							 payType="货到付款";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==3)
						 {
							 payType="网银在线";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==4)
						 {
							 payType="快钱";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==5)
						 {
							 payType="代理商";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==6)
						 {
							 payType="真友";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==7)
						 {
							 payType="银行汇款";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==8)
						 {
							 payType="银联";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==9)
						 {
							 payType="课程卡";
							 if(payS==0)
							 {
								 payStatus="未付";
							 }else if(payS==1)
							 {
								 payStatus="已付";
							 }else if(payS==3)
							 {
								 payStatus="退费";
							 }else if(payS==4)
							 {
								 payStatus="取消";
							 }
						 }else if(payT==10)
                         {
                             payType="银联语音";
                             if(payS==0)
                             {
                                 payStatus="未付";
                             }else if(payS==1)
                             {
                                 payStatus="已付";
                             }else if(payS==3)
                             {
                                 payStatus="退费";
                             }else if(payS==4)
                             {
                                 payStatus="取消";
                             }
                         }
						 
						 cell = row.createCell((short) 12);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(payType);
						 //交易状态
						 cell = row.createCell((short) 13);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(payStatus);
						 //订单金额					
						 cell = row.createCell((short) 14);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  						
						 cell.setCellValue((Float.parseFloat(contractDTOList.get(j).getContractSumMoney().toString())));

						 
						 cell = row.createCell((short) 15);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 if(payT==2)//如果货倒个需求加邮费 
						 {
						 cell.setCellValue((Float.parseFloat(contractDTOList.get(j).getContractCutSumMoney().toString()))+10);
						 }else{
							 cell.setCellValue((Float.parseFloat(contractDTOList.get(j).getContractCutSumMoney().toString())));
						 }
						
						 
						 cell = row.createCell((short) 16);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 if(contractDTOList.get(j).getCouponMoney()==null){
							 contractDTOList.get(j).setCouponMoney(0);
						 }
						 cell.setCellValue((Float.parseFloat(contractDTOList.get(j).getCouponMoney().toString())));
						 
						 cell = row.createCell((short) 17);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue((Float.parseFloat(contractDTOList.get(j).getFreight().toString())));
						 
						 if(contractDTOList.get(j).getUseCoupon()==1){
							 cell = row.createCell((short) 18);    
							 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
							 cell.setCellValue("是");
						 }else {
							 cell = row.createCell((short) 18);    
							 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
							 cell.setCellValue("否");
						 }
						
						 //导出excle添加售卖方式列
						 cell = row.createCell((short) 19);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getSellWayName());
						//导出excle添加售卖方式所属项目
						 cell = row.createCell((short) 20);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
						 Subject subject=null;
						 if(contractDTOList.get(j).getSellWayId()!=0){
							 SellWay sellWay = sellWayService.getSellWayById(contractDTOList.get(j).getSellWayId());
							 subject = subjectService.getSubjectById(sellWay.getSubjectId());
							 cell.setCellValue(subject.getSubjectName());
						 }
						//导出excle添加地址
						 //添加用户配送地址
						 cell = row.createCell((short) 21);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(getCusAddress(contractDTOList.get(j).getCusId()).substring(0, getCusAddress(contractDTOList.get(j).getCusId()).indexOf("电话：")));
						 //导出电话
						 if(type!=66)
						 {
						 cell = row.createCell((short) 22);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(getCusAddress(contractDTOList.get(j).getCusId()).substring(getCusAddress(contractDTOList.get(j).getCusId()).indexOf("电话：")+3));
						 }
					 if(type==66 && contractDTOList.get(j).getCrmFinance()!=null){//如果是crm 需要导出的订单
						 //创建销售
						 cell = row.createCell((short) 22);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getCrmFinance().getUserName());
						//机会创建时间
						 cell = row.createCell((short) 23);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(sdf.format(contractDTOList.get(j).getCrmFinance().getRecordTime()));
						 
						 //创建场景
						 cell = row.createCell((short) 24);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 int origin=contractDTOList.get(j).getCrmFinance().getOrigin();
						 String crmOrigin="";
						 switch(origin){
						 case 1: crmOrigin="自然注册"; break;
						 case 2: crmOrigin="乐语在线";break;
						 case 4: crmOrigin="自然留言";break;
						 case 6: crmOrigin="CallIn";break;
						 }
						 cell.setCellValue(crmOrigin);
						 
						 //最后指派销售
						 cell = row.createCell((short) 25);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(contractDTOList.get(j).getCrmFinance().getLastUserName());
						 
						 //最后指派时间
						 cell = row.createCell((short) 26);    
						 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
						 cell.setCellValue(sdf.format(contractDTOList.get(j).getCrmFinance().getLastRecordTime()));
						 }
					 }
					 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					 try 
					 {              
						 workbook.write(baos);          
					 } 
					 catch (IOException e) 
					 {
						 logger.error("ContractAction.createExcelFile", e);
					 }          
					 byte[] ba = baos.toByteArray();          
					 ByteArrayInputStream bais = new ByteArrayInputStream(ba);          
					 this.setExcelFile(bais) ;  
			
		 }
	 
	 /**
	  * 为excle导出地址提供 得到学员地址
	  * 
	  */
	 private String getCusAddress(int customerId){
		 String addString="";
		 try{
					 if(customerId!=0){
			
					 List<Address> addressList = this.addressService.getAddressByCusId(customerId);
					 if(addressList!=null){
					 if(addressList.size()!=0){
						 for (int i = 0; i < addressList.size(); i++) {
							 if(addressList.size()>1){
								
								 Area area1 = areaService.getAreaById(addressList.get(addressList.size()-1).getProvinceId());
								 Area area2 = areaService.getAreaById(addressList.get(addressList.size()-1).getCityId());
								 Area area3 = areaService.getAreaById(addressList.get(addressList.size()-1).getTownId());
								 if(area1!=null){
									 addString+=area1.getAreaName();
								 }
								 if(area2!=null){
									 addString+=area2.getAreaName();
								 }
								 if(area3!=null){
									 addString+=area3.getAreaName();
								 }
								 addString+=addressList.get(addressList.size()-1).getAddress()+",邮编："+addressList.get(addressList.size()-1).getPostCode()+",姓名："+addressList.get(addressList.size()-1).getReceiver()+"电话："+addressList.get(addressList.size()-1).getMobile();
								 break;
							 }else {
								 Area area1 = areaService.getAreaById(addressList.get(i).getProvinceId());
								 Area area2 = areaService.getAreaById(addressList.get(i).getCityId());
								 Area area3 = areaService.getAreaById(addressList.get(i).getTownId());
								 if(area1!=null){
									 addString+=area1.getAreaName();
								 }
								 if(area2!=null){
									 addString+=area2.getAreaName();
								 }
								 if(area3!=null){
									 addString+=area3.getAreaName();
								 }
								 addString+=addressList.get(i).getAddress()+",邮编："+addressList.get(i).getPostCode()+",姓名："+addressList.get(i).getReceiver()+"电话："+addressList.get(i).getMobile();
								 break;
							 }
						    }
						     }else {
						    	 addString="当前用户无配送地址 电话：无联系电话";
							}
					 }else {
						addString="当前用户无配送地址 电话：无联系电话";
					}
				}
		 }catch(Exception e){
			 logger.error("ContractAction.getCusAddress", e);
		 }
		 return addString;
	 }
	
	/**
	 * 审核订单 
	 * @return
	 */
	public String getApprove(){
		try{
//			if(contract.getContractId()!=null&&!"".equals(contract.getContractId())){
//				this.getQueryCashRecordCondition().setContractId(contract.getContractId());
//			}
			if(contract.getId()!=0){
				this.getQueryCashRecordCondition().setCtId(contract.getId());
			}
			if(contract.getCusId()!=0){
				this.getQueryCashRecordCondition().setUserId(contract.getCusId());
			}
			Contract contractTemp=this.getContractService().getContractById(contract.getId());
			//订单状态改为修复，支付类型改为(赠送) 已支付
			//contractTemp.setPayType(0);
			//contractTemp.setStatus(4);
			
			contractTemp.setStatus(1);
			contractTemp.setPayTime(new Date());
			
			this.getContractService().updateContract(contractTemp);
			List<CashRecord> cashRecordList=this.getCashRecordService().getCashRecordByList(getQueryCashRecordCondition());
			for(int i=0;cashRecordList!=null&&i<cashRecordList.size();i++){
				if(cashRecordList.get(i).getStatus()!=1)
				{
					CashRecord cashRecordTemp=cashRecordList.get(i);
					cashRecordTemp.setStatus(1);
					cashRecordTemp.setShopStatus(1);     //商品状态设为：已激活
					//cashRecordTemp.setShopPayType(0);		 //支付类型设为：内部开通 
					cashRecordTemp.setShopPayTime(new Date()); //支付时间设为：now
					
					this.getCashRecordService().updateCashRecord(cashRecordTemp);
				}
			}
			
			//为审核添加计入
			ChangeSellWay  changeSellWay = new ChangeSellWay();
			changeSellWay.setCusId(contract.getCusId());
			changeSellWay.setContractId(contractTemp.getContractId());
			changeSellWay.setType(2);
			String userName = this.getLoginedUser().getLoginName();
			changeSellWay.setUserName(userName);
			changeSellWay.setUpdateTime(new Date());
			
			changeService.addChangeSellWay(changeSellWay);
			
		} catch (Exception e) {
			logger.error("ContractAction.getApprove", e);
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 退费的方法
	 *  
	 * @return
	 */
	public String ReFund(){
		try{
			//更改状态，是退费的状态，为　3
			if(contract.getId()!=0){
				contract=this.contractService.getContractById(contract.getId());
				contract.setStatus(3);
				this.contractService.updateContract(contract);
				
				//this.getQueryCashRecordCondition().setContractId(contract.getContractId());
				/*this.getQueryCashRecordCondition().setCtId(contract.getId());
				this.getQueryCashRecordCondition().setUserId(contract.getCusId());
				List<CashRecord> cashRecordList=this.getCashRecordService().getCashRecordByList(getQueryCashRecordCondition());
				for(int i=0;i<cashRecordList.size();i++)
				{
					CashRecord cashRecordTemp=cashRecordList.get(i);
					//流水状态时0的 无课程
					cashRecordTemp.setStatus(0);
					cashRecordService.updateCashRecord(cashRecordTemp);
				}*/
				
				int cashRecirdCtId = contract.getId();
				CashRecord cr = new CashRecord();
				cr.setStatus(3);      //状态设为：退费
				cr.setCtId(cashRecirdCtId); //订单Id
				this.getCashRecordService().updateCashReocrdStatusByCtId(cr);
			
			}
		}catch(Exception e){
			logger.error("ContractAction.ReFund", e);
			return ERROR;
		}
	return "changeSuccess";
	}
//二级联动查询	
	public String getContractByPayType() {
		try {
			List<Contract> contractList = this.contractService
					.getContractByPayType(payType);
			JSONArray jslist = JSONArray.fromObject(contractList);
			this.setResult(new Result<Object>(true, jslist.toString(), null,
					null));
		} catch (RuntimeException e) {
			logger.error("ContractAction.getContractByPayType", e);
			return "ERROR";
		}
		return "json";
	}
//更改货到付款订单的状态
	public String getContractCOD(){
		try{
		Date date=new Date();
		user=this.getLoginedUser();
		if(contract.getId()!=0&&contractStep!=0){
			Cod cod=new Cod();
			cod.setCodCtId(contract.getId());
			cod.setCodTime(date);
			cod.setCodAuditId(user.getUserId());
			cod.setCodStatus(contractStep);
			if(contractStep==1){
				cod.setCodContent("步骤一：");
			}else if(contractStep==2){
				cod.setCodContent("步骤二：");
			}else if(contractStep==3){
				cod.setCodContent("步骤三：");
			}else if(contractStep==4){
				cod.setCodContent("步骤四：");
			}
			this.codService.addCod(cod);
		}
		}catch (RuntimeException e) {
			logger.error("ContractAction.getContractCOD", e);
			return "ERROR";
		}
		return "getContractCOD";
	}
	
	//详细页面显示的数据
	public String getContractView(){
	 try{ 
		 if(contract!=null&&contract.getId()!=0){
			 contract=this.contractService.getContractById(contract.getId());
			 if(contract!=null && contract.getCusIdAddress()!=0){
				 address=this.addressService.getAddressById(contract.getCusIdAddress());
				 customer=this.customerService.getCustomerById(address.getCusId());
				 String provinceName="";
				 String cityName="";
				 String townName="";
				 Area areaProvince = areaService.getAreaById(address.getProvinceId());
				 if(areaProvince !=null){
					 provinceName = areaProvince.getAreaName();
				 }
				 Area areaCity = areaService.getAreaById(address.getCityId());
				 if(areaCity!=null){
					 cityName=areaCity.getAreaName();
				 }
				 Area areaTown = areaService.getAreaById(address.getTownId());
				 if(areaTown!=null){
					 townName = areaTown.getAreaName();
				 }
				 addressStr=provinceName + cityName + townName + address.getAddress();
			 }
			 this.getQueryCashRecordCondition().setCtId(contract.getId());
			 this.getQueryCashRecordCondition().setUserId(contract.getCusId());
			 List <CashRecord>cashRecordList=this.cashRecordService.getCashRecordByList(getQueryCashRecordCondition());
			 sellWayList = new ArrayList<SellWay>();
			 SellWay sellWayco = new SellWay(); 
			 Set<Integer> tmpset = new HashSet<Integer>(); 
			 for(int i=0;cashRecordList!=null&&i<cashRecordList.size();i++){
			     //针对以前旧的订单，流水中记录的是所有的课程，售卖方式会重复,一个售卖方式只需查一次即可
			     if (cashRecordList.get(i)!=null && !tmpset.contains(cashRecordList.get(i).getPackId())&&cashRecordList.get(i).getPackId()!=0){
			         tmpset.add(cashRecordList.get(i).getPackId());
			         sellWayco=sellWayService.getSellWayById(cashRecordList.get(i).getPackId());
			         if (sellWayList!=null &&  sellWayco!=null){
			        	 sellWayList.add(sellWayco);
	                 }
			     }
			 }
		 }
		}catch (RuntimeException e){
			logger.error("ContractAction.getContractView", e);
			return "ERROR";
		}
		return "getContractView";
	}
	

	
	public String getContractInfo(){
		 try{ 
			 if(contract.getId()!=0){
				 contract=this.contractService.getContractById(contract.getId());
				 //针对流水中售卖方式会重复,去掉重复的售卖方式
				  List<SellWay> sellWayListtmp = new ArrayList<SellWay>();
				  sellWayListtmp = this.contractService.getContractInfoByContractId(contract.getContractId());
				  Set<Integer> settmp =new HashSet<Integer>();
				  if(sellWayListtmp!=null && sellWayListtmp.size()>0){
				      for (int i = 0; i < sellWayListtmp.size(); i++) {
    				      if(!settmp.contains(sellWayListtmp.get(i).getSellId())){
    				          settmp.add(sellWayListtmp.get(i).getSellId());
    				          if(i==0){
                                  sellNames = sellWayListtmp.get(0).getSellName();
                              }else{
                                  sellNames += "-"+sellWayListtmp.get(i).getSellName();
                              }
    				          getSellWayList().add(sellWayListtmp.get(i));
    				      }
				      }
				  }
				  
				 customer= this.customerService.getCustomerById(contract.getCusId());
				
				 if(contract.getCusIdAddress()!=0){
					 address=this.addressService.getAddressById(contract.getCusIdAddress());
					 customer=this.customerService.getCustomerById(address.getCusId());
					 String provinceName="";
					 String cityName="";
					 String townName="";
					 Area areaProvince = areaService.getAreaById(address.getProvinceId());
					 if(areaProvince !=null){
						 provinceName = areaProvince.getAreaName();
					 }
					 Area areaCity = areaService.getAreaById(address.getCityId());
					 if(areaCity!=null){
						 cityName=areaCity.getAreaName();
					 }
					 Area areaTown = areaService.getAreaById(address.getTownId());
					 if(areaTown!=null){
						 townName = areaTown.getAreaName();
					 }
					 addressStr=provinceName + cityName + townName + address.getAddress();
				 }
				 coup= this.financeCouponService.getCouponById(contract.getCouponId());;
			 }
			}catch (RuntimeException e){
				logger.error("ContractAction.getContractInfo", e);
				return "ERROR";
			}
		return "getContractInfo";
	}
	/**
	 * 货到付款取消订单 
	 * @return
	 */
	public String getContractCancel(){
		updateContractStatus(4);
		return "getContractCancel";
	}
	/**
	 * 货到付款退费功能
	 * @return
	 */
	public String getReFundHDFK()
	{
		updateContractStatus(3);
	//----------------fanxin  2011-11-01---------------------------
	/*	if(contract!=null){
			int cashRecirdId = contract.getId();
			
			CashRecord cr = new CashRecord();
			cr.setStatus(3);      //状态设为：退费
			cr.setCtId(cashRecirdId); //订单Id
			
			this.getCashRecordService().updateCashReocrdStatusByCtId(cr);	
		}*/
	//----------------fanxin  2011-11-01---------------------------		
		return "getUserContractCancel";
	}
	/**
	 * 2011-10-24 13:32
	 * 修改取消来源的信息
	 * wd
	 * @param status
	 */
	//修改订单 王迪改2011-11-3

		private void updateContractStatus(int status) {
			try {
				if(contract.getId()!=0){
					contract=this.contractService.getContractById(contract.getId());
					contract.setStatus(status);
					String contractId=contract.getContractId();
					//后台修改
					Map<String,String> mcash=new HashMap<String,String>();
					if(0==status){
						contract.setCancelFrom(0);
						mcash.put("status", "0");
						mcash.put("contractid", contractId);
						this.contractService.updateContract(contract);
						cashRecordService.updateCashRecordStatus(mcash);
					}
					if(4==status){
						contract.setCancelFrom(1);
						contract.setCancelTime(new Date());
						mcash.put("status", "2");
						mcash.put("contractid", contractId);
						this.contractService.updateContract(contract);
						cashRecordService.updateCashRecordStatus(mcash);
					}
					// ---- fanxin  后台 货到付款 退费 ------
					if(status==3 && contract!=null){
						mcash.put("status", "3");
						mcash.put("contractid", contractId);
						this.contractService.updateContract(contract);
						
						int cashRecirdCtId = contract.getId();
						CashRecord cr = new CashRecord();
						cr.setStatus(3);      //状态设为：退费
						cr.setCtId(cashRecirdCtId); //订单Id
						this.getCashRecordService().updateCashReocrdStatusByCtId(cr);
					}
				}
			} catch (Exception e) {
				logger.error("ContractAction.updateContractStatus", e);
			}
		}

	/*
	 * 客服看的银行付款的订单列表
	 * fanxin 2011-12-08
	 */
	public String getContractBankPayList(){
		try {
			subjectList = subjectService.getAllSubject();
			//查询条件
			hdfkTJ();			
			this.getQueryContractCondition().setPageSize(30);
			setPage(this.contractService.getContractBankPayList(getQueryContractCondition()));
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			 }
			 //还原时间条件的格式
			 initDateTJ();
		} catch (Exception e) {
			logger.error("ContractAction.getContractBankPayList", e);
			return ERROR;
		}
		return "getContractBankPayList";
	}
		
		
	//客服看的货到付款的订单列表
	public String getContractCODList(){
		try {
			yestartTime="";
			yeendTime="";
			subjectList = subjectService.getAllSubject();
			//查询条件
			hdfkTJ();			
			this.getQueryContractCondition().setPageSize(30);
			setPage(this.contractService.getContractCODList(getQueryContractCondition()));
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			 }
			 //还原时间条件的格式
			 initDateTJ();
		} catch (Exception e) {
			logger.error("ContractAction.getContractCODList", e);
			return ERROR;
		}
		return "contractCODList";
	}
	/**
	 * 货到付款查询条件
	 */
	private void hdfkTJ() {
		if(stime!=null&&stime.equals("stime")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryContractCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryContractCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			yestartTime=sdf.format(new Date()).trim();
			yeendTime=sdf.format(new Date()).trim();
			}
		if (startTime != null && !"".equals(startTime)) {
			yestartTime=startTime;
			startTime=startTime+startHH;
			this.getQueryContractCondition().setStartTime(startTime);
		}
		if(endTime!=null &&!"".equals(endTime)){
			yeendTime=endTime;
			endTime=endTime+endHH;
			this.getQueryContractCondition().setEndTime(endTime);
		}
		if (payStartTime != null && !"".equals(payStartTime)) {
			payStartTime=payStartTime+fkStartHH;
			this.getQueryContractCondition().setPayStartTime(payStartTime);
		}
		if(payEndTime!=null &&!"".equals(payEndTime)){
			payEndTime=payEndTime+fkEndHH;
			this.getQueryContractCondition().setPayEndTime(payEndTime);
		}
		if(status!=null&&!"".equals(status)){
			this.getQueryContractCondition().setNewStatus(status);
		}
		if(contract.getContractId()!=null&&!"".equals(contract.getContractId().trim())){
			this.getQueryContractCondition().setContractId(contract.getContractId().trim());
		}
		if(contract.getContractFrom()!=null&&!"".equals(contract.getContractFrom().trim())){
			this.getQueryContractCondition().setContractFrom(contract.getContractFrom().trim());
		}
		if(contract.getCusId()!=0){
			this.getQueryContractCondition().setUserId(contract.getCusId());
		}
		if(mail!=null&&!"".equals(mail)){
			Customer customer = this.customerService.getCustomerByEmail(mail.trim());
			if(customer!=null){
				if(customer.getCusId()!=0){
					this.getQueryContractCondition().setUserId(customer.getCusId());
				}
			}else {
				this.getQueryContractCondition().setUserId(1);  //添加一个数据库里不会出现的用户id
			}
		}
		
		if(subjectId!=0 && subjectId!=-1 && !"".equals(subjectId)){
			this.getQueryContractCondition().setSubjectId(subjectId);
		}
		
	}
	/**
	 * 货到付款导出excel功能
	 * @return
	 */
	public String hdfkExportExcel()
	{
		try{
			//查询条件		
			hdfkTJ();
			QuerySellWayCondition querySellWayCondition= new QuerySellWayCondition();
			sellWayList=sellWayService.getSellWayList(querySellWayCondition);
			contractHDFKTempList=contractService.getCodContractLists(queryContractCondition);
			createHDFKExcel();
			return "exportExcelhdfk";
		}catch(Exception e){
			logger.error("ContractAction.hdfkExportExcel", e);
			return ERROR;
		}
	}
	private void createHDFKExcel() {
		//付款状态、购买课程、注册路径
		try{
			float yf=10;
			 String[] headName={
					 "学员账号"
					 ,"订单号"
					 ,"课程激活码"
					 ,"姓名"
					 ,"地址"
					 ,"邮政编码"
					 ,"电话"
					 ,"下单日期"
					 ,"付款状态"				//8
					 ,"购买课程"	
					 ,"主讲老师"//9
					 ,"购买专业"
					 ,"注册路径"				//10
					 ,"订单金额"
					 ,"优惠金额"
					 ,"优惠后金额"
					 ,"运费"
					 ,"合计"
					 ,"送货时间"};
			 HSSFWorkbook workbook = new HSSFWorkbook(); 
			 HSSFSheet sheet = workbook.createSheet("sheet1");  
			               // 创建表头 
				 HSSFRow row = sheet.createRow(0);			
				 HSSFCell cell = row.createCell((short) 0);
				 for(int i=0;i<headName.length;i++)
				 {
					 cell = row.createCell((short) i);  
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
					 cell.setCellValue(headName[i]);
				 }		      
			 // 创建数据行              
				 for(int i=1,j=0;contractHDFKTempList!=null && j<contractHDFKTempList.size();i++,j++){
					
					 String address="";
					 String email="";
					 String mobile="";
					 String name="";
					 String teacher="";
					 int subjectId=0;
					 String subjectName="";
					 if(contractHDFKTempList.get(j).getAddressDTO()!=null)
					 {
						 address=contractHDFKTempList.get(j).getAddressDTO().getProvinceName()
						 +contractHDFKTempList.get(j).getAddressDTO().getCityName()
						 +contractHDFKTempList.get(j).getAddressDTO().getTownName()
						 +contractHDFKTempList.get(j).getAddressDTO().getAddress();
						 email=contractHDFKTempList.get(j).getAddressDTO().getPostCode();
						 mobile=contractHDFKTempList.get(j).getAddressDTO().getMobile();
						 name=contractHDFKTempList.get(j).getAddressDTO().getReceiver();
					 }
					 
					 if(contractHDFKTempList.get(j).getPayType()==2)
					 {
						 yf=10;
					 }else
					 {
						 yf=0;
					 }
					 row = sheet.createRow(i);  
					 //用户名
					 cell = row.createCell((short) 0);  
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);             
					 cell.setCellValue(contractHDFKTempList.get(j).getCustomer().getEmail());   
					 //订单号
					 cell = row.createCell((short) 1);              
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);              
					 cell.setCellValue(contractHDFKTempList.get(j).getContractId());   
					 //激活码
					 cell = row.createCell((short) 2);              
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);              
					 cell.setCellValue(contractHDFKTempList.get(j).getContractCDkey());     
					 //姓名
					 cell = row.createCell((short) 3);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(name);   
					 //地址    
					 cell = row.createCell((short) 4);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  					
					 cell.setCellValue(address);
					 //邮政编码  
					 cell = row.createCell((short) 5);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(email);
					 //手机号
					 cell = row.createCell((short) 6);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(mobile);
					 //下单日期
					 cell = row.createCell((short) 7);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 cell.setCellValue(sdf.format(contractHDFKTempList.get(j).getCreateTime()));
					//付款状态
					 cell = row.createCell((short) 8);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					String payType=null;
					 if(contractHDFKTempList.get(j).getStatus()==0)
					 {
						payType="未付"; 
					 }else if(contractHDFKTempList.get(j).getStatus()==1)
					 {
						 payType="已付";  
					 }else if(contractHDFKTempList.get(j).getStatus()==3)
					 {
						 payType="取消"; 
					 }else if(contractHDFKTempList.get(j).getStatus()==4)
					 {
						 payType="退费"; 
					 }
					 cell.setCellValue(payType);
					//购买课程
					 StringBuffer courseTemp=new StringBuffer("");
					 //价格
					 StringBuffer price=new StringBuffer("");
					 
					 //课程价格
					 StringBuffer prices=new StringBuffer("");
					 //查出订单中所有售卖方式的名 赋给courseTemp
//					 getCashRecordByCTId(contractTempList.get(j).getId());
//					 for(int index=0;cashRecordList!=null&&index<cashRecordList.size();index++)
//					 {
//						 courseTemp.append(cashRecordList.get(index).getCrInfo().replaceAll("购买", ""));
//					 }
//					 for(int k=0;k<sellWayList.size();k++)
//					 {
//						 int a=0;
//						 for(int index=0;cashRecordList!=null&&index<cashRecordList.size();index++)
//						 {
//							 if(sellWayList.get(k).getSellId()==cashRecordList.get(index).getPackId()&&a==0)
//							 {
//								 price.append(sellWayList.get(k).getSellName()).append(sellWayList.get(k).getSellPrice());
//								 a++;
//							 }
//						 }
//					 }
					 List<CashRecord> cashRecordTemp=contractHDFKTempList.get(j).getCashRecordList();
					 for(int index=0;cashRecordTemp!=null&&cashRecordTemp.size()!=0&&index<cashRecordTemp.size();index++)
					 {
						 courseTemp.append(cashRecordTemp.get(index).getCrInfo().replaceAll("购买", ""));
					 }
					 for(int k=0;k<sellWayList.size();k++)
					 {
						 int a=0;
						 for(int index=0;cashRecordTemp!=null&&index<cashRecordTemp.size();index++)
						 {
							 if(sellWayList.get(k).getSellId()==cashRecordTemp.get(index).getPackId()&&a==0)
							 {
								 price.append(sellWayList.get(k).getSellName());
								 teacher=sellWayList.get(k).getTeacher();
								 subjectId = sellWayList.get(k).getSubjectId();
								 Subject subject = subjectService.getSubjectById(subjectId);
								 subjectName = subject.getSubjectName();
								 a++;
							 }
						 }
					 }
					 cell = row.createCell((short) 9);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(courseTemp.toString());
					 
					 cell = row.createCell((short) 10);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(teacher.toString());
					 
					 cell = row.createCell((short) 11);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(subjectName.toString());
					 
					//注册路径
					 cell = row.createCell((short) 12);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(contractHDFKTempList.get(j).getCustomer().getCusFromUrl());
					 
					//订单金额					
					 cell = row.createCell((short) 13);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue((Float.parseFloat(contractHDFKTempList.get(j).getContractSumMoney().toString())));
					 
					 
					 cell = row.createCell((short) 14);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 if(contractHDFKTempList.get(j).getCouponMoney()==null){
						 contractHDFKTempList.get(j).setCouponMoney(0);
					 }
					 cell.setCellValue((Float.parseFloat(contractHDFKTempList.get(j).getCouponMoney().toString())));

					 cell = row.createCell((short) 15);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue((Float.parseFloat(contractHDFKTempList.get(j).getContractCutSumMoney().toString())));
					 
					//运费
					 cell = row.createCell((short) 16);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(yf+"");
					 
					 cell = row.createCell((short) 17);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue((yf+(Float.parseFloat(contractHDFKTempList.get(j).getContractCutSumMoney().toString())))+"");
					 //送货时间
					 String sendTime="";
					 if(contractHDFKTempList.get(j).getAddressDTO()!=null)
					 {
						 if(contractHDFKTempList.get(j).getAddressDTO().getSendTime()==1)
						 {
							 sendTime="时间不限";
						 }else if(contractHDFKTempList.get(j).getAddressDTO().getSendTime()==2)
						 {
							 sendTime="周一至周五";
						 }else if(contractHDFKTempList.get(j).getAddressDTO().getSendTime()==3)
						 {
							 sendTime="周六日及公众假期";
						 }
					 }
					 cell = row.createCell((short) 18);    
					 cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);  
					 cell.setCellValue(sendTime);
				 }			 
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				 try 
				 {              
					 workbook.write(baos);          
				 } 
				 catch (IOException e) 
				 {
					 e.printStackTrace();          
				 }          
				 byte[] ba = baos.toByteArray();          
				 ByteArrayInputStream bais = new ByteArrayInputStream(ba);          
				 this.setExcelFile(bais) ;  
			}catch (Exception e) {
				logger.error("ContractAction.createHDFKExcel", e);
		}
	}
	//通过订单查出该订单下的所有流水
	private void getCashRecordByCTId(int ctId)
	{
		getQueryCashRecordCondition().setCtId(ctId);
		cashRecordList= cashRecordService.getCashRecordByList(queryCashRecordCondition);
	}
	
	//更改货到付款订单的状态 给客服看的
	public String getContractUserCOD(){
		try{
		Date date=new Date();
		user=this.getLoginedUser();
		if(contract.getId()!=0&&contractStep!=0){
			Cod cod=new Cod();
			cod.setCodCtId(contract.getId());
			cod.setCodTime(date);
			cod.setCodAuditId(user.getUserId());
			cod.setCodStatus(contractStep);
			if(contractStep==1){
				cod.setCodContent("步骤一：");
			}else if(contractStep==2){
				cod.setCodContent("步骤二：");
			}else if(contractStep==3){
				cod.setCodContent("步骤三：");
			}else if(contractStep==4){
				cod.setCodContent("步骤四：");
			}
			this.codService.addCod(cod);
		}
		}catch (Exception e) {
			logger.error("ContractAction.getContractUserCOD", e);
			return ERROR;
		}
		return "getContractUserCOD";
	}
	
	//取消订单 客服看的
	public String getUserContractCancel(){
		updateContractStatus(4);
		return "getUserContractCancel";
	}
	
	public String getUserContractRecoverment(){
		updateContractStatus(0);
		return "getUserContractCancel";
	}
	/**
	 * 学员DTO
	 */
	
	private CustomerCountDTO customerCountDTO;
	
	
	
	public String getSellWayBySubjectId(){
		try{
		if(this.subjectId!=0 && this.subjectId!=-1){
			List<SellWay> sellWayList = this.sellWayService.getSellWayBySubjectId(this.subjectId);
			if(sellWayList!=null && sellWayList.size()!=0){
				this.setResult(new Result<List<SellWay>>(true,"",null,sellWayList));
			}
		}
		}catch(Exception e){
			logger.error("ContractAction.getSellWayBySubjectId", e);
			this.setResult(new Result<List<SellWay>>(false,"error",null,null));
		}
		return "json";
	}

	
    /*--fanxin --2011-12-08--
     * 确认银行付款成功后，
     * 客服在后台 开通课程
     */	
	public String bankPayOpenCourse(){
		try{
			if(contract.getId()!=0){
				this.getQueryCashRecordCondition().setCtId(contract.getId());
			}
			if(contract.getCusId()!=0){
				this.getQueryCashRecordCondition().setUserId(contract.getCusId());
			}
			Contract contractTemp = this.getContractService().getContractById(contract.getId());
			//订单状态改为 已支付
			contractTemp.setStatus(1);
			contractTemp.setPayTime(new Date());
			
			this.getContractService().updateContract(contractTemp);
			List<CashRecord> cashRecordList=this.getCashRecordService().getCashRecordByList(getQueryCashRecordCondition());
			for(int i=0;cashRecordList!=null&&i<cashRecordList.size();i++){
				if(cashRecordList.get(i).getStatus()!=1)
				{
					CashRecord cashRecordTemp=cashRecordList.get(i);
					cashRecordTemp.setStatus(1);		//流水的状态改为 已支付
					cashRecordTemp.setShopStatus(1);     //流水的商品状态设为：已激活
					cashRecordTemp.setShopPayTime(new Date()); //流水的支付时间设为：now
					
					this.getCashRecordService().updateCashRecord(cashRecordTemp);
				}
			}
			
		}catch(Exception e){
			logger.error("ContractAction.bankPayOpenCourse", e);
		}
		return "changeSuccess";
	}
	
	/**
	 * 2011-11-3
	 * 修改订单状态
	 * 王迪
	 * @throws IOException 
	 */
	public void updateOrderStatus() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		try {
			int test=contract.getId();
			Contract ctTemp=contractService.getContractById(test);
			Map<String,String> mcash=new HashMap<String,String>();
			if("cancel".equals(status)){
				if(ctTemp!=null){
//					if((2==ctTemp.getPayType())|| (7==ctTemp.getPayType())){
//						ctTemp.setStatus(3);
//					}else{
//						ctTemp.setStatus(4);
//					}
					ctTemp.setStatus(4);
					ctTemp.setCancelFrom(1);
					ctTemp.setCancelTime(new Date());
					contractService.updateContract(ctTemp);
					String contractId=ctTemp.getContractId();
					mcash.put("status", "2");
					mcash.put("contractid", contractId);
					cashRecordService.updateCashRecordStatus(mcash);
					response.getWriter().print("success");
				}
				else{
					response.getWriter().print("error");
				}

			}else if("restoration".equals(status)){
				if(ctTemp!=null){
					ctTemp.setStatus(0);
					ctTemp.setCancelFrom(0);
					contractService.updateContract(ctTemp);
					String contractId=ctTemp.getContractId();
					mcash.put("status", "0");
					mcash.put("contractid", contractId);
					cashRecordService.updateCashRecordStatus(mcash);
					response.getWriter().print("success");
				}else{
					response.getWriter().print("error");
				}
			}else{
				response.getWriter().print("error");
			}
		} catch (Exception e) {						
			log.error("error",e);
			response.getWriter().print("error");
		}
	}



	
	/**
	 * 按支付方式获取内容按月
	 * @return
	 */
	public String getMontyDayForpayType(){
		try{
		String day=years+months;
		if(day.equals(""))
		{
			Date date=new Date();
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			years=cal.get(Calendar.YEAR)+"";
			months=(cal.get(Calendar.MONTH)+1)+"";
		}
		day=years+"-"+months+"-01";
		monthDayList=contractService.getMontyDayForpayType(day);
		}catch(Exception e){
			log.error("getMontyDayForpayType"+e.toString());
		}
		return "getMonthForpayType";
	}
	
	public String openContract() {

		return "success";
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
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

	public QueryCustomerCondition getQueryCustomerCondition() {
		if(queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}
	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if(queryCashRecordCondition == null) {
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
	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}
	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}
	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IUser getUserService() {
		return userService;
	}
	public void setUserService(IUser userService) {
		this.userService = userService;
	}
	public IUserGradeSubjectRole getUserGradeSubjectRoleService() {
		return userGradeSubjectRoleService;
	}
	public void setUserGradeSubjectRoleService(
			IUserGradeSubjectRole userGradeSubjectRoleService) {
		this.userGradeSubjectRoleService = userGradeSubjectRoleService;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayStartTime() {
		return payStartTime;
	}
	public void setPayStartTime(String payStartTime) {
		this.payStartTime = payStartTime;
	}
	public String getPayEndTime() {
		return payEndTime;
	}
	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
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
	public String getFkStartHH() {
		return fkStartHH;
	}
	public void setFkStartHH(String fkStartHH) {
		this.fkStartHH = fkStartHH;
	}
	public String getFkEndHH() {
		return fkEndHH;
	}
	public void setFkEndHH(String fkEndHH) {
		this.fkEndHH = fkEndHH;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getContractStep() {
		return contractStep;
	}
	public void setContractStep(int contractStep) {
		this.contractStep = contractStep;
	}
	public ICod getCodService() {
		return codService;
	}
	public void setCodService(ICod codService) {
		this.codService = codService;
	}
	public ISubject getSubjectService() {
		return subjectService;
	}
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public IAddress getAddressService() {
		return addressService;
	}
	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public IArea getAreaService() {
		return areaService;
	}
	public void setAreaService(IArea areaService) {
		this.areaService = areaService;
	}
	public List<CourseDTO> getNewCourseList() {
		return newCourseList;
	}
	public void setNewCourseList(List<CourseDTO> newCourseList) {
		this.newCourseList = newCourseList;
	}
	public ConfigService getConfigurator() {
		return configurator;
	}
	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}
	public List<String> getWebFromStr() {
		return webFromStr;
	}
	public void setWebFromStr(List<String> webFromStr) {
		this.webFromStr = webFromStr;
	}
	public List<String> getWebAgentStr() {
		return webAgentStr;
	}
	public void setWebAgentStr(List<String> webAgentStr) {
		this.webAgentStr = webAgentStr;
	}
	public int getContractPriceSum() {
		return contractPriceSum;
	}
	public void setContractPriceSum(int contractPriceSum) {
		this.contractPriceSum = contractPriceSum;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public int getFromURLType() {
		return fromURLType;
	}
	public void setFromURLType(int fromURLType) {
		this.fromURLType = fromURLType;
	}
	public String getDownloadFileNames() {
		return downloadFileNames;
	}
	public void setDownloadFileNames(String downloadFileNames) {
		this.downloadFileNames = downloadFileNames;
	}
	public InputStream getDownloadStreams() {
		return downloadStreams;
	}
	public void setDownloadStreams(InputStream downloadStreams) {
		this.downloadStreams = downloadStreams;
	}
	public List<Contract> getContractTempList() {
		return contractTempList;
	}
	public void setContractTempList(List<Contract> contractTempList) {
		this.contractTempList = contractTempList;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public CustomerCountDTO getCustomerCountDTO() {
		return customerCountDTO;
	}
	public void setCustomerCountDTO(CustomerCountDTO customerCountDTO) {
		this.customerCountDTO = customerCountDTO;
	}

	public List<CashRecord> getCashRecordList() {
		return cashRecordList;
	}
	public void setCashRecordList(List<CashRecord> cashRecordList) {
		this.cashRecordList = cashRecordList;
	}
	public List<SellWay> getSellWayList() {
		return sellWayList;
	}
	public void setSellWayList(List<SellWay> sellWayList) {
		this.sellWayList = sellWayList;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public List<LoginRecord> getLoginRecordList() {
		return loginRecordList;
	}
	public void setLoginRecordList(List<LoginRecord> loginRecordList) {
		this.loginRecordList = loginRecordList;
	}
	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}
	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}
	public List<ContractExcelDTO> getContractHDFKTempList() {
		return contractHDFKTempList;
	}
	public void setContractHDFKTempList(List<ContractExcelDTO> contractHDFKTempList) {
		this.contractHDFKTempList = contractHDFKTempList;
	}
	public QueryContractCondition getQwm() {
		if(qwm==null){
			qwm= new QueryContractCondition();
		}
		return qwm;
	}
	public void setQwm(QueryContractCondition qwm) {
		this.qwm = qwm;
	}
	public QuerySellWayCondition getQuerySellWayCondition() {
		if(querySellWayCondition==null){
			querySellWayCondition = new QuerySellWayCondition();
		}
		return querySellWayCondition;
	}
	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}
	public QueryCourseCondition getQueryCourseCondition() {
		return queryCourseCondition;
	}
	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId=subjectId;		
	}
	public void setSubjectId(String subjectId) {
        try {
			this.subjectId = Integer.parseInt(subjectId);
		} catch (NumberFormatException e) {
			this.subjectId = -1;
		}		
	}
	public List<ContractDTO> getContractDTOList() {
		return contractDTOList;
	}
	public void setContractDTOList(List<ContractDTO> contractDTOList) {
		this.contractDTOList = contractDTOList;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public SellWay getSellWay() {
		return sellWay;
	}
	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}
	public String getSellNames() {
		return sellNames;
	}
	public void setSellNames(String sellNames) {
		this.sellNames = sellNames;
	}
	public ISellCou getSellCouService() {
		return sellCouService;
	}
	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}
	public ICoupon getFinanceCouponService() {
		return financeCouponService;
	}
	public void setFinanceCouponService(ICoupon financeCouponService) {
		this.financeCouponService = financeCouponService;
	}
	public Coupon getCoup() {
		return coup;
	}
	public void setCoup(Coupon coup) {
		this.coup = coup;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	
	public List<MonthDayForpayTypeDTO> getMonthDayList() {
		return monthDayList;
	}

	public void setMonthDayList(List<MonthDayForpayTypeDTO> monthDayList) {
		this.monthDayList = monthDayList;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
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
	