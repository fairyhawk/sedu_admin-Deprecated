package com.shangde.edu.finance.web;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;

/**
 * 流水管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class CashRecordWebAction extends CommonAction {

	/**
	 * 声名流水的PO对象
	 */
	private CashRecord cashRecord=new CashRecord();
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
	/**
	 * 声名logger
	 */
	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	private List<Course> courseList;
	
	/**
	 * 课程分类服务Service
	 */
	private ICoursesort coursesortService; 
	
	private List<CourseSortListDTO> courseSortListDTOList;
	/**
	 * 声名订单的PO对象
	 */
	private Contract contract=new Contract();
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	
	private String couid;//课程id
	private String userid;//用户id
	private List <CashRecord>cashRecordList=new ArrayList<CashRecord>();
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	/**
	 * 课程List
	 */
	private List <CourseDTO>newCourseList=new ArrayList<CourseDTO>();
	/**
	 * 课程数量
	 */
	private int courseSum;
	
	/**
	 * 教师服务对象
	 */
	private ITeacher teacherService;
	/**
	 * 教师查询条件
	 */
	private QueryTeacherCondition queryTeacherCondition;
	/**
	 * 教师list
	 */
	private List<Teacher> newTeacherList=new ArrayList<Teacher>();
	
	private String headerHTML = "";
	
	private String footerHTML = "";
	
	private String leftHTML = "";
	
	private ITemplate templateService;
	
	private ISellCou sellService;
	
	private Coupon coupon;
	/**
	 * 获得流水列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getCashRecordList() {
		try {
			//通过cookie获得userId
//			String cookieKey = CookieHandler.getCookieValueByName(getServletRequest(),LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);  
//			String userId = "";
//			if(!StringUtil.isNullString(cookieKey)){
//				String str[]  = cookieKey.split(",");
//				userId = str[0];
//				this.getQueryCashRecordCondition().setUserId(new Integer(userId));
//			} 
			int userId=this.getLoginUserId();
			if(userId!=0){
				this.getQueryCashRecordCondition().setUserId(userId);
			}
//			if(cashRecord.getContractId()!=null&&!"".equals(cashRecord.getContractId())){
//				this.getQueryCashRecordCondition().setContractId(cashRecord.getContractId());
//			}
			
			if(cashRecord.getCtId()!=0){
				this.getQueryCashRecordCondition().setCtId(cashRecord.getCtId());
				contract=this.contractService.getContractById(cashRecord.getCtId());
			}
//			if(contract.getId()!=0){
//				contract=this.contractService.getContractById(contract.getId());
//			}
//		    this.getQueryCashRecordCondition().setPageSize(10);
//			setPage(this.cashRecordService.getCashRecordList(queryCashRecordCondition));
//			setPageUrlParms();
//			if(getPage()!=null){
//				   getPage().setPageSize(10);
//				   }
			this.setCourseSortListDTOList(userId);
			//查询课程的详细信息　根据课程Id
			CourseDTO course=null;
			cashRecordList=this.cashRecordService.getCashRecordByList(getQueryCashRecordCondition());
			if(cashRecordList.size()>0){
				courseSum=cashRecordList.size();
			}
			
			ArrayList<Object> pkList = new ArrayList<Object>();
			for(int i=0;cashRecordList!=null&&i<cashRecordList.size();i++){
				
				if(cashRecordList.get(i).getPackId() != 0){
					pkList.add(cashRecordList.get(i).getPackId());
				}
				/**
				 course=new CourseDTO();
				 //通过课程获取课程的详细信息

				 if(cashRecordList.get(i).getCourseId()!=0){
					//course=this.courseService.getCourseById(cashRecordList.get(i).getCourseId());
					course=this.courseService.getCourseDTOById(cashRecordList.get(i).getCourseId());
					Float price=new Float(course.getPrice());
					int tsScore=price.intValue()*10;
					course.setTsScore(tsScore);
				}
				newCourseList.add(course);**/
			}
			pkList = (ArrayList<Object>) this.filterList(pkList);
			for(int i=0;i<pkList.size();i++){
				QuerySellCouCondition qscc = new QuerySellCouCondition();
				qscc.setSellId((Integer) pkList.get(i));
				List<SellCou> sellCouList = (ArrayList<SellCou>)sellService.getSellCouList(qscc);
				for(int z=0;z<sellCouList.size();z++){
					if(sellCouList.get(z).getCourseId() != 0){
						course=this.courseService.getCourseDTOById(sellCouList.get(z).getCourseId());
						Float price=new Float(course.getPrice());
						int tsScore=price.intValue()*10;
						course.setTsScore(tsScore);
					}
					newCourseList.add(course);
				}
			}
			//查询老师列表借前台用
			//查询所有教师
			//newTeacherList=this.teacherService.getTeacherList(getQueryTeacherCondition());
			//谢添加开始
			 String contractId=String.valueOf(contract.getId());
			 String couponId=sellService.getContractForCouponIdById(contractId);
			 int mycouponId=0;
			 if(couponId!=null){
				 mycouponId=Integer.parseInt(couponId);
			 }
			 coupon=sellService.getCouponById(mycouponId);
			 //谢添加结束
		} catch (Exception e) {
			logger.error("前台/个人中心/消费记录/Exception:"+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return "listCashRecord";

	}
	
	/**
	 * 设置左侧工具栏
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
	
	/**
	 * 查询此用户是否已经购买此课程
	 * @return
	 */
	public String  Getshu()
	{
		
		try {
			CashRecord cr = new CashRecord();
			cr.setCusId(new Integer(userid));//用户id
			cr.setPackId(new Integer(couid));//包id
			int boughtCount = cashRecordService.getshu(cr);
			int sendCount = cashRecordService.getSendCount(cr);
			setResult(new Result<int[]>(false, "" , null, new int[]{boughtCount, sendCount}));
			return "json";
		} catch (Exception e) {
			logger.error("前台/购物车/结算/Exception:"+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		
	}
	//付款方法
	public String payContract(){
		try {
			QueryTemplateCondition queryTemplateCondition = new QueryTemplateCondition();
			queryTemplateCondition.setTmpName("网站头部");
			List<Template> tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				headerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}

			queryTemplateCondition.setTmpName("网站尾部");
			tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				footerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}

			queryTemplateCondition.setTmpName("左部导航1");
			tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				leftHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}
			//通过cookie获得userId
//			String cookieKey = CookieHandler.getCookieValueByName(getServletRequest(),LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);  
			int userId=this.getLoginUserId();
			if(userId!=0){
				this.getQueryCashRecordCondition().setUserId(userId);
			}
			if(contract.getId()!=0){
				contract=this.contractService.getContractById(contract.getId());
				this.getQueryCashRecordCondition().setCtId(contract.getId());
			}
			this.setCourseSortListDTOList(userId);
			//查询课程的详细信息　根据课程Id
			CourseDTO course=null;
			
			cashRecordList=this.cashRecordService.getCashRecordByList(getQueryCashRecordCondition());
			if(cashRecordList.size()>0){
				courseSum=cashRecordList.size();
			}
			for(int i=0;cashRecordList!=null&&i<cashRecordList.size();i++){
				 course=new CourseDTO();
				if(cashRecordList.get(i).getCourseId()!=0){
					course=this.courseService.getCourseDTOById(cashRecordList.get(i).getCourseId());
					Float price=new Float(course.getPrice());
					int tsScore=price.intValue()*10;
					course.setTsScore(tsScore);
				}
				newCourseList.add(course);
			}
			//谢添加开始
			 String contractId=String.valueOf(contract.getId());
			 String couponId=sellService.getContractForCouponIdById(contractId);
			 int mycouponId=0;
			 if(couponId!=null){
				 mycouponId=Integer.parseInt(couponId);
			 }
			 coupon=sellService.getCouponById(mycouponId);
			 //谢添加结束
		} catch (Exception e) {
			logger.error("前台/个人中心/消费记录/Exception:"+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return "payContract";
	}
	
	public String openContract(){
		
		return "success";
	}
	public String contract1(){
		Date date=new Date();
	
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

	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public void setCashRecordList(List<CashRecord> cashRecordList) {
		this.cashRecordList = cashRecordList;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public QueryCourseCondition getQueryCourseCondition() {
		if (queryCourseCondition == null) {
			queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public List<CourseDTO> getNewCourseList() {
		return newCourseList;
	}

	public void setNewCourseList(List<CourseDTO> newCourseList) {
		this.newCourseList = newCourseList;
	}

	public int getCourseSum() {
		return courseSum;
	}

	public void setCourseSum(int courseSum) {
		this.courseSum = courseSum;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public QueryTeacherCondition getQueryTeacherCondition() {
		if (queryTeacherCondition == null) {
			queryTeacherCondition = new QueryTeacherCondition();
		}
		return queryTeacherCondition;
	}

	public void setQueryTeacherCondition(QueryTeacherCondition queryTeacherCondition) {
		this.queryTeacherCondition = queryTeacherCondition;
	}

	public List<Teacher> getNewTeacherList() {
		return newTeacherList;
	}

	public void setNewTeacherList(List<Teacher> newTeacherList) {
		this.newTeacherList = newTeacherList;
	}

	public String getCouid() {
		return couid;
	}

	public void setCouid(String couid) {
		this.couid = couid;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
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

	public String getLeftHTML() {
		return leftHTML;
	}

	public void setLeftHTML(String leftHTML) {
		this.leftHTML = leftHTML;
	}

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}
	
	public ISellCou getSellService() {
		return sellService;
	}

	public void setSellService(ISellCou sellService) {
		this.sellService = sellService;
	}
	
	

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
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
}
