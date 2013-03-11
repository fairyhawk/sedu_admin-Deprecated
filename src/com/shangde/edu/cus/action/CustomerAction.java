package com.shangde.edu.cus.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.IPUtil;
import com.shangde.common.util.MD5;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryNoteContentCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.CellPhone;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerDTO;
import com.shangde.edu.cus.domain.CustomerNewDTO;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.domain.NoteContent;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.CusLoginCountDTO;
import com.shangde.edu.cus.dto.CustomerCountDTO;
import com.shangde.edu.cus.dto.CustomerCountNewDTO;
import com.shangde.edu.cus.dto.NoteContentDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.cus.service.IStudyPlan;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.dto.SubjectCountsDTO;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomerAction extends CommonAction implements Callable {

	private static final Logger logger = Logger.getLogger(CustomerAction.class);
	/**
	 * 查询条件
	 */
	private String startTime;
	private String endTime;
	private String sendLinks;
	private String sendInfo;
	private String startHH = " 00:00:00";
	private String endHH = " 23:59:59";
	int zsSum = 0; // 声明支付方式变量 赠送数
	int zfbSum = 0; // 支付宝下单总数
	int hdfkSum = 0; // 货到付款下单总数
	int wyzxSum = 0; // 网银在线
	int kqSum = 0; // 快钱
	int yfZsSum = 0; // 已付赠送
	int yfZfbSum = 0; // 已付支付宝
	int yfHdfkSum = 0; // 已付货到付款
	int yfWyzxSum = 0; // 已付网银在线
	int yfKqSum = 0; // 已付快钱
	int dlsktSum = 0; // 代理商下单总数（默认已经支付的）
	int tyhfk = 0;// 银行汇款总数
	int tyfyhhk = 0;// 已付银行汇款总数
	int SUM = 0;
	int YFSUM = 0;
	int cusAll = 0;
	int cusWbAll = 0;
	int TFSUM = 0;
	int tzy = 0;// 全部真友支付
	int zy = 0;// 单个项目真友支付
	int yfzy = 0;// 单个项目已付真友
	int tyfzy = 0;// 所有真友已付
	Integer diffDay;// 相减天数

	public Integer getDiffDay() {
		return diffDay;
	}

	public void setDiffDay(Integer diffDay) {
		this.diffDay = diffDay;
	}

	private static Boolean lockOper = false;

	/**
	 * 报表属性
	 */
	String contractPr = "["; // 订单量线轴
	String contractPayPr = "["; // 已付订单量线轴
	String datePr = "["; // 日期标示线轴
	int webType = 0;
	int privilegeSum = 0;
	
	private String smsLimit;

	/**
	 * 用户服务对象
	 */
	private IsmsService smsService;

	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	/**
	 * 邮件服务对象
	 */
	private IMail mailService;

	/**
	 * 用户实体
	 */
	private Customer customer = new Customer();

	/**
	 * 用户查询条件
	 */
	private QueryCustomerCondition queryCustomerCondition;

	/**
	 * 用户列表
	 */
	private List<Customer> customerList = new ArrayList<Customer>();
	private List<Customer> newCusList = new ArrayList<Customer>();
	private List<Integer> cusList = new ArrayList<Integer>();
	private List<CashRecordDTO> cashList = new ArrayList<CashRecordDTO>();
	private List<CashRecordDTO> YFcashList = new ArrayList<CashRecordDTO>();

	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 新密码
	 */
	private String newPwd;
	private String newMD5Pwd;

	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	/**
	 * DTO
	 */
	private CustomerCountDTO customerCountDTO;
	private CustomerCountDTO customerCountSum;
	private List<CustomerCountDTO> customerCountDTOList = new ArrayList<CustomerCountDTO>();
	private CustomerDTO customerDTO = new CustomerDTO();
	private CustomerDTO hdfkCustomerDTO = new CustomerDTO();
	private CustomerDTO zfbCustomerDTO = new CustomerDTO();
	/**
	 * 查询条件
	 */
	private QueryContractCondition queryContractCondition;
	private QueryCashRecordCondition queryCashRecordCondition;
	private QueryContractCondition zfbPayedQueryContractCondition;
	private QueryContractCondition zfbQueryContractCondition;
	private QueryContractCondition hdfkPayedQueryContractCondition;
	private QueryContractCondition hdfkQueryContractCondition;
	private QueryContractCondition wyzxQueryContractCondition;
	private QueryContractCondition wyzxPayedQueryContractCondition;
	/**
	 * 月List
	 */
	private List<Customer> monthList = new ArrayList<Customer>();
	/**
	 * 月参数
	 */
	private String monthDay;
	private String month;
	/**
	 * 日参数
	 */
	private String dateDay;

	/**
	 * 支付类型
	 */
	private int payType = -1;
	/**
	 * List
	 */
	private List<Customer> monthDayList = new ArrayList<Customer>();
	/**
	 * 每天List
	 */
	private List<Customer> DayList = new ArrayList<Customer>();
	/**
	 * 后台用户
	 */
	private User user;
	/**
	 * 用户服务
	 */
	private IUser userService;
	/**
	 * 赠送方式
	 */
	private String giveAway;
	/**
	 * 课程实体
	 */
	private Course course;

	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 服务
	 */
	private ICusCouKpoint cusCouKpointService;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;
	/**
	 * 已存在的customerList
	 */
	private List<Customer> existCusList = new ArrayList<Customer>();
	private int newCusId = 0;
	private String[] cus = null;
	private int cusId;
	private int raBatch;

	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	/**
	 * 活动服务对象
	 */
	private ICpCus cpCusService;
	/**
	 * 学习计划服务对象
	 */
	private IStudyPlan studyPlanService;
	/**
	 * 考试服务
	 */
	private IOptRecord optRecordService;
	/**
	 * 考试服务
	 */
	private IExampaperRecord exampaperRecordService;
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 登录信息记录
	 */
	private ILoginRecord loginRecordService;
	/**
	 * 删除成功
	 */
	private String delSucess;
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList;
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	/**
	 * 切换参数
	 */
	private int location = 0;
	private int dateLocation = -1;

	private List<SubjectCountsDTO> subjectCountsDTOList = new ArrayList<SubjectCountsDTO>();
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 回复问题的服务
	 */
	private IReProblem reProblemService;
	/**
	 * 任务
	 */
	private ITaskCus taskCusService;
	/**
	 * 售卖方式关系服务
	 */
	private ISellCou sellCouService;
	private QuerySellCouCondition querySellCouCondition;
	private CusLoginCountDTO loginCountDTO;
	/**
	 * 修改学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	private int fromURLType;

	/**
	 * 专业服务
	 * 
	 * @return
	 */
	private QuerySubjectCondition querySubjectCondition;

	private InputStream excelFile;

	List<CustomerWithConSizeDTO> cwcsList = new ArrayList<CustomerWithConSizeDTO>();

	List<AddressDTO> addressList = new ArrayList<AddressDTO>();
	/**
	 * 售卖方式服务
	 */
	private ISellWay sellWayService;
	/**
	 * Liming checkIdSum 页面传送的选中多小
	 */
	private String checkIdSum;
	/**
	 * Liming 手机和Email 存的实体类
	 */
	private CellPhone cellPhone;
	/**
	 * Liming 短信DTO实体类
	 */
	private NoteContentDTO noteContentDTO;
	/**
	 * Liming 页面传送的状态
	 */
	private String allAndsingleStatus;
	/**
	 * Liming 查询的Condition；
	 */
	private QueryNoteContentCondition queryNoteContentCondition;
	/**
	 * Liming 信息内容Entity；
	 */
	private NoteContent noteContent;
	/**
	 * Liming 页面传送的状态
	 */
	private String discern;
	/**
	 * Liming 定义个集合
	 */
	private List cellPhoneListCrabs;
	private List cellPhoneList;

	/**
	 * 批量导入用户前台反馈容器
	 */
	private Map<String, String> bathSucMap;
	/**
	 * Liming 手机号插叙所有;
	 */
	private int cellPhoneSingleCout;

	private String message;

	private String years;// 年参数
	private String months;// 月参数
	private String contractFromUrl;// 域名来源
	List<CusLoginCountDTO> cusLoginCountList; //学员登录统计
	List<CusLoginCountDTO> cusLoginCountSum;//学员登录数量 
	/**
	 * 已发送短信数量
	 */
	private int sendSmsCount;
	/**
	 * 权限id
	 */
	private int roleId;
	
	public String getContractFromUrl() {
		return contractFromUrl;
	}

	public void setContractFromUrl(String contractFromUrl) {
		this.contractFromUrl = contractFromUrl;
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

	private String radios;// 单选按钮参数

	public String getRadios() {
		return radios;
	}

	public void setRadios(String radios) {
		this.radios = radios;
	}

	private List<CustomerCountNewDTO> monthDayNewList;// 订单实收统计

	public List<CustomerCountNewDTO> getMonthDayNewList() {
		return monthDayNewList;
	}

	public void setMonthDayNewList(List<CustomerCountNewDTO> monthDayNewList) {
		this.monthDayNewList = monthDayNewList;
	}

	// 学员统计
	List<CustomerNewDTO> ccNewDTO = new ArrayList<CustomerNewDTO>();

	public List<CustomerNewDTO> getCcNewDTO() {
		return ccNewDTO;
	}

	public void setCcNewDTO(List<CustomerNewDTO> ccNewDTO) {
		this.ccNewDTO = ccNewDTO;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public List<AddressDTO> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressDTO> addressList) {
		this.addressList = addressList;
	}

	public String updateCustomer() {
		try {
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 跳转到免费赠送课程页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String freeCourse() {
		String Result = "";
		try {
			if (customer.getCusType() == 1) {
				// 内部学员
				Result = "freeCourse1";
			} else {
				// 注册学员
				Result = "freeCourse";
			}
		} catch (Exception e) {
			logger.error("CustomerAction.freeCourse", e);
			return ERROR;
		}
		return Result;
	}

	/**
	 * 修改密码
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String updatePwd() {
		try {
			if(customer.getCusId()!=0){
				customer = customerService.getCustomerById(customer.getCusId());
			}
			if (customer != null) {
				customer.setCusPwd(MD5.getMD5(newPwd));
			}
			customerService.updateCustomer(customer);
			return "changeSuccess";
		} catch (Exception e) {
			logger.error("CustomerAction.updatePwd", e);
			return ERROR;
		}
	}

	/**
	 * 修改MD5密码
	 * 
	 * @return
	 */
	public String updateMD5Pwd() {
		try {
			if(customer.getCusId()!=0){
				customer = customerService.getCustomerById(customer.getCusId());
			}
			if (customer != null) {
				customer.setCusPwd(newMD5Pwd);
			}
			customerService.updateCustomer(customer);
			return "changeSuccess";
		} catch (Exception e) {
			logger.error("CustomerAction.updateMD5Pwd", e);
			return ERROR;
		}
	}

	/**
	 * 根据ids删除学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String deleteCustomer() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					customerService.delCustomerById(ids[i]);
				}
			}
		} catch (Exception e) {
			logger.error("CustomerAction.deleteCustomer", e);
			return ERROR;
		}
		return "relist";
	}
	/**
	 * 首次点击查询学员列表
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showFirstCustomerList() {
		try {
			subjectList = subjectService.getAllSubject();

		} catch (Exception e) {
			logger.error("CustomerAction.showCustomerList", e);
			return ERROR;
		}
		return "list";
	}
	/**
	 * 分页查询 查询学员列表
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showCustomerList() {
		try {
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();
			String cusName = customer.getCusName();
			if (cusName != null) {
				cusName = URLDecoder.decode(cusName.trim(), "UTF-8");
			}
			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition()
							.setCusFromUrl("ss.haixue.com");
				}
			}
			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (startTime != null && !"".equals(startTime) && startHH != null
					&& !startHH.equals("")) {

				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);

			}
			if (endTime != null && !"".equals(endTime) && endHH != null
					&& !endHH.equals("")) {

				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			if (cusType != null && !"".equals(cusType)) {
				// 6为未支付类型，但数据库没有对应字段描述。重设为0，即注册学员，再通过查询筛选出未支付学员
				if (cusType.trim().equals("6")) {
					cusType = "0";
				}
				getQueryCustomerCondition().setCusType(cusType.trim());
			} else {
				// 如果是查询注册学员的操作
				if (getQueryCustomerCondition().getVisitType() == 1
						|| getQueryCustomerCondition().getVisitType() == 2) {
					getQueryCustomerCondition().setCusType("0");
				}
			}
			if (cusName != null && cusName != "") {
				getQueryCustomerCondition().setCusName(cusName);
			}
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			if (endHH != null && !endHH.equals("") && endTime != null
					&& !"".equals(endTime)) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));

			}
			if (startHH != null && !startHH.equals("") && startTime != null
					&& !"".equals(startTime)) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		} catch (Exception e) {
			logger.error("CustomerAction.showCustomerList", e);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 查询学员列表，为外呼人员提供，查询学员的统计信息。
	 */
	public String showCustomerStatsList() {
		try {
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();

			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.org.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition()
							.setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("4")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.com.cn");
				} else if (customer.getCusFromUrl().equals("5")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.net.cn");
				}
			}

			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}

			this.getQueryCustomerCondition().setSubjectId(-1);
			this.getQueryCustomerCondition().setEndNumber(999999);

			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			if (endHH != null && !endHH.equals("") && endTime != null
					&& !"".equals(endTime)) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));

			}
			if (startHH != null && !startHH.equals("") && startTime != null
					&& !"".equals(startTime)) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		} catch (Exception e) {
			logger.error("CustomerAction.showCustomerStatsList", e);
			return ERROR;
		}

		return "showCustomerStatsList";
	}

	/**
	 * 导出订单到excel
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFile() {
		try {
			// 查询条件
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();
			String cusName = customer.getCusName();
			if (cusName != null) {
				try {
					cusName = URLDecoder.decode(cusName.trim(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("CustomerAction.exportExcelFile", e);
					return ERROR;
				}
			}
			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.org.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition()
							.setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("4")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.com.cn");
				} else if (customer.getCusFromUrl().equals("5")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.net.cn");
				}
			}
			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (startTime != null && !"".equals(startTime) && startHH != null
					&& !startHH.equals("")) {

				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);

			}
			if (endTime != null && !"".equals(endTime) && endHH != null
					&& !endHH.equals("")) {

				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			if (cusType != null && !"".equals(cusType)) {
				// 6为未支付类型，但数据库没有对应字段描述。重设为0，即注册学员，再通过查询筛选出未支付学员
				if (cusType.trim().equals("6")) {
					cusType = "0";
				}
				getQueryCustomerCondition().setCusType(cusType.trim());
			} else {
				// 如果是查询注册学员的操作
				if (getQueryCustomerCondition().getVisitType() == 1
						|| getQueryCustomerCondition().getVisitType() == 2) {
					getQueryCustomerCondition().setCusType("0");
				}
			}
			// getQueryCustomerCondition().setQueryFlag(1);
			if (cusName != null && cusName != "") {
				getQueryCustomerCondition().setCusName(cusName);
			}
			this.getQueryCustomerCondition().setPageSize(5000);
			cwcsList = customerService.getCustomerListByCondition(
					queryCustomerCondition).getPageResult();
			if (endHH != null && !endHH.equals("") && endTime != null
					&& !"".equals(endTime)) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));

			}
			if (startHH != null && !startHH.equals("") && startTime != null
					&& !"".equals(startTime)) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			// 初始化一个集合用来存放查询出的订单列表
			// 根据条件查询订单列表
			createExcelFile();
		} catch (Exception e) {
			logger.error("CustomerAction.exportExcelFile", e);
			return ERROR;
		}
		// 初始化时间条件
		return "exportExcelCus";
	}

	/**
	 * 学员列表excel
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public void createExcelFile() {
		try {
			// 格式化时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			// 下次修改内容
			String[] headName = { "昵称", "电子邮箱", "注册项目", "注册位置", "WebFrom", "WebAgent", "注册域名", "登录地区", "手机",
					"登陆次数", "注册时间", "支付数/订单数", "是否保过"};
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 创建表头
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			for (int i = 0; i < cwcsList.size(); i++) {
				row = sheet.createRow(i + 1);
				// 昵称
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusName());
				// 电子邮箱
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getEmail());
				// //注册项目
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (null != cwcsList.get(i).getCusSubject()) {
					cell.setCellValue(cwcsList.get(i).getCusSubject()
							.getSubjectName());
				} else {
					cell.setCellValue("--");
				}
				// 注册位置
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				String fromType = cwcsList.get(i).getFromType();
				String fromTypeStr = "";
				if ("1".equals(fromType)) {
					fromTypeStr = "项目页";
				} else if ("2".equals(fromType)) {
					fromTypeStr = "项目购买页";
				} else if ("4".equals(fromType)) {
					fromTypeStr = "首页";
				} else if ("5".equals(fromType)) {
					fromTypeStr = "haixue.com";
				} else if ("6".equals(fromType)) {
					fromTypeStr = "代理商";
				} else if ("7".equals(fromType)) {
					fromTypeStr = "内部开通";
				}
				cell.setCellValue(fromTypeStr);
				// WebFrom
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusWebFrom());
				// WebAgent
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusWebAgent());
				// 注册域名
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusFromUrl());
				// 登录地区
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (cwcsList.get(i).getLoginRecordList().size() > 0) {
					cell.setCellValue(cwcsList.get(i).getLoginRecordList()
							.get(0).getAddress());
				} else {
					cell.setCellValue("--");
				}
				// 手机
				cell = row.createCell((short) 8);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getMobile());
				// 登陆次数
				cell = row.createCell((short) 9);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getLoginTimes());
				// 注册时间
				cell = row.createCell((short) 10);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(cwcsList.get(i).getRegTime()));
				// 支付数/订单数
				cell = row.createCell((short) 11);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getContractStatus() + "|"
						+ cwcsList.get(i).getContractCount());
				// 是否保过
				cell = row.createCell((short) 12);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (cwcsList.get(i).getIsProtocal() == null || cwcsList.get(i).getIsProtocal() == false) {
					cell.setCellValue("否");
				} else {
					cell.setCellValue("是");
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			logger.error("CustomerAction.createExcelFile", e);
		}
	}

	/**
	 * 分页查询Json查询手机号
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showCustomerListJson() {
		try {

			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();

			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (cusType != null && !"".equals(cusType)) {
				getQueryCustomerCondition().setCusType(cusType.trim());
			}
			if (startTime != null && !"".equals(startTime)) {
				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}

			List<Map> dataList = new ArrayList<Map>();
			for (int a = 0; a < getPage().getPageResult().size(); a++) {
				Map map = new HashMap();
				Customer cus = (Customer) getPage().getPageResult().get(a);
				map.put("email", cus.getEmail());
				map.put("mobile", cus.getMobile());
				dataList.add(map);
			}

			this.setResult(new Result(true, new Integer(getPage()
					.getTotalRecord()).toString(), new Integer(getPage()
					.getTotalPage()).toString(), dataList));

			return "json";
		} catch (Exception e) {
			logger.error("CustomerAction.createExcelFile", e);
			this.setResult(new Result(false, "error", null, null));
			return "json";
		}

	}

	/**
	 * 根据专业ID查找当前条件下的注册用户数量
	 * 
	 * @param subjectId
	 *            cusType 0 注册学员 1 内部学员 3 全部
	 */
	private void selRegCusNumbers(int subjectId) {
		try {
			for (int cusType = 0; cusType <= 4; cusType++) { // 根据当前专业 查询专业下
																// 内部数量
				// 外部数量 总数量
				if (cusType == 0) {
					this.getCustomerInfo(subjectId, cusType);
				} else if (cusType == 1) {
					this.getCustomerInfo(subjectId, cusType);
					cusType++;
				} else if (cusType == 3) {
					this.getCustomerInfo(subjectId, cusType);
				} else if (cusType == 4) {
					this.getCustomerInfo(subjectId, cusType);
					this.getCustomerInfo(subjectId, 100);
				}
			}
		} catch (Exception e) {
			logger.error("CustomerAction.selRegCusNumbers", e);
		}
	}

	/**
	 * @author wangzheng 学员统计附加查询条件
	 */
	private void selectCondition() {
		getSelectTime1();
		getSelectTime(); // 调用时间附加条件
	}

	private void getSelectTime1() {
		if (startTime != null) { // 如果初次查询时间不为空 则添加查询条件 即 当天时间
			if (startTime.length() != 0) {
				this.getQueryCustomerCondition().setStartCountTime(
						startTime + startHH);
				this.getQueryContractCondition().setStartCountTime(
						startTime + startHH);
			}
		}
		if (endTime != null) {
			if (endTime.length() != 0) {
				this.getQueryCustomerCondition().setEndCountTime(
						endTime + endHH);
				this.getQueryContractCondition().setEndCountTime(
						endTime + endHH);
			}
		}
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar todayDate = Calendar.getInstance(); // 当天时间对象
		Calendar weekDate = Calendar.getInstance(); // 周时间对象
		Calendar monthDate = Calendar.getInstance(); // 当月时间对象
		Calendar threeMonthDate = Calendar.getInstance(); // 三个月时间对象

		Date today = todayDate.getTime(); // 获取时间
		String todayStrartTime = dateFm.format(today); // 格式化当天开始时间
		String todayEndTime = dateFm.format(today); // 格式化当天结束时间
		todayStrartTime = todayStrartTime + startHH; // 完整开始时间
		todayEndTime = todayEndTime + endHH; // 完整结束时间

		// SimpleDateFormat dateFmt = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		weekDate.add(Calendar.DAY_OF_MONTH, -7); // 一周时间处理
		Date week = weekDate.getTime();
		String weekStartTime = dateFmt.format(week);

		monthDate.add(Calendar.MONTH, -1); // 一月时间处理
		Date month = monthDate.getTime();
		String monthStartTime = dateFmt.format(month);

		threeMonthDate.add(Calendar.MONTH, -3); // 三月时间处理
		Date threeMonth = threeMonthDate.getTime();
		String threeMonthStartTime = dateFmt.format(threeMonth);
		if (location == 1) { // 当天查询条件
			this.getQueryCustomerCondition().setStartCountTime(todayStrartTime);
			this.getQueryCustomerCondition().setEndCountTime(todayEndTime);
		} else if (location == 2) { // 一周查询条件
			this.getQueryCustomerCondition().setStartCountTime(
					weekStartTime + startHH);
			this.getQueryCustomerCondition().setEndCountTime(todayEndTime);
		} else if (location == 3) {
			this.getQueryCustomerCondition().setStartCountTime(
					monthStartTime + startHH);
			this.getQueryCustomerCondition().setEndCountTime(todayEndTime);
		} else if (location == 4) {
			this.getQueryCustomerCondition().setStartCountTime(
					threeMonthStartTime + startHH);
			this.getQueryCustomerCondition().setEndCountTime(todayEndTime);
		} else if (location == 0) {
			this.getQueryCustomerCondition().setStartCountTime("");
			this.getQueryCustomerCondition().setEndCountTime("");
			this.getQueryContractCondition().setPayType(-1);
		}
	}

	/**
	 * @author wangzheng 学员统计数量
	 * @param subjectId
	 * @param cusType
	 */
	private void getCustomerInfo(int subjectId, int cusType) {
		Subject subject = this.subjectService.getSubjectById(subjectId); // 查询统计当前的专业
		this.getQueryCustomerCondition().setSubjectId(subjectId); // 查询当前专业下学员，添加专业ID
		this.getQueryCustomerCondition().setCusType("" + cusType); // 添加查询学员类型
		this.customerCountDTO.setSubjectName(subject.getSubjectName()); // 添加专业名字
		this.selectCondition(); // 调用查询附加条件方法

		if (cusType == 0) { // 注册
			// this.selectCondition(); // 调用查询附加条件方法
			this.customerCountDTO.setWbRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition())); // 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getWbRegCustomer();
			cusWbAll += this.customerCountDTO.getWbRegCustomer();

		} else if (cusType == 1) { // 面授
			// this.selectCondition();
			this.customerCountDTO.setNbRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition())); // 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getNbRegCustomer();
		} else if (cusType == 3) { // 内部体验
			// this.selectCondition();
			this.customerCountDTO.setLsRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getLsRegCustomer();
		} else if (cusType == 4) { // 4内部体验(三十天)
			// this.selectCondition();
			this.customerCountDTO.setLsCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getLsCustomer();
		} else if (cusType == 5) {
			// this.selectCondition(); // 调用查询附加条件方法
			this.customerCountDTO.setAllRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getAllRegCustomer();
		} else {
			// this.selectCondition(); // 调用查询附加条件方法
			this.customerCountDTO.setAllRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
		}
	}

	/**
	 * 得到全部订单方法
	 */
	private void getContractAll() {
		cashList = this.contractService.getContractSum(this
				.getQueryContractCondition());
		YFcashList = this.contractService.getYfContractSum(this
				.getQueryContractCondition());
	}

	private void getContractYFSum(int subjectId) { // 查询各个专业 各个类型已付订单数
		int YFZFB = 0;
		int YFPOST = 0;
		int YFCB = 0;
		int YFKQ = 0;
		int YfContractSum = 0;
		int dlskt = 0;// 代理商开通的订单数量
		int TFZFB = 0;
		int yfyhhk = 0;
		int tfyhhk = 0;
		int yfzy = 0;
		float totelPrice = this.customerCountDTO.getTotelPrice();
		;
		for (int i = 0; i < YFcashList.size(); i++) {
			if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 1) { // 支付宝订单处理

				if (YFcashList.get(i).getStatus() == 3) {
					TFZFB++;
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() == 1) {
					totelPrice = totelPrice + YFcashList.get(i).getPayPrice();
					YFZFB++;
					yfZfbSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				} else if (YFcashList.get(i).getStatus() == 2) {
					YFZFB++;
					yfZfbSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 2) { // 货到付款订单处理

				if (YFcashList.get(i).getStatus() == 4) {
					TFZFB++;
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() != 3) {
					YFPOST++;
					yfHdfkSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 3) { // 网银在线订单处理
				if (YFcashList.get(i).getStatus() == 3) {
					TFZFB++;
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() == 1) {
					totelPrice = totelPrice + YFcashList.get(i).getPayPrice();
					YFCB++;
					yfWyzxSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				} else if (YFcashList.get(i).getStatus() == 2) {
					YFCB++;
					yfWyzxSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 4) { // 快钱订单处理

				if (YFcashList.get(i).getStatus() == 3) {
					TFZFB++;
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() == 1) {
					totelPrice = totelPrice + YFcashList.get(i).getPayPrice();
					YFKQ++;
					yfKqSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				} else if (YFcashList.get(i).getStatus() == 2) {
					YFKQ++;
					yfKqSum++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 7) { // 银行汇款处理
				if (YFcashList.get(i).getStatus() == 3) {
					tfyhhk++;
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() == 1) {
					totelPrice = totelPrice + YFcashList.get(i).getPayPrice();
					yfyhhk++;
					tyfyhhk++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getSubjectId() == subjectId
					&& YFcashList.get(i).getPayType() == 6) { // 真友支付处理
				if (YFcashList.get(i).getStatus() == 3) {
					TFSUM++;
				} else if (YFcashList.get(i).getStatus() == 1) {
					totelPrice = totelPrice + YFcashList.get(i).getPayPrice();
					yfzy++;
					this.tyfzy++;
					YfContractSum++; // 当前专业已付款总数
					YFSUM++;
				}
			} else if (YFcashList.get(i).getPayType() == 5
					&& YFcashList.get(i).getSubjectId() == subjectId) {
				dlskt++;
				dlsktSum++;
			}
		}
		this.customerCountDTO.setYfZfbSum(YFZFB); // 当前 已付专业支付宝总数
		this.customerCountDTO.setYfWyzxSum(YFCB); // 当前专业 已付网银在线总数
		this.customerCountDTO.setYfKqSum(YFKQ); // 当前专业 已付快钱总数
		this.customerCountDTO.setYfHdfkSum(YFPOST); // 当前专业货到付款 总数
		this.customerCountDTO.setYfSubjectContractSum(YfContractSum); // 当前专业
		this.customerCountDTO.setDlskt(dlskt); // 当前专业代理商开通
		this.customerCountSum.setYfWyzxSum(yfWyzxSum); // 所有专业已付网银在线总计
		this.customerCountSum.setYfKqSum(yfKqSum); // 所有专业已付快钱总计
		this.customerCountSum.setYfHdfkSum(yfHdfkSum); // 已付货到付款总计
		this.customerCountSum.setYfZfbSum(yfZfbSum); // 已付支付宝总计
		this.customerCountSum.setYfContractSum(YFSUM); // 所有专业已付订单总计
		this.customerCountDTO.setTfZfb(TFZFB);
		this.customerCountSum.setTfSum(TFSUM);
		this.customerCountSum.setDlskt(dlsktSum);// 代理商开通的订单
		this.customerCountDTO.setTotelPrice(totelPrice);
		this.customerCountDTO.setYfyhhk(yfyhhk);// 已付银行汇款
		this.customerCountDTO.setYfzy(yfzy);// 已付真友
	}

	/**
	 * 得到专业下 各种支付方式 订单信息
	 * 
	 * 优化过
	 */
	public void getContractSum(int subjectId) {
		int GIVE = 0;
		int ZFB = 0;
		int POST = 0;
		int CB = 0;
		int KQ = 0;
		int dlskt = 0;
		int zy = 0;
		/*-单项银行汇款-*/
		int yhhk = 0;
		/**
		 * @王超 用户取消订单量
		 */
		int cancelCount = 0;
		/**
		 * @author 王超 管理员取消订单量
		 */
		int backCancelCount = 0;
		/**
		 * 订单流水
		 */
		float totelPrice = 0;
		int ContractSum = 0;

		// payType付款方式0 为赠送 1支付宝 2货到付款 3 网银在线 4 块钱
		// stutas 付款状态 //赠送的包括 2赠送 4修复 0未付 1已付 3退费; 货到付款的包括 0未激活 1已激活 3已取消 4退费
		// int a = 0;
		for (int i = 0; i < cashList.size(); i++) {
			if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 0) { // 赠送订单处理
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				GIVE++; // 当前专业赠送总数
				zsSum++; // 所有专业赠送总数
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 1) { // 支付宝订单处理
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				ZFB++;
				zfbSum++;
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 2) { // 货到付款订单处理
				if (cashList.get(i).getStatus() == 3
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 3
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				if (cashList.get(i).getStatus() == 1) {
					totelPrice += cashList.get(i).getPayPrice();
				}
				POST++;
				hdfkSum++;
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 3) { // 网银在线订单处理
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				CB++;
				wyzxSum++;
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 4) { // 快钱订单处理
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				KQ++;
				kqSum++;
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 7) { // 银行汇款
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				yhhk++;
				tyhfk++;
				ContractSum++;
			} else if (cashList.get(i).getSubjectId() == subjectId
					&& cashList.get(i).getPayType() == 6) { // 真友支付
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 0) {
					cancelCount++;
				}
				if (cashList.get(i).getStatus() == 4
						&& cashList.get(i).getCancelFrom() == 1) {
					backCancelCount++;
				}
				zy++;
				this.tzy++;
				ContractSum++;
			}
		}

		SUM = cashList.size();

		this.customerCountDTO.setZsSum(GIVE); // 当前专业赠送总数
		this.customerCountDTO.setZfbSum(ZFB); // 当前专业支付宝总数
		this.customerCountDTO.setWyzxSum(CB); // 当前专业网银在线总数
		this.customerCountDTO.setKqSum(KQ); // 当前专业会计总数
		this.customerCountDTO.setHdfkSum(POST); // 当前专业货到付款总数
		this.customerCountDTO.setSubjectContractSum(ContractSum); // 当前专业 订单总数
		// this.customerCountDTO.setDlskt(dlskt); // 当前专业 代理商开通数
		this.customerCountSum.setZfbSum(zfbSum); // 所有专业支付宝总计
		this.customerCountSum.setContractSum(SUM); // 所有专业 订单总计
		this.customerCountSum.setKqSum(kqSum); // 所有专业快钱总计
		this.customerCountSum.setWyzxSum(wyzxSum); // 所有专业网银在线总计
		this.customerCountSum.setZsSum(zsSum); // 赠送总计
		this.customerCountSum.setZfbSum(zfbSum); // 支付宝总计
		this.customerCountSum.setHdfkSum(hdfkSum); // 货到付款总计
		this.customerCountSum.setDlskt(dlskt);// 代理开通
		this.customerCountDTO.setCancelCount(cancelCount);
		this.customerCountDTO.setBackCancelCount(backCancelCount);
		this.customerCountDTO.setTotelPrice(totelPrice);
		this.customerCountDTO.setYhhk(yhhk);
		this.customerCountDTO.setZy(zy);
		// this.customerCountSum.setDlskt(dlsktSum);// 代理商开通的订单

	}

	/**
	 * @author wangzheng 学员订单统计主方法，调用各个模块字方法
	 */
	public String customerDisplayInfo() { // 学员统计主方法
		try {
			selectCondition(); // 添加时间查询条件
			ccNewDTO = this.customerService
					.getCustomerAll(queryCustomerCondition);
			List<CustomerNewDTO> crmlist=customerService.getUserBySubjectGroup(queryCustomerCondition);
			for(int i=0;i<crmlist.size();i++){
				CustomerNewDTO dto=ccNewDTO.get(i);
				if(dto.getSubName().equals(crmlist.get(i).getSubName())){
					dto.setLeyuStudent(crmlist.get(i).getLeyuStudent());
					dto.setLiuyanStudent(crmlist.get(i).getLiuyanStudent());
					dto.setCallInStudent(crmlist.get(i).getCallInStudent());
				}
			}
		} catch (Exception e) {
			logger.error("CustomerAction.customerDisplayInfo", e);
		}
		return "customerCountDTOList";
	}

	/**
	 * DS商品学员统计  谢添加
	 * @return
	 */
	public String dsCustomerDisplayInfo() { // 学员统计主方法
		try {
			selectCondition(); // 添加时间查询条件
			ccNewDTO = this.customerService
					.getDSCustomerAll(queryCustomerCondition);
		} catch (Exception e) {
			logger.error("CustomerAction.customerDisplayInfo", e);
		}
		return "dsCustomerCountDTOList";
	}

	/**
	 * 学员月统计显示
	 * 
	 * @return
	 */
	public String customerDisplayInfoMonth() {
		try {
			monthDayNewList = this.customerService
					.getStudentMonth(queryCustomerCondition);

		} catch (Exception e) {
			logger.error("月统计错误:", e);
		}
		return "customerCountDTOListmonth";
	}

	/**
	 * 统计 学员所有专业下 总计数量 即 所有专业人数相加
	 */
	public void getCustomerSum() {
		int wbSum = 0; // 声明外部 内部 临时总和变量
		int nbSum = 0;
		int allSum = 0;
		int lsSum = 0;
		int lsSum1 = 0;
		int cancelCount = 0;
		int backCancelCount = 0;
		float totelPrice = 0;
		for (int i = 0; i < customerCountDTOList.size(); i++) { // 遍历所有专业
			allSum += customerCountDTOList.get(i).getAllRegCustomer(); // 经过处理
			// 所有专业DTO里边的人数已经添加完毕
			wbSum += customerCountDTOList.get(i).getWbRegCustomer(); // 现在将所有专业人数相加
			// 得到总计
			nbSum += customerCountDTOList.get(i).getNbRegCustomer();
			lsSum += customerCountDTOList.get(i).getLsRegCustomer();
			lsSum1 += customerCountDTOList.get(i).getLsCustomer();
			cancelCount += customerCountDTOList.get(i).getCancelCount();
			backCancelCount += customerCountDTOList.get(i).getBackCancelCount();
			totelPrice += customerCountDTOList.get(i).getTotelPrice();
		}
		customerCountSum.setAllSum(allSum);
		customerCountSum.setNbSum(nbSum);
		customerCountSum.setWbSum(wbSum);
		customerCountSum.setLsSum((lsSum + lsSum1));
		customerCountSum.setAllSum(cusAll);
		customerCountSum.setWbSum(cusWbAll);
		customerCountSum.setTotelPrice(totelPrice);
		customerCountSum.setCancelCount(cancelCount);
		customerCountSum.setBackCancelCount(backCancelCount);
		customerCountSum.setTyfyhhk(tyfyhhk);
		customerCountSum.setTyhhk(tyhfk);
		customerCountSum.setTzy(tzy);// 所有真友支付
		customerCountSum.setTyfzy(tyfzy);// 所有已付真友支付
	}

	/**
	 * 学员统计后台处理
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String openCusCountList() {

		try {
			SubjectCountsDTO subjectCountsDTOTemp = null;
			Subject subjectTemp = null;
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			for (int i = 0; i < subjectList.size(); i++) {
				subjectCountsDTOTemp = new SubjectCountsDTO();
				subjectTemp = subjectList.get(i);
				subjectCountsDTOTemp.setSubjectId(subjectTemp.getSubjectId());
				subjectCountsDTOTemp.setSubjectName(subjectTemp
						.getSubjectName());
				subjectCountsDTOTemp.setCreateTime(subjectTemp.getCreateTime());
				subjectCountsDTOTemp.setStatus(subjectTemp.getStatus());
				subjectCountsDTOTemp.setUpdateTime(subjectTemp.getUpdateTime());
				subjectCountsDTOTemp.setTestTime(subjectTemp.getTestTime());
				subjectCountsDTOList.add(subjectCountsDTOTemp);
			}
			if (startTime != null && !"".equals(startTime)) {
				location = 5;
			}
			this.getQueryContractCondition().setPayType(-1);
			// int subject =getQueryCustomerCondition().getSubjectId(); //获取专业id
			String cType = getQueryCustomerCondition().getCusType(); // 获取注册类型

			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			Calendar todayDate = Calendar.getInstance();
			Calendar weekDate = Calendar.getInstance();
			Calendar monthDate = Calendar.getInstance();
			Calendar threeMonthDate = Calendar.getInstance();

			Date today = todayDate.getTime();
			String todayStrartTime = dateFm.format(today);
			String todayEndTime = dateFm.format(today);
			todayStrartTime = todayStrartTime + startHH;
			todayEndTime = todayEndTime + endHH;

			SimpleDateFormat dateFmt = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			weekDate.add(Calendar.DAY_OF_MONTH, -7);
			Date week = weekDate.getTime();
			String weekStartTime = dateFmt.format(week);

			monthDate.add(Calendar.MONTH, -1);
			Date month = monthDate.getTime();
			String monthStartTime = dateFmt.format(month);

			threeMonthDate.add(Calendar.MONTH, -3);
			Date threeMonth = threeMonthDate.getTime();
			String threeMonthStartTime = dateFmt.format(threeMonth);

			if (location == 5) {
				String timeBeginPart = null;
				timeBeginPart = this.startTime + this.startHH;
				String timeEndPart = null;
				if (!"".equals(endTime) && endTime != null) {
					timeEndPart = this.endTime + this.endHH;
				}
				// 按时间段显示
				// 注册时间参数
				this.getQueryCustomerCondition().setStartCountTime(
						timeBeginPart);
				this.getQueryCustomerCondition().setEndCountTime(timeEndPart);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getQueryContractCondition().setEndCountTime(timeEndPart);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getHdfkQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getZfbQueryContractCondition().setStartCountTime(
						timeBeginPart);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getHdfkQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getZfbPayedQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getZfbQueryContractCondition()
						.setEndCountTime(timeEndPart);

			} else if (location == 0) {
				// 全部统计

			} else if (location == 1) {
				// 今日

				// 注册时间参数
				this.getQueryCustomerCondition().setStartCountTime(
						todayStrartTime);
				this.getQueryCustomerCondition().setEndCountTime(todayEndTime);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getQueryContractCondition().setEndCountTime(todayEndTime);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						todayStrartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getHdfkQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getZfbPayedQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getZfbQueryContractCondition().setEndCountTime(
						todayEndTime);

			} else if (location == 2) {
				// 一周内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						weekStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						weekStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);

			}

			else if (location == 3) {
				// 一个月内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						monthStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						monthStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);

			} else if (location == 4) {

				// 三个月内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						threeMonthStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						threeMonthStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);
			}
			// 调用公共的方法
			this.getCountRegistAndContract();

			this.getQueryCustomerCondition().setCusType("");

			// 调用查询月份和注册数据列表
			this.getMonthAndRegistAndContract(cType); // 调用查询方法 因为参数传过去
			// 已经被上边的公共方法改了，所以先把参数保存下来，在将参数传进方法
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);

		} catch (Exception e) {
			logger.error("CustomerAction.openCusCountList", e);
			return ERROR;
		}
		return "openCusCountList";
	}

	public void getCountRegistAndContract() {
		this.getQueryCustomerCondition().setCusType("0");
		// 会计证 ID 3
		this.getQueryCustomerCondition().setSubjectId(3);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 3);
		// 人力 ID 1
		this.getQueryCustomerCondition().setSubjectId(1);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 1);
		// 司法 ID 5
		this.getQueryCustomerCondition().setSubjectId(5);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 5);
		// 证券 ID 8
		this.getQueryCustomerCondition().setSubjectId(8);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 8);
		// 注会 ID 7
		this.getQueryCustomerCondition().setSubjectId(7);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 7);
		// 一级建造师
		this.getQueryCustomerCondition().setSubjectId(9);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 9);
		// 高级会计师
		this.getQueryCustomerCondition().setSubjectId(10);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 10);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 11);
		// 全部 0
		this.getQueryCustomerCondition().setSubjectId(0);
		this.getQueryCustomerCondition().setCusType("3");
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 0);
		// １为查看订单数 ２为已付订单数
		this.getQueryContractCondition().setConStr(1);

		// 会计证
		this.getQueryContractCondition().setSubjectId(3);
		this.getQuerySubjectContract(getQueryContractCondition(), 3);
		// 人力
		this.getQueryContractCondition().setSubjectId(1);
		this.getQuerySubjectContract(getQueryContractCondition(), 1);
		// 司法
		this.getQueryContractCondition().setSubjectId(5);
		this.getQuerySubjectContract(getQueryContractCondition(), 5);
		// 证券
		this.getQueryContractCondition().setSubjectId(8);
		this.getQuerySubjectContract(getQueryContractCondition(), 8);
		// 注会
		this.getQueryContractCondition().setSubjectId(7);
		this.getQuerySubjectContract(getQueryContractCondition(), 7);
		// 一级建造师
		this.getQueryContractCondition().setSubjectId(9);
		this.getQuerySubjectContract(getQueryContractCondition(), 9);
		// 高级会计师
		this.getQueryContractCondition().setSubjectId(10);
		this.getQuerySubjectContract(getQueryContractCondition(), 100);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 110);
		// 全部
		this.getQueryContractCondition().setSubjectId(0);
		this.getQuerySubjectContract(getQueryContractCondition(), 0);

		// １为查看订单数 ２为已付订单数

		this.getQueryContractCondition().setConStr(2);
		// 会计证
		this.getQueryContractCondition().setSubjectId(3);
		this.getQuerySubjectContract(getQueryContractCondition(), 13);
		// 人力
		this.getQueryContractCondition().setSubjectId(1);
		this.getQuerySubjectContract(getQueryContractCondition(), 11);
		// 司法
		this.getQueryContractCondition().setSubjectId(5);
		this.getQuerySubjectContract(getQueryContractCondition(), 15);
		// 证券
		this.getQueryContractCondition().setSubjectId(8);
		this.getQuerySubjectContract(getQueryContractCondition(), 18);
		// 注会
		this.getQueryContractCondition().setSubjectId(7);
		this.getQuerySubjectContract(getQueryContractCondition(), 17);
		// 一级建造师
		this.getQueryContractCondition().setSubjectId(9);
		this.getQuerySubjectContract(getQueryContractCondition(), 19);
		// 高级会计师
		this.getQueryContractCondition().setSubjectId(10);
		this.getQuerySubjectContract(getQueryContractCondition(), 200);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 210);
		// 全部
		this.getQueryContractCondition().setSubjectId(0);
		this.getQuerySubjectContract(getQueryContractCondition(), 10);

	}

	public void getQuerySubjectRegist(
			QueryCustomerCondition queryCustomerCondition, int subjectId) {

		if (subjectId == 3) {
			customerDTO.setKjRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 1) {
			customerDTO.setRlRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 5) {
			customerDTO.setSfRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 8) {
			customerDTO.setZqRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 7) {
			customerDTO.setZkRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 9) {
			customerDTO.setJzRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 10) {
			customerDTO.setGkRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 11) {
			customerDTO.setGwyRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 0) {
			customerDTO.setQbRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
			// ////////1为内部学员 0为注册学员 区分内部学员和外部学员
			this.getQueryCustomerCondition().setCusType("1");
			customerDTO.setQbRegistNb(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
			customerDTO.setQbRegistWb(customerDTO.getQbRegist()
					- customerDTO.getQbRegistNb());
		}

	}

	public void getQuerySubjectContract(
			QueryContractCondition queryContractCondition, int subjectId) {
		// 支付宝支付的已付订单
		this.getZfbPayedQueryContractCondition().setConStr(2);
		this.getZfbPayedQueryContractCondition().setPayType(1);
		// 支付宝所有的订单
		this.getZfbQueryContractCondition().setConStr(1);
		this.getZfbQueryContractCondition().setPayType(1);
		// 货到付款的已激活的订单
		this.getHdfkPayedQueryContractCondition().setConStr(2);
		this.getHdfkPayedQueryContractCondition().setPayType(2);
		// 货到付款的所有的订单
		this.getHdfkQueryContractCondition().setConStr(1);
		this.getHdfkQueryContractCondition().setPayType(2);
		// 网银支付订单

		// 网银支付已付订单

		// 订单数
		if (subjectId == 3) {
			customerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));

			this.getZfbQueryContractCondition().setSubjectId(3);
			this.getHdfkQueryContractCondition().setSubjectId(3);
			// 支付宝订单
			zfbCustomerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付活订单
			hdfkCustomerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 1) {
			customerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(1);
			this.getHdfkQueryContractCondition().setSubjectId(1);
			// 支付宝订单
			zfbCustomerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付活订单
			hdfkCustomerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 5) {
			customerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(5);
			this.getHdfkQueryContractCondition().setSubjectId(5);
			// 支付宝订单
			zfbCustomerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 8) {
			customerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(8);
			this.getHdfkQueryContractCondition().setSubjectId(8);
			// 支付宝订单
			zfbCustomerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 7) {
			customerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(7);
			this.getHdfkQueryContractCondition().setSubjectId(7);
			// 支付宝订单
			zfbCustomerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));

		} else if (subjectId == 9) {
			customerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(9);
			this.getHdfkQueryContractCondition().setSubjectId(9);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 100) {
			customerDTO.setGkContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(10);
			this.getHdfkQueryContractCondition().setSubjectId(10);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 110) {
			customerDTO.setGwyContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(11);
			this.getHdfkQueryContractCondition().setSubjectId(11);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 0) {
			customerDTO.setQbContract(this.contractService
					.getContractNumber(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(0);
			this.getHdfkQueryContractCondition().setSubjectId(0);
			// 支付宝订单
			zfbCustomerDTO.setQbContract(this.contractService
					.getContractNumber(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setQbContract(this.contractService
					.getContractNumber(hdfkQueryContractCondition));
			customerDTO.setWyzxContract(customerDTO.getQbContract()
					- zfbCustomerDTO.getQbContract()
					- hdfkCustomerDTO.getQbContract());
		}

		// 已付订单数
		else if (subjectId == 13) {
			customerDTO.setKjPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(3);
			this.getHdfkPayedQueryContractCondition().setSubjectId(3);
			// 支付宝已付
			zfbCustomerDTO
					.setKjPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setKjPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 11) {
			customerDTO.setRlPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(1);
			this.getHdfkPayedQueryContractCondition().setSubjectId(1);
			// 支付宝已付
			zfbCustomerDTO
					.setRlPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setRlPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 15) {
			customerDTO.setSfPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(5);
			this.getHdfkPayedQueryContractCondition().setSubjectId(5);
			// 支付宝已付
			zfbCustomerDTO
					.setSfPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setSfPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 18) {
			customerDTO.setZqPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(8);
			this.getHdfkPayedQueryContractCondition().setSubjectId(8);
			// 支付宝已付
			zfbCustomerDTO
					.setZqPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setZqPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 17) {
			customerDTO.setZkPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(7);
			this.getHdfkPayedQueryContractCondition().setSubjectId(7);
			// 支付宝已付
			zfbCustomerDTO
					.setZkPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setZkPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 19) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(9);
			this.getHdfkPayedQueryContractCondition().setSubjectId(9);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 200) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(10);
			this.getHdfkPayedQueryContractCondition().setSubjectId(10);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 210) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(11);
			this.getHdfkPayedQueryContractCondition().setSubjectId(11);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 10) {
			customerDTO.setQbPayContract(this.contractService
					.getContractNumber(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(0);
			this.getHdfkPayedQueryContractCondition().setSubjectId(0);
			// 支付宝已付
			zfbCustomerDTO.setQbPayContract(this.contractService
					.getContractNumber(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO.setQbPayContract(this.contractService
					.getContractNumber(hdfkPayedQueryContractCondition));
			customerDTO.setWyzxPayContract(customerDTO.getQbPayContract()
					- zfbCustomerDTO.getQbPayContract()
					- hdfkCustomerDTO.getQbPayContract());
		}

		setCounts();

	}

	/**
	 * 设置各个学科的注册和订单的数量
	 */
	public void setCounts() {
		for (int i = 0; i < subjectCountsDTOList.size(); i++) {
			if (subjectCountsDTOList.get(i).getSubjectId() == 1) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getRlRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getRlContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getRlContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getRlContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getRlPayContract()
								- zfbCustomerDTO.getRlPayContract()
								- hdfkCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getRlContract()
								- zfbCustomerDTO.getRlContract()
								- hdfkCustomerDTO.getRlContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 3) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getKjRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getKjContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getKjContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getKjContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getKjPayContract()
								- zfbCustomerDTO.getKjPayContract()
								- hdfkCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getKjContract()
								- zfbCustomerDTO.getKjContract()
								- hdfkCustomerDTO.getKjContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 5) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getSfRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getSfContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getSfContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getSfContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getSfPayContract()
								- zfbCustomerDTO.getSfPayContract()
								- hdfkCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getSfContract()
								- zfbCustomerDTO.getSfContract()
								- hdfkCustomerDTO.getSfContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 7) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getZkRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getZkContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getZkContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getZkContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getZkPayContract()
								- zfbCustomerDTO.getZkPayContract()
								- hdfkCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getZkContract()
								- zfbCustomerDTO.getZkContract()
								- hdfkCustomerDTO.getZkContract());

			} else if (subjectCountsDTOList.get(i).getSubjectId() == 8) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getZqRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getZqContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getZqContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getZqContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getZqPayContract()
								- zfbCustomerDTO.getZqPayContract()
								- hdfkCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getZqContract()
								- zfbCustomerDTO.getZqContract()
								- hdfkCustomerDTO.getZqContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 9) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getJzRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getJzContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getJzContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getJzContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getJzPayContract()
								- zfbCustomerDTO.getJzPayContract()
								- hdfkCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getJzContract()
								- zfbCustomerDTO.getJzContract()
								- hdfkCustomerDTO.getJzContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 10) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getGkRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getGkContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getGkContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getGkContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getGkPayContract()
								- zfbCustomerDTO.getGkPayContract()
								- hdfkCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getGkContract()
								- zfbCustomerDTO.getGkContract()
								- hdfkCustomerDTO.getGkContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 11) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getGwyRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getGwyPayContract()
								- zfbCustomerDTO.getGwyPayContract()
								- hdfkCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getGwyContract()
								- zfbCustomerDTO.getGwyContract()
								- hdfkCustomerDTO.getGwyContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 0) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getQbRegist());
				subjectCountsDTOList.get(i).setQbRegistNb(
						customerDTO.getQbRegistNb());
				subjectCountsDTOList.get(i).setQbRegistWb(
						customerDTO.getQbRegistWb());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getQbContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getQbPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getQbContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getQbPayContract());
			}

		}

	}

	public void getMonthAndRegistAndContract(String cType) {
		// 根据条件查询
		// getQueryCustomerCondition().setSubjectId(subId);
		getQueryCustomerCondition().setCusType(cType);
		String email = getQueryCustomerCondition().getEmail();
		String mobile = getQueryCustomerCondition().getMobile();
		String cusType = getQueryCustomerCondition().getCusType();
		int subjectId = customer.getSubjectId();
		int startNumber = getQueryCustomerCondition().getStartNumber();
		int endNumber = getQueryCustomerCondition().getEndNumber();

		if (email != null && !"".equals(email) || cusType != null
				&& !"".equals(cusType) || startTime != null
				&& !"".equals(startTime) || endTime != null
				&& !"".equals(endTime) || subjectId != 0 || startNumber != 0
				|| endNumber != 0 || customer.getCusFromUrl() != null
				&& !"".equals(customer.getCusFromUrl().trim())) {
			if (dateLocation == -1 || dateLocation == 0) {
				if (customer.getCusFromUrl() != null
						&& !"".equals(customer.getCusFromUrl().trim())) {
					if (customer.getCusFromUrl().equals("1")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.org.cn");
					} else if (customer.getCusFromUrl().equals("2")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.cn");
					} else if (customer.getCusFromUrl().equals("3")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.org");
					} else if (customer.getCusFromUrl().equals("4")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.net.cn");
					} else if (customer.getCusFromUrl().equals("5")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.com.cn");
					}
				}
				if (startNumber != 0) {
					this.getQueryCustomerCondition()
							.setStartNumber(startNumber);
				}
				if (endNumber != 0) {
					this.getQueryCustomerCondition().setEndNumber(endNumber);
				}

				if (endNumber == 0) {
					this.getQueryCustomerCondition().setEndNumber(999999);
				}

				if (subjectId == 0) {
					this.getQueryCustomerCondition().setSubjectId(-1);
				} else {
					this.getQueryCustomerCondition().setSubjectId(subjectId);
				}
				if (email != null && !"".equals(email)) {
					getQueryCustomerCondition().setEmail(email.trim());
				}
				if (mobile != null && !"".equals(mobile)) {
					getQueryCustomerCondition().setMobile(mobile.trim());
				}
				if (cusType != null && !"".equals(cusType)) {
					getQueryCustomerCondition().setCusType(cusType.trim());
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime + startHH;
					this.getQueryCustomerCondition().setStartTime(startTime);
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime + endHH;
					this.getQueryCustomerCondition().setEndTime(endTime);
				}
				// 查询的用户信息
				this.getQueryCustomerCondition().setPageSize(30);
				setPage(customerService
						.getCustomerListByCondition(getQueryCustomerCondition()));
				setPageUrlParms();
				if (getPage() != null) {
					getPage().setPageSize(30);
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime.substring(0,
							startTime.indexOf(startHH));
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime.substring(0, endTime.indexOf(endHH));
				}
			} else {
				if (dateLocation == 1) {
					if (startTime != null && !"".equals(startTime)) {
						startTime = startTime + startHH;
						this.getQueryContractCondition()
								.setStartTime(startTime);
					}
					if (endTime != null && !"".equals(endTime)) {
						endTime = endTime + endHH;
						this.getQueryContractCondition().setEndTime(endTime);
					}
					this.getQueryContractCondition().setConStr(1);
				}
				if (dateLocation == 2) {
					if (startTime != null && !"".equals(startTime)) {
						startTime = startTime + startHH;
						this.getQueryContractCondition().setPayStartTime(
								startTime);
					}
					if (endTime != null && !"".equals(endTime)) {
						endTime = endTime + endHH;
						this.getQueryContractCondition().setPayEndTime(endTime);
					}
					this.getQueryContractCondition().setConStr(2);
					// this.getQueryContractCondition().setNewStatus("1");

				}
				// customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
				// 查询订单的信息
				if (subjectId == 0) {
					this.getQueryContractCondition().setSubjectId(-1);
				} else {
					this.getQueryContractCondition().setSubjectId(subjectId);
				}
				if (customer.getCusFromUrl() != null
						&& !"".equals(customer.getCusFromUrl().trim())) {
					if (customer.getCusFromUrl().equals("1")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.org.cn");
					} else if (customer.getCusFromUrl().equals("2")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.cn");
					} else if (customer.getCusFromUrl().equals("3")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.org");
					} else if (customer.getCusFromUrl().equals("4")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.net.cn");
					} else if (customer.getCusFromUrl().equals("5")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.com.cn");
					}
				}

				this.getQueryContractCondition().setPageSize(30);
				setPage(this.contractService
						.getContractNumberBySubjectList(getQueryContractCondition()));
				setPageUrlParms();
				if (getPage() != null) {
					getPage().setPageSize(30);
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime.substring(0,
							startTime.indexOf(startHH));
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime.substring(0, endTime.indexOf(endHH));
				}
			}

		} else {
			this.getQueryCustomerCondition().setEndNumber(999999);
			// 分析数据，按月份显示
			int monthContract = 0;
			int monthPayContract = 0;
			float percent;
			String monthS;
			monthList = this.customerService.getMonthList();
			for (int i = 0; monthList != null && i < monthList.size(); i++) {
				Date date = monthList.get(i).getRegTime();
				SimpleDateFormat dateFmm = new SimpleDateFormat("yyyy-MM");
				monthS = dateFmm.format(date);
				monthContract = this.contractService
						.getMonthContractNumber(monthS);
				monthPayContract = this.contractService
						.getMonthPayContractNumber(monthS);
				monthList.get(i).setMonthRigist(
						this.customerService.getMonthRegistNumber(monthS));
				monthList.get(i).setMonthContract(monthContract);
				monthList.get(i).setMonthPayContract(monthPayContract);
				percent = Math
						.round(((float) monthPayContract / monthContract) * 1000) / 10;
				monthList.get(i).setPercent(percent);
			}

		}

	}

	// ----------------------------------------------互查范围-------------------------------------------
	public String getMonthDay() {
		try {
			 
			subjectList = this.subjectService.getAllSubject();
			String yearsMonth = "";
			if (radios == null) {
				DateFormat format = new SimpleDateFormat("yyyy-MM");
				Date date =new Date();
				if(monthDay!=null && !monthDay.equals(""))
				{
					date= format.parse(monthDay);
				}
				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					years = String.valueOf(calendar.get(Calendar.YEAR));
					months = String.valueOf(calendar.get(Calendar.MONTH)+1);
					if(months.length()==1)
					months="0"+months;
					monthDay=years+"-"+months;
				}
				// /此注释为获取当前日期
				yearsMonth = monthDay;
				queryCustomerCondition = new QueryCustomerCondition();
				queryCustomerCondition.setStartTimeNew(yearsMonth + "-01");
				queryCustomerCondition.setMonthTimeNew(yearsMonth);
				monthDayNewList = this.customerService
						.getOrdersPaid(queryCustomerCondition);
			} else {
				if ("m".equals(radios)) {
					yearsMonth = years + "-" + months;
					if (queryCustomerCondition == null) {
						queryCustomerCondition = new QueryCustomerCondition();
					}
					if (queryCustomerCondition.getSubjectId() == -1) {
						queryCustomerCondition.setSubjectId(0);
					}
					queryCustomerCondition.setStartTimeNew(yearsMonth + "-01");
					queryCustomerCondition.setMonthTimeNew(yearsMonth);
					contractFromUrl = queryCustomerCondition
							.getContractFromUrl();// 域名来源
					webType = queryCustomerCondition.getWebType();// 部门
					monthDayNewList = this.customerService
							.getOrdersPaid(queryCustomerCondition);
				}
				if ("c".equals(radios)) {
					if (queryCustomerCondition.getSubjectId() == -1) {
						queryCustomerCondition.setSubjectId(0);
					}
					if (startTime != null && !"".equals(startTime)) {
						String d = startTime.toString() + " ";
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
						Date dd = format.parse(d);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(dd);
						calendar.add(Calendar.DAY_OF_MONTH, -1);
						this.getQueryCustomerCondition().setStartCountTime(
								format.format(calendar.getTime()));
					}
					if (endTime != null && !"".equals(endTime)) {
						this.getQueryCustomerCondition().setEndCountTime(
								endTime);
					}
					String start = queryCustomerCondition.getStartCountTime();
					String end = queryCustomerCondition.getEndCountTime();
					if (start == "2010-01-01 00:00:00"
							&& end == "2020-01-01 00:00:00") {
						queryCustomerCondition = new QueryCustomerCondition();
						queryCustomerCondition.setStartTimeNew(yearsMonth
								+ "-01");
						queryCustomerCondition.setMonthTimeNew(yearsMonth);
						monthDayNewList = this.customerService
								.getOrdersPaid(queryCustomerCondition);
					} else {
						String b = "select 1 as s ";
						for (int i = 2; i < diffDay + 2; i++) {
							b += "union all select " + i + " ";
						}
						queryCustomerCondition.setMonthTimeNew(b);
						contractFromUrl = queryCustomerCondition
								.getContractFromUrl();// 域名来源
						webType = queryCustomerCondition.getWebType();// 部门
						monthDayNewList = this.customerService
								.getOrdersPaidTime(queryCustomerCondition);
					}
				}
			}
			for (CustomerCountNewDTO l : monthDayNewList) {
				datePr += "'" + l.getDt() + "',";
				contractPr += "['" + l.getDt() + "',"
						+ (l.getTotalAmount() == null ? 0 : l.getTotalAmount())
						+ "],";
				contractPayPr += "['" + l.getDt() + "'," + l.getOrdersPaid()
						+ "],";
			}
			datePr = datePr.substring(0, datePr.length() - 1) + "]";
			contractPr = contractPr.substring(0, contractPr.length() - 1) + "]";
			contractPayPr = contractPayPr.substring(0,
					contractPayPr.length() - 1) + "]";
		} catch (Exception e) {
			logger.error("CustomerAction.getMonthDay", e);
			return ERROR;
		}
		return "getMonthDay";
	}

	/**
	 * 折线图 统计 按条件查询
	 * 
	 * @return
	 */
	public String getMonthDayByCondition() {
		try {
			float percent;
			subjectList = this.subjectService.getAllSubject();
			this.monthDayList.clear();
			if (startTime != null && !"".equals(startTime)) {
				this.getQueryContractCondition().setPayStartTime(
						startTime + startHH);
			}
			if (endTime != null && !"".equals(endTime)) {
				this.getQueryContractCondition().setPayEndTime(endTime + endHH);
			}
			if (webType != 0) {
				this.getQueryContractCondition().setWebType(webType);
			}

			List<String> dateList = getDateList(startTime, endTime);
			for (int i = 0; i < dateList.size(); i++) {
				this.getQueryContractCondition().setMonth(dateList.get(i));
				int payContractNumber = this.contractService
						.getPayContractNumberByCondition(this
								.getQueryContractCondition());
				Integer contractNumber = this.contractService
						.getContractNumberByCondition(this
								.getQueryContractCondition());
				Integer sumMoney = this.contractService
						.getPayContractSumMoneyByCondition(this
								.getQueryContractCondition());
				if (sumMoney == null) {
					sumMoney = 0;
				}
				if (startTime == null || "".equals(startTime)) {
					this.monthDayList.get(i).setMonthRigist(
							this.customerService.getMonthRegistNumber(dateList
									.get(i)));
					this.monthDayList.get(i).setMonthPayContract(
							payContractNumber);
					this.monthDayList.get(i).setMonthContract(contractNumber);
					this.monthDayList.get(i).setMonthPaySumMoney(sumMoney);

					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					this.monthDayList.get(i).setRegTime(
							dateFormat.parse(dateList.get(i)));
					percent = Math
							.round(((float) payContractNumber / contractNumber) * 1000) / 10;
					this.monthDayList.get(i).setPercent(percent);
				} else {
					Customer customer = new Customer();
					customer.setMonthRigist(this.customerService
							.getMonthRegistNumber(dateList.get(i)));
					customer.setMonthPayContract(payContractNumber);
					customer.setMonthContract(contractNumber);
					customer.setMonthPaySumMoney(sumMoney);

					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					customer.setRegTime(dateFormat.parse(dateList.get(i)));
					percent = Math
							.round(((float) payContractNumber / contractNumber) * 1000) / 10;
					customer.setPercent(percent);
					this.monthDayList.add(customer);
				}

				datePr += "'" + dateList.get(i) + "',";
				contractPr += "['" + dateList.get(i) + "'," + sumMoney + "],";
				contractPayPr += "['" + dateList.get(i) + "',"
						+ payContractNumber + "],";
			}
			datePr = datePr.substring(0, datePr.length() - 1) + "]";
			contractPr = contractPr.substring(0, contractPr.length() - 1) + "]";
			contractPayPr = contractPayPr.substring(0,
					contractPayPr.length() - 1) + "]";
		} catch (ParseException e) {
			logger.error("CustomerAction.getMonthDayByCondition", e);
			return ERROR;
		}
		return "getMonthDay";
	}

	/**
	 * 处理 开始时间 结束时间 之间的时间段
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<String> getDateList(String startTime, String endTime) {
		List<String> dateList = new ArrayList<String>();
		if (startTime == null || "".equals(startTime)) {
			month = month.toString().trim();
			monthDayList = this.customerService.getMonthDay(month);
			for (int j = 0; j < monthDayList.size(); j++) {
				Date date = monthDayList.get(j).getRegTime();
				SimpleDateFormat dateFmm = new SimpleDateFormat("yyyy-MM-dd");
				String monthS = dateFmm.format(date);
				dateList.add(monthS);
			}
		} else {

			String lastEndTime = endTime;
			String sta = startTime.substring(0, startTime.length() - 2);
			String startDayStr = startTime.substring(startTime.length() - 2,
					startTime.length());
			String endDayStr = endTime.substring(endTime.length() - 2,
					endTime.length());
			int startDay = Integer.parseInt(startDayStr);
			int endDay = Integer.parseInt(endDayStr);

			for (; startDay < endDay; startDay++) {
				if (startDay < 10) {
					dateList.add(sta + "0" + startDay);
				} else {
					dateList.add(sta + startDay);
				}
			}
			dateList.add(lastEndTime);

		}
		return dateList;
	}

	// ----------------------------------------------互查范围-------------------------------------------

	public String getDay() {

		try {
			if (dateDay != null && !"".equals(dateDay)) {
				DayList = this.customerService.getDay(dateDay);
			}
		} catch (Exception e) {
			logger.error("CustomerAction.getDay", e);
			return ERROR;
		}
		return "getDay";
	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @return
	 */
	public String checkCusName() {
		try {

		} catch (Exception e) {
			logger.error("CustomerAction.checkCusName", e);
		}
		return null;
	}

	/**
	 * 初始化添加页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initAddCustomer() {
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateCustomer() {
		try {
			if (customer != null) {
				customer = customerService.getCustomerById(customer.getCusId());
			}
		} catch (Exception e) {
			logger.error("CustomerAction.initUpdateCustomer", e);
			return ERROR;
		}
		return "updatePage";
	}

	/**
	 * 跳转输入
	 * 
	 * @return Author:Yangning CreateDate:2011-12-7
	 */
	public String importCustomer() {
		try {
			subjectList = subjectService.getAllSubject();
		} catch (Exception e) {
			logger.error("CustomerAction.importCustomer", e);
			return ERROR;
		}
		return "importCustomer";
	}

	/**
	 * 功能:批量删除用户操作,action
	 * 
	 * @return Author:Yangning CreateDate:2011-12-5
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delCusBatch() {
		String oriContent = (this.servletRequest
				.getParameter("customerContent"));
		if (oriContent != null) {
			oriContent = oriContent.trim();
		} else {
			message = "输入数据不合法";
			return "invalid";
		}
		try {
			String emails = oriContent.replaceAll("\n", ",");
			String sqlString = extractEmail(emails);
			List<Map> existEmail = customerService
					.getUserEmailByStrings(sqlString);
			if (existEmail != null && existEmail.size() > 0) {
				// --生成in()格式为del sql语句准备 example 4566666,45666--//
				StringBuffer sb = new StringBuffer();
				for (Map map : existEmail) {
					sb.append(map.get("CUSID"));
					sb.append(",");
				}
				// --通过邮箱得到用户cusId--//
				sb.delete(sb.length() - 1, sb.length());
				// --通过cusIds批量删除--//
				Map<String, Integer> mapDeled = customerService
						.delCustomerBath(sb.toString());
				setResult(new Result(true, "success", null, mapDeled));
				return "json";
			} else {
				setResult(new Result(true, "delnone", null, null));
				return "json";
			}
		} catch (Exception e) {
			logger.error("CustomerAction.delCusBach", e);
			return ERROR;
		}

	}

	/**
	 * 内部学员批量导入处理 Yangning 2011/12/02
	 * 
	 * @return String
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String importProcess() {
		logger.info("importProcess");
		// 防止多人操作,并行冲突
		if (lockOper == true) {
			message = "....有人操作中，请待心等待.....";
			setResult(new Result(false, "invalid", null, message));
			return "json";
		}
		String oriContent = (this.servletRequest
				.getParameter("customerContent"));
		Integer custype = Integer.parseInt((this.servletRequest
				.getParameter("cusType")));
		Integer giveway = Integer.parseInt((this.servletRequest
				.getParameter("raBatch")));
		Integer cusSubjectId = Integer.parseInt((this.servletRequest
				.getParameter("subjectId")));
		//Yangning add 2012/04/08 生成用户时同时生成体验账户临时表
		Boolean isTemp = Boolean.parseBoolean((this.servletRequest
				.getParameter("isTemp")));
		if (oriContent != null) {
			oriContent = oriContent.trim();
		} else {
			message = "输入非法";
			setResult(new Result(false, "error", null, message));
			lockOper = false;
			return "json";
		}
		try {
			lockOper = true;
			List<Map> list = content2ListMap(oriContent);
			// 检测所有授卖方式是否存在
			if (!checkSellWays(list)) {
				message = "输入错误，售卖方式不存在";
				setResult(new Result(true, "invalid", null, message));
				lockOper = false;
				return "json";
			}
			if (!checkEmails(list)) {
				message = "输入格式不正确,邮箱重复";
				setResult(new Result(true, "invalid", null, message));
				lockOper = false;
				return "json";
			}
			// --提取用户email 拼接where条件语句 --//
			String emails = extractEmail(list);
			if (emails != null && emails.length() > 0) {
				// --根据邮箱查询数据库，得到存在map 格式email,cusid--//
				List<Map> existListMap = customerService
						.getUserEmailByStrings(emails);
				if (existListMap != null && existListMap.size() > 0) {
					// --根据存在List,与原始List,分离存在用户与非存在用户--//
					Map<String, List> splitedMap = spliteListMap(existListMap,
							list);
					List<Map> fmtExistList = splitedMap.get("exist");
					List<Map> notExistList = splitedMap.get("notExist");
					int result = 0;
					// --不存在用户建立用户，赠送课程--//
					if (notExistList != null && notExistList.size() > 0) {
						result = customerService.addCustomerNotExistBath(
								notExistList, custype, cusSubjectId,isTemp);
					}
					// --存在用户直接赠送课程--//
					if (fmtExistList != null && fmtExistList.size() > 0) {
						if (giveway == 0) {
							// --完全赠送 删除以前商品记录--//
							result += customerService
									.addCustomerExistBathComplete(fmtExistList);
						} else {
							// --部分赠送 直接赠送--//
							result += customerService
									.addCustomerExistBathPart(fmtExistList);
						}
					}
					setResult(new Result(true, "success", null, result));
					return "json";
				} else {
					// --不存在用户建立用户，赠送课程--//
					int result = customerService.addCustomerNotExistBath(list,
							custype, cusSubjectId,isTemp);
					setResult(new Result(true, "success", null, result));
					lockOper = false;
					return "json";
				}
			} else {
				message = "输入非法";
				setResult(new Result(false, "invalid", null, message));
				lockOper = false;
				return "json";
			}
		} catch (Exception e) {
			logger.error("CustomerAction.importProcess", e);
			return ERROR;
		} finally {
			lockOper = false;
		}
	}

	/**
	 * 功能:检测当前提交授卖方式是否存在
	 * 
	 * @return Author:Yangning CreateDate:2011-12-15
	 */
	@SuppressWarnings("rawtypes")
	private Boolean checkSellWays(List<Map> list) {
		try {
			List<String> allSellWays = new ArrayList<String>();
			Map<String, String> intMap = new HashMap<String, String>();
			// 查找所提交的sellWays
			for (Map map : list) {
				String sellways = (String) map.get("sellways");
				if (sellways != null && sellways.length() > 0) {
					String[] strSellWays = sellways.split(",");
					for (String string : strSellWays) {
						allSellWays.add(string);
					}
				}
			}
			// 将sellwaysID放入map,消除重复SellWayID
			for (int i = 0; i < allSellWays.size(); i++) {
				intMap.put(allSellWays.get(i), allSellWays.get(i));
			}

			Iterator iter = intMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String sellId = (String) entry.getValue();
				SellWay sellway = sellWayService.getSellWayById(Integer
						.parseInt(sellId));
				if (sellway == null) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 功能:判断Emai是否重复
	 * 
	 * @return Author:Yangning CreateDate:2011-12-15
	 */
	@SuppressWarnings("rawtypes")
	private Boolean checkEmails(List<Map> list) {
		try {
			// 判断Email是否重复
			for (int i = 0; i < list.size(); i++) {
				Map emailMap = list.get(i);
				String email = (String) emailMap.get("email");
				for (int j = i + 1; j < list.size(); j++) {
					Map tmpMap = list.get(j);
					String tmpEmail = (String) tmpMap.get("email");
					if (email.equals(tmpEmail)) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 内部学员批量导入处理
	 * 
	 * @return String
	 * @thorows Exception public String importProcess() { try { //
	 *          用时间来生成订单号，加上用户Id // Long cId=date.getTime(); // String
	 *          contractId=cId.toString();
	 * 
	 *          String customerContent = ""; String customerEmail = ""; //
	 *          raBatch，默认的是批量添加，0，批量禁止，1，批量删除，2 if (raBatch == 2) {
	 *          customerEmail = this.servletRequest
	 *          .getParameter("customerContent");
	 *          this.delBatchCustomer(customerEmail); this.delSucess = "删除成功!";
	 *          } else { customerContent = this.servletRequest
	 *          .getParameter("customerContent"); } String[] sellId = null; if
	 *          (customerContent.trim() != null &&
	 *          !"".equals(customerContent.trim())) {
	 * 
	 *          // 处理字符串判断是已存在用户还是新用户，存到相应的list中
	 *          this.getCustomerContent(customerContent.trim());
	 * 
	 *          // 对这个list进行整体添加,返回整型的list 存放的cusId if (customerList.size() !=
	 *          0) { cusList = this.customerService
	 *          .addBathCustomer(customerList); for (int b = 0; cusList != null
	 *          && b < cusList.size(); b++) { // 返回前台成功的提示list Customer newCus =
	 *          this.customerService .getCustomerById(cusList.get(b));
	 *          newCusList.add(newCus); } } // 把已存在的customer，取出id，添加到cusList中。
	 *          if (existCusList.size() != 0) { for (int a = 0; a <
	 *          existCusList.size(); a++) {
	 *          cusList.add(existCusList.get(a).getCusId()); } } for (int m = 0;
	 *          cusList != null && m < cusList.size(); m++) { Date date = new
	 *          Date(); cusId = cusList.get(m); int ctId =
	 *          this.getContract(date, customer.getCusType()); // 课程ID列表 sellId
	 *          = cus[3].split(","); for (int j = 0; j < sellId.length; j++) {
	 *          this.getCashRecord(new Integer(sellId[j]), date,
	 *          customer.getCusType(), ctId); // 增加一个packID // 售卖方式ID } } } }
	 *          catch (Exception e) { e.printStackTrace(); return ERROR; }
	 *          return "importCustomer"; }
	 */
	public int getContract(Date date, int cusType) {
		Long cId = Long.valueOf(date.getTime());
		Contract contract = new Contract();
		contract.setContractId(this.cusId + cId.toString());
		contract.setCusId(this.cusId);
		contract.setCreateTime(date);
		contract.setContractFrom("后台用户");
		contract.setPayType(0);
		if ((cusType == 1) || (cusType == 3))
			contract.setStatus(2);
		else
			contract.setStatus(4);

		contract.setContractCutSumMoney(Double.valueOf(0D));
		contract.setContractSumMoney(Double.valueOf(0D));
		contract.setCouponMoney(Double.valueOf(0D));
		contract.setUseCoupon(0);
		int ctId = this.contractService.addContract(contract).intValue();
		return ctId;
	}

	public void getCashRecord(int sellId, Date date, int cusType, int ctId) {
		CashRecord cashRecord = null;

		Long cId = Long.valueOf(date.getTime());
		getQuerySellCouCondition().setSellId(sellId);
		SellWay sellWay = this.sellWayService.getSellWayById(sellId);

		cashRecord = new CashRecord();
		cashRecord.setPackId(sellId);
		cashRecord.setStatus(1);
		cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》");
		cashRecord.setPrice(Float.valueOf(sellWay.getSellPrice()));
		cashRecord.setContractId(this.cusId + cId.toString());
		cashRecord.setCreateTime(date);
		cashRecord.setCusId(this.cusId);
		cashRecord.setCourseId(0);
		if ((cusType == 1) || (cusType == 3))
			cashRecord.setType(2);
		else
			cashRecord.setType(3);

		cashRecord.setCtId(ctId);
		this.cashRecordService.addCashRecord(cashRecord);
	}

	public void delBatchCustomer(String email) {
		String[] newEmail = (String[]) null;
		if ((email.trim() != null) && (!("".equals(email.trim())))) {
			newEmail = email.split("\r\n");
			for (int i = 0; i < newEmail.length; ++i) {
				Customer customer = this.customerService
						.getCustomerByEmail(newEmail[i]);
				if (customer != null) {
					int cusId = customer.getCusId();

					this.notesService.delNotesByCusId(cusId);

					this.cashRecordService.delCashRecordByCusId(cusId);

					this.contractService.delContractByCusId(cusId);

					this.cpCusService.delCpCusByCusId(cusId);

					this.studyPlanService.delStudyPlanByCusId(cusId);

					this.exampaperRecordService.delERecordByCusId(cusId);

					this.optRecordService.delOptRecordByCusId(cusId);

					this.tsRecordService.delTsRecordByCusId(cusId);

					this.totolsScoreService.delTotolsScoreByCusId(cusId);

					this.loginRecordService.delLoginRecordByCusId(cusId);

					this.taskCusService.deleteTaskCusByCusId(cusId);

					QueryProblemCondition queryProblemCondition = new QueryProblemCondition();
					queryProblemCondition.setCusId(cusId);
					List problemList = this.problemService
							.getProblemList(queryProblemCondition);
					for (int j = 0; (problemList != null)
							&& (j < problemList.size()); ++j) {
						for (int n = 0; (((Problem) problemList.get(j))
								.getReProblemList() != null)
								&& (n < ((Problem) problemList.get(j))
										.getReProblemList().size()); ++n) {
							this.reProblemService
									.delReProblemById(((ReProblem) ((Problem) problemList
											.get(j)).getReProblemList().get(n))
											.getReId());
						}

						this.problemService
								.delProblemById(((Problem) problemList.get(j))
										.getPblId());
					}

					QueryReProblemCondition queryReProblemCondition = new QueryReProblemCondition();
					queryReProblemCondition.setReManId(cusId);
					List reProblemList = this.reProblemService
							.getReProblemList(queryReProblemCondition);
					for (int k = 0; (reProblemList != null)
							&& (k < reProblemList.size()); ++k) {
						this.reProblemService
								.delReProblemById(((ReProblem) reProblemList
										.get(k)).getReId());
					}

					this.customerService.delCustomerById(cusId);
				}
			}
		}
	}

	public void getCustomerContent(String customerContent) {
		String[] cusCon = (String[]) null;

		cusCon = customerContent.split("\r\n");
		Customer newCustomer = null;
		for (int i = 0; i < cusCon.length; ++i) {
			newCustomer = new Customer();
			this.cus = cusCon[i].split("\t");
			String email = this.cus[0];
			String cusPwd = this.cus[1];
			String realName = this.cus[2];
			Customer cusCustomer = this.customerService
					.getCustomerByEmail(email);
			if (cusCustomer == null) {
				newCustomer.setEmail(email);
				newCustomer.setCusPwd(MD5.getMD5(cusPwd));
				newCustomer.setRealName(realName);
				newCustomer.setCusName(realName);
				newCustomer.setCusType(this.customer.getCusType());

				newCustomer.setLoginTimes(1);
				newCustomer.setSex("1");
				newCustomer.setIsComplete(Customer.CUS_COMPLETE_FALSE);

				newCustomer.setSubjectId(3);

				this.customerList.add(newCustomer);
			} else {
				this.existCusList.add(cusCustomer);
			}
		}
	}

	/**
	 * 将页面提交数据转化为ListMap对象进行处理
	 * 
	 * @param customerContent
	 * @return map对象 邮箱，用户密码，用户密码,赠送课程 Author:Yangning CreateDate:2011-12-2
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> content2ListMap(String content) {
		List<Map> list = new ArrayList<Map>();
		String[] info = content.split("\n");
		for (String string : info) {
			String[] record = string.split("\t");
			Map map = new HashMap<String, String>();
			map.put("email", record[0]);
			map.put("password", record[1]);
			map.put("realname", record[2]);
			map.put("sellways", record[3]);
			list.add(map);
		}
		return list;
	}

	/**
	 * Yangning 2011/12/02 提取ListMap对象中的email，准备where条件语句
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String extractEmail(List<Map> list) {
		StringBuffer sb = new StringBuffer();
		for (Map map : list) {
			sb.append("'");
			sb.append(map.get("email"));
			sb.append("'");
			sb.append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * Yangning 2011/12/02 提取ListMap对象中的email，准备where条件语句
	 * 
	 * @param list
	 * @return
	 */
	private String extractEmail(String emails) {
		String[] strArray = emails.split(",");
		StringBuffer sb = new StringBuffer();
		for (String string : strArray) {
			sb.append("'");
			sb.append(string);
			sb.append("'");
			sb.append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * 功能:分离数据，得到存在与非存在用户,并格式化存在用户
	 * 
	 * @param list
	 *            ,oriList
	 * @return Map 格式 map.get("exist")存在，map.get("notExist")不存在
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, List> spliteListMap(List<Map> existList,
			List<Map> oriList) {
		Map map = new HashMap<String, List>();
		List<Map> fExistList = null;
		if (existList != null && existList.size() > 0) {
			fExistList = new ArrayList<Map>();
			for (int i = 0; i < existList.size(); i++) {
				Map existMap = existList.get(i);
				for (int j = 0; j < oriList.size(); j++) {
					Map oriMap = oriList.get(j);
					if (oriMap.get("email").equals(existMap.get("EMAIL"))) {
						existMap.get("EMAIL");
						existMap.put("password", oriMap.get("password"));
						existMap.put("sellways", oriMap.get("sellways"));
						existMap.put("realname", oriMap.get("realname"));
						fExistList.add(existMap);
						oriList.remove(j);
						break;
					}
				}
			}
		}
		map.put("exist", fExistList);
		map.put("notExist", oriList);
		return map;
	}

	/**
	 * 查看用户信息
	 * 
	 * @return
	 */
	public String viewCus() {
		try {

			customer = customerService.getCustomerByView(customer.getCusId());
			addressList = customerService.GetAddrByCusId(customer.getCusId());
			List<LoginRecord> loR = customer.getLoginRecordList();
			for (LoginRecord l : loR) {
				if (l.getAddress() == null || "".equals(l.getAddress())) {
					IPUtil ip = new IPUtil();
					String newAddress = ip.getAddressByIP(l.getLoginIp());
					if (newAddress == null || "".equals(newAddress)
							|| newAddress.length() > 50) {
						l.setAddress("未知地区");
					} else {
						l.setAddress(newAddress);
					}
					loginRecordService.updateLoginRecord(l);
				}
			}
		} catch (Exception e) {
			logger.error("CustomerAction.viewCus", e);
			return ERROR;
		}
		return "viewCus";
	}

	/**
	 * 初始化修改密码页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdatePwd() {
		try {
			if(customer.getCusId()!=0){
				customer = customerService.getCustomerById(customer.getCusId());
			}
		} catch (Exception e) {
			logger.error("CustomerAction.initUpdatePwd", e);
			return ERROR;
		}
		return "updatePwd";
	}

	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
	}
	
	public String toUpdateSmsLimit(){
			try {
				//根据文件路径找到文件对象
				File file=new File(ServletActionContext.getServletContext().getRealPath("/back/smslimit.txt"));
				//获取文件的输入流
				java.io.FileInputStream fi=new java.io.FileInputStream(file);
				//文件对应长度的字节数组
				byte[] b=new byte[(int)file.length()];
				//将文件内容放到b[]里
				fi.read(b);
				//转换成字符串
				smsLimit=new String(b);
				return"smsLimit";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("CustomerAction.toUpdateSmsLimit", e);
				return ERROR;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("CustomerAction.toUpdateSmsLimit", e);
				return ERROR;
			}
	}
	
	
	public String updateSmsLimit(){
			try {
				
				java.io.FileOutputStream fs = new java.io.FileOutputStream(ServletActionContext.getServletContext().getRealPath("/back/smslimit.txt"));
				byte[] aa=smsLimit.getBytes();
				for (int i=0;i<aa.length;i++){
					//写入数据
					fs.write(aa[i]);
					}
				//强制写出输出流中所有字节
				fs.flush();
				//关闭流
				fs.close();
				this.setResult(new Result(true,null,null,null));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				this.setResult(new Result(false,null,null,null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setResult(new Result(false,null,null,null));
			}finally{
				return "json";
			}
	}

	/**
	 * 查看所有 return String; Liming
	 */
	public String sendNoteContentList() {
		try {
			this.getQueryNoteContentCondition().setPageSize(20);
			if(this.getQueryNoteContentCondition().getNoteAddTime()==null||this.getQueryNoteContentCondition().getEndTime()==null||this.getQueryNoteContentCondition().getNoteAddTime()==""||this.getQueryNoteContentCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryNoteContentCondition().setNoteAddTime((sdf.format(new Date())+" 00:00:00"));
				this.getQueryNoteContentCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
				}
			setPage(this.customerService.getNoteContentList(this
					.getQueryNoteContentCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception e) {
			logger.error("CustomerAction.sendNoteContentList", e);
			return ERROR;
		}
		return "sendNoteContentList";
	}

	/**
	 * 多条件查询短信内容 return String; Liming
	 */
	public String sendNoteContentListWhere() {
		try {

			this.getQueryNoteContentCondition().setPageSize(20);
			setPage(this.customerService.sendNoteContentListWhere(this
					.getQueryNoteContentCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception e) {
			logger.error("CustomerAction.sendNoteContentListWhere", e);
			return ERROR;
		}
		return "sendNoteContentList";
	}

	/**
	 * 根据项目ID查询售卖方式； return json Liming
	 */
	public String getSinggleSellListJson() {
		try {
			int subjectId = Integer.parseInt(servletRequest
					.getParameter("subjectId"));
			List<NoteContentDTO> NoteContentDTOSellList = this.customerService
					.showSinggleSellListJson(subjectId);
			setResult(new Result(false, null, null, NoteContentDTOSellList));
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("CustomerAction.getSinggleSellListJson", e);
			setResult(new Result(false, "error", null, null));
		}
		return "json";
	}

	/**
	 * 给制定用户通过账户手机号 发送指定短信内容sendinfo
	 * 
	 * @return
	 */
	public String sendSMSForCus() {
		try {
			String aa = sendLinks.replaceAll("\r\n", "");
			String[] str = aa.split(",");// 定义一个数组
			List list = new ArrayList();// new一个arralist
			Set set = new HashSet();// new 一个hashset
			set.addAll(java.util.Arrays.asList(str));// 将数组转为list并存入set中，就可以去掉重复项了
			for (java.util.Iterator it = set.iterator(); it.hasNext();) {
				list.add(it.next());// 遍历set 将所有元素键入list中
			}
			java.util.Collections.sort(list); // 对list进行快速排序

			String wuchongfu = list.toString();
			wuchongfu = wuchongfu.replace("[", "");
			wuchongfu = wuchongfu.replace("]", "");
			wuchongfu = wuchongfu.trim();
			String sl[] = wuchongfu.split(",");

			StringBuffer rltMsg = new StringBuffer();
			for (int i = 0; i < sl.length; i++) {
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(sl[i].toString().trim());
				if (!isNum.matches()) {
					rltMsg.append("<font color='red'>"
							+ sl[i].replaceAll("\r\n", "") + "发送失败!</font><br>");
				} else {

					if (sl[i].trim().length() == 11) {
						Thread.sleep(100);
						SendExResp sp = smsService.sendEx(sendInfo.trim(),
								sl[i].toString().trim(), "", "", "");
						rltMsg.append("<font color='green'>" + sl[i]
								+ "发送成功!</font><br>");
					} else {
						rltMsg.append("<font color='red'>"
								+ sl[i].replaceAll("\r\n", "")
								+ "发送失败!</font><br>");
					}

				}

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			this.addActionMessage(rltMsg.toString());
			
		} catch (Exception e) {
			logger.error("CustomerAction.sendSMSForCus", e);
			return ERROR;
		}
		return "toSendSMSForCus";
	}

	/**
	 * 给制定用户通过账户手机号 发送指定短信内容sendinfo Liming
	 * 
	 * @return String
	 */
	public Object call() throws Exception {
		try {
			{
				// 手机号sendLinks
				// 编号：checkIdSum
				String aa = sendLinks.replaceAll("\r\n", "");

				String[] str = aa.split(",");// 定义一个数组
				List list = new ArrayList();// new一个arralist
				Set set = new HashSet();// new 一个hashset
				set.addAll(java.util.Arrays.asList(str));// 将数组转为list并存入set中，就可以去掉重复项了
				for (java.util.Iterator it = set.iterator(); it.hasNext();) {
					list.add(it.next());// 遍历set 将所有元素键入list中
				}
				String wuchongfu = list.toString();
				wuchongfu = wuchongfu.replace("[", "");
				wuchongfu = wuchongfu.replace("]", "");
				wuchongfu = wuchongfu.trim();
				// 手机号 s1
				String sl[] = wuchongfu.split(",");

				StringBuffer rltMsg = new StringBuffer();
				String bb = checkIdSum.replaceAll("\r\n", "");
				String[] strr = bb.split(",");// 定义一个数组
				List listIdSUm = new ArrayList();
				Set sett = new HashSet();// new 一个hashset
				sett.addAll(java.util.Arrays.asList(strr));// 将数组转为list并存入set中，就可以去掉重复项了
				for (java.util.Iterator itt = sett.iterator(); itt.hasNext();) {
					listIdSUm.add(itt.next());// 遍历set 将所有元素键入list中
				}
				java.util.Collections.sort(listIdSUm); // 对list进行快速排序
				String wuchongfuu = listIdSUm.toString();
				wuchongfuu = wuchongfuu.replace("[", "");
				wuchongfuu = wuchongfuu.replace("]", "");
				wuchongfuu = wuchongfuu.trim();
				String sll[] = wuchongfuu.split(",");
				int slll = sll.length;
				User user = this.getSession("sedu_user");
				noteContent = new NoteContent();
				Date date = new Date();
				noteContent.setNoteAddTime(date);
				noteContent.setNoteContent(sendInfo);
				noteContent.setStatus(1);
				noteContent.setNoteDespatcher(user.getUserName());
				noteContent.setUserId(user.getUserId());
				int zhuangtai = 1;
				int zhuangtai1 = 10;
				for (int i = 0; i < sl.length; i++) {
					if (!sl[i].trim().matches("^1[0-9]{10}$")
							&& !"0".equals(sl[i].trim())) {
						zhuangtai = 2;
						break;
					}
				}
				String fanhui = "";
				int noteIdd = 0;
				if (zhuangtai != 2) {
					if (Integer.parseInt(discern) == -1) {
						noteIdd = this.customerService
								.addNoteContent(noteContent);
						discern = noteIdd + "";
					} else if (Integer.parseInt(allAndsingleStatus) == 5
							&& Integer.parseInt(discern) != -1) {
						noteContentDTO = this.customerService
								.getDiscernEntity(Integer.parseInt(discern));
						noteIdd = noteContentDTO.getNoteId();
						discern = noteContentDTO.getNoteId() + "";
					}
					cellPhone = new CellPhone();
					cellPhone.setSellAddTime(new Date());
					cellPhone.setNoteContentId(noteIdd);
					cellPhone.setSellStatus(1);
					if ("".equals(sll[0])
							|| Integer.parseInt(sll[0].trim()) == 0) {
						for (int f = 0; f < sl.length; f++) {
							if (sl[f].trim().equals("0")) {
								cellPhone.setSellPhone("0");
								cellPhone.setCellEmail("无");
								this.customerService
										.addNoteCellPhone(cellPhone);
							} else {
								getQueryCustomerCondition().setMobile(
										sl[f].trim());
								List<Customer> existCusList = this.customerService
										.getCustomerEmail(getQueryCustomerCondition());
								cellPhone = new CellPhone();
								cellPhone.setSellAddTime(new Date());
								if (existCusList.size() == 0) {
									cellPhone.setSellPhone(sl[f].trim());
									cellPhone.setCellEmail("无");
									cellPhone.setNoteContentId(noteIdd);
									cellPhone.setSellStatus(1);
									this.customerService
											.addNoteCellPhone(cellPhone);
								} else {
									for (int h = 0; h < existCusList.size(); h++) {
										customer = existCusList.get(h);
										cellPhone.setNoteContentId(noteIdd);
										cellPhone.setSellPhone(customer
												.getMobile());
										cellPhone.setCellEmail(customer
												.getEmail());
										this.customerService
												.addNoteCellPhone(cellPhone);
									}
								}
							}

						}
						this.sendSMS(sl, 0, sendInfo.trim());
						fanhui = "你编辑的短信已经发完!";
						zhuangtai1 = 13;
					} else {
						for (int j = 0; j < sll.length; j++) {
							Customer customer = this.customerService
									.getCustomerByView(Integer.parseInt(sll[j]
											.trim()));
							cellPhone.setCellEmail(customer.getEmail());
							if ("".equals(customer.getMobile())
									|| customer.getMobile() == ""
									|| customer.getMobile() == null) {
								cellPhone.setSellPhone("0");
							} else {
								cellPhone.setSellPhone(customer.getMobile());
							}
							this.customerService.addNoteCellPhone(cellPhone);
						}
						this.sendSMS(sl, 0, sendInfo.trim());
						zhuangtai1 = 12;
						fanhui = slll + "条全部已经发完!";
					}
				} else {
					zhuangtai1 = 11;
					fanhui = "所发的手机号码不符合要求不能进行发送!";
				}
				noteContentDTO = new NoteContentDTO();
				noteContentDTO.setFanhui(fanhui);
				noteContentDTO.setZhuangtai(zhuangtai1);
				noteContentDTO.setSlll(slll);
				noteContentDTO.setAllAndsingleStatus(allAndsingleStatus);
				noteContentDTO.setDiscern(discern);
				this.setResult(new Result(true, "", fanhui, noteContentDTO));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "json";

	}

	public String sendSMSForCusAll() {
		try {
			return String.valueOf(this.call());
		} catch (Exception e) {
			logger.error("CustomerAction.call", e);
			return "error";
		}
	}

	/**
	 * 总的发短信
	 * 
	 * @return String; name Liming
	 */
	public void sendSMS(String tel[], int num, String content)
			throws InterruptedException {
		for (int i = num; i < tel.length; i++) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(tel[i].toString().trim());
			if (!isNum.matches()) {
				logger.info(tel[i] + "发送失败!");
			} else {
				if (tel[i].trim().length() == 11) {
					try {
						Thread.sleep(100);
						smsService.sendEx(content, tel[i].toString().trim(),
								"", "", "");
						logger.info("第" + i + "个电话号码为" + tel[i]);
					} catch (Exception e) {
						logger.info("第" + i + "个错误电话号码为" + tel[i]
								+ e.toString());
						this.sendSMS(tel, i, sendInfo.trim());
						Thread.sleep(500);
						break;
					}
				}
			}
		}
	}

	/**
	 * 延迟方法
	 * 
	 * @return joson; name Liming
	 */
	public String sendSMSDingshiForCus() {
		try {
			this.setResult(new Result(true, "", "", ""));
		} catch (Exception e) {
			logger.error("CustomerAction.sendSMSDingshiForCus", e);
			this.setResult(new Result(false, "error", "", ""));
		}
		return "json";
	}

	/**
	 * 查看所有的短信接收者
	 * 
	 * @return String; name Liming
	 */
	public String serchDisplayInfoAll() {
		try {
			cellPhoneList = this.customerService.getListCellPhone(this
					.getQueryNoteContentCondition());
			cellPhoneListCrabs = this.customerService
					.getListCellPhoneCrabs(this.getQueryNoteContentCondition());
		} catch (Exception e) {
			logger.error("CustomerAction.serchDisplayInfoAll", e);
			return ERROR;
		}

		return "serchDisplayInfoAll";
	}

	/**
	 * 查看短息接受者；
	 * 
	 * @param String
	 *            Liming
	 */
	public String serchContentInfo() {
		try {
			noteContent = this.customerService.getSingleContent(this
					.getQueryNoteContentCondition().getNoteId());
			this.getQueryNoteContentCondition().setNoteId(
					noteContent.getNoteId());
			cellPhoneSingleCout = this.customerService
					.getListCellPhoneCout(this.getQueryNoteContentCondition()
							.getNoteId());
			cellPhoneList = this.customerService.getListCellPhone(this
					.getQueryNoteContentCondition());
			cellPhoneListCrabs = this.customerService
					.getListCellPhoneCrabs(this.getQueryNoteContentCondition());
		} catch (Exception e) {
			logger.error("CustomerAction.serchContentInfo", e);
			return ERROR;
		}
		return "getSerchContentInfo";
	}

	/**
	 * 分页查询查询手机号
	 * 
	 * @return String
	 * @thorows Exception Liming
	 */
	public String showCustomerCellphoneListJson() {
		try {
			int sell = getQueryCustomerCondition().getSellId();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();

			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (cusType != null && !"".equals(cusType)) {
				getQueryCustomerCondition().setCusType(cusType.trim());
			}
			if (startTime != null && !"".equals(startTime)) {
				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerCellPhoneListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}

			List<Map> dataList = new ArrayList<Map>();
			for (int a = 0; a < getPage().getPageResult().size(); a++) {
				Map map = new HashMap();
				Customer cus = (Customer) getPage().getPageResult().get(a);
				map.put("email", cus.getEmail());
				map.put("mobile", cus.getMobile());
				map.put("cusId", cus.getCusId());
				dataList.add(map);
			}

			this.setResult(new Result(true, new Integer(getPage()
					.getTotalRecord()).toString(), new Integer(getPage()
					.getTotalPage()).toString(), dataList));

			return "json";
		} catch (Exception e) {
			logger.error("CustomerAction.showCustomerCellphoneListJson", e);
			return ERROR;
		}

	}

	/**
	 * 分页查询Json查询手机号1千条
	 * 
	 * @return String
	 * @thorows Exception Liming
	 */
	public String showCustomerListQianJson() {
		try {
			int sell = getQueryCustomerCondition().getSellId();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();

			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (cusType != null && !"".equals(cusType)) {
				getQueryCustomerCondition().setCusType(cusType.trim());
			}
			if (startTime != null && !"".equals(startTime)) {
				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			this.getQueryCustomerCondition().setPageSize(1000);
			setPage(customerService
					.getCustomerCellPhoneListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(1000);
			}

			List<Map> dataList = new ArrayList<Map>();
			for (int a = 0; a < getPage().getPageResult().size(); a++) {
				Map map = new HashMap();
				Customer cus = (Customer) getPage().getPageResult().get(a);
				map.put("email", cus.getEmail());
				map.put("mobile", cus.getMobile());
				map.put("cusId", cus.getCusId());
				dataList.add(map);
			}

			this.setResult(new Result(true, new Integer(getPage()
					.getTotalRecord()).toString(), new Integer(getPage()
					.getTotalPage()).toString(), dataList));

			return "json";
		} catch (Exception e) {
			logger.error("CustomerAction.showCustomerListQianJson", e);
			return ERROR;
		}

	}

	public String toSendSMSForCus() {
		try {
			//根据文件路径找到文件对象
			File file=new File(ServletActionContext.getServletContext().getRealPath("/back/smslimit.txt"));
			//获取文件的输入流
			java.io.FileInputStream fi=new java.io.FileInputStream(file);
			//文件对应长度的字节数组
			byte[] b=new byte[(int)file.length()];
			//将文件内容放到b[]里
			fi.read(b);
			//转换成字符串
			smsLimit=new String(b);
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			
			/** 判断是否是销售人员，是的话查出当天已发送短信数量 王超 */
			User user=this.getLoginedUser();
			roleId=user.getRoleList().get(0).getRoleId();
			if(roleId==73){
				sendSmsCount=this.getCustomerService().getTodaySendCountByUserId(user.getUserId());
			}
			
			/** 判断是否是销售人员，是的话查出当天已发送短信数量 王超 */
			
		} catch (Exception e) {
			logger.error("CustomerAction.toSendSMSForCus", e);
		}
		return "toSendSMSForCus";
	}

	/**
	 * 获取学员登录统计
	 * @return
	 */
	public String getCusLoginCount(){
		try{
			subjectList = subjectService.getAllSubject();
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateFmt.format(new Date());
			if(queryCustomerCondition==null){
				queryCustomerCondition=new QueryCustomerCondition();
				queryCustomerCondition.setStartTime(time);
				queryCustomerCondition.setEndTime(time+" 23:59:59");
			}else{
				if(queryCustomerCondition.getStartTime()==null||queryCustomerCondition.getStartTime().equals("")){
					queryCustomerCondition.setStartTime(time);
				}
				if(queryCustomerCondition.getEndTime()==null||queryCustomerCondition.getEndTime().equals("")){
					queryCustomerCondition.setEndTime(time+" 23:59:59");
				}else{
					queryCustomerCondition.setEndTime(queryCustomerCondition.getEndTime()+" 23:59:59");
				}
			}
			Map map=new HashMap();
			map.put("begin", queryCustomerCondition.getStartTime());
			map.put("end", queryCustomerCondition.getEndTime());
           cusLoginCountList = customerService.getCusLoginCount(map);
           cusLoginCountSum=customerService.getCusLoginSum(map);
		}catch(Exception e){
			logger.error("获取学员登录统计异常"+e.toString());
		}
		return "cusLoginCount";
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

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setMailService(IMail mailService) {
		this.mailService = mailService;
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

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
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

	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}

	public List<Customer> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Customer> monthList) {
		this.monthList = monthList;
	}

	public List<Customer> getMonthDayList() {
		return monthDayList;
	}

	public void setMonthDayList(List<Customer> monthDayList) {
		this.monthDayList = monthDayList;
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

	public String getGiveAway() {
		return giveAway;
	}

	public void setGiveAway(String giveAway) {
		this.giveAway = giveAway;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public QueryKpointCondition getQueryKpointCondition() {
		if (queryKpointCondition == null) {
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(
			QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
	}

	public List<Customer> getNewCusList() {
		return newCusList;
	}

	public void setNewCusList(List<Customer> newCusList) {
		this.newCusList = newCusList;
	}

	public List<Customer> getExistCusList() {
		return existCusList;
	}

	public void setExistCusList(List<Customer> existCusList) {
		this.existCusList = existCusList;
	}

	public List<Integer> getCusList() {
		return cusList;
	}

	public void setCusList(List<Integer> cusList) {
		this.cusList = cusList;
	}

	public int getNewCusId() {
		return newCusId;
	}

	public void setNewCusId(int newCusId) {
		this.newCusId = newCusId;
	}

	public String[] getCus() {
		return cus;
	}

	public void setCus(String[] cus) {
		this.cus = cus;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getRaBatch() {
		return raBatch;
	}

	public void setRaBatch(int raBatch) {
		this.raBatch = raBatch;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}

	public IStudyPlan getStudyPlanService() {
		return studyPlanService;
	}

	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}

	public IOptRecord getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(
			IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public IMail getMailService() {
		return mailService;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}

	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}

	public String getDelSucess() {
		return delSucess;
	}

	public void setDelSucess(String delSucess) {
		this.delSucess = delSucess;
	}

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public List<Customer> getDayList() {
		return DayList;
	}

	public void setDayList(List<Customer> dayList) {
		DayList = dayList;
	}

	public String getSendLinks() {
		return sendLinks;
	}

	public void setSendLinks(String sendLinks) {
		this.sendLinks = sendLinks;
	}

	public String getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
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

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(int dateLocation) {
		this.dateLocation = dateLocation;
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

	public CustomerDTO getHdfkCustomerDTO() {
		return hdfkCustomerDTO;
	}

	public void setHdfkCustomerDTO(CustomerDTO hdfkCustomerDTO) {
		this.hdfkCustomerDTO = hdfkCustomerDTO;
	}

	public CustomerDTO getZfbCustomerDTO() {
		return zfbCustomerDTO;
	}

	public void setZfbCustomerDTO(CustomerDTO zfbCustomerDTO) {
		this.zfbCustomerDTO = zfbCustomerDTO;
	}

	public QueryContractCondition getZfbPayedQueryContractCondition() {
		if (zfbPayedQueryContractCondition == null) {
			zfbPayedQueryContractCondition = new QueryContractCondition();
		}
		return zfbPayedQueryContractCondition;
	}

	public void setZfbPayedQueryContractCondition(
			QueryContractCondition zfbPayedQueryContractCondition) {
		this.zfbPayedQueryContractCondition = zfbPayedQueryContractCondition;
	}

	public QueryContractCondition getZfbQueryContractCondition() {
		if (zfbQueryContractCondition == null) {
			zfbQueryContractCondition = new QueryContractCondition();
		}
		return zfbQueryContractCondition;
	}

	public void setZfbQueryContractCondition(
			QueryContractCondition zfbQueryContractCondition) {
		this.zfbQueryContractCondition = zfbQueryContractCondition;
	}

	public QueryContractCondition getHdfkPayedQueryContractCondition() {
		if (hdfkPayedQueryContractCondition == null) {
			hdfkPayedQueryContractCondition = new QueryContractCondition();
		}
		return hdfkPayedQueryContractCondition;
	}

	public void setHdfkPayedQueryContractCondition(
			QueryContractCondition hdfkPayedQueryContractCondition) {
		this.hdfkPayedQueryContractCondition = hdfkPayedQueryContractCondition;
	}

	public QueryContractCondition getHdfkQueryContractCondition() {
		if (hdfkQueryContractCondition == null) {
			hdfkQueryContractCondition = new QueryContractCondition();
		}
		return hdfkQueryContractCondition;
	}

	public void setHdfkQueryContractCondition(
			QueryContractCondition hdfkQueryContractCondition) {
		this.hdfkQueryContractCondition = hdfkQueryContractCondition;
	}

	public List<SubjectCountsDTO> getSubjectCountsDTOList() {
		return subjectCountsDTOList;
	}

	public void setSubjectCountsDTOList(
			List<SubjectCountsDTO> subjectCountsDTOList) {
		this.subjectCountsDTOList = subjectCountsDTOList;
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public IReProblem getReProblemService() {
		return reProblemService;
	}

	public void setReProblemService(IReProblem reProblemService) {
		this.reProblemService = reProblemService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public QueryContractCondition getWyzxQueryContractCondition() {
		return wyzxQueryContractCondition;
	}

	public void setWyzxQueryContractCondition(
			QueryContractCondition wyzxQueryContractCondition) {
		this.wyzxQueryContractCondition = wyzxQueryContractCondition;
	}

	public QueryContractCondition getWyzxPayedQueryContractCondition() {
		return wyzxPayedQueryContractCondition;
	}

	public void setWyzxPayedQueryContractCondition(
			QueryContractCondition wyzxPayedQueryContractCondition) {
		this.wyzxPayedQueryContractCondition = wyzxPayedQueryContractCondition;
	}

	public int getFromURLType() {
		return fromURLType;
	}

	public void setFromURLType(int fromURLType) {
		this.fromURLType = fromURLType;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public QuerySellCouCondition getQuerySellCouCondition() {
		if (querySellCouCondition == null) {
			querySellCouCondition = new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(
			QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}

	public String getNewMD5Pwd() {
		return newMD5Pwd;
	}

	public void setNewMD5Pwd(String newMD5Pwd) {
		this.newMD5Pwd = newMD5Pwd;
	}

	public List<CustomerCountDTO> getCustomerCountDTOList() {
		return customerCountDTOList;
	}

	public void setCustomerCountDTOList(
			List<CustomerCountDTO> customerCountDTOList) {
		this.customerCountDTOList = customerCountDTOList;
	}

	public CustomerCountDTO getCustomerCountDTO() {
		if (this.customerCountDTO == null) {
			customerCountDTO = new CustomerCountDTO();
		}
		return customerCountDTO;
	}

	public void setCustomerCountDTO(CustomerCountDTO customerCountDTO) {
		this.customerCountDTO = customerCountDTO;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(
			QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public CustomerCountDTO getCustomerCountSum() {
		if (this.customerCountDTO == null) {
			customerCountSum = new CustomerCountDTO();
		}
		return customerCountSum;
	}

	public void setCustomerCountSum(CustomerCountDTO customerCountSum) {
		this.customerCountSum = customerCountSum;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public List<CustomerWithConSizeDTO> getCwcsList() {
		return cwcsList;
	}

	public void setCwcsList(List<CustomerWithConSizeDTO> cwcsList) {
		this.cwcsList = cwcsList;
	}

	public List<CashRecordDTO> getYFcashList() {
		return YFcashList;
	}

	public void setYFcashList(List<CashRecordDTO> fcashList) {
		YFcashList = fcashList;
	}

	public String getContractPr() {
		return contractPr;
	}

	public void setContractPr(String contractPr) {
		this.contractPr = contractPr;
	}

	public String getContractPayPr() {
		return contractPayPr;
	}

	public void setContractPayPr(String contractPayPr) {
		this.contractPayPr = contractPayPr;
	}

	public String getDatePr() {
		return datePr;
	}

	public void setDatePr(String datePr) {
		this.datePr = datePr;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getWebType() {
		return webType;
	}

	public void setWebType(int webType) {
		this.webType = webType;
	}

	public String getCheckIdSum() {
		return checkIdSum;
	}

	public void setCheckIdSum(String checkIdSum) {
		this.checkIdSum = checkIdSum;
	}

	public CellPhone getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(CellPhone cellPhone) {
		this.cellPhone = cellPhone;
	}

	public NoteContentDTO getNoteContentDTO() {
		return noteContentDTO;
	}

	public void setNoteContentDTO(NoteContentDTO noteContentDTO) {
		this.noteContentDTO = noteContentDTO;
	}

	public String getAllAndsingleStatus() {
		return allAndsingleStatus;
	}

	public void setAllAndsingleStatus(String allAndsingleStatus) {
		this.allAndsingleStatus = allAndsingleStatus;
	}

	public QueryNoteContentCondition getQueryNoteContentCondition() {
		return queryNoteContentCondition;
	}

	public void setQueryNoteContentCondition(
			QueryNoteContentCondition queryNoteContentCondition) {
		this.queryNoteContentCondition = queryNoteContentCondition;
	}

	public String getDiscern() {
		return discern;
	}

	public void setDiscern(String discern) {
		this.discern = discern;
	}

	public NoteContent getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(NoteContent noteContent) {
		this.noteContent = noteContent;
	}

	public List getCellPhoneListCrabs() {
		return cellPhoneListCrabs;
	}

	public void setCellPhoneListCrabs(List cellPhoneListCrabs) {
		this.cellPhoneListCrabs = cellPhoneListCrabs;
	}

	public List getCellPhoneList() {
		return cellPhoneList;
	}

	public void setCellPhoneList(List cellPhoneList) {
		this.cellPhoneList = cellPhoneList;
	}

	public int getCellPhoneSingleCout() {
		return cellPhoneSingleCout;
	}

	public void setCellPhoneSingleCout(int cellPhoneSingleCout) {
		this.cellPhoneSingleCout = cellPhoneSingleCout;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getBathSucMap() {
		return bathSucMap;
	}

	public void setBathSucMap(Map<String, String> bathSucMap) {
		this.bathSucMap = bathSucMap;
	}

	public List<CusLoginCountDTO> getCusLoginCountList() {
		return cusLoginCountList;
	}

	public void setCusLoginCountList(List<CusLoginCountDTO> cusLoginCountList) {
		this.cusLoginCountList = cusLoginCountList;
	}

	public CusLoginCountDTO getLoginCountDTO() {
		return loginCountDTO;
	}

	public void setLoginCountDTO(CusLoginCountDTO loginCountDTO) {
		this.loginCountDTO = loginCountDTO;
	}

	public List<CusLoginCountDTO> getCusLoginCountSum() {
		return cusLoginCountSum;
	}

	public void setCusLoginCountSum(List<CusLoginCountDTO> cusLoginCountSum) {
		this.cusLoginCountSum = cusLoginCountSum;
	}

	public String getSmsLimit() {
		return smsLimit;
	}

	public void setSmsLimit(String smsLimit) {
		this.smsLimit = smsLimit;
	}

	public int getSendSmsCount() {
		return sendSmsCount;
	}

	public void setSendSmsCount(int sendSmsCount) {
		this.sendSmsCount = sendSmsCount;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
