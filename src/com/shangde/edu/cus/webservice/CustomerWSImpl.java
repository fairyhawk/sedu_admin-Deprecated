/**
 * 
 */
package com.shangde.edu.cus.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.MD5;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;

/**
 * @author	caowei
 * @date	2011-8-9
 * @desc	
 */
public class CustomerWSImpl extends BaseService implements ICustomerWS{
	
	private Log loggin = LogFactory.getLog(CustomerWSImpl.class);

	//前向客户的集合
	private List<Customer> cusList = new ArrayList<Customer>();
	
	private List<Integer> addressIdList = new ArrayList<Integer>();
	//前向客户服务
	private ICustomer customerService;
	//订单服务
	private IContract contractService;
	
	private ITsRecord tsRecordService;
	
	//查询售卖方式
	private QuerySellCouCondition querySellCouCondition = new QuerySellCouCondition();
	//售卖方式服务
	private ISellCou sellCouService;
	//课程服务
	private ICourse courseService;
				     
	private IAddress addressService;
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	//课程
	private Course course;
	//流水服务
	private ICashRecord cashRecordService;
	//返利网返回的数据
	private String returnValueToAgent = "";
	//购买记录服务
	private IGmrecord gmrecordService;
	//售卖方式服务
	private ISellWay sellWayService;
	//售卖方式
	private SellWay sellWay;
		
	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
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
	
	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
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

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public QuerySellCouCondition getQuerySellCouCondition() {
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
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

	public List<Customer> getCusList() {
		return cusList;
	}

	public void setCusList(List<Customer> cusList) {
		this.cusList = cusList;
	}

	public String getReturnValueToAgent() {
		return returnValueToAgent;
	}

	public void setReturnValueToAgent(String returnValueToAgent) {
		this.returnValueToAgent = returnValueToAgent;
	}

	/**
	 * 代理商创建账户
	 * 
	 * @param agentId
	 * 			代理商ID
	 * @param agentName
	 * 			代理商帐号
	 * @param createSum
	 * 			创建数量
	 * @param project
	 * 			项目ID
	 * @param sellWay
	 * 			售卖方式ID 
	 */
	public String createCustomerByAgent(int agentId, String agentName,
			int createSum, String project, String sellWay,double discount,double consumeamount,double consumediscountamount) {
		String rToAgent ="";
		try{
			 rToAgent = this.createCustomerByAgent(agentName, createSum, project);
			for(int i=0;i<cusList.size();i++){
				Customer preC = cusList.get(i);
				Date date = new Date();
				int ctId = this.createContract(preC, date,discount,consumeamount,consumediscountamount,0);//生成订单
				this.createCashRecord(Integer.parseInt(sellWay), date, preC.getCusType(), ctId, preC.getCusId(),project,discount,consumediscountamount);
			}
			return rToAgent;
		}catch(Exception e){
		    System.out.println("生成帐号错误！\n"+e.toString());
		}
		return rToAgent;
	}
	

	/**
	 * @see com.shangde.edu.cus.webservice.ICustomerWS#createCustomerByCustom(String, String, String, double, double, double)
	 */
	public String createByCustom(String createInfo,String project,String sellWay,double discount,double consumeamount,double consumediscountamount){
		String result = "";
		try {
			 result = this.createCoustomeByCoustom(createInfo, project);
			for(int i=0;i<cusList.size();i++){
				Customer preC = cusList.get(i);
				int addressId = addressIdList.get(i);
				Date date = new Date();
				int ctId = this.createContract(preC, date,discount,consumeamount,consumediscountamount,addressId);//生成订单
				this.createCashRecord(Integer.parseInt(sellWay), date, preC.getCusType(), ctId, preC.getCusId(),project,consumeamount,consumediscountamount);
			}
			return result;
			
		} catch (Exception e) {
			System.out.println("自定义生成账号失败！\n"+e.toString());
		}
		return result;
	}
	
	/**
	 * 生成订单
	 * 
	 * @param customer
	 * 			前向客户对象
	 * @param date
	 * 			创建时间
	 * @return
	 * 			创建的订单编号
	 */
	public int createContract(Customer customer,Date date,double discount,double consumeamount,double consumediscountamount,int addressId){
		Long cId = date.getTime();
		Contract contract = new Contract();
		int cusId = customer.getCusId();
		contract.setContractId(cusId + cId.toString());
		contract.setCusId(cusId);
		contract.setCusIdAddress(addressId);//设置收货地址id
		//contract.setCustomer(customer);
		contract.setCreateTime(date);
		contract.setPayTime(date);
		contract.setContractFrom("后台用户");
		int cusType = customer.getCusType();//查询注册类型
		contract.setFreight(0);
		contract.setRelief(0);
		contract.setPayType(5);
		if (cusType == 1 || cusType == 3) {
			contract.setStatus(2);// 2代表赠送
		} else {
			//contract.setStatus(0);// 0未付 1已付 2内部赠送 3外部退费 4外部修复 5外部过期 6内部过期
			contract.setStatus(1);
		}
		contract.setContractCutSumMoney(consumediscountamount);// 减去优惠券应付的价格，折后总金额。
		contract.setContractSumMoney(consumeamount);// 应付总价，总金额。
		contract.setCouponMoney(discount);
		contract.setUseCoupon(0);// 0代表没有使用 1代表使用
//		contract.setUseCoupon(0);
		int ctId = this.contractService.addContract(contract);
		return ctId;
	}
	
	/**
	 * 生成帐号的名称：帐号的生成规则是代理商账户前4位+6位随机数+@highso.cn
	 * 
	 * @param agentName
	 * 			代理商帐号名称
	 * @return
	 */
	public String createCustomerName(String agentName){
		String prefix = "@highso.cn";
		String userName = agentName.substring(0,4);
		Random random = new Random();
		for(int x=0;x<6;x++){
			userName += random.nextInt(10);
		}
		userName += prefix;
		return userName;
	}
	
	/**
	 * 生成流水
	 * 
	 * @param sellId
	 * 			售卖方式的ID
	 * @param date
	 * 			当前时间
	 * @param cusType
	 * 			用户类型
	 * @param ctId
	 * 			订单ID
	 * @param cusId
	 * 			用户ID
	 */
	public void createCashRecord(int sellId, Date date, int cusType, int ctId,int cusId, String subjectId, double consumeamount,double consumediscountamount) {

		try {
            
    		// 添加到流水表中
    		CashRecord cashRecord = null;
    		Gmrecord gmercord = null;
    
    		Long cId = date.getTime();
    		sellWay = sellWayService.getSellWayById(sellId);
    		/*this.getQuerySellCouCondition().setSellId(sellId);
    		List<SellCou> sellCouList = this.getSellCouService().getSellCouList(
    				this.getQuerySellCouCondition());*/
    		//		
    		/*for (int i = 0; i < sellCouList.size(); i++) {
    			course = this.courseService.getCourseById(sellCouList.get(i)
    					.getCourseId());*/
    		/**
    		 * 将多条订单流水合并。以往我们在生成流水的时候，根据课程的不同的课程生成不同的流水。
    		 * 我们现在根据售卖方式的不同，生成不同的流水，即一个订单中有一个售卖方式即是一条流水。
    		 */
    		cashRecord = new CashRecord();
    		cashRecord.setPackId(sellId);
    		cashRecord.setStatus(1);
    		cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》");
    		cashRecord.setPrice(sellWay.getSellPrice());
    		cashRecord.setContractId(cusId + cId.toString());
    		cashRecord.setCreateTime(date);
    		cashRecord.setCusId(cusId);
    		cashRecord.setCourseId(0);//课程id
    		if (cusType == 1 || cusType == 3) {
    			cashRecord.setType(2);// 2代表后台赠送
    		} else {
    			//cashRecord.setType(3);// 1前台购买 2 后台赠送 3 后台修复
    			cashRecord.setType(1);
    		}
    		cashRecord.setCtId(ctId);// 把订单的id存到流水中
    		
    		cashRecord.setCouponMoney(consumeamount);
    		cashRecord.setShopStatus(1); // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
    		cashRecord.setShopPayType(5); //0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通
    		cashRecord.setShopPayTime(new Date());
    		cashRecord.setCashRecordPrice(consumediscountamount);
    		cashRecord.setShopType(1);//1，课程 2，书籍
    		cashRecord.setReliefPrice(0);
    		//TODO  设置专业id(项目id)
    		cashRecord.setCrSubjectId(Integer.parseInt(subjectId));
    		this.cashRecordService.addCashRecord(cashRecord);
    
    		// 把每一条添加到购买记录表中
    		gmercord = new Gmrecord();
    		gmercord.setUserId(cusId);
    		gmercord.setCourseId(sellWay.getSellId());
    		gmercord.setAddTime(date);
    		this.gmrecordService.addGmrecord(gmercord);
		} catch (Exception e) {
           e.printStackTrace();
        }
		//}
	}
	
	/**
	 * 生成帐号（系统生成）
	 * 
	 * @param agentName
	 * 			代理商名称
	 * @param createSum
	 * 			要创建的帐号数量
	 * @param subjectId
	 * 			专业名称
	 */
	public String createCustomerByAgent(String agentName,int createSum,String subjectId){
		String password = "";
		String customerListString = "";
		cusList = new ArrayList<Customer>();
		for(int i=0;i<createSum;i++){
			String userName = this.createCustomerName(agentName);
			while(this.getCustomerService().getCustomerByEmail(userName) != null){
				userName = this.createCustomerName(agentName);
			}
			//Customer customerFlag = this.getCustomerService().getCustomerByEmail(userName);
			//Customer customerFlag = cccService.getCustomerByEmail("highsocn@test.com");
			password = this.getRandomPassword();
			customerListString += i+1+","+userName+","+password+";";
			password = MD5.getMD5(password);
			Customer newCustomer = new Customer();
			//用户昵称
			newCustomer.setCusName(userName.split("@")[0]);
			newCustomer.setEmail(userName);
			newCustomer.setCusPwd(password);
			newCustomer.setCusType(0);//用户类型：0、注册用户  1、内部用户	2、临时用户	3、体验10天	4、体验30天
			newCustomer.setRealName(userName);
			newCustomer.setLoginTimes(1);
			newCustomer.setSex("1");
			newCustomer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
			newCustomer.setSubjectId(Integer.parseInt(subjectId));
			newCustomer.setFromType("6");//代理商生成帐号 注册位置为6
			int cusId = customerService.addCustomer(newCustomer);
			addTsScore(cusId);//给用户添加积分
			cusList.add(newCustomer);
	    }
		return customerListString;
	}
	/**
	 * 自定义生成账号
	 * @param createInfo
	 * @param subjectId
	 * @return
	 */
	public String createCoustomeByCoustom(String createInfo,String subjectId){
		String[] customerInfo= createInfo.split(";");
		String customerListString = "";
		cusList = new ArrayList<Customer>();
		List<String> haveUser = isCompareName(customerInfo);
		if(haveUser.size() != 0){
			for (String string : haveUser) {
				customerListString += string+";";
			}
			return customerListString;//有重复的email地址
		}else{
			for(int i=0;i<customerInfo.length;i++){
				String userName =  customerInfo[i].split(",")[0];
				String password =  MD5.getMD5(customerInfo[i].split(",")[1]);
				customerListString += i+1+","+userName+","+customerInfo[i].split(",")[1]+";";
				Customer newCustomer = new Customer();
				newCustomer.setEmail(userName);
				newCustomer.setCusPwd(password);
				newCustomer.setCusName(customerInfo[i].split(",")[2]);
				newCustomer.setCusType(0);//用户类型：0、注册用户  1、内部用户	2、临时用户	3、体验10天	4、体验30天
				newCustomer.setRealName(customerInfo[i].split(",")[2]);
				newCustomer.setLoginTimes(1);
				newCustomer.setSex("1");
				newCustomer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
				newCustomer.setSubjectId(Integer.parseInt(subjectId));
				newCustomer.setFromType("6");
				int cusId = customerService.addCustomer(newCustomer);
				addTsScore(cusId);//给用户添加积分
				
				Address address = new Address();
				address.setCusId(cusId);
				address.setMobile(customerInfo[i].split(",")[1]);
				address.setIsFirst(0);//是否是默认发送地址
				address.setReceiver(userName);
				address.setTownId(0);
				address.setSendTime(1);//1,不限时间发送 2，限周一之周五 3，周六日
				address.setCreateTime(new Date());
				address.setAddress(customerInfo[i].split(",")[3]);
				address.setCityId(Integer.parseInt(customerInfo[i].split(",")[5]));
				address.setPostCode(customerInfo[i].split(",")[6]);
				address.setProvinceId(Integer.parseInt(customerInfo[i].split(",")[4]));
				
				int id = addressService.addAddress(address);
				addressIdList.add(id);
				cusList.add(newCustomer);
			}
			return customerListString; 
		}
		
	}
	
	/**
	 * 查看是否有重复用户名称
	 * @param userInfo
	 * @return
	 */
	private List<String> isCompareName(String[] userInfo){
		List<String> haveUserName = new ArrayList<String>();
		if(userInfo != null){
			for(int i=0;i<userInfo.length;i++){
				String userName =  userInfo[i].split(",")[0];
				if(this.getCustomerService().getCustomerByEmail(userName) == null){
				}else{
					haveUserName.add(userName);
				}
			}
		}
		return haveUserName;
		
	}
	
	/**
	 * 获取随机密码
	 * @return
	 */
	public String getRandomPassword(){
		Random random = new Random();
		String tempPwd = "";
		for(int x=0;x<6;x++){
			tempPwd += random.nextInt(10);
		}
		return tempPwd;
	}
	
	/**
	 * 为注册成功的用户赠送积分
	 * 
	 * @throws IOException
	 * @throws IOException
	 */
	private void addTsScore(int cusId) {
		Date date = new Date();
		// 积分表进行记录
		TotolsScore totolsScore = new TotolsScore();
		totolsScore.setCusId(cusId);
		// 兑换积分 +50
		int tsA = totolsScore.getTsCurrent();
		tsA = tsA + 55;
		totolsScore.setTsCurrent(tsA);
		// 成长积分（经验值） +50
		int tsC = totolsScore.getTsAction();
		tsC = tsC + 55;
		totolsScore.setTsAction(tsC);
		int tsId = this.totolsScoreService.addTotolsScore(totolsScore);

		// 积分记录表进行记录

		// 兑换积分记录
		TsRecord tsRecord = new TsRecord();
		tsRecord.setCusId(cusId);
		tsRecord.setTrType(TsRecord.TRTYPE_FOR);
		tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_REGISTE);
		tsRecord.setAccessTime(date);

		tsRecord.setTsId(tsId);
		tsRecord.setTrNum(55);
		this.tsRecordService.addTsRecord(tsRecord);

		// 成长积分兑换记录
		TsRecord tsR = new TsRecord();
		tsR.setCusId(cusId);
		tsR.setTrType(TsRecord.TRTYPE_ACTION);
		tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_REG);
		tsR.setAccessTime(date);

		tsR.setTsId(tsId);
		tsR.setTrNum(55);
		this.tsRecordService.addTsRecord(tsR);

	}



	public List<Integer> getAddressIdList() {
		return addressIdList;
	}

	public void setAddressIdList(List<Integer> addressIdList) {
		this.addressIdList = addressIdList;
	}

	public IAddress getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}
}
