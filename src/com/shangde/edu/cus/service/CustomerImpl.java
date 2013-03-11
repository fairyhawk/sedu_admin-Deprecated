package com.shangde.edu.cus.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.alipay.util.UtilDate;
import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.util.MD5;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.CouFreeGive;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICouFreeGive;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryNoteContentCondition;
import com.shangde.edu.cus.domain.CellPhone;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerDTOBath;
import com.shangde.edu.cus.domain.CustomerNewDTO;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.NoteContent;
import com.shangde.edu.cus.domain.SubjectCustomerDTO;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.CusLoginCountDTO;
import com.shangde.edu.cus.dto.CustomerCountNewDTO;
import com.shangde.edu.cus.dto.NoteContentDTO;
import com.shangde.edu.cus.dto.SimpleCustomerDTO;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.tk.service.ITaskCus;
import com.shangde.edu.tmp.domain.TmpStUser;


/**
 * Customer对象操作实现类
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
@SuppressWarnings("unchecked")
public class CustomerImpl extends BaseServiceManyDb  implements ICustomer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(CustomerImpl.class);
	
	/**
	 * 邮件服务对象
	 */
	private IMail mailService;
	
	/**
	 * 流水服务对象
	 */
	private ICashRecord  cashRecordService;
	
	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	
	/**
	 * 优惠券服务对象
	 */
	private ICpCus cpCusService;
	
	/**
	 * 学习计划服务对象
	 */
	private IStudyPlan studyPlanService;
	
	/**
	 * 试卷服务对象
	 */
	private IExampaperRecord exampaperRecordService;
	
	/**
	 * 考试结果服务对象
	 */
	private IOptRecord optRecordService;
	
	/**
	 * 积分记录服务对象
	 */
	private ITsRecord tsRecordService;
	
	/**
	 * 积分服务对象
	 */
	private ITotolsScore totolsScoreService;
	
	/**
	 * 任务服务对象
	 */
	private ITaskCus taskCusService;
	
	/**
	 * 售卖方式服务
	 */
	private ISellWay sellWayService;
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	
	private ICusCouKpoint cusCouKpointService;
	
	private ICustomer customerService;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 知识点服务
	 */
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;

	
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 回复问题的服务
	 */
	private IReProblem reProblemService;
	
	/**
	 * 登录信息记录
	 */
	private ILoginRecord loginRecordService;
	


	/**
	 * 售卖方式关系服务
	 */
	private ISellCou sellCouService;
	
	public java.lang.Integer addCustomer(Customer customer) {
		return simpleDao.createEntity("Customer_NS.createCustomer",customer);
    }

    public void delCustomerById(int cusId){
    	simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByCusId",cusId);
        simpleDao.deleteEntity("Customer_NS.deleteCustomerById",cusId);
    }

    public void updateCustomer(Customer customer) {
        simpleDao.updateEntity("Customer_NS.updateCustomer",customer);
    }

	/**
	 * 范昕 2011-08-03
     * 修改Customer 的昵称
     * @param customer 要修改的Customer
     */
	public void updateCustomerName(Customer customer){
		simpleDao.updateEntity("Customer_NS.updateCustomerName", customer);
	}
	
    public Customer getCustomerById(int cusId) {
        return simpleDao.getEntity("Customer_NS.getCustomerById",cusId);
    }

    public List<Customer> getCustomerList(QueryCustomerCondition queryCustomerCondition) {
        return simpleDao.getForList("Customer_NS.getCustomerList",queryCustomerCondition);
    }

	public PageResult getCustomerListByCondition(
			PageQuery queryCustomerCondition) {
		return simpleDaoRead.getPageResult("Customer_NS.getCustomerListByCondition", 
									   "Customer_NS.getCustomerCountByCondition", 
									   queryCustomerCondition);
	}

	public boolean checkOldPwd(Customer customer) {
		if((Integer)simpleDao.getEntity("Customer_NS.checkOldPwd", customer) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Customer getCustomerByEmail(String email) {
		 return simpleDao.getEntity("Customer_NS.getCustomerByEmail",email);
	}
	
	public List<Customer> getCustomerListByMobile(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Customer_NS.getCustomerListByMobile", queryCustomerCondition);
	}
	
	public Integer getUIDByLogin(Customer customer) {
		return simpleDao.getEntity("Customer_NS.getUIDByLogin", customer);
	}

	public boolean checkEmail(String email) {
		if((Integer)simpleDao.getEntity("Customer_NS.checkEmail", email) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
   public boolean checkMoblie(String moblie) {
        if((Integer)simpleDao.getEntity("Customer_NS.checkMobile", moblie) < 1) {
            return true;
        } else {
            return false;
        }
    }

	public List<Subject> getSubjectListByCus(int userId) {
		return simpleDao.getForList("Customer_NS.getSubjectListByCus",userId);
	}
	
	public int isComplete(int userId) {
		return simpleDao.getEntity("Customer_NS.isComplete", userId);
	}

	public void sendRegEmail(Customer customer) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", customer.getEmail());
		map.put("date", format.format(new Date()));
		map.put("cusName", customer.getCusName());
		mailService.getMail(IMail.CUS_REG_MAIL, map);
	}
	
    public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public String genericRandomPwd() {
		StringBuffer ranPwd = new StringBuffer(""); 
		Random ran = new Random();
		int location = 0;
		for(int i=0; i<9; i++) {
			location = ran.nextInt(10);
			ranPwd.append(location);
		}
		return ranPwd.toString();
	}

	public void sendForgotPwdEmail(Customer customer) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("cusName", customer.getCusName());
		map.put("email", customer.getEmail());
		map.put("newPwd", customer.getCusPwd());
		map.put("date", format.format(new Date()));
		
		mailService.getMail(IMail.FORGOT_PWD_MAIL, map);
	}

	public String genericConfirmCode() {
		StringBuffer newConfirmationCode = new StringBuffer(""); 
		Random ran = new Random();
		for(int i=0; i<6; i++) {
			newConfirmationCode.append(ran.nextInt(10));
		}
		return newConfirmationCode.toString();
	}

	public int getRegistNumber(QueryCustomerCondition queryCustomerCondition) {
	int number=simpleDao.getEntity("Customer_NS.getRegistNumber", queryCustomerCondition);
	return number;
	}
	public List<SubjectCustomerDTO> getRegistNumberList(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Customer_NS.getRegistNumberList", queryCustomerCondition);
	}
	public List getMonthList(){
		
		return simpleDao.getForList("Customer_NS.getMonthList", "");
	}
	public int getMonthRegistNumber(String month){
		return simpleDao.getEntity("Customer_NS.getMonthRegist", month);
	}
	public List getMonthDay(String month){
		return simpleDao.getForList("Customer_NS.getMonthDay", month);
	}
	
	public List getDay(String dateDay){
		
		return simpleDao.getForList("Customer_NS.getDay", dateDay);
	}
	
	public List addBathCustomer(List cusList) throws SQLException{
		return simpleDao.createBatchEntity("Customer_NS.createCustomer", cusList);
	}

    public Customer getCustomerByView(int cusId) {
        return simpleDao.getEntity("Customer_NS.getCustomerByView",cusId);
    }
    
    public List<SimpleCustomerDTO> getSimpleCustomerDTOListByCondition(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getForList("Customer_NS.getSimpleCustomerDTOListByCondition", queryCustomerCondition);
    }
    
    //修改---用户
    public void fixFinance(){
    	//修改用户
    	QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
    	List<Customer> cusList =  simpleDao.getForList("Customer_NS.getTestCustomerList", queryCustomerCondition);
    	
    	//开始循环遍历
    	if(cusList != null && cusList.size() > 0){
    		Customer cus  = null;
    		
    		List<Course> userCourseList = null;
    		
    		List<Contract> ctList = null;
    		//---订单
    		List<Contract> ctQList = null;
    		Contract ctTemp = null;
    		
    		QueryContractCondition queryContractCondition = new QueryContractCondition();
    		
    		for(int i = 0; i < cusList.size(); i ++){
    			cus = cusList.get(i);//取出用户
    			
    			//查出该用户的课程集合
    			userCourseList = cusCouKpointService.getCusCouKpointListByCusId(cus.getCusId());//课程集合
    			
    			//查出已付订单集合
    			queryContractCondition.setUserId(cus.getCusId());
    			ctList = simpleDao.getForList("Customer_NS.getPayContractListByCusId", queryContractCondition);
    			
    			//查询所有---订单（第一个保留，剩下的删除）根据用户ID
				ctQList = simpleDao.getForList("Customer_NS.getQuestionContractListByCusId", queryContractCondition);
				
    			if(ctList != null && ctList.size() > 0){//存在已付订单
    				List<Course> couTempList = null;
    				
    				for(int j = 0; j < ctList.size(); j ++){//去掉课程集合中，已购买的课程，剩下的都是赠送的课程
    					ctTemp = ctList.get(j);
    					
    					queryContractCondition.setContractId(ctTemp.getContractId());
    					couTempList = simpleDao.getForList("Customer_NS.getCourseListByContractId", queryContractCondition);//查出购买课程
    					
    					for(int k = 0; k < couTempList.size(); k ++){//去掉相等的课程
    						if(userCourseList.contains(couTempList.get(k))){//去掉
    							userCourseList.remove(couTempList.get(k));
    						}
    					}
    				}
    				
    				if(userCourseList.size() > 0){//当存在赠送课程
        				Date date=new Date();
        				Long cId=date.getTime();
    					for(int j = 0; j < ctQList.size(); j ++){
        					ctTemp = ctQList.get(j);
        					if(j == 0){//修改
        						//修改订单号 
        						ctTemp.setContractId(cus.getCusId()+cId.toString());
        						contractService.updateContract(ctTemp);
        						//生成流水---------------------------------------------------------------
        						CashRecord cashRecord = null;
        						Course course = null;
        						for(int k = 0; k < userCourseList.size(); k ++ ){
        								cashRecord=new CashRecord();
        								course=userCourseList.get(k);
        								cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
        								cashRecord.setPrice(course.getPrice());
        								cashRecord.setContractId(ctTemp.getContractId());
        								cashRecord.setCreateTime(ctTemp.getCreateTime());
        								cashRecord.setCusId(cus.getCusId());
        								cashRecord.setCtId(ctTemp.getId());
        								cashRecord.setCourseId(course.getCourseId());
        								cashRecord.setType(2);//2代表后台赠送
        								this.cashRecordService.addCashRecord(cashRecord);
        							}
        					}else{//删除
        						contractService.delContractById(ctTemp.getId());
        					}
        				}
    				}else{//如果只是修复，删掉---订单，保留以前的
    					//删掉---的订单
        				for(int j = 0; j < ctQList.size(); j ++){
        					ctTemp = ctQList.get(j);
        					contractService.delContractById(ctTemp.getId());
        				}
    				}
    				
    			}else{
    				Date date=new Date();
    				Long cId=date.getTime();
    				for(int j = 0; j < ctQList.size(); j ++){
    					ctTemp = ctQList.get(j);
    					if(j == 0){//修改
    						//修改订单号 
    						ctTemp.setContractId(cus.getCusId()+cId.toString());
    						contractService.updateContract(ctTemp);
    						//生成流水---------------------------------------------------------------
    						CashRecord cashRecord = null;
    						Course course = null;
    						for(int k = 0; k < userCourseList.size(); k ++ ){
    								cashRecord=new CashRecord();
    								course=userCourseList.get(k);
    								cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
    								cashRecord.setPrice(course.getPrice());
    								cashRecord.setContractId(ctTemp.getContractId());
    								cashRecord.setCreateTime(ctTemp.getCreateTime());
    								cashRecord.setCusId(cus.getCusId());
    								cashRecord.setCtId(ctTemp.getId());
    								cashRecord.setCourseId(course.getCourseId());
    								cashRecord.setType(2);//2代表后台赠送
    								this.cashRecordService.addCashRecord(cashRecord);
    						}
    					}else{//删除
    						contractService.delContractById(ctTemp.getId());
    					}
    				}
    			}
    		}
    	}
    }
	   public void cusTypeFix(){
		 //查询出
    	QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
    	List<Customer> cusList =  simpleDao.getForList("Customer_NS.getCusTypeCustomerList", queryCustomerCondition);
    	if(cusList != null && cusList.size() > 0){
    		Customer cus  = null;
    		List<Course> userCourseList = null;	
    		Contract contract=null;
    		CashRecord cashRecord=null;
    		Course course=null;
    		for(int i = 0; i < cusList.size(); i ++){
    			Date date=new Date();
    			Long ctId=date.getTime();
    			cus=cusList.get(i);
    			//添加到订单表中
    			contract=new Contract();
    			contract.setContractId(cus.getCusId()+ctId.toString());
    			contract.setCusId(cus.getCusId());
    			contract.setCreateTime(date);           
    			contract.setContractFrom("后台用户");
    			contract.setPayType(0);
    			contract.setStatus(2);//2代表赠送
    			contract.setContractCutSumMoney(0.0);//减去优惠券应付的价格，折后总金额。
    			contract.setContractSumMoney(0.0);//应付总价，总金额。
    			contract.setCouponMoney(0.0);
    			contract.setUseCoupon(0);//0代表没有使用 1代表使用
    			int cId=this.contractService.addContract(contract);
    			//查出该用户的课程集合
    			userCourseList = cusCouKpointService.getCusCouKpointListByCusId(cus.getCusId());//课程集合
    			for(int j = 0; userCourseList != null && j < userCourseList.size(); j ++){
    				//添加到流水表中
    				cashRecord=new CashRecord();
    				course=userCourseList.get(j);
    				cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
    				cashRecord.setPrice(course.getPrice());
    				cashRecord.setContractId(cus.getCusId()+ctId.toString());
    				cashRecord.setCreateTime(date);
    				cashRecord.setCusId(cus.getCusId());
    				cashRecord.setCourseId(course.getCourseId());
    				cashRecord.setType(2);//2代表后台赠送
    				cashRecord.setCtId(cId);//把订单的id存到流水中
    				this.cashRecordService.addCashRecord(cashRecord);
    			}
    		}
    	}
	}
		
	/**
	 * 删除体验（临时）学员的体验（临时）数据
	 * @param cusId
	 */
	public void recoverTempCustomer(int cusId) {
		recoverTempCustomer(cusId, 0);
	}

	/**
	 * 删除体验（临时）学员除了传入订单的的其他体验（临时）数据
	 * @param cusId
	 * @param ctId
	 */
	public void recoverTempCustomer(int cusId, int ctId) {
		//删除笔记
		notesService.delNotesByCusId(cusId);
		//删除优惠券
		cpCusService.delCpCusByCusId(cusId);
		//删除学习计划
		studyPlanService.delStudyPlanByCusId(cusId);
		//删除考试
		exampaperRecordService.delERecordByCusId(cusId);
		//删除考试关联
		optRecordService.delOptRecordByCusId(cusId);
		//删除知识树的表
		cusCouKpointService.delCusCouKpointByCusId(cusId);
		//删除积分记录
		tsRecordService.delTsRecordByCusId(cusId);
		//积分清零		
//		TotolsScore totolsScore = totolsScoreService.getTotolsScore(cusId);
//		totolsScore.setTsAction(0);
//		totolsScore.setTsCurrent(0);		
//		totolsScoreService.updateTotolsScore(totolsScore);
//		//删除积分
//		totolsScoreService.delTotolsScoreByCusId(cusId);
		//删除学员登录信息
//		loginRecordService.delLoginRecordByCusId(cusId);
		//删除task_cus表
//		taskCusService.deleteTaskCusByCusId(cusId);
		
		if(ctId <= 0) {
			//删除流水
			cashRecordService.delCashRecordByCusId(cusId);
			//删除订单
			contractService.delContractByCusId(cusId);
		} else {
			QueryCashRecordCondition queryCashRecordCondition = new QueryCashRecordCondition();
			queryCashRecordCondition.setUserId(cusId);
			queryCashRecordCondition.setCtId(ctId);
			cashRecordService.delCashRecordExceptCtId(queryCashRecordCondition);
			
			QueryContractCondition queryContractCondition = new QueryContractCondition();
			queryContractCondition.setUserId(cusId);
			queryContractCondition.setId(ctId);
			contractService.delContractExceptCtId(queryContractCondition);
		}
	}
	
	
	/**
	 * 学员统计
	 * @return  customerCount
	 */

	public Integer getCustomerCount(QueryCustomerCondition queryCustomerCondition) {
		// TODO Auto-generated method stub
			int cusCount=0;
		try {
			cusCount = this.simpleDao.getEntity("Customer_NS.getCustomerCount",
					queryCustomerCondition);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cusCount;
		
	}
	/**
     * 修改Customer 的newerflag 新手引导用
     * @param customer 要修改的Customer
     */
    public void updateCustomerNewerflag(Customer customer){
        simpleDao.updateEntity("Customer_NS.updateCustomerNewerFlag",customer);
    }
    

    //获取用户地址列表 
    public List<AddressDTO> GetAddrByCusId(int cusId)
    {
    	List<AddressDTO> list=simpleDao.getForList("Customer_NS.getAddrByUserId", cusId);
    	return list;
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

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public IStudyPlan getStudyPlanService() {
		return studyPlanService;
	}

	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public IOptRecord getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public IMail getMailService() {
		return mailService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-10 16:49:16
     */
    public int getCustomerRegNum(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getCustomerRegNum",queryCustomerCondition );
    }

    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-23 16:49:16
     */
    public int getCustomerRegNumCount(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getCustomerRegNumCount",queryCustomerCondition );
    }   
    
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-24 16:49:16
     */
    public List<SimpleCustomerDTO> getSimpleCustomerDTOListCusIdByCondition(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getForList("Customer_NS.getSimpleCustomerDTOListCusIdByCondition", queryCustomerCondition);
    }
    
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员量
     * Date : 2011-8-24 16:49:16
     */
    public int getContractNum(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getContractNum", queryCustomerCondition);
    }
 
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员 下订单总数量
     * Date : 2011-8-24 16:49:16
     */
    public int getPayNum(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getPayNum", queryCustomerCondition);
    }
    
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员 支付成功数量
     * Date : 2011-8-24 16:49:16
     */
    public int getPaySucNum(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getPaySucNum", queryCustomerCondition);
    }

   


/**
     *@author  何海强
     * 返回排序的条件
     * Date: 2011-9-20 16:47
     */
    public int getTimesPaixu(){
    	return simpleDao.getEntity("Customer_NS.getTimepaixu", null);
    }
    /**
     * 李明 添加
     */
    /**
     * 李明
     * 发短信，查询所有的短信
     */
	public PageResult getNoteContentList(QueryNoteContentCondition queryNoteContentCondition){
		return simpleDao.getPageResult("CellContent_NS.getPayNoteContent", "CellContent_NS.getPayNoteContentCount", queryNoteContentCondition);
	}
	/**
	 * 李明
	 * 多条件查询发送短信的内容
	 */
	public PageResult sendNoteContentListWhere(
			QueryNoteContentCondition queryNoteContentCondition) {
		return simpleDao.getPageResult("CellContent_NS.getSendNoteContentListWhere", "CellContent_NS.getSendNoteContentListWhereCount", queryNoteContentCondition);
	}
	/**
	 * 李明 根绝项目ID查询所要的售卖方式；
	 * @param subjectId
	 * @return
	 */

	public List<NoteContentDTO> showSinggleSellListJson(int subjectId){
		return simpleDao.getForList("CellContent_NS.getshowSinggleSellListJson", subjectId);
	}
	/**
	 * 添加短信内容
	 * @param noteContent
	 */
	public int addNoteContent(NoteContent noteContent){
		return simpleDao.createEntity("CellContent_NS.createNoteContent", noteContent);
	}
	/**
	 * 添加短信内容
	 * @param cellPhone
	 */
	public void addNoteCellPhone(CellPhone cellPhone){
		 simpleDao.createEntity("CellContent_NS.createCellPhone", cellPhone);
	}
	/**
	 *   李明 查询发送失败的短信
	 * @param queryNoteContentCondition
	 * @return
	 */
	public List<CellPhone> getListCellPhoneCrabs(QueryNoteContentCondition queryNoteContentCondition){
		return simpleDao.getForList("CellContent_NS.getListCellPhoneCrabs", queryNoteContentCondition);
	}
	/**
	 * Liming 查询发送所有短信
	 * @param noteId
	 * @return
	 */
	public int getListCellPhoneCout(int noteId){
		return simpleDao.getEntity("CellContent_NS.getListCellPhoneSingleCout", noteId);
	}
	 /**
     * 分页查询
     * @param queryCustomerCondition 查询条件
     * @return 分页结果
     */
	public PageResult getCustomerCellPhoneListByCondition(
			QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getPageResult("Customer_NS.getCustomerListByConditionn", 
				   "Customer_NS.getCustomerCountByConditionn", 
				   queryCustomerCondition);
	}

	/**
	 * Liming 查询单个的短信内容
	 * @param notedId
	 */
	public NoteContent getSingleContent(
			int notedId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("CellContent_NS.getSingleContent", notedId);
	}
	/**
	 * Liming  查询手机号
	 */
	public List<CellPhone> getListCellPhone(
			QueryNoteContentCondition queryNoteContentCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("CellContent_NS.getListCellPhoneSucceed", queryNoteContentCondition);
	}
	/**
	 * Liming
	 * 比较信息记录
	 * @param discern
	 * @return
	 */
	public NoteContentDTO getDiscernEntity(int discern){
		return simpleDao.getEntity("CellContent_NS.getSetMessages", discern);
	}
	/**Liming
	 * 根据手机号查询手机号
	 * @param queryCustomerCondition
	 */
	public List<Customer> getCustomerEmail(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Customer_NS.getCustomerEmail", queryCustomerCondition);
	}
	
	public List<CustomerWithConSizeDTO> getCustomerWithConSizeDTOList(
			QueryCustomerCondition queryCustomerCondition) {
		return simpleDao.getForList("Customer_NS.getCustomerListByCondition", queryCustomerCondition);
	}

	public Map<String, Integer> delCustomerBath(String ids) {
		Map<String,Integer> mapDeled = new HashMap<String,Integer>();
		/*--笔记--*/
		Integer result = notesService.delBathNotesByCusIds(ids);
		mapDeled.put("笔记", result);
		/*--流水--*/
		result = cashRecordService.delBatchCashRecordByCusIds(ids);
		mapDeled.put("流水", result);
		/*--订单--*/
		result = contractService.delBathContractByCusIds(ids);
		mapDeled.put("订单", result);
		/*--优惠券--*/
		result = cpCusService.delBathCpCusByCusIds(ids);
		mapDeled.put("优惠券", result);
		/*--学习计划--*/
		result = studyPlanService.delBathStudyPlanByCusIds(ids);
		mapDeled.put("学习计划", result);
		/*--考试--*/
		result = exampaperRecordService.delBathERecordByCusIds(ids);
		mapDeled.put("考试", result);
		/*--考试关联--*/
		result = optRecordService.delBathOptRecordByCusIds(ids);
		mapDeled.put("考试关联", result);
		/*--积分记录--*/
		result = tsRecordService.delBathTsRecordByCusIds(ids);
		mapDeled.put("积分记录", result);
		/*--积分--*/
		result = totolsScoreService.delBathTotolsScoreByCusIds(ids);
		mapDeled.put("积分", result);
		/*--学员登录信息--*/
		result = loginRecordService.delBathLoginRecordByCusIds(ids);
		mapDeled.put("学员登录信息", result);
		/*--任务--*/
		result = taskCusService.deleteBathTaskCusByCusIds(ids);
		mapDeled.put("任务", result);
		/*--问题及问题的回复--*/
		result = problemService.delBathProblemByCusIds(ids);
		mapDeled.put("问题", result);
		result = reProblemService.delBathReProblemByCusIds(ids);
		mapDeled.put("问题回复", result);
		/*--学员--*/
		result = simpleDao.delete("Customer_NS.deleteCustomerByCusIds", ids);
		mapDeled.put("用户", result);
		return mapDeled;
	}
	
	/**
	 * 辅助方法,map转为实体类
	 * @param list
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-5
	 */
	@SuppressWarnings("rawtypes")
	private List<CustomerDTOBath> mapList2EntityList(List<Map> list,Integer cusType,Integer cusSubjectId){
		List<CustomerDTOBath> listResult = new ArrayList<CustomerDTOBath>();
		for(Map map : list){
			CustomerDTOBath customer = new CustomerDTOBath();
			customer.setEmail((String)map.get("email"));
			customer.setCusPwd(MD5.getMD5((String)map.get("password")));
			customer.setOriPwd((String)map.get("password"));
			customer.setRealName((String)map.get("realname"));
			customer.setCusName((String)map.get("realname"));
			customer.setLoginTimes(0);
			customer.setSex("1");
			customer.setCusType(cusType);
			customer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
			customer.setSubjectId(cusSubjectId);
			customer.setSellways((String)map.get("sellways"));
			listResult.add(customer);
		}
		return listResult;
	}
	
	/**
	 * 
	 * @param cusId
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-5
	 */
	private Contract createContractEntity(Integer cusId){
		Contract contract = new Contract();
		contract.setContractId(cusId + UtilDate.getOrderNum());
		contract.setCusId(cusId);
		contract.setCreateTime(new Date());
		contract.setContractFrom("后台用户");
		contract.setPayType(0);
		contract.setStatus(2);// 2代表赠送
		contract.setContractCutSumMoney(0.0);// 减去优惠券应付的价格，折后总金额。
		contract.setContractSumMoney(0.0);// 应付总价，总金额。
		contract.setCouponMoney(0.0);
		contract.setUseCoupon(0);// 0代表没有使用 1代表使用
		return contract;
	}
	
	/**
	 * @param cusId
	 * @param contractId
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-5
	 */
	private CashRecord createCashRecord(Integer cusId,Integer contractId,String contractno,Integer sellwayId){
		CashRecord cashRecord = new CashRecord();
		SellWay sellway = sellWayService.getSellWayById(sellwayId);
		cashRecord.setPackId(sellwayId);
		cashRecord.setStatus(1);
		cashRecord.setCrInfo("购买《" + sellWayService.getSellWayById(sellwayId).getSellName() + "》");
		cashRecord.setCrSubjectId(sellway.getSubjectId());
		cashRecord.setPrice(sellway.getSellPrice());
		cashRecord.setContractId(contractno);
		cashRecord.setCtId(contractId);
		cashRecord.setCreateTime(new Date());
		cashRecord.setCusId(cusId);
		cashRecord.setCourseId(0);
		cashRecord.setCashRecordPrice(0);
		return cashRecord;
	}
	
	@SuppressWarnings("rawtypes")
	public Integer addCustomerNotExistBath(List<Map> list,Integer cusType,Integer cusSubjectId,Boolean isTemp) {
		Integer cashResult = 0;
		List<CustomerDTOBath> cusList = mapList2EntityList(list,cusType,cusSubjectId);
		if(cusList != null && cusList.size() > 0){
				int cusId = 0;
				for (CustomerDTOBath customer : cusList) {
					/*--建立用户--*/
					customer.setFromType("7");//内部生成 注册标识来源为7
					cusId = simpleDao.createEntity("Customer_NS.createCustomer", customer);
					if(cusId > 0){
						/*--生成订单--*/
						Contract contract = createContractEntity(cusId);
						int contractId = simpleDao.createEntity("Contract_NS.createContract", contract);
						String sellways = customer.getSellways();
						String [] strArray = sellways.split(",");
						/*--生成流水--*/
						for (String string : strArray) {
							simpleDao.createEntity("CashRecord_NS.createCashRecord",createCashRecord(cusId,contractId,contract.getContractId(),Integer.parseInt(string)));
							cashResult++;
						}
						
						//范昕  添加赠课记录
			            CouFreeGive couFreeGive = new CouFreeGive();
			            couFreeGive.setCusId(contract.getCusId());
			            couFreeGive.setCtId(contract.getContractId());
			            User users = (User)ActionContext.getContext().getSession().get(CommonAction.SYS_USER_SESSION_NAME);
			            couFreeGive.setUserName(users.getLoginName());
			            couFreeGive.setCreateTime( new Date());
			            
			            couFreeGiveService.addCouFreeGive(couFreeGive);
			            
						if(isTemp){
				            //Yangning 生成临时体验账户
				            TmpStUser tmp = new TmpStUser();
				            tmp.setSubjectId(cusSubjectId);
				            tmp.setSellids(customer.getSellways());
				            tmp.setCusEmail(customer.getEmail());
				            tmp.setCusPwd(customer.getOriPwd());
				            tmp.setCusId(customer.getCusId());
				            tmp.setStCusName(customer.getCusName());
				            simpleDao.createEntity("TmpStUser_NS.insertTmpUser", tmp);
						}
					}
				}
		}
		return cashResult;
	}
	
	/**
	 * 调整售卖方式,用户是否已存在售卖方式，如果存在去掉map里对应的sellwayId
	 * @param list
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-5
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> adjustSellWays(List<Map> list){
		for (Map map : list) {
			String sellWays = (String)map.get("sellways");
			String [] strArray = sellWays.split(",");
			StringBuffer sb = new StringBuffer();
			sb.append(sellWays);
			for (String string : strArray) {
				/*--查看流水下用户是否已存在售卖方式，如果存在去掉map里对应的sellways --*/
				CashRecord record = new CashRecord();
				record.setCusId((Integer)map.get("CUSID"));
				record.setPackId(Integer.parseInt(string));
				Integer result = simpleDao.getEntity("CashRecord_NS.existCashRecordBath", record);
				if(result != null && result > 0){
					int position = sb.toString().indexOf(string);
					if(position == 0){
						sb.delete(position,string.length() + 1);
					}else{
						sb.delete(position - 1, string.length());
					}
				}
			}
			map.put("sellways", sb.toString());
		}
		return list;
	}
	
	
	/**
	 * 部分赠送
	 */
	@SuppressWarnings("rawtypes")
	public Integer addCustomerExistBathPart(List<Map> list){
		Integer cashRecordCount = 0;
		/*--调整sellWays防止用户下有相同课程--*/
		list = adjustSellWays(list);
		for (Map map : list) {
				String sellWays = (String)map.get("sellways");
				if(sellWays != null && sellWays.length() > 0){
				String [] strArray = sellWays.split(",");
					/*--生成订单--*/
					Contract contract = createContractEntity((Integer)map.get("CUSID"));
					int contractId = simpleDao.createEntity("Contract_NS.createContract", contract);
					/*--生成流水--*/
					for (String string : strArray) {
						simpleDao.createEntity("CashRecord_NS.createCashRecord",createCashRecord((Integer)map.get("CUSID"),contractId,contract.getContractId(),Integer.parseInt(string)));
						cashRecordCount++;
					}
					
					
					//范昕  添加赠课记录
		            CouFreeGive couFreeGive = new CouFreeGive();
		            couFreeGive.setCusId(contract.getCusId());
		            couFreeGive.setCtId(contract.getContractId());
		            User users = (User)ActionContext.getContext().getSession().get(CommonAction.SYS_USER_SESSION_NAME);
		            couFreeGive.setUserName(users.getLoginName());
		            couFreeGive.setCreateTime( new Date());
		            
		            couFreeGiveService.addCouFreeGive(couFreeGive);
				}
				
				
		}
		return cashRecordCount;
	}
	
	
	private ICouFreeGive  couFreeGiveService;
	
	/**
	 * @return the couFreeGiveService
	 */
	public ICouFreeGive getCouFreeGiveService() {
		return couFreeGiveService;
	}

	/**
	 * @param couFreeGiveService the couFreeGiveService to set
	 */
	public void setCouFreeGiveService(ICouFreeGive couFreeGiveService) {
		this.couFreeGiveService = couFreeGiveService;
	}

	/**
	 * 完全赠送
	 */
	@SuppressWarnings("rawtypes")
	public Integer addCustomerExistBathComplete(List<Map> list){
		/*--where 条件拼接--*/
		Integer cashRecordCount = 0;
		StringBuffer sb = new StringBuffer();
		for (Map map : list) {
			sb.append(map.get("CUSID"));
			sb.append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		/*--删除以前流水与订单--*/
		Integer result = simpleDao.delete("CashRecord_NS.delCashRecordByCusIds", sb.toString());
		result += simpleDao.delete("Contract_NS.delContractByCusIds", sb.toString());
		for (Map map : list) {
			/*--生成订单--*/
			Contract contract = createContractEntity((Integer)map.get("CUSID"));
			int contractId = simpleDao.createEntity("Contract_NS.createContract", contract);
			String sellWays = (String)map.get("sellways");
			String [] strArray = sellWays.split(",");
			/*--生成流水--*/
			for (String string : strArray) {
				simpleDao.createEntity("CashRecord_NS.createCashRecord",createCashRecord((Integer)map.get("CUSID"),contractId,contract.getContractId(),Integer.parseInt(string)));
				cashRecordCount++;
			}
			
			//范昕  添加赠课记录
            CouFreeGive couFreeGive = new CouFreeGive();
            couFreeGive.setCusId(contract.getCusId());
            couFreeGive.setCtId(contract.getContractId());
            User users = (User)ActionContext.getContext().getSession().get(CommonAction.SYS_USER_SESSION_NAME);
            couFreeGive.setUserName(users.getLoginName());
            couFreeGive.setCreateTime( new Date());
            
            couFreeGiveService.addCouFreeGive(couFreeGive);
			
			
		}
		return cashRecordCount;
	}
	
	/**
	 * @author 王超
	 * 通过userId获取当天发送短信的个数
	 * @param userId
	 * @return
	 */
	public int getTodaySendCountByUserId(int userId){
		return simpleDao.getEntity("CellContent_NS.getTodaySendCountByUserId", userId);
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<Map> getUserEmailByStrings(String ids) {
		return simpleDao.getForList("Customer_NS.getCustomerExistInEmail", ids);
	}
	
	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public QueryKpointCondition getQueryKpointCondition() {
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
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

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}
	
	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}

	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}

	public List<CustomerNewDTO> getCustomerAll(QueryCustomerCondition queryCustomerCondition) {
		return simpleDaoRead.getForList("Customer_NS.getCustomerAll", queryCustomerCondition);
	}
	
	public List<CustomerNewDTO> getDSCustomerAll(QueryCustomerCondition queryCustomerCondition) {
		return simpleDaoRead.getForList("Customer_NS.getDSCustomerAll", queryCustomerCondition);
	}

	public List<CustomerCountNewDTO> getOrdersPaid(QueryCustomerCondition queryCustomerCondition) {
		return simpleDao.getForList("Customer_NS.getOrdersPaid", queryCustomerCondition);
	}

	public List<CustomerCountNewDTO> getOrdersPaidTime(QueryCustomerCondition queryCustomerCondition) {
		return simpleDao.getForList("Customer_NS.getOrdersPaidTime", queryCustomerCondition);
	}

	public List<CustomerCountNewDTO> getStudentMonth(QueryCustomerCondition queryCustomerCondition) {
		return simpleDaoRead.getForList("Customer_NS.getStudentMonth", queryCustomerCondition);
	}

	public List<CusLoginCountDTO> getCusLoginCount(Map map) {
		return simpleDao.getForList("Customer_NS.getCusLoginCount", map);
	}

	public List<CusLoginCountDTO> getCusLoginSum(Map map) {
		return simpleDao.getForList("Customer_NS.getCusLoginSum", map);
	}

	public List<CustomerNewDTO> getUserBySubjectGroup(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Users_NS.getUserBySubjectGroup",  queryCustomerCondition);
	}

	public int getCustomerNotTempCount(String cusIds) {
		return simpleDao.getEntity("Customer_NS.getCustomerNotTempCount", cusIds);
	}
	@Override
	public void addCusRankToTable() {
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("icount", 0);
		simpleDao.getEntity("Customer_NS.addCusRankToTable", param);
	}
}
