package com.shangde.edu.cus.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.crm.condition.QuerySellrecordCondition;
import com.shangde.edu.crm.domain.Sellrecord;
import com.shangde.edu.crm.dto.SimpleStatDTO;
import com.shangde.edu.crm.service.ISellrecord;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.sys.condition.QueryGroupCondition;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGroup;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;

public class CustomerOrderAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(CustomerOrderAction.class);

	private static final long serialVersionUID = 1L;
	
	private List<Subject> subjectList;

	/**
	 *专业服务接口 
	 */
	private ISubject subjectService;
	
	private QueryCustomerCondition queryCustomerCondition;
	
	private int hour;
	private String dates;
	private List simStats=new ArrayList();
	private int scene;
	
	/**
	 * 注册学员
	 */
	private Customer customer = new Customer();
	private String startTime;
	private String endTime;
	
	private String sellRecordBox;
	
	private QueryGroupCondition queryGroupCondition;
	private IGroup groupService;
	private List<Group> groupList=new ArrayList<Group>();
	private int groupIds;
	private int status;
	
	
	private String monthDay;

	private String dateDay;
	private String startHH = " 00:00:00";
	private String endHH = " 23:59:59";

	private ICustomer customerService;
	
	private List<CustomerWithConSizeDTO> cwcsList = new ArrayList<CustomerWithConSizeDTO>();
	private InputStream excelFile;
	
	private String mobile;
	private Sellrecord sellrecord;
	private ISellrecord sellrecordService;
	private int crmId;
	private int cusId;
	private int roleId;
	private IUser userService;
	
	private QuerySellrecordCondition querySellrecordCondition;
	
	private QueryUserCondition queryUserCondition;
	
	private List <User> userList=new ArrayList<User>();
	private List <SimpleStatDTO> simpleStatList=new ArrayList<SimpleStatDTO>();
	private SimpleStatDTO simStat;
	private int sysUserId;
	
	private List<String> startTimeList=new ArrayList<String> ();
	private List<String> endTimeList=new ArrayList<String> ();

	public String toAddDes(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=sellrecordService.getSoldUserList(this.getQueryUserCondition());
			cusId=Integer.parseInt(servletRequest.getParameter("cusId"));
			mobile=customerService.getCustomerById(cusId).getMobile();
			return "toAddDesignate";
		} catch (Exception e) {
			logger.error("CustomerOrderAction.toAddDes",e);
			return ERROR;
		}
	}
	
	public String toUpdateDes(){
		try {
			
			groupList=groupService.getChildGroupById("40");
			userList=sellrecordService.getSoldUserList(this.getQueryUserCondition());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			roleId=users.getRoleList().get(0).getRoleId();
			sellrecord=sellrecordService.getSellrecordByCrmId(crmId);
			User user=userService.getUserById(sellrecord.getSysUserId());
			groupIds=user.getGroupId();
			return "toUpdateDesignate";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("CustomerOrderAction.toUpdateDes",e);
			return ERROR;
		}
	}

	public String addDes(){
		try {
			if(sellrecord!=null){
				Sellrecord s=sellrecordService.getSellrecordByCUSId(sellrecord.getCusId());
				if(s==null){
					sellrecord.setSysUserName(userService.getUserById(sellrecord.getSysUserId()).getUserName());
					sellrecord.setSellName(userService.getUserById(sellrecord.getSysUserId()).getLoginName());	
					sellrecord.setReasonTime(new Date());
					sellrecord.setSellTime(new Date());
					sellrecordService.addSellrecord(sellrecord);
					return "toOrderList";
				}else{
					return "designateError";
				}				
			}else{
				return ERROR;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("CustomerOrderAction.addDes",e);
			return ERROR;
		}
	}
	
	public String updateDes(){
		try {
			if(sellrecord!=null){
				Sellrecord sr=sellrecordService.getSellrecordByCrmId(sellrecord.getCrmId());
				sr.setSysUserId(sellrecord.getSysUserId());
				sr.setSysUserName(userService.getUserById(sellrecord.getSysUserId()).getUserName());
				sr.setSellName(userService.getUserById(sellrecord.getSysUserId()).getLoginName());
				sr.setCommuStatus(sellrecord.getCommuStatus());
				sr.setReason(sellrecord.getReason());
				sr.setScene(sellrecord.getScene());
				sr.setReasonTime(new Date());
				sr.setAge(sellrecord.getAge());
				sr.setConsultType(sellrecord.getConsultType());
				sr.setFProblem(sellrecord.getFProblem());
				sr.setFWorry(sellrecord.getFProblem());
				sr.setMobile(sellrecord.getMobile());
				sr.setProfession(sellrecord.getProfession());
				sr.setRealName(sellrecord.getRealName());
				sr.setSex(sellrecord.getSex());
				sr.setThProblem(sellrecord.getThProblem());
				sr.setThWorry(sellrecord.getThWorry());
				sr.setTwProblem(sellrecord.getTwProblem());
				sr.setTwWorry(sellrecord.getTwWorry());
				sr.setStudyReason(sellrecord.getStudyReason());
				sellrecordService.updateSellrecord(sr);
				return "toOrderList";				
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("CustomerOrderAction.addDes",e);
			return ERROR;
		}
	}
	
	public String sellrecordBatchAdd(){
		try {
			ArrayList <String> cusIdList=new ArrayList<String>();
			String [] cusList =sellRecordBox.split(",");
			for(int i=0;i<cusList.length;i++){
				cusIdList.add(cusList[i]);
			}
			
			int count=sellrecordService.getSellrecordByCUSIdList(cusIdList);
			
			if(count==0){
				for(int i=0;i<cusIdList.size();i++){
				Sellrecord sellr=new Sellrecord();
				sellr.setCusId(Integer.valueOf(cusIdList.get(i)));
				mobile=customerService.getCustomerById(sellr.getCusId()).getMobile();
				if(mobile==null){
					sellr.setMobile("0");
				}else{
				sellr.setMobile(mobile);
				}
				sellr.setSysUserId(sysUserId);
				sellr.setSysUserName(userService.getUserById(sysUserId).getUserName());
				sellr.setSellName(userService.getUserById(sysUserId).getLoginName());
				sellr.setScene(scene);
				sellr.setSellTime(new Date());
				sellr.setSex("0");
				sellr.setReasonTime(new Date());
				sellrecordService.addSellrecord(sellr);
				}
				this.setResult(new Result(true,null,null,null));
			}else{
				this.setResult(new Result(false,"error",null,null));
				
			}

			return "json";
		} catch (NumberFormatException e) {
			logger.error("CustomerOrderAction.addDes",e);
			this.setResult(new Result(false,"error",null,null));
			return "json";
		}
	}

	public String showCustomerListByBuyTime() {
		try {
			userList=sellrecordService.getSoldUserList(this.getQueryUserCondition());
			groupList=groupService.getChildGroupById("40");
			if(this.getQueryCustomerCondition().getShowSelf()==1){
				this.getQuerySellrecordCondition().setShowSelf(1);
			}
			this.getQuerySellrecordCondition().setPageSize(150);
			//获取角色ID
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			roleId=users.getRoleList().get(0).getRoleId();
			this.getQuerySellrecordCondition().setSysUserId(users.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQuerySellrecordCondition().setStartTime(sdf.format(new Date())+" 00:00:00");
			this.getQuerySellrecordCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			hour=new Date().getHours();
			dates=sdf.format(new Date()).substring(0, 10);
			simpleStatList=sellrecordService.getSimpleStatList(this.getQuerySellrecordCondition());
			
			this.getQueryCustomerCondition().setSysUserId(users.getUserId());
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();
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
				getQueryCustomerCondition().setCusType(cusType.trim());
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
			logger.error("CustomerOrderAction.addDes",e);
			return ERROR;
		}
		return "orderList";
	}

	public String simpleStat(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=sellrecordService.getSoldUserList(this.getQueryUserCondition());
		
			int a=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List testSimStats=new ArrayList();
			if(this.getQuerySellrecordCondition().getStartTime()==null||this.getQuerySellrecordCondition().getEndTime()==null||this.getQuerySellrecordCondition().getStartTime()==""||this.getQuerySellrecordCondition().getEndTime()==""){
				
				this.getQuerySellrecordCondition().setGroupId(groupIds);
				this.getQuerySellrecordCondition().setStartTime(sdf.format(new Date())+" 00:00:00");
				this.getQuerySellrecordCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
				a=1;
				List<SimpleStatDTO> simStatList=new ArrayList<SimpleStatDTO>();
				
				simStatList=sellrecordService.getSimpleStatObj(this.getQuerySellrecordCondition());
				if(simStatList!=null&&simStatList.size()!=0){
				int id=simStatList.get(0).getSysUserId();
				scene=simStatList.get(0).getScene();
				List <SimpleStatDTO>simstatlist=new ArrayList<SimpleStatDTO>();
				for(int i=0;i<simStatList.size();i++){
						if(simStatList.get(i).getSysUserId()==id){
							if(simStatList.get(i).getScene()==scene){
							simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<16;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k+8==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
											
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SimpleStatDTO>();
								scene=simStatList.get(i).getScene();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<16;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k+8==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SimpleStatDTO>();
							id=simStatList.get(i).getSysUserId();
							scene=simStatList.get(i).getScene();
							simstatlist.add(simStatList.get(i)); 
						}
						if(i==simStatList.size()-1){
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<16;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k+8==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									}
								}
							}
							testSimStats.add(s);
						}
					
					}
				}
			}else{
				a=0;
			}
			
			this.getQuerySellrecordCondition().setPageSize(200);
			this.setPage(sellrecordService.getSimpleStat(this.getQuerySellrecordCondition()));
			this.getPage().setPageSize(200);
			this.setPageUrlParms();
			if(testSimStats!=null&&testSimStats.size()!=0){
				int size=this.getPage().getPageSize();
				int currentPage=this.getQuerySellrecordCondition().getCurrentPage();
				int length=testSimStats.size();
				for(int i=size*(currentPage-1);i<length&&i<size*currentPage;i++){
					simStats.add(testSimStats.get(i));
				}
			}
			if(a==0){
				for(int i=0;i<this.getPage().getPageResult().size();i++){
					String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
					simStats.add(s);
				}
			}
			
			return "simpleStat";
		} catch (Exception e) {
			logger.error("CustomerOrderAction.simpleStat",e);
			return ERROR;
		}
	}

	public String getGroupUserInfo(){
		try {
			userList=sellrecordService.getGroupUserInfo(groupIds);
			StringBuffer s=new StringBuffer();
			if(status==0){
				s.append("<select name='querySellrecordCondition.sysUserId'><br><option value=0>请选择指派人</option><br>");
			}if(status==1){
				s.append("<select name='sellrecord.sysUserId' id='sysUserId'><br><option value=0>请选择指派人</option><br>");
			}
			for(int i=0;i<userList.size();i++){
				s.append("<option value="+userList.get(i).getUserId()+">"+userList.get(i).getUserName()+"</option><br>");
			}
			s.append("</select>");
			this.setResult(new Result(true,s.toString(),null,null));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("CustomerOrderAction.getGroupUserInfo",e);
			return ERROR;
		}
	}
	

	/**
	 * 导出excel
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFile() {
		try {
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();
			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.org.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("4")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.com.cn");
				} else if (customer.getCusFromUrl().equals("5")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.net.cn");
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
				getQueryCustomerCondition().setCusType(cusType.trim());
			}

			this.getQueryCustomerCondition().setPageSize(5000);
			cwcsList = customerService.getCustomerListByCondition(
					getQueryCustomerCondition()).getPageResult();
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
			createExcelFile();
			return "exportExcelCus";
		} catch (RuntimeException e) {
			logger.error("CustomerOrderAction.exportExcelFile",e);
			return ERROR;
		}
	}

	/**
	 * 创建excel
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public void createExcelFile() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String[] headName = { "昵称","电子邮箱", "注册项目", "注册域名", "登录地区", "手机", "登陆次数",
					"注册时间", "支付数/订单数" };
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			for (int i = 0; i < cwcsList.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getEmail());
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (null != cwcsList.get(i).getCusSubject()) {
					cell.setCellValue(cwcsList.get(i).getCusSubject()
							.getSubjectName());
				} else {
					cell.setCellValue("--");
				}
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusFromUrl());
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (cwcsList.get(i).getLoginRecordList().size() > 0) {
					cell.setCellValue(cwcsList.get(i).getLoginRecordList().get(
							0).getAddress());
				} else {
					cell.setCellValue("--");
				}
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getMobile());
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getLoginTimes());
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(cwcsList.get(i).getRegTime()));
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getContractStatus() + "|"
						+ cwcsList.get(i).getContractCount());
				if(cwcsList.get(i).getSellrecord()==null){
					cell = row.createCell((short) 8);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue("--");
					cell = row.createCell((short) 9);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue("--");
					cell = row.createCell((short) 10);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue("--");
					cell = row.createCell((short) 11);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue("--");
				}else{
				cell = row.createCell((short) 8);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getSellrecord().getSysUserName());
				cell = row.createCell((short) 9);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(cwcsList.get(i).getSellrecord().getSellTime()));
				cell = row.createCell((short) 10);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getSellrecord().getReason());
				cell = row.createCell((short) 11);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(cwcsList.get(i).getSellrecord().getReasonTime()));
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			logger.error("CustomerOrderAction.createExcelFile",e);
		}
	}
	/**
	 *
	 */
	public String simStatExportExcelFile(){
		try {
			this.getQuerySellrecordCondition().setGroupId(groupIds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQuerySellrecordCondition().setStartTime(sdf.format(new Date())+" 00:00:00");
			this.getQuerySellrecordCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			List<SimpleStatDTO> simStatList=new ArrayList<SimpleStatDTO>();
			simStatList=sellrecordService.getSimpleStatObj(this.getQuerySellrecordCondition());
			if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getSysUserId();
			scene=simStatList.get(0).getScene();
			List <SimpleStatDTO>simstatlist=new ArrayList<SimpleStatDTO>();
			
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getSysUserId()==id){
						if(simStatList.get(i).getScene()==scene){
						simstatlist.add(simStatList.get(i));
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<16;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k+8==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							simStats.add(s);
							simstatlist=new ArrayList<SimpleStatDTO>();
							scene=simStatList.get(i).getScene();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<16;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k+8==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						simStats.add(s);
						simstatlist=new ArrayList<SimpleStatDTO>();
						id=simStatList.get(i).getSysUserId();
						scene=simStatList.get(i).getScene();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<16;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k+8==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						simStats.add(s);
					}
				
				}
			}
			simpleStatList=sellrecordService.getSimpleStatList(this.getQuerySellrecordCondition());
			createSimStatExcelFile();
			return "simStatExportExcelFile";
		} catch (RuntimeException e) {
			logger.error("CustomerOrderAction.simStatExportExcelFile",e);
			return ERROR;
		}
	}

	public void createSimStatExcelFile() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String[] headName = { "项目","销售坐席","场景","订单流水","货到付款激活量","货到付款激活金额","分配量","拨打量","接通量","成交量","成交总量","取消量","转化率","08:00","09:00","10:00","11:00","12:00","13:00",
					"14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 鍒涘缓琛ㄥご
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			if(simpleStatList!=null&&simStats!=null){
				for (int i = 0; i < simStats.size(); i++) {
					row = sheet.createRow(i + 1);
					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getGroupName());
					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						cell.setCellValue(simpleStatList.get(i).getSysUserName());
					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(simpleStatList.get(i).getScene()==0){
					cell.setCellValue("--");
					}else if(simpleStatList.get(i).getScene()==1){
					cell.setCellValue("在线");
					}else if(simpleStatList.get(i).getScene()==2){
					cell.setCellValue("订单");
					}else if(simpleStatList.get(i).getScene()==3){
					cell.setCellValue("注册");
					}
					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					String s=simpleStatList.get(i).getIntBankTotelPrice()+simpleStatList.get(i).getSendTotelPrice()+"";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getSendSuccessCount());
					cell = row.createCell((short) 5);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					s=simpleStatList.get(i).getSendTotelPrice()+"";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					cell = row.createCell((short) 6);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getSellCount());
					cell = row.createCell((short) 7);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getCallCount());
					cell = row.createCell((short) 8);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getSuccessCount());
					cell = row.createCell((short) 9);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getPayCount()+"+"+simpleStatList.get(i).getSendCount());
					cell = row.createCell((short) 10);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getPayCount()+simpleStatList.get(i).getSendCount());
					cell = row.createCell((short) 11);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(simpleStatList.get(i).getBackCancelCount()+simpleStatList.get(i).getCancelCount()+"|"+simpleStatList.get(i).getCancelCount()+"+"+simpleStatList.get(i).getBackCancelCount());
					
					cell = row.createCell((short) 12);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					s=(simpleStatList.get(i).getPayCount()+simpleStatList.get(i).getSendCount())*100.0/simpleStatList.get(i).getSellCount()+"%";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					String[][] ss=(String[][])simStats.get(i);
					//08:00-23:00
					for(int j=0;j<16;j++){
						int k=13+j;
						cell = row.createCell((short) k);
						cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						cell.setCellValue(ss[j][0]+ss[j][1]);
					}
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			logger.error("CustomerOrderAction.createSimStatExcelFile",e);
		}
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



	public QueryCustomerCondition getQueryCustomerCondition() {
		if(queryCustomerCondition==null){
			queryCustomerCondition=new QueryCustomerCondition();
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



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getMonthDay() {
		return monthDay;
	}



	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}



	public String getDateDay() {
		return dateDay;
	}



	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}



	public String getEndHH() {
		return endHH;
	}



	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}



	public ICustomer getCustomerService() {
		return customerService;
	}



	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	
	public Sellrecord getSellrecord() {
		return sellrecord;
	}

	public void setSellrecord(Sellrecord sellrecord) {
		this.sellrecord = sellrecord;
	}

	public ISellrecord getSellrecordService() {
		return sellrecordService;
	}

	public void setSellrecordService(ISellrecord sellrecordService) {
		this.sellrecordService = sellrecordService;
	}

	public int getCrmId() {
		return crmId;
	}

	public void setCrmId(int crmId) {
		this.crmId = crmId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public QueryUserCondition getQueryUserCondition() {
		if(queryUserCondition==null){
			queryUserCondition=new QueryUserCondition();
		}
		return queryUserCondition;
	}

	public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
		this.queryUserCondition = queryUserCondition;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public QuerySellrecordCondition getQuerySellrecordCondition() {
		if(querySellrecordCondition==null){
			querySellrecordCondition=new QuerySellrecordCondition();
		}
		return querySellrecordCondition;
	}

	public void setQuerySellrecordCondition(
			QuerySellrecordCondition querySellrecordCondition) {
		this.querySellrecordCondition = querySellrecordCondition;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	public List<SimpleStatDTO> getSimpleStatList() {
		return simpleStatList;
	}

	public void setSimpleStatList(List<SimpleStatDTO> simpleStatList) {
		this.simpleStatList = simpleStatList;
	}

	public String getSellRecordBox() {
		return sellRecordBox;
	}

	public void setSellRecordBox(String sellRecordBox) {
		this.sellRecordBox = sellRecordBox;
	}

	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public List<String> gendTimeListst() {
		return startTimeList;
	}

	public void setStartTimeList(List<String> startTimeList) {
		this.startTimeList = startTimeList;
	}

	public List<String> getEndTimeList() {
		return endTimeList;
	}

	public void setEndTimeList(List<String> endTimeList) {
		this.endTimeList = endTimeList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public SimpleStatDTO getSimStat() {
		return simStat;
	}
	public void setSimStat(SimpleStatDTO simStat) {
		this.simStat = simStat;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public IGroup getGroupService() {
		return groupService;
	}
	public void setGroupService(IGroup groupService) {
		this.groupService = groupService;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public QueryGroupCondition getQueryGroupCondition() {
		if(queryGroupCondition==null){
			queryGroupCondition=new QueryGroupCondition();
		}
		return queryGroupCondition;
	}
	public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
		this.queryGroupCondition = queryGroupCondition;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(int groupIds) {
		this.groupIds = groupIds;
	}
	public int getScene() {
		return scene;
	}
	public void setScene(int scene) {
		this.scene = scene;
	}
	public List getSimStats() {
		return simStats;
	}
	public void setSimStats(List simStats) {
		this.simStats = simStats;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
